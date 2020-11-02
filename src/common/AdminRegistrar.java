package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminRegistrar extends Remote {
    /**
     * create registration in registrar
     * @param username account username
     * @param password account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already used
     */
    void register(String username, String password) throws RemoteException, UsernameExists;

    /**
     *
     * @param username username to authenticate
     * @param password password to authenticate
     * @throws RemoteException RMI error
     * @throws UsernameDoesNotExist username does not exist
     * @return true if username is authenticated
     */
     Boolean authenticate(String username, String password) throws RemoteException, UsernameDoesNotExist;
}
