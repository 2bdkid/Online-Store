package server;

import common.AdminController;
import common.AdminRegistrar;
import database.ItemDatabase;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class StoreServer {
    private final AdminController adminController;
    private final int rmiPort;
    private final AdminRegistrar adminRegistrar;

    public StoreServer(int rmiBindPort) {
        rmiPort = rmiBindPort;
        adminRegistrar = new AdminRegistrarImpl();
        adminController = new AdminControllerImpl(adminRegistrar);
        // TODO: export RMI objects
    }

    public static void main(String[] args) {
        int rmiPort = 33333;
        StoreServer server = new StoreServer(rmiPort);

        try {
            server.start();
        } catch (RemoteException e) {
            System.err.printf("Unable to bind to RMI port %s%n", rmiPort);
        } catch (Exception e) {
            System.err.println("Unable to bind admin controller");
        }
    }

    public void start() throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(rmiPort);
        Naming.bind("//localhost/admincontroller", adminController);
    }
}
