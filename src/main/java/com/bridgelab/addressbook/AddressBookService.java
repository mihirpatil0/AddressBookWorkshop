package com.bridgelab.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookService
{
    Scanner scanner;
    //To store contacts.
    ArrayList<PersonDetails> contactList;

    //instantiating scanner and ArrayList in constructor.
    public AddressBookService()
    {
        scanner = new Scanner(System.in);
        contactList = new ArrayList<>();
    }

    /**
     * Name : addNewContact
     *
     * Description : Adding new contact to AddressBook.
     *
     * Algorithm : In this method accepting all necessary contact details from user,
     * and adding those all details to store in AddressBook as a new contact.
     *
     * Modification : First commit 14-July-2021.
     */
    public void addNewContact()
    {
        PersonDetails contactDetails = new PersonDetails();

        System.out.print("Enter First Name : ");
        contactDetails.setFirstName(scanner.next());

        System.out.print("Enter Last Name : ");
        contactDetails.setLastName(scanner.next());

        System.out.print("Enter Address : ");
        contactDetails.setAddress(scanner.next());

        System.out.print("Enter City : ");
        contactDetails.setCity(scanner.next());

        System.out.print("Enter State : ");
        contactDetails.setState(scanner.next());

        System.out.print("Enter ZipCode : ");
        contactDetails.setZipCode(scanner.nextInt());

        System.out.print("Enter Phone-Number : ");
        contactDetails.setPhoneNumber(scanner.nextLong());

        System.out.print("Enter Email-Id : ");
        contactDetails.setEmailId(scanner.next());

        contactList.add(contactDetails);
        System.out.println("\nContact added successfully.\n");
    }
}
