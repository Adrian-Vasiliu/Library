package Exceptions;

public class QuotaException extends Exception { // definire exceptie pentru "quota" din clasa "Book"
    public QuotaException(String message) {
        super(message);
    }
}
