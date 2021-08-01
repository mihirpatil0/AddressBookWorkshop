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
    public void chooseOption() throws CustomException {
        AddressBookService addressBookService = new AddressBookService();
        AddressBookFileService addressBookFileService = new AddressBookFileService();
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
                    6.Sort And Display Contacts:
                    7.Sort By Zip-Code:
                    8.Write Into File:
                    9.Read From File:
                    10.Write Into CSV-File:
                    11.Read From CSV-File;
                    12.Write From Json-File:
                    13.Read from Json-File:
                    14.Exit\n""");
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
                    //sorting contacts alphabetically.
                    addressBookService.sortContactByFirstName();
                    break;
                case 7:
                    //sort contacts by zip-code.
                    addressBookService.sortByZipCode();
                case 8:
                    //write to file:
                    addressBookFileService.writingToTextFile();
                    break;
                case 9:
                    //read from file:
                    addressBookFileService.readFromTextFile();
                    break;
                case 10:
                    //write into csv-file.
                    addressBookFileService.writingToCsvFile();
                    break;
                case 11:
                    //read from csv-file.
                    addressBookFileService.readFromCsvFile();
                    break;
                case 12:
                    //write to json file.
                    addressBookFileService.writingToJsonFile();
                    break;
                case 13:
                    //read from json file.
                    addressBookFileService.readFromJsonFile();
                    break;
                case 14:
                    //Exit from program.
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }

    public static void main(String[] args) throws CustomException {
        AddressBookMain addressBookMain = new AddressBookMain();
        addressBookMain.chooseOption();
    }
}
