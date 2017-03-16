package com.mc.kushnirenko;

import java.util.Locale;
import java.util.Scanner;

public class RunApp {

    public static void main(String[] args) {
        Locale.setDefault(new Locale( "uk_UA"));
        System.out.println("Hello!");

        URLProcessor urlProcessor = new URLProcessor();

        if (args.length > 0 && args[0] != null) {
            System.out.println("Command line argument is: " + args[0]);
            urlProcessor.processResource(args[0]);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter URL-address (or 'quit' to exit): ");
            String arg = scanner.next();

            if (arg.length() == 0) {
                System.out.println("Url cannot be empty!");
                continue;
            }

            if (arg.equals("quit")) {
                System.out.println("BYE!");
                return;
            }
            urlProcessor.processResource(arg);
        }
    }
}
