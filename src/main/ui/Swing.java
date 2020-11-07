package ui;

import model.League;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Swing extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/League.json";
    private Team team = new Team();
    private League league = new League();
    private Player player;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    DefaultTableModel dtm;
    String [] header = new String[]{"Player Name","Age","Goal Scored","Assists"};
    private JTable table;

    JButton addPlayer = new JButton("Add Player and Set Stats");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JLabel age = new JLabel("Age:");
    JLabel goals = new JLabel("Goals:");
    JLabel assists = new JLabel("Assists");
    JLabel name = new JLabel("Player Name");

    JTextField nameText = new JTextField(10);
    JTextField assistText = new JTextField(3);
    JTextField goalText = new JTextField(3);
    JTextField ageText = new JTextField(3);

    public Swing() {
        super("League App");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        league.addTeam(team);
        setSize(600,600);
        setResizable(true);
        setLayout(new FlowLayout());
        initialize();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int goal1 = Integer.parseInt(goalText.getText());
        int assist1 = Integer.parseInt(assistText.getText());
        String name1 = nameText.getText();
        int age1 = Integer.parseInt(ageText.getText());

        if (e.getSource() == addPlayer) {
            player = new Player();
            player.setAge(age1);
            player.setName(name1);
            player.setAssists(assist1);
            player.setGoals(goal1);
            team.addPlayers(player);
            displayPlayerDetails();
        }
        if (e.getSource() == save) {
            save();
        }
        if (e.getSource() == load) {
            load();
        }

    }

    private void displayPlayerDetails() {
        dtm.setRowCount(0);
        dtm.addRow(header);
        for (int i = 0; i < team.getPlayers().size(); i++) {
            Object [] obj = {team.getPlayers().get(i).getName(),team.getPlayers().get(i).getAge(),
                    team.getPlayers().get(i).getGoals(),team.getPlayers().get(i).getAssists()};
            dtm.addRow(obj);
        }

    }

    private void initialize() {
        table = new JTable();
        add(name);
        add(nameText);
        add(age);
        add(ageText);
        add(goals);
        add(goalText);
        add(assists);
        add(assistText);
        add(addPlayer);
        add(save);
        add(load);
        addPlayer.addActionListener(this);
        dtm = new DefaultTableModel(header,0);
        table.setModel(dtm);
        add(table);
    }

    // EFFECTS: saves the League to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads League from file
    private void load() {
        try {
            league = jsonReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
