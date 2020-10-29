package server;

import common.*;

import java.rmi.RemoteException;

public class AdminDispatcherImpl implements AdminDispatcher {
    private final AdminController adminController;

    /**
     * Implementation of AdminDispatcher
     * @param adminController admin controller to handle account registration
     */
    public AdminDispatcherImpl(AdminController adminController) {
        this.adminController = adminController;
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
     * @throws ItemDoesNotExist item does not exist
     */
    public void updateItemDescription(String item, String description) throws RemoteException, ItemDoesNotExist {

    }

    /**
     * Update item price
     *
     * @param item  item name
     * @param price new item price
     * @throws RemoteException  RMI error
     * @throws ItemDoesNotExist item does not exist
     */
    public void updateItemPrice(String item, Double price) throws RemoteException, ItemDoesNotExist {

    }

    /**
     * Update item quantity
     *
     * @param item     item name
     * @param quantity new quantity
     * @throws RemoteException  RMI error
     * @throws ItemDoesNotExist item does not exist
     */
    public void updateItemQuantity(String item, Integer quantity) throws RemoteException, ItemDoesNotExist {

    }

    /**
     * Add item
     *
     * @param name        item name
     * @param description item description
     * @param type        item type
     * @param price       item price
     * @throws RemoteException RMI error
     * @throws ItemExists      item already exists
     */
    public void addItem(String name, String description, String type, Double price) throws RemoteException, ItemExists {

    }

    /**
     * Remove item
     *
     * @param name item name
     * @throws RemoteException  RMI error
     * @throws ItemDoesNotExist item does not exist
     */
    public void removeItem(String name) throws RemoteException, ItemDoesNotExist {

    }
}
