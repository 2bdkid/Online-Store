package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminController extends Remote {
    /**
     * Register administrator account
     * @param username account username
     * @param password account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already taken
     */
    void register(String username, String password) throws RemoteException, UsernameExists;

    /**
     * Authenticate administrator account
     * @param username account username
     * @param password account password
     * @return token
     * @throws RemoteException RMI error
     * @throws UsernameDoesNotExist account does not exist
     * @throws InvalidPassword password does not authenticate account
     */
    Integer authenticate(String username, String password) throws RemoteException, UsernameDoesNotExist, InvalidPassword;

    /**
     * Get administrator command dispatcher
     * @param username account username
     * @param token authentication token
     * @return command dispatcher
     * @throws RemoteException RMI error
     * @throws InvalidToken token does not belong to user
     * @throws UsernameDoesNotExist account does not exist
     */
    AdminDispatcher getDispatcher(String username, Integer token) throws RemoteException, InvalidToken, UsernameDoesNotExist;
}
