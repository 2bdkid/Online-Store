package database;

import common.DatabaseError;

import java.io.Serializable;

public abstract class DatabaseCommand implements Serializable {
    /**
     * Execute command on database
     * @param database database to operate on
     */
    public abstract void execute(ItemDatabaseImpl database) throws DatabaseError;
}
