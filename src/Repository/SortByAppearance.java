package Repository;

import Domain.Book;

import java.util.Comparator;

public class SortByAppearance implements Comparator<Book> { // implementare clasa pentru comparare si sortare
    public int compare(Book book1, Book book2) {
        return book1.getAppearanceYear() - book2.getAppearanceYear();
    }
}
