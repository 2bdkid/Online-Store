package client.customer;

import common.CustomerController;

import java.rmi.Naming;
import java.util.Scanner;

public class RegisterAccount {
    /**
     * Program to register customer account with store server
     * @param args command line arguments
     */
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

        System.out.println("Creating new customer account");
        System.out.print("Username: ");
        String username = stdin.nextLine();

        System.out.print("Password: ");
        String password = stdin.nextLine();

        try {
            controller.register(username, password);
        } catch (Exception e) {
            System.err.printf("Exception: %s%n", e.getMessage());
            System.err.println("Account was not registered");
            return;
        }

        System.out.printf("Account %s created%n", username);
    }
}
