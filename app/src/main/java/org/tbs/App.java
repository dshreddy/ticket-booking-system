package org.tbs;
import org.tbs.entities.User;
import org.tbs.services.UserBookingService;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserBookingService userBookingService = new UserBookingService();

    public static void main(String[] args) {
        appConsole();
    }

    private static void appConsole() {
        User loggedInUser;
        while (true) {
            System.out.println("""
                    Welcome to TBS
                    The following operations are supported:
                    0. exit
                    1. login
                    2. signup
                    Which operation do you want to perform ? (press the index of the operation)
                    """);
            Integer operation = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if(operation == 0) {
                return;
            } else if(operation == 1) {
                loggedInUser = login();
                if(loggedInUser == null) {
                    System.out.println("Invalid username or password!");
                }
                else {
                    System.out.println("Login successful");
                }
            } else if(operation == 2) {
                loggedInUser = signup();
                if(loggedInUser == null) {
                    System.out.println("User with username {} already exists!");
                }
                else {
                    System.out.println("Signup successful");
                }
            } else {
                System.out.println("Invalid operation!");
            }
        }
    }

    private static User signup() {
        System.out.println("Enter your username:\n");
        String name = scanner.nextLine();
        System.out.println("Enter your password: (no spaces are allowed)\n");
        String password = scanner.next();
        password = hashPassword(password);
        return userBookingService.signup(name, password);
    }

    private static User login() {
        System.out.println("Enter your username:\n");
        String name = scanner.nextLine();
        System.out.println("Enter your password: (no spaces are allowed)\n");
        String password = scanner.next();
        password = hashPassword(password);
        return userBookingService.login(name, password);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
