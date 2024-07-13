package org.vakya.bookmyshowproject.exeption;

public class UnAuthorizedAccessException extends Exception{
    public UnAuthorizedAccessException(String message){
        super(message);
    }
}
