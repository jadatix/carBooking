package org.jadatix.carbooking.exception;

public class FieldValidationException extends RuntimeException{
    public FieldValidationException(){
        super("Field validation error");
    }

    public FieldValidationException(String message){
        super(message);
    }
}
