package server;

import common.UsernameDoesNotExist;
import common.UsernameExists;

import java.util.HashMap;
import java.util.Map;

public class AdminRegistrar {

    private final Map<String, String> entries;
    private int nextToken;

    /**
     * Handles administrator account registration
     */
    public AdminRegistrar() {
        entries = new HashMap<>();
    }

    /**
     * create registration in registrar
     * @param username account username
     * @param password account password
     * @throws UsernameExists username already used
     */
    public void createRegistration(String username, String password) throws UsernameExists {
        // check if username already exists
        if (entries.containsKey(username)) {
            throw new UsernameExists(username);
        }

        entries.put(username, password);
    }

    /**
     *
     * @param username username to authenticate
     * @param password password to authenticate
     * @return true if username is authenticated
     */
    public Boolean authenticate(String username, String password) throws UsernameDoesNotExist {
        // find user entry or throw
        String entryPassword = entries.get(username);

        if (entryPassword == null) {
            throw new UsernameDoesNotExist(username);
        }

        return password.equals(entryPassword);
    }
}
