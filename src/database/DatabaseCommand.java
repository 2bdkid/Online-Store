package database;

import common.DatabaseError;

public abstract class DatabaseCommand {
    /**
     * Execute command on database
     * @param database database to operate on
     */
    public abstract void execute(ItemDatabase database) throws DatabaseError;
}
