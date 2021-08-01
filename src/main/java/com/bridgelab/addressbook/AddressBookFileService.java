package com.bridgelab.addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class AddressBookFileService
{
    private static final String FILE_PATH = "C:\\Users\\mihir\\IdeaProjects\\AddressBookWorkshop\\src\\Resources";
    public static final String TEXT_FILE="/addressBook.txt";

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
}
