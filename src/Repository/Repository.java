package Repository;

import Domain.*;
import Exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Repository { // clasa de stocare a obiectelor de tip Book
    private ArrayList<BorrowedBook> books = new ArrayList<>(); // definire lista dinamica

    public Repository() { // constructorul de initializare nu are nevoie de parametri
    }

    public ArrayList<BorrowedBook> getList() { // returneaza lista de obiecte
        return books;
    }

    // metoda de citire din fisier
    public void readFile() throws FileNotFoundException, YearException, PriceException, QuotaException {
        books.clear();
        File file = new File("data.txt");
        //File file = new File("data.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] attributes = scanner.nextLine().split("/");
            int appearanceYear = Integer.parseInt(attributes[2]);
            float price = Float.parseFloat(attributes[3]);
            if (attributes[6].equals("false")) {
                this.add(attributes[0], attributes[1], appearanceYear, price, attributes[4], attributes[5],
                        false);
            } else if (attributes[6].equals("true")) {
                this.add(attributes[0], attributes[1], appearanceYear, price, attributes[4], attributes[5],
                        true, attributes[7], attributes[8]);
            }
        }
    }

    // metoda de adaugare carte neimprumutata
    public void add(String title, String authors, int appearanceYear, float price, String publishing,
                    String quota, boolean borrowed) throws YearException, PriceException, QuotaException {
        String[] authorsArray = authors.split(",");
        books.add(new BorrowedBook(title, authorsArray, appearanceYear, price, publishing, quota, borrowed));
    }

    public void add(String title, String authors, int appearanceYear, float price, String publishing,
                    String quota, boolean borrowed, String personString, String dateString)
            throws YearException, PriceException, QuotaException { // metoda de adaugare carte imprumutata
        String[] authorsArray = authors.split(",");
        String[] personArray = personString.split(" ");
        Person person = new Person(personArray[0], personArray[1]);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateString, dateFormat);
        books.add(new BorrowedBook(title, authorsArray, appearanceYear, price, publishing, quota, borrowed, person,
                date));
    }

    public void delete(int bookPosition) { // sterge obiectul de pe pozitia data
        books.remove(bookPosition);
    }

    // metoda de actualizare
    public void update(int bookPosition, String title, String authors, String appearanceYear, String price,
                       String publishing, String quota, String borrowed, String personString, String dateString)
            throws YearException, PriceException, QuotaException {
        if (!title.equals(""))
            books.get(bookPosition).setTitle(title);
        if (!authors.equals("")) {
            String[] authorsArray = authors.split(",");
            books.get(bookPosition).setAuthors(authorsArray);
        }
        if (!appearanceYear.equals(""))
            books.get(bookPosition).setAppearanceYear(Integer.parseInt(appearanceYear));
        if (!price.equals(""))
            books.get(bookPosition).setPrice(Integer.parseInt(price));
        if (!publishing.equals(""))
            books.get(bookPosition).setPublishing(publishing);
        if (!quota.equals(""))
            books.get(bookPosition).setQuota(quota);
        if (borrowed.equals("nu"))
            books.get(bookPosition).setBorrowed(false);
        else if (borrowed.equals("da")) {
            books.get(bookPosition).setBorrowed(true);
            String[] personArray = personString.split(" ");
            Person person = new Person(personArray[0], personArray[1]);
            books.get(bookPosition).setPerson(person);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(dateString, dateFormat);
            books.get(bookPosition).setDate(date);
        }
    }

    public void sortByAppearance() { // sorteazza lista dupa anul aparitiei
        books.sort(new SortByAppearance());
    }

    public ArrayList<BorrowedBook> searchTitle(String keyWord) { // returneaza lista cu obiectele cautate dupa un titlu
        ArrayList<BorrowedBook> foundedBooks = new ArrayList<>();
        for (BorrowedBook b : books)
            if (b.getTitle().contains(keyWord))
                foundedBooks.add(b);
        return foundedBooks;
    }

    public ArrayList<BorrowedBook> searchPublishing(String publishing) { // returneaza lista cu obiectele cautate
        ArrayList<BorrowedBook> foundedBooks = new ArrayList<>();
        for (BorrowedBook b : books)
            if (b.getPublishing().equals(publishing))
                foundedBooks.add(b);
        return foundedBooks;
    }
}
