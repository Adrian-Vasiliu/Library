package UI;

import Repository.Repository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Repository model;
    JTextArea textArea = new JTextArea();
    JPanel verticalPanel = new JPanel();
    JButton[] buttons = new JButton[8];
    JTextField inputField = new JTextField();
    JPanel finalPanel = new JPanel();

    public View(Repository model) {
        this.model = model;
        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //verticalPanel.setLayout(new BorderLayout());
        //verticalPanel.setLayout(new GridLayout(0, 1));
        //verticalPanel.setPreferredSize(new Dimension(500, 300));
        textArea.setPreferredSize(new Dimension(200, 20));
        textArea.setEditable(false);
        textArea.setText("Alegeti o optiune:");
        textArea.setFont(textArea.getFont().deriveFont(Font.BOLD, 12f));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(textArea);

        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setAlignmentX(LEFT_ALIGNMENT);
        verticalPanel.add(messagePanel);

        String[] options = {"Incarcare date din fisier", "Afisare date", "Citire carte", "Actualizare carte",
                "Stergere carte", "Sortare carti dupa anul aparitiei", "Cautare titlu carti",
                "Cautare carti dupa editura", "Inchidere program"};
        for (int i = 0; i < 8; i++) {
            JButton button = new JButton(Integer.toString(i + 1));
            buttons[i] = button;
            JLabel description = new JLabel(options[i]);
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.add(button);
            panel.add(description);
            verticalPanel.add(panel);
        }
//        JTextField textField = new JTextField();
//        textField.setText("");
//        verticalPanel.add(textField);
//        JTextArea listaProduse = new JTextArea(7, 20);
        finalPanel.add(verticalPanel);
        setContentPane(finalPanel);
        pack();
    }

    void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    void showTable(String[][] data) {
        String[] columns = {"Titlu", "Autor", "An", "Pret", "Editura", "Cota", "Imprumutata", "Persoana", "Data"};
        JTable table = new JTable(data, columns);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        //table.setBounds(30, 40, 200, 300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1270, 320));

        finalPanel.removeAll();
        finalPanel.add(verticalPanel);
        finalPanel.add(scrollPane);
        setContentPane(finalPanel);
        pack();
        //setVisible(true);
    }

    void showInput(String message) {
        JLabel text = new JLabel(message);
        inputField.setPreferredSize(new Dimension(200, 20));
        finalPanel.removeAll();
        finalPanel.add(verticalPanel);
        finalPanel.add(text);
        finalPanel.add(inputField);
        setContentPane(finalPanel);
        pack();
    }

    String getUserInput() {
        return inputField.getText();
    }

//    public void afisare(String s) {
//        listaProduse.setText(s);
//    }
//
//    void vanzareActionListener(ActionListener val) {
//        vanzareButon.addActionListener(val);
//    }

    void button1Listener(ActionListener listener) {
        buttons[0].addActionListener(listener);
    }

    void button2Listener(ActionListener listener) {
        buttons[1].addActionListener(listener);
    }

    public void button3Listener(ActionListener listener) {
        buttons[2].addActionListener(listener);
    }

    public void button4Listener(ActionListener listener) {
        buttons[3].addActionListener(listener);
    }

    void button6Listener(ActionListener listener) {
        buttons[5].addActionListener(listener);
    }
}
