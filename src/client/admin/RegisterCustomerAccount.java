package client.admin;

import common.AdminController;
import common.AdminDispatcher;

import java.rmi.Naming;
import java.util.Scanner;

public class RegisterCustomerAccount {
    /**
     * Program to register new customer account from existing administrator account
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

        System.out.println("Creating new customer account");
        System.out.print("New username: ");
        String newUsername = stdin.nextLine();

        System.out.print("New password: ");
        String newPassword = stdin.nextLine();

        try {
            dispatcher.addCustomer(newUsername, newPassword);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not create new customer account");
            return;
        }

        System.out.printf("New customer account %s created%n", newUsername);
    }
}
