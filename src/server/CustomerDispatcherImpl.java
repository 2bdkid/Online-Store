package server;

import common.CustomerDispatcher;
import common.ItemDoesNotExist;
import database.Item;
import database.ItemDatabase;
import database.ItemUnavailable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class CustomerDispatcherImpl implements CustomerDispatcher, Serializable {
    private final ItemDatabase database;

    public CustomerDispatcherImpl(ItemDatabase database) {
        this.database = database;
    }

    /**
     * Get list of available items
     * @return list of items
     * @throws RemoteException RMI error
     */
    public List<Item> browseItems() throws RemoteException {
        return database.getItems();
    }

    /**
     * Checkout some items
     * @param items items to checkout
     * @throws RemoteException RMI error
     * @throws ItemUnavailable requested quantity is greater than quantity available
     */
    public void checkout(List<Item> items) throws RemoteException, ItemUnavailable {
        List<Item> dbitems = database.getItems();

        // check availability
        for (Item item : items) {
            Item dbitem = dbitems.stream().filter(i -> i.getName().equals(item.getName())).findAny().get();
            if (item.getQuantity() > dbitem.getQuantity()) {
                throw new ItemUnavailable(item.getName());
            }
        }

        for (Item item : items) {
            Item dbitem = dbitems.stream().filter(i -> i.getName().equals(item.getName())).findAny().get();
            try {
                database.updateItemQuantity(item.getName(), dbitem.getQuantity() - item.getQuantity());
            } catch (ItemDoesNotExist e) {
                // ignore
            }
        }
    }
}
