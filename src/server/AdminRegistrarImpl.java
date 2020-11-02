package server;

import common.AdminRegistrar;
import common.UsernameDoesNotExist;
import common.UsernameExists;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminRegistrarImpl implements AdminRegistrar {
    private final Map<String, String> entries;

    /**
     * Handles administrator account registration
     * Reads existing accounts from adminaccounts.csv
     */
    public AdminRegistrarImpl() {
        entries = new HashMap<>();
        InputStream adminAccountStream = getClass().getResourceAsStream("adminaccounts.csv");

        if (adminAccountStream != null) {
            Scanner accounts = new Scanner(adminAccountStream);
            accounts.useDelimiter("\n");

            while (accounts.hasNext()) {
                String line = accounts.next();
                String[] data = line.split(",");
                entries.put(data[0], data[1]);
            }
        }
    }

    /**
     * create registration in registrar
     * @param username account username
     * @param password account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already used
     */
    public void register(String username, String password) throws RemoteException, UsernameExists {
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
     * @throws RemoteException RMI error
     * @throws UsernameDoesNotExist usename does not exist
     */
    public Boolean authenticate(String username, String password) throws RemoteException, UsernameDoesNotExist {
        // find user entry or throw
        String entryPassword = entries.get(username);

        if (entryPassword == null) {
            throw new UsernameDoesNotExist(username);
        }

        return password.equals(entryPassword);
    }
}
