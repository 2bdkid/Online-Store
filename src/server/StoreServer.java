package server;

import common.AdminController;
import common.AdminRegistrar;
import database.ItemDatabase;
import database.ItemDatabaseImpl;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StoreServer {
    private final AdminController adminController;

    /**
     * Exports controllers, registrars, and database to RMI
     * @param rmiPort RMI port
     * @throws RemoteException RMI error
     */
    public StoreServer(int rmiPort) throws RemoteException {
        // stubs
        ItemDatabase database = (ItemDatabase) UnicastRemoteObject.exportObject(new ItemDatabaseImpl(), rmiPort);
        AdminRegistrar adminRegistrar = (AdminRegistrar) UnicastRemoteObject.exportObject(new AdminRegistrarImpl(), rmiPort) ;
        adminController = (AdminController) UnicastRemoteObject.exportObject(new AdminControllerImpl(adminRegistrar, database), rmiPort);
    }

    /**
     * Server backend program. Starts RMI registry and creates StoreServer object
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int rmiPort = 54321;

        try {
            java.rmi.registry.LocateRegistry.createRegistry(rmiPort);
        } catch (RemoteException e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not start registry");
            return;
        }

        StoreServer server;

        try {
            server = new StoreServer(rmiPort);
        } catch (RemoteException e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not export all objects to RMI");
            return;
        }

        try {
            server.start();
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Unable to bind admin controller");
            return;
        }
    }

    /**
     * Export controllers to RMI
     * @throws Exception RMI error
     */
    public void start() throws Exception {
        Naming.bind("//localhost:54321/admincontroller", adminController);
    }
}
