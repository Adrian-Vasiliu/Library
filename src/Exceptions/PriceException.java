package Exceptions;

public class PriceException extends Exception { // definire exceptie pentru "price" din clasa "Book"
    public PriceException(String message) {
        super(message);
    }
}
