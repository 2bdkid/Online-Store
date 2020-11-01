package client.admin;

import common.AdminController;
import common.AdminDispatcher;

import java.rmi.Naming;
import java.util.Scanner;

public class RegisterFromExistingAccount {

    /**
     * Program to register new administrator account from existing administrator account
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AdminController controller;

        try {
            controller = (AdminController) Naming.lookup("//localhost/admincontroller");
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not connect to admin controller");
            return;
        }

        Scanner stdin = new Scanner(System.in);

        System.out.println("Login to existing administrator account");
        System.out.print("Username: ");
        String existingUsername = stdin.next();

        System.out.print("Password: ");
        String existingPassword = stdin.next();

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

        System.out.println("Creating new administrator account");
        System.out.print("New username: ");
        String newUsername = stdin.next();

        System.out.print("New password: ");
        String newPassword = stdin.next();

        try {
            dispatcher.addAdmin(newUsername, newPassword);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Could not create new administrator account");
            return;
        }

        System.out.printf("New administrator account %s created", newUsername);
    }
}
