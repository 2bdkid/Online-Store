package server;

import common.*;
import database.ItemDatabase;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AdminControllerImpl implements AdminController {
    private final Registrar adminRegistrar;
    private final Map<String, Integer> tokens;
    private final ItemDatabase database;
    private final Random randomizer;
    private final Registrar customerRegistrar;

    /**
     * Implementation of AdminController
     * @param adminRegistrar administrator registrar, expects RMI stub
     */
    public AdminControllerImpl(Registrar adminRegistrar, Registrar customerRegistrar, CustomerController customerController, ItemDatabase database) {
        this.adminRegistrar = adminRegistrar;
        this.customerRegistrar = customerRegistrar;
        this.database = database;
        tokens = new HashMap<>();
        randomizer = new Random();
    }

    /**
     * Register administrator account
     * @param username account username
     * @param password account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already used
     */
    public void register(String username, String password) throws RemoteException, UsernameExists {
        adminRegistrar.register(username, password);
    }

    /**
     * Authenticate with registrar
     * @param username username to authenticate
     * @param password password to authenticate
     * @return token
     * @throws RemoteException RMI error
     * @throws InvalidPassword password does not authenticate username
     * @throws UsernameDoesNotExist user does not exist in registrar
     */
    public Integer authenticate(String username, String password) throws RemoteException, InvalidPassword , UsernameDoesNotExist {
        if (!adminRegistrar.authenticate(username, password)) {
            throw new InvalidPassword(username);
        }

        Integer token = randomizer.nextInt();
        tokens.put(username, token);
        return token;
    }

    /**
     * Get command dispatcher of authenticated user
     * @param token authentication token
     * @return command dispatcher
     * @throws RemoteException RMI error
     * @throws InvalidToken invalid token given
     * @throws UsernameDoesNotExist username does not have token
     */
    public AdminDispatcher getDispatcher(String username, Integer token) throws RemoteException, InvalidToken , UsernameDoesNotExist {
        Integer userToken = tokens.get(username);

        // check if username exists
        if (userToken == null) {
            throw new UsernameDoesNotExist(username);
        }

        // token validation
        if (!userToken.equals(token)) {
            throw new InvalidToken(token);
        }

        return new AdminDispatcherImpl(this, customerRegistrar, database);
    }
}
