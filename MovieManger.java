import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Movie {
    public String title;
    public String genre;
    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }
}

public class MovieManger {
    private ArrayList<Movie> mov = new ArrayList<>();
    private JFrame frame;
    private JTextField titleF;
    private JTextField searchF;
    private JComboBox<String> genreB;
    private DefaultTableModel tableM;
    private JLabel countL;

    public MovieManger() {
        frame = new JFrame("Movie Collection Manager");
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 500);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleF = new JTextField(20);
        titlePanel.add(titleLabel);
        titlePanel.add(titleF);
        topPanel.add(titlePanel);

        JPanel genrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        genreB = new JComboBox<>(new String[]{"Action", "Comedy", "Horror", "Drama"});
        JButton addButton = new JButton("Add Movie");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        genrePanel.add(genreLabel);
        genrePanel.add(genreB);
        genrePanel.add(Box.createHorizontalStrut(50));
        genrePanel.add(addButton);
        topPanel.add(genrePanel);

        frame.add(topPanel, BorderLayout.NORTH);

        tableM = new DefaultTableModel(new String[]{"Title", "Genre"}, 0);
        JTable movieTable = new JTable(tableM);
        movieTable.setFont(new Font("Arial", Font.PLAIN, 14));
        movieTable.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        JButton sortTitleButton = new JButton("Sort by Title");
        JButton sortGenreButton = new JButton("Sort by Genre");
        countL = new JLabel("Movies: 0");
        countL.setFont(new Font("Arial", Font.BOLD, 14));
        sortTitleButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortGenreButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortPanel.add(sortTitleButton);
        sortPanel.add(Box.createHorizontalStrut(100));
        sortPanel.add(countL);
        sortPanel.add(Box.createHorizontalStrut(50));
        sortPanel.add(sortGenreButton);
        bottomPanel.add(sortPanel);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchF = new JTextField(15);
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(searchLabel);
        searchPanel.add(searchF);
        searchPanel.add(searchButton);
        bottomPanel.add(searchPanel);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String title = titleF.getText().trim();
            String genre = (String) genreB.getSelectedItem();
            if (!title.isEmpty()) {
                mov.add(new Movie(title, genre));
                tableM.addRow(new Object[]{title, genre});
                countL.setText("Movies: " + mov.size());
                titleF.setText("");
            }
        });

        sortTitleButton.addActionListener(e -> {
            mov.sort(Comparator.comparing(m -> m.title));
            updateTable();
        });

        sortGenreButton.addActionListener(e -> {
            mov.sort(Comparator.comparing(m -> m.genre));
            updateTable();
        });

        searchButton.addActionListener(e -> {
            String search = searchF.getText().trim().toLowerCase();
            for (int i = 0; i < mov.size(); i++) {
                if (mov.get(i).title.toLowerCase().contains(search)) {
                    movieTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateTable() {
        tableM.setRowCount(0);
        for (Movie m : mov) {
            tableM.addRow(new Object[]{m.title, m.genre});
        }
    }

    public static void main(String[] args) {
        new MovieManger();
    }
}