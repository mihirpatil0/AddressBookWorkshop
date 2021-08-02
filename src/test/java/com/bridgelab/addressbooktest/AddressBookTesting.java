package com.bridgelab.addressbooktest;

import com.bridgelab.addressbook.AddressBookService;
import com.bridgelab.addressbook.PersonDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressBookTesting
{
    @Test
    public void givenReadFromDB_ShouldReturnListOfContacts()
    {
        AddressBookService addressBookService = new AddressBookService();
        List<PersonDetails> contactList  = addressBookService.readFromDataBase();
        Assertions.assertEquals(4, contactList.size());
    }

    @Test
    public void givenUpadteEmployeeData_WhenUpdated_shouldSyncWithDatabase()
    {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readFromDataBase();
        addressBookService.updateContactInDataBase("Mihir",900000);
        Assertions.assertTrue(addressBookService.checkSyncWithDB("Mihir"));
    }

    @Test
    public void givenAddressbookDB_WhenBetweenDates_ShouldReturnListOfContacts()
    {
        AddressBookService addressBookService = new AddressBookService();
        List<PersonDetails> contactList  = addressBookService.getContatctsAddedAfterdate("2020-1-1");
        Assertions.assertEquals(4, contactList.size());
    }
}
