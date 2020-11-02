package database;

import common.DatabaseError;

import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class DatabaseCommand implements Serializable {
    /**
     * Execute command on database
     * @param database database to operate on
     */
    public abstract void execute(ItemDatabase database) throws DatabaseError, RemoteException;
}
