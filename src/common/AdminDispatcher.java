package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminDispatcher extends Remote {
    /**
     * Add administrator account
     * @param username new account username
     * @param password new account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already taken
     */
    void addAdmin(String username, String password) throws RemoteException, UsernameExists;

    /**
     * Add customer account
     * @param username new account username
     * @param password new account password
     * @throws RemoteException RMI error
     * @throws UsernameExists username already taken
     */
    void addCustomer(String username, String password) throws RemoteException, UsernameExists;

    /**
     * Remote customer account
     * @param customer account username
     * @throws RemoteException RMI error
     * @throws UsernameDoesNotExist account does not exist
     */
    void removeCustomer(String customer) throws RemoteException, UsernameDoesNotExist;

    /**
     * Update item description
     * @param item item name
     * @param description new item description
     * @throws RemoteException RMI error
     * @throws DatabaseError item does not exist
     */
    void updateItemDescription(String item, String description) throws RemoteException, DatabaseError;

    /**
     * Update item price
     * @param item item name
     * @param price new item price
     * @throws RemoteException RMI error
     * @throws DatabaseError item does not exist
     */
    void updateItemPrice(String item, Double price) throws RemoteException, DatabaseError;

    /**
     * Update item quantity
     * @param item item name
     * @param quantity new quantity
     * @throws RemoteException RMI error
     * @throws DatabaseError item does not exist
     */
    void updateItemQuantity(String item, Integer quantity) throws RemoteException, DatabaseError;

    /**
     * Add item
     * @param name item name
     * @param description item description
     * @param type item type
     * @param price item price
     * @throws RemoteException RMI error
     * @throws DatabaseError item already exists
     */
    void addItem(String name, String description, String type, Double price) throws RemoteException, DatabaseError;

    /**
     * Remove item
     * @param name item name
     * @throws RemoteException RMI error
     * @throws DatabaseError item does not exist
     */
    void removeItem(String name) throws RemoteException, DatabaseError;
}
