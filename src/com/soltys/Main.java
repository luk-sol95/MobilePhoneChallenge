package com.soltys;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        powerOn();

    }

    public static void powerOn() {
        System.out.println("Enter your name: ");
        String ownerName = scanner.nextLine();
        if (loadPhone(ownerName) != null) {
            mobileMenu(loadPhone(ownerName));
        } else {
            mobileMenu(new MobilePhone(ownerName));
        }
    }

    public static void mobileMenu(MobilePhone phone) {
        int number;
        String name;
        boolean quit = false;
        int choice;
        while (!quit) {
            System.out.println("What do you want to do, " + phone.getOwnerName() + "?\n" +
                    "Press 0 to print list of possibilities");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input detected. Enter correct number.");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("You chose add contact.");
                    System.out.println("Enter contact name: ");
                    name = scanner.next();
                    System.out.println("Enter contact number: ");
                    try {
                        number = scanner.nextInt();
                        phone.addContact(name, number);
                    } catch (InputMismatchException e) {
                        System.out.println("Entered wrong number. Try again.");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.println("You chose remove contact. Enter contact name: ");
                    name = scanner.next();
                    phone.removeContact(name);
                    break;
                case 3:
                    System.out.println("Enter name to print out: ");
                    name = scanner.next();
                    phone.findContact(name);
                    break;

                case 4:
                    phone.printAllContacts();
                    break;

                case 5:
                    System.out.println("1. Change contact number.\n" +
                            "2. Change contact name.\n" +
                            "3. Change both name and number.");
                    int choice2 = scanner.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.println("Enter contact name: ");
                            name = scanner.next();
                            System.out.println("Enter contact new number: ");
                            try {
                                number = scanner.nextInt();
                                phone.modifyContact(name, number);
                            } catch (InputMismatchException e) {
                                System.out.println("Wrong number entered. Try again.");
                                scanner.nextLine();
                                continue;
                            }
                            break;
                        case 2:
                            System.out.println("Enter contact name: ");
                            name = scanner.next();
                            System.out.println("Enter contact new name: ");

                            String newName = scanner.next();
                            phone.modifyContact(name, newName);

                            break;
                        case 3:
                            System.out.println("Enter contact name: ");
                            name = scanner.next();
                            System.out.println("Enter new contact name: ");
                            newName = scanner.next();
                            System.out.println("Enter new contact number: ");
                            try {
                                number = scanner.nextInt();
                                phone.modifyContact(name, newName, number);
                            } catch (InputMismatchException e) {
                                System.out.println("Wrong number entered. Try again.");
                                scanner.nextLine();
                                continue;
                            }
                            break;
                        default:
                            System.out.println("Wrong choice. Choose again: ");
                            break;
                    }

                case 0:
                    System.out.println("Press any of following buttons:\n" +
                            "1. Add contact.\n" +
                            "2. Remove contact.\n" +
                            "3. Find contact\n" +
                            "4. Print list of contacts.\n" +
                            "5. Change contact.\n" +
                            "0. Print list of possible choices again.\n" +
                            "99. Save phone's state and power off.");
                    break;

                case 99:
                    System.out.println("Saving phone state and quitting, good bye.");
                    save(phone);
                    quit = true;
                    break;
            }
        }
    }

    public static void save(MobilePhone phone) {
        Path phonePath = FileSystems.getDefault().getPath(phone.getOwnerName() + ".dat");
        try (ObjectOutputStream phoneFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(phonePath)))) {
            phoneFile.writeObject(phone);
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    public static MobilePhone loadPhone(String ownerName) {
        Path phonePath = FileSystems.getDefault().getPath(ownerName + ".dat");
        try (ObjectInputStream phoneFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(phonePath)))) {
            return (MobilePhone) phoneFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Mobile phone not found");
            return null;
        }
    }


}
