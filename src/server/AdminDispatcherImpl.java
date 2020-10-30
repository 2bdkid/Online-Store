package server;

import common.*;
import database.DatabaseCommand;
import database.DatabaseCommandFactory;
import database.ItemDatabase;

import java.rmi.RemoteException;

public class AdminDispatcherImpl implements AdminDispatcher {
    private final AdminController adminController;
    private final ItemDatabase database;
    private final DatabaseCommandFactory commandFactory;

    /**
     * Implementation of AdminDispatcher
     * @param adminController admin controller to handle account registration
     */
    public AdminDispatcherImpl(AdminController adminController, ItemDatabase database) {
        this.adminController = adminController;
        this.database = database;
        commandFactory = new DatabaseCommandFactory();
    }

    /**
     * Add administrator account
     *
     * @param username new account username
     * @param password new account password
     * @throws RemoteException RMI error
     * @throws UsernameExists  username already taken
     */
    public void addAdmin(String username, String password) throws RemoteException, UsernameExists {
        adminController.register(username, password);
    }

    /**
     * Add customer account
     *
     * @param username new account username
     * @param password new account password
     * @throws RemoteException RMI error
     * @throws UsernameExists  username already taken
     */
    public void addCustomer(String username, String password) throws RemoteException, UsernameExists {

    }

    /**
     * Remote customer account
     *
     * @param customer account username
     * @throws RemoteException      RMI error
     * @throws UsernameDoesNotExist account does not exist
     */
    public void removeCustomer(String customer) throws RemoteException, UsernameDoesNotExist {

    }

    /**
     * Update item description
     *
     * @param item        item name
     * @param description new item description
     * @throws RemoteException  RMI error
     * @throws DatabaseError item does not exist
     */
    public void updateItemDescription(String item, String description) throws RemoteException, DatabaseError {
        DatabaseCommand command = commandFactory.getUpdateItemDescriptionCommand(item, description);
        database.acceptCommand(command);
    }

    /**
     * Update item price
     *
     * @param item  item name
     * @param price new item price
     * @throws RemoteException  RMI error
     * @throws DatabaseError item does not exist
     */
    public void updateItemPrice(String item, Double price) throws RemoteException, DatabaseError {
        DatabaseCommand command = commandFactory.getUpdateItemPriceCommand(item, price);
        database.acceptCommand(command);
    }

    /**
     * Update item quantity
     *
     * @param item     item name
     * @param quantity new quantity
     * @throws RemoteException  RMI error
     * @throws DatabaseError item does not exist
     */
    public void updateItemQuantity(String item, Integer quantity) throws RemoteException, DatabaseError {
        DatabaseCommand command = commandFactory.getUpdateItemQuantity(item, quantity);
        database.acceptCommand(command);
    }

    /**
     * Add item
     *
     * @param name        item name
     * @param description item description
     * @param type        item type
     * @param price       item price
     * @throws RemoteException RMI error
     * @throws DatabaseError item already exists
     */
    public void addItem(String name, String description, String type, Double price) throws RemoteException, DatabaseError {
        DatabaseCommand command = commandFactory.getAddItemCommand(name, description, type, price);
        database.acceptCommand(command);
    }

    /**
     * Remove item
     *
     * @param name item name
     * @throws RemoteException  RMI error
     * @throws DatabaseError item does not exist
     */
    public void removeItem(String name) throws RemoteException, DatabaseError {
        DatabaseCommand command = commandFactory.getRemoveItemCommand(name);
        database.acceptCommand(command);
    }
}
