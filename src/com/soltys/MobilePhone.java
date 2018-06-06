package com.soltys;

import java.io.Serializable;

public class MobilePhone implements Serializable {

    private ContactList contacts;
    private String ownerName;

    public MobilePhone(String ownerName) {
        this.contacts = new ContactList();
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void addContact (String name, int number) {
        if (contacts.addContact(name, number)) {
            System.out.println("Contact " + name + " : " + number + " added succesfully.");
        } else {
            System.out.println("Contact with that name already exists.");
        }
    }

    public void modifyContact (String name, String newName, int newNumber) {
        if (contacts.modifyContact(name, newName, newNumber)) {
            System.out.println("Contact modified succesfully");
        } else {
            System.out.println("There is no such contact or contact with that name already exists.");
        }
    }

    public void modifyContact (String name, String newName) {
        if (contacts.modifyContact(name, newName)) {
            System.out.println("Contact changed succesfully");
        } else {
            System.out.println("There is no such contact or contact with that name already exists.");
        }
    }

    public void modifyContact (String name, int newNumber) {
        if (contacts.modifyContact(name, newNumber)) {
            System.out.println("Contact changed succesfully.");
        } else {
            System.out.println("There is contact with such name.");
        }
    }

    public void findContact (String name) {
        if (contacts.getNumber(name) == -1) {
            System.out.println("There is no such contact");
        } else {
            System.out.println(name + " : " + contacts.getNumber(name));
        }
    }

    public void removeContact (String name) {
        if (contacts.removeContact(name)) {
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("There is contact with such name.");
        }
    }

    public void printAllContacts () {
        for (int i = 0; i<contacts.getContactList().size(); i++) {
            System.out.println(contacts.getContactList().get(i));
        }
    }

}
