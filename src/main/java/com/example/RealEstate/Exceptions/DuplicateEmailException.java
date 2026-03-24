package com.example.RealEstate.Exceptions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String msg) {
        super(msg);
    }

}
