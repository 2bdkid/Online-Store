package client.customer;

import common.AdminController;
import common.AdminDispatcher;
import common.CustomerController;
import common.CustomerDispatcher;
import database.Item;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class BrowseAndCheckout {
    public static void main(String[] args) {
        CustomerController controller;

        try {
            controller = (CustomerController) Naming.lookup("//in-csci-rrpc01:54321/customercontroller");
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not connect to customer controller");
            return;
        }

        Scanner stdin = new Scanner(System.in);

        System.out.println("Login to existing customer account");
        System.out.print("Username: ");
        String username = stdin.nextLine();

        System.out.print("Password: ");
        String password = stdin.nextLine();

        Integer token;

        try {
            token = controller.authenticate(username, password);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not authenticate");
            return;
        }

        CustomerDispatcher dispatcher;

        try {
            dispatcher = controller.getDispatcher(username, token);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not get dispatcher");
            return;
        }

        List<Item> items;

        try {
            items = dispatcher.browseItems();
        } catch (RemoteException e) {
            System.err.println("Couldn't get items from database");
            return;
        }

        System.out.println("Available Items");

        for (int i = 0; i < items.size(); ++i) {
            Item item = items.get(i);
            System.out.printf("ID: %d%n", i);
            System.out.printf("Name: %s%n", item.getName());
            System.out.printf("Price: $%f%n", item.getPrice());
            System.out.printf("Type: %s%n", item.getType());
            System.out.printf("Description: %s%n", item.getDescription());
            System.out.printf("Available quantity: %s%n", item.getQuantity());
            System.out.println();
        }


    }
}
