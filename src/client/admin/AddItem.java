package client.admin;

import common.AdminController;
import common.AdminDispatcher;

import java.rmi.Naming;
import java.util.Scanner;

public class AddItem {
    /**
     * Program to add new item
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AdminController controller;

        try {
            controller = (AdminController) Naming.lookup("//in-csci-rrpc01:54321/admincontroller");
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not connect to admin controller");
            return;
        }

        Scanner stdin = new Scanner(System.in);

        System.out.println("Login to existing administrator account");
        System.out.print("Username: ");
        String existingUsername = stdin.nextLine();

        System.out.print("Password: ");
        String existingPassword = stdin.nextLine();

        Integer token;

        try {
            token = controller.authenticate(existingUsername, existingPassword);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not authenticate");
            return;
        }

        AdminDispatcher dispatcher;

        try {
            dispatcher = controller.getDispatcher(existingUsername, token);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not get dispatcher");
            return;
        }

        System.out.println("Adding new item");
        System.out.print("New item name: ");
        String item = stdin.nextLine();

        System.out.print("New item description: ");
        String description = stdin.nextLine();

        System.out.print("New item type: ");
        String type = stdin.nextLine();

        Double price = null;
        while (price == null) {
            try {
                System.out.print("New price: ");
                price = Double.parseDouble(stdin.nextLine());
            } catch (Exception e) {
                System.out.println("Try that again");
            }
        }

        try {
            dispatcher.addItem(item, description, type, price);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("New item was not added");
            return;
        }

        System.out.printf("New item %s added%n", item);
    }
}
