package UI;

import Domain.*;
import Exceptions.*;
import Repository.Repository;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Console { // clasa de gestionare a programului care interactioneaza cu utilizatorul

    private Repository repository = new Repository();

    public static String readString(String message) { //returneaza un numar citit de la tastatura
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int readInt(String message) { //returneaza un numar intreg citit de la tastatura
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception exception) {
            System.out.println("Intrduceti un numar intreg!");
            return readInt(message);
        }
    }

    public static float readFloat(String message) { //returneaza un numar citit de la tastatura
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            return Float.parseFloat(scanner.nextLine());
        } catch (Exception exception) {
            System.out.println("Intrduceti un numar!");
            return readFloat(message);
        }
    }

    void show(ArrayList<BorrowedBook> books) { // afisare tuturor obiectelor din memorie
        int crt = 0;
        System.out.format("%-2s| %-30s| %-25s|%7s |%7s | %-15s|%10s |%12s | %-15s| %-15s\n", "#", "Titlu", "Autor",
                "An", "Pret", "Editura", "Cota", "Imprumutata", "Persoana", "Data");
        System.out.println("----------------------------------------------------------------------------------------" +
                "----------------------------------------------------------------");
        for (BorrowedBook book : books) {
            String authors = "";
            for (String s : book.getAuthors())
                authors += s + ";";
                //authors += book.getAuthors().length - 1;
            if (!book.getBorrowed()) {
                System.out.format("%-2s| %-30s| %-25s|%7d |%7.2f | %-15s|%10s |%12s | %-15s| %-15s\n", ++crt,
                        book.getTitle(), authors, book.getAppearanceYear(), book.getPrice(), book.getPublishing(),
                        book.getQuota(), "nu", "-", "-");
            } else {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                System.out.format("%-2s| %-30s| %-25s|%7d |%7.2f | %-15s|%10s |%12s | %-15s| %-15s\n", ++crt,
                        book.getTitle(), authors, book.getAppearanceYear(), book.getPrice(), book.getPublishing(),
                        book.getQuota(), "DA", book.getPerson(), book.getDate().format(dateFormat));
            }
        }
    }

    void add() {
        try {
            String attribute1 = readString("Titlul: ");
            String attribute2 = readString("Autorii (separati prin virgula): ");
            int attribute3 = readInt("Anul aparitiei: ");
            float attribute4 = readFloat("Pretul: ");
            String attribute5 = readString("Editura: ");
            String attribute6 = readString("Cota: ");
            String borrowed = readString("Este imprumutata? ");
            if (borrowed.equals("nu"))
                repository.add(attribute1, attribute2, attribute3, attribute4, attribute5, attribute6,
                        false);
            else if (borrowed.equals("da")) {
                String person = readString("Persoana (nume, spatiu, prenume): ");
                String date = readString("Data imprumutarii: ");
                repository.add(attribute1, attribute2, attribute3, attribute4, attribute5, attribute6,
                        true, person, date);
            }
            System.out.println("Carte adaugata");
        } catch (QuotaException | PriceException | YearException e) {
            System.out.println(e.getMessage());
        }
    }

    void update() {
        int bookPosition = readInt("Actualizati cartea (nr. crt): ");
        try {
            String attribute1 = readString("Titlul: ");
            String attribute2 = readString("Autorii (separati prin virgula): ");
            String attribute3 = readString("Anul aparitiei: ");
            String attribute4 = readString("Pretul: ");
            String attribute5 = readString("Editura: ");
            String attribute6 = readString("Cota: ");
            String borrowed = readString("Este imprumutata? ");
            if (borrowed.equals("nu")) {
                repository.update(bookPosition - 1, attribute1, attribute2, attribute3, attribute4,
                        attribute5, attribute6, borrowed, "", "");
            } else if (borrowed.equals("da")) {
                String person = readString("Persoana (nume, spatiu, prenume): ");
                String date = readString("Data imprumutarii: ");
                repository.update(bookPosition - 1, attribute1, attribute2, attribute3, attribute4,
                        attribute5, attribute6, borrowed, person, date);
            }
            System.out.println("Carte actualizata");
        } catch (QuotaException | PriceException | YearException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() { // interactiunea cu utilizatorul
        int option = 0;
        while (option != 9) {
            System.out.print("\n1. Incarcare date din fisier\n" +
                    "2. Afisare date\n" +
                    "3. Citire carte\n" +
                    "4. Actualizare carte\n" +
                    "5. Stergere carte\n" +
                    "6. Sortare carti dupa anul aparitiei\n" +
                    "7. Cautare titlu carti (case sensitive)\n" +
                    "8. Cautare carti dupa editura\n" +
                    "9. Inchidere program\n");
            //"8. Cautare autor carti\n" +
            option = readInt("Option: ");
            switch (option) {
                case 1:
                    try {
                        repository.readFile();
                        System.out.println("Fisier citit");
                    } catch (FileNotFoundException ex) {
                        System.out.println("Eroare! Fisier negasit!");
                    } catch (YearException | PriceException | QuotaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    show(repository.getList());
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    int bookPosition = readInt("Stergeti cartea (nr. crt): ");
                    repository.delete(bookPosition - 1);
                    System.out.println("Carte stearsa");
                    break;
                case 6:
                    repository.sortByAppearance();
                    show(repository.getList());
                    break;
                case 7:
                    String keyWord = readString("Cuvant din titlu: ");
                    show(repository.searchTitle(keyWord));
                    break;
                case 8:
                    String publishing = readString("Editura: ");
                    show(repository.searchPublishing(publishing));
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Optiune invalida! Reincercati!");
            }
        }
    }
}
