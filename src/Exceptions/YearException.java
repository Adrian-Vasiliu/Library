package Exceptions;

public class YearException extends Exception { // definire exceptie pentru "appearanceYear" din clasa "Book"
    public YearException(String message) {
        super(message);
    }
}
