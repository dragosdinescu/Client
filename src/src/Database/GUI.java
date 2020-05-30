package src.Database;

import javax.swing.*;
import java.awt.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

    public class GUI extends JFrame {
        private Database database;
        public GUI(Database db) {
            this.database = db;
            makeGUI();
        }
        private void makeGUI() {
            JFrame window = new JFrame("Database query form");

            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            window.setContentPane(panel);
            panel.setLayout(new FlowLayout()); // randarea layout-ului
            JButton searchButton = new JButton("Search");
            panel.add(new JLabel("Enter key word"));
            JTextField searchText = new JTextField(15);
            JTextArea displayText = new JTextArea(4,25);
            displayText.setEditable(false);
            panel.add(searchText);
            panel.add(searchButton);
            panel.add(displayText);
            panel.setPreferredSize(new Dimension(700,400));
            window.setSize(800,400);

            /**
             * Adds a action listener, when button pressed
             * check if text is not null
             * if true make the text box null and query
             * the database with the entered text
             */
            searchButton.addActionListener(buttonPressed -> {
                String searchWord = searchText.getText();
                if (!searchWord.equals("")) {
                    searchText.setText("");
                    displayText.setText(database.searchDB(searchWord));
                }
            });
            window.pack();

            window.setVisible(true);
        }
    }

