package common;

import database.Item;
import database.ItemUnavailable;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerDispatcher extends Remote {
    /**
     * Get list of available items
     * @return list of items
     * @throws RemoteException RMI error
     */
    List<Item> browseItems() throws RemoteException;

    /**
     * Checkout some items
     * @param items items to checkout
     * @throws RemoteException RMI error
     * @throws ItemUnavailable requested quantity is greater than quantity available
     */
    void checkout(List<Item> items) throws RemoteException, ItemUnavailable;
}
