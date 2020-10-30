package server;

import common.AdminController;
import common.AdminRegistrar;
import database.ItemDatabase;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StoreServer {
    private final AdminController adminController;
    private final int rmiPort;
    private final AdminRegistrar adminRegistrar;
    private final ItemDatabase database;

    /**
     * Exports controller objects and registrars to RMI
     * @param rmiBindPort RMI port
     * @throws RemoteException RMI error
     */
    public StoreServer(int rmiBindPort) throws RemoteException {
        rmiPort = rmiBindPort;
        database = new ItemDatabase();
        // stubs
        adminRegistrar = (AdminRegistrar) UnicastRemoteObject.exportObject(new AdminRegistrarImpl(), rmiPort) ;
        adminController = (AdminController) UnicastRemoteObject.exportObject(new AdminControllerImpl(adminRegistrar, database), rmiPort);
    }

    public static void main(String[] args) {
        int rmiPort = 1099;
        StoreServer server;

        try {
            server = new StoreServer(rmiPort);
        } catch (RemoteException e) {
            e.printStackTrace(System.err);
            System.err.println("Could not export admin controller or admin registrar to RMI");
            return;
        }

        try {
            server.start();
        } catch (RemoteException e) {
            e.printStackTrace(System.err);
            System.err.printf("Unable to bind to RMI port %s%n", rmiPort);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.err.println("Unable to bind admin controller");
        }
    }

    /**
     * Export controllers to RMI
     * @throws Exception RMI error
     */
    public void start() throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(rmiPort);
        Naming.bind("//localhost/admincontroller", adminController);
    }
}
