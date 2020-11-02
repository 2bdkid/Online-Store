package client.admin;

import common.AdminController;
import common.AdminDispatcher;

import java.rmi.Naming;
import java.util.Scanner;

public class UpdateItemQuantity {
    /**
     * Program to change item quantity
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AdminController controller;

        try {
            controller = (AdminController) Naming.lookup("//localhost:54321/admincontroller");
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

        System.out.println("Updating item quantity");
        System.out.print("Item name: ");
        String item = stdin.nextLine();

        Integer quantity = null;
        while (quantity == null) {
            try {
                System.out.print("New quantity: ");
                quantity = Integer.parseInt(stdin.nextLine());
            } catch (Exception e) {
                System.out.println("Try that again");
            }
        }

        try {
            dispatcher.updateItemQuantity(item, quantity);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Quantity was not updated");
            return;
        }

        System.out.printf("Item %s quantity updated%n", item);
    }
}
