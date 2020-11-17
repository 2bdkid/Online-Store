package client.customer;

import common.CustomerController;
import common.CustomerDispatcher;
import database.Item;
import database.ItemUnavailable;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
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

        System.out.println();
        System.out.println("Available Items");

        for (int i = 0; i < items.size(); ++i) {
            Item item = items.get(i);
            System.out.printf("ID: %d%n", i);
            System.out.printf("Name: %s%n", item.getName());
            System.out.printf("Price: $%.2f%n", item.getPrice());
            System.out.printf("Type: %s%n", item.getType());
            System.out.printf("Description: %s%n", item.getDescription());
            System.out.printf("Available quantity: %s%n", item.getQuantity());
            System.out.println();
        }

        System.out.println("Add items to your cart by specifying the ID followed by the quantity requested");
        System.out.println("Eg: 0 2");
        System.out.println("Enter \"checkout\" when you are ready to checkout");

        stdin.useDelimiter("\n");
        String line = stdin.nextLine();

        List<Item> cart = new ArrayList<>();

        while (!line.equals("checkout")) {
            line = stdin.nextLine();
            Scanner linescanner = new Scanner(line);
            int id = linescanner.nextInt();
            int quantity = linescanner.nextInt();
            Item item = items.get(id);
            item.setQuantity(quantity);
            cart.add(item);
        }

        try {
            dispatcher.checkout(cart);
        } catch (ItemUnavailable e) {
            System.err.printf("Requested too much of item %s%n", e.getMessage());
            System.out.println("Cart was not checked out");
            return;
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            return;
        }

        System.out.println("Cart was successfully checked out");
    }
}
