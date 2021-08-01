package com.bridgelab.addressbook;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookFileService
{
    private static final String FILE_PATH = "C:\\Users\\mihir\\IdeaProjects\\AddressBookWorkshop\\src\\Resources";
    public static final String TEXT_FILE="/addressBook.txt";
    public static final String CSV_FILE="/addressBook.csv";
    public static final String JSON_FILE="/addressBook.json";

    HashMap<String, ArrayList<PersonDetails>> addressBooks = AddressBookService.addressBook;
    /**
     * Store contacts to file.
     * @throws CustomException
     */
    public void writingToTextFile() throws CustomException {
        //checking file is already there
        checkFile();

        StringBuffer addressBookBuffer = new StringBuffer();
        AddressBookService.addressBook.entrySet().stream()
                .map(books->books.getKey())
                .map(bookNames->{
                    addressBookBuffer.append(bookNames+"\n");
                    return AddressBookService.addressBook.get(bookNames);
                })
                .forEach(contactInBook->addressBookBuffer.append(contactInBook+"\n"));
        try
        {
            if (!TEXT_FILE.split("\\.")[1].equals("txt"))
            {
                throw new CustomException(CustomException.ExceptionsType.WRONG_FILE_TYPE,"enter proper extension");
            }
            Files.write(Paths.get(FILE_PATH+TEXT_FILE), addressBookBuffer.toString().getBytes());
            System.out.println("Written in the file \n\n");
        }
        catch (NoSuchFileException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read data from file.
     * @throws CustomException
     */
    public void readFromTextFile() throws CustomException {
        try
        {
            //reading data from file
            String contentOfFile = Files.readString(Paths.get(FILE_PATH+TEXT_FILE));
            //printing data in console
            System.out.println(contentOfFile);
        }
        catch (NoSuchFileException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (FileNotFoundException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException e)
        {
            System.err.println("Faced some problem while reading the file ");
        }
    }

    /**
     * Create file if file does not exits.
     */
    private void checkFile()
    {
        File file = new File(FILE_PATH+TEXT_FILE);
        try
        {
            //checking file already exists
            if (!file.exists())
            {
                //if not creating a new file
                file.createNewFile();
                System.out.println("Created a file at "+FILE_PATH+TEXT_FILE);
            }
        }
        catch (IOException e1)
        {
            System.err.println("Problem encountered while creating a file");
        }
    }

    /**
     * Writing to csv file.
     */
    public void writingToCsvFile() throws CustomException {
        try
        {
            Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH+CSV_FILE));
            StatefulBeanToCsv<PersonDetails> beanToCsv = new StatefulBeanToCsvBuilder<PersonDetails>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<PersonDetails> ContactList = new ArrayList<>();
            addressBooks.entrySet().stream()
                    .map(books->books.getKey())
                    .map(bookNames->{
                        return addressBooks.get(bookNames);
                    }).forEach(contacts ->{
                ContactList.addAll(contacts);
            });
            beanToCsv.write(ContactList);
            writer.close();
        }
        catch (NoSuchFileException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (FileNotFoundException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (CsvDataTypeMismatchException e)
        {
            throw new CustomException(CustomException.ExceptionsType.DATA_MISSMATCH,"data type missmatch");
        }
        catch (CsvRequiredFieldEmptyException e)
        {
            throw new CustomException(CustomException.ExceptionsType.EMPTY_FILE,"problem while writing");
        }
    }
    public void readFromCsvFile() throws CustomException {
        List<PersonDetails> contacts = getContentOfCsv();
        printContacts(contacts);
    }

    private List<PersonDetails> getContentOfCsv() throws CustomException {
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));
            CsvToBean<PersonDetails> csvToBean = new CsvToBeanBuilder<PersonDetails>(reader)
                    .withType(PersonDetails.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();

        }
        catch (NoSuchFileException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (FileNotFoundException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //Writing to json file
    public void writingToJsonFile() throws CustomException {
        List<PersonDetails> contacts = getContentOfCsv();
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        try
        {
            FileWriter writer = new FileWriter(FILE_PATH+JSON_FILE);
            writer.write(json);
            writer.close();
            System.out.println("Written successfully");
        }
        catch (IOException e)
        {
            System.err.println("Error while writing into file");
        }
    }

    public void readFromJsonFile() throws CustomException {
        try
        {
            Gson gson = new Gson();
            //creating a buffer reader to read data form file
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH+JSON_FILE));
            //reading contacts from json file
            PersonDetails[] contacts = gson.fromJson(br,PersonDetails[].class);
            List<PersonDetails> contactsList = Arrays.asList(contacts);
            printContacts(contactsList);
        }
        catch (FileNotFoundException e)
        {
            throw new CustomException(CustomException.ExceptionsType.FILE_NOT_FOUND,"File Not Found");
        }
    }

    /**
     * Printing contacts.
     * @param contacts
     */
    private void printContacts(List<PersonDetails> contacts) {
        for(PersonDetails contact: contacts)
        {
            System.out.println("Name : " + contact.getFirstName()+" "+contact.getLastName());
            System.out.println("Email : " + contact.getEmailId());
            System.out.println("PhoneNo : " + contact.getPhoneNumber());
            System.out.println("Address : " + contact.getAddress());
            System.out.println("State : " + contact.getState());
            System.out.println("City : " + contact.getCity());
            System.out.println("Zip : " + contact.getZipCode());
            System.out.println("==========================");
        }
    }

}
