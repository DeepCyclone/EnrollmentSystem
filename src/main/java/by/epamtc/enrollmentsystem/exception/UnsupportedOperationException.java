package by.epamtc.enrollmentsystem.exception;

public class UnsupportedOperationException extends Exception{
    public UnsupportedOperationException(){
        super();
    }
    public UnsupportedOperationException(String message){
        super(message);
    }
    public UnsupportedOperationException(String message, Throwable cause){
        super(message,cause);
    }
}
