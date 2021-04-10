package UI;

import Domain.BorrowedBook;
import Exceptions.*;
import Repository.Repository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

public class Controller {
    private View view;
    private Repository model;

    public Controller(Repository model, View view) {
        this.model = model;
        this.view = view;
        view.button1Listener(new Button1Listener());
        view.button2Listener(new Button2Listener());
        view.button3Listener(new Button3Listener());
        view.button6Listener(new Button6Listener());
    }

    void table() {
        String[][] data = new String[model.getList().size()][9];
        for (int i = 0; i < model.getList().size(); i++) {
            BorrowedBook book = model.getList().get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthors()[0];
            // + " " + book.get(i).getAuthors()[1];
            data[i][2] = Integer.toString(book.getAppearanceYear());
            data[i][3] = Float.toString(book.getPrice());
            data[i][4] = book.getPublishing();
            data[i][5] = book.getQuota();
            if (book.getBorrowed()) {
                data[i][6] = "da";
                data[i][7] = book.getPerson().getFirstName() + " " + book.getPerson().getLastName();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                data[i][8] = book.getDate().format(dateFormat);
            } else {
                data[i][6] = "nu";
                data[i][7] = data[i][8] = "-";
            }
        }
        view.showTable(data);
    }

    class Button1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent action) {
            try {
                model.readFile();
                view.textArea.setText("Fisier citit");
            } catch (FileNotFoundException | YearException | PriceException | QuotaException error) {
                view.showError(error.getMessage());
            }
        }
    }

    class Button2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent action) {
            table();
        }
    }

    class Button3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent action) {
            view.showInput("Citire...");
        }
    }

    class Button6Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent action) {
            model.sortByAppearance();
            table();
        }
    }
}
