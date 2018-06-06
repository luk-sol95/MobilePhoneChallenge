package com.soltys;

import java.io.Serializable;
import java.util.ArrayList;

public class ContactList implements Serializable {

    private ArrayList<Contact> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public boolean addContact(String name, int number) {
        if (findContact(name) == null) {
            contacts.add(new Contact(name, number));
            return true;
        } else return false;
    }

    private Contact findContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public boolean removeContact(String name) {
        if (findContact(name) == null) {
            return false;
        } else {
            contacts.remove(findContact(name));
            return true;
        }

    }

    public boolean modifyContact(String name, String newName, int newNumber) {
        if (findContact(name) == null) {
            return false;
        } else if (findContact(newName) != null) {
            return false;
        } else {
            Contact contact = findContact(name);
            contact.setName(newName);
            contact.setNumber(newNumber);
            return true;
        }
    }

    public boolean modifyContact(String name, String newName) {
        if (findContact(name) == null) {
            return false;
        } else if (findContact(newName) != null) {
            return false;
        } else {
            Contact contact = findContact(name);
            contact.setName(newName);
            return true;
        }
    }

    public boolean modifyContact(String name, int newNumber) {
        if (findContact(name) == null) {
            return false;
        } else {
            findContact(name).setNumber(newNumber);
            return true;
        }
    }

    public int getNumber (String name) {
        if (findContact(name) == null) {
            return -1;
        } else {
            return findContact(name).getNumber();
        }
    }

    public ArrayList getContactList () {
        ArrayList<String> contactList = new ArrayList<>();
        for (Contact contact: contacts) {
            String temp = contact.getName() + " : " + contact.getNumber();
            contactList.add(temp);
        }
        return contactList;
    }


}
