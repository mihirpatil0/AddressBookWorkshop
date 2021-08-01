package com.bridgelab.addressbook;

public class CustomException extends Throwable {
    public enum ExceptionsType
    {
        EMPTY_BOOK,
        WRONG_INPUT,
        NUll_VALUE,
        WRONG_FILE_TYPE,
        FILE_NOT_FOUND
    }

    public ExceptionsType type;

    public CustomException(ExceptionsType type,String message)
    {
        super(message);
        this.type = type;
    }
}
