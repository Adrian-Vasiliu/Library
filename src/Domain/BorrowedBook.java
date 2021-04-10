package Domain;

import Exceptions.*;

import java.time.LocalDate;

public class BorrowedBook extends Book {
    private boolean borrowed;
    private Person person;
    private LocalDate date;

    // constructor carte neimprumutata
    public BorrowedBook(String title, String[] authors, int appearanceYear, float price, String publishing,
                        String quota, boolean borrowed) throws YearException, PriceException, QuotaException {

        super(title, authors, appearanceYear, price, publishing, quota);
        this.borrowed = borrowed;
    }

    // constructor carte imprumutata
    public BorrowedBook(String title, String[] authors, int appearanceYear, float price, String publishing,
                        String quota, boolean borrowed, Person person, LocalDate date)
            throws YearException, PriceException, QuotaException {
        super(title, authors, appearanceYear, price, publishing, quota);
        this.borrowed = borrowed;
        this.person = person;
        this.date = date;
    }

    public boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
