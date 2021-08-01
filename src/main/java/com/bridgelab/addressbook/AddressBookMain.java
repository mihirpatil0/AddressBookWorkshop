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
            System.out.print("""
                    Enter Number As Per Your Choice:
                    1.Add-New Contacts:
                    2.Edit Contact:
                    3.Delete Contact:
                    4.Search Person In city Or State:
                    5.Get Number Of Contacts In City Or State:
                    6.Exit\n""");
            int userInput = scanner.nextInt();
            switch (userInput)
            {
                case 1:
                    //add new contact.
                    addressBookService.addNewContact();
                    break;
                case 2:
                    //edit existing contact.
                    addressBookService.editFullContact();
                    break;
                case 3:
                    //delete existing contact.
                    addressBookService.deleteContact();
                    break;
                case 4:
                    //search person by city or state.
                    addressBookService.searchPersonInACityOrState();
                    break;
                case 5:
                    //get number contact in a city or state.
                    addressBookService.getNumberOfContacts();
                    break;
                case 6:
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
