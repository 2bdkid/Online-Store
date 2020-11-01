package common;

import database.DatabaseCommand;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ItemDatabase extends Remote {
    /**
     * Add item to database, zero initial quantity
     * @param name item name
     * @param description item description
     * @param type item type
     * @param price item price
     * @throws RemoteException RMI error
     * @throws ItemExists item name exists in database
     */
    void add(String name, String description, String type, Double price) throws RemoteException, ItemExists;

    /**
     * Remove item from database
     * @param name name of item to remove
     * @throws ItemDoesNotExist item does not exist
     * @throws RemoteException RMI error
     */
    void remove(String name) throws RemoteException, ItemDoesNotExist;

    /**
     * Update item description
     * @param name item to change
     * @param description new description
     * @throws ItemDoesNotExist item does not exist
     * @throws RemoteException RMI error
     */
    public void updateItemDescription(String name, String description) throws RemoteException, ItemDoesNotExist;

    /**
     * Change item price
     * @param name item to change
     * @param price new price
     * @throws ItemDoesNotExist item does not exist
     * @throws RemoteException RMI error
     */
    void updateItemPrice(String name, Double price) throws RemoteException, ItemDoesNotExist;

    /**
     * Change item quantity
     * @param name item to change
     * @param quantity new quantity
     * @throws ItemDoesNotExist item does not exist
     * @throws RemoteException RMI error
     */
    void updateItemQuantity(String name, Integer quantity) throws RemoteException, ItemDoesNotExist;

    /**
     * Execute DatabaseCommand on database
     * @param command command to execute
     * @throws DatabaseError database error
     * @throws RemoteException RMI error
     */
    void acceptCommand(DatabaseCommand command) throws RemoteException, DatabaseError;
}
