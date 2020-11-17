package server;

import common.*;
import database.ItemDatabase;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CustomerControllerImpl implements CustomerController {
    private final Registrar customerRegistrar;
    private final Map<String, Integer> tokens;
    private final ItemDatabase database;
    private final Random randomizer;

    /**
     * Implementation of CustomerController
     * @param customerRegistrar customer registrar, expects RMI stub
     */
    public CustomerControllerImpl(Registrar customerRegistrar, ItemDatabase database) {
        this.customerRegistrar = customerRegistrar;
        this.database = database;
        tokens = new HashMap<>();
        randomizer = new Random();
    }

    /**
     * Register customer account
     *
     * @param username account username
     * @param password account password
     * @throws RemoteException RMI error
     * @throws UsernameExists  username already taken
     */
    public void register(String username, String password) throws RemoteException, UsernameExists {
        customerRegistrar.register(username, password);
    }

    /**
     * Authenticate customer account
     *
     * @param username account username
     * @param password account password
     * @return token
     * @throws RemoteException      RMI error
     * @throws UsernameDoesNotExist account does not exist
     * @throws InvalidPassword      password does not authenticate account
     */
    public Integer authenticate(String username, String password) throws RemoteException, UsernameDoesNotExist, InvalidPassword {
        if (!customerRegistrar.authenticate(username, password)) {
            throw new InvalidPassword(username);
        }

        Integer token = randomizer.nextInt();
        tokens.put(username, token);
        return token;
    }

    /**
     * Get customer command dispatcher
     *
     * @param username account username
     * @param token    authentication token
     * @return command dispatcher
     * @throws RemoteException      RMI error
     * @throws InvalidToken         token does not belong to user
     * @throws UsernameDoesNotExist account does not exist
     */
    public CustomerDispatcher getDispatcher(String username, Integer token) throws RemoteException, InvalidToken, UsernameDoesNotExist {
        Integer userToken = tokens.get(username);

        // check if username exists
        if (userToken == null) {
            throw new UsernameDoesNotExist(username);
        }

        // token validation
        if (!userToken.equals(token)) {
            throw new InvalidToken(token);
        }

        return new CustomerDispatcherImpl(database);
    }
}
