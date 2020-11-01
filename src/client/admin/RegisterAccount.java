package client.admin;

import common.AdminController;
import common.UsernameExists;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RegisterAccount {
    /**
     * Program to register administrator account with store server
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

        System.out.println("Creating new administrator account");
        System.out.print("Username: ");
        String username = stdin.next();

        System.out.print("Password: ");
        String password = stdin.next();

        try {
            controller.register(username, password);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Account was not registered");
            return;
        }

        System.out.printf("Account %s created", username);
    }
}
