package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerController extends Remote {
    /**
     * Register customer account
     * @param username account username
     * @param password account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already taken
     */
    void register(String username, String password) throws RemoteException, UsernameExists;

    /**
     * Authenticate customer account
     * @param username account username
     * @param password account password
     * @return token
     * @throws RemoteException RMI error
     * @throws UsernameDoesNotExist account does not exist
     * @throws InvalidPassword password does not authenticate account
     */
    Integer authenticate(String username, String password) throws RemoteException, UsernameDoesNotExist, InvalidPassword;

    /**
     * Get customer command dispatcher
     * @param username account username
     * @param token authentication token
     * @return command dispatcher
     * @throws RemoteException RMI error
     * @throws InvalidToken token does not belong to user
     * @throws UsernameDoesNotExist account does not exist
     */
    CustomerDispatcher getDispatcher(String username, Integer token) throws RemoteException, InvalidToken, UsernameDoesNotExist;
}
