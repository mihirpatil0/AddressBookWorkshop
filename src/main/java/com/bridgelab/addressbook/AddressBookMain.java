package com.bridgelab.addressbook;

import java.util.Scanner;

public class AddressBookMain
{
    Scanner scanner;

    public AddressBookMain()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Name : chooseOption
     *
     * Description : Displaying option to user to choose from.
     *
     * Algorithm : In this method displaying user multiple option from switch statement.
     * according to users choice respective method will get called.
     *
     * Modification : First commit 14-July-2021
     */
    public void chooseOption()
    {
        AddressBookService addressBookService = new AddressBookService();
        boolean isExit = false;
        while (!isExit)
        {
            System.out.print("Enter Number As Per Your Choice:\n1.Add-New Contacts:\n2.Edit Contact:\n3.Delete Contact:\n4.Exit\n");
            int userInput = scanner.nextInt();
            switch (userInput)
            {
                case 1:
                    //add contact.
                    addressBookService.addNewContact();
                    break;
                case 2:
                    //edit contact.
                    addressBookService.editFullContact();
                    break;
                case 3:
                    //delete contact.
                    addressBookService.deleteContact();
                case 4:
                    //Exit from program.
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }

    public static void main(String[] args)
    {
        AddressBookMain addressBookMain = new AddressBookMain();
        addressBookMain.chooseOption();
    }
}
