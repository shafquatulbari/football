package ui;

import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public class Swing extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/Swing.json";
    private Team team;
    private Player player;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader1;

    DefaultTableModel dtm;
    String [] header = new String[]{"Player Name","Age","Goal Scored","Assists"};
    private JTable table;

    JButton addPlayer = new JButton("Add Player and Set Stats");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JButton highest = new JButton("Click");
    JLabel age = new JLabel("Age:");
    JLabel goals = new JLabel("Goals:");
    JLabel assists = new JLabel("Assists");
    JLabel name = new JLabel("Player Name");
    JLabel topGoalScorer = new JLabel("Press Click to see top goal scorer");

    JTextField nameText = new JTextField(10);
    JTextField assistText = new JTextField(3);
    JTextField goalText = new JTextField(3);
    JTextField ageText = new JTextField(3);

    JLabel background;

    //EFFECTS: Constructs Swing which creates a Team object so that the application runs and
    // other methods can be executed inside for example add players to the team which can be shown on a table
    public Swing() {
        super("League App");
        team = new Team();
        jsonReader1 = new JsonReader(JSON_STORE);
        jsonWriter1 = new JsonWriter(JSON_STORE);
        setSize(500,1000);
        setResizable(true);
        setLayout(new FlowLayout());
        ImageIcon img = new ImageIcon("./data/soccer.jpg");
        initialize();
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,1000,1000);
        add(background);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayer) {
            initializePlayer();
            soundEffect("./data/Whistle.wav");
        } else if (e.getSource() == save) {
            save();
            soundEffect("./data/Whistle.wav");
        } else if (e.getSource() == load) {
            load();
            soundEffect("./data/Whistle.wav");
        } else if (e.getSource() == highest) {
            Player max = Collections.max(team.getPlayers());
            topGoalScorer.setText("Top goal scorer is:" + max.getName());
        }

    }

    //EFFECTS: display all the players details from a Team on a table
    private void displayPlayerDetails() {
        dtm.setRowCount(0);
        dtm.addRow(header);
        for (int i = 0; i < team.getPlayers().size(); i++) {
            Object [] obj = {team.getPlayers().get(i).getName(),team.getPlayers().get(i).getAge(),
                    team.getPlayers().get(i).getGoals(),team.getPlayers().get(i).getAssists()};
            dtm.addRow(obj);
        }

    }

    //EFFECTS: initializes and adds buttons/listeners/text fields etc to JFrame
    private void initialize() {
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
        save.addActionListener(this);
        load.addActionListener(this);
        highest.addActionListener(this);
        initializeTable();
        add(highest);
        add(topGoalScorer);
    }

    // EFFECTS: saves the Team to file
    private void save() {
        try {
            jsonWriter1.open();
            jsonWriter1.writeTeam(team);
            jsonWriter1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Team from file
    private void load() {
        try {
            team = jsonReader1.readTeam();
            displayPlayerDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: initializes Player object and adds player to the team
    private void initializePlayer() {
        int goal1 = Integer.parseInt(goalText.getText());
        int assist1 = Integer.parseInt(assistText.getText());
        String name1 = nameText.getText();
        int age1 = Integer.parseInt(ageText.getText());
        player = new Player();
        player.setAge(age1);
        player.setName(name1);
        player.setAssists(assist1);
        player.setGoals(goal1);
        team.addPlayers(player);
        displayPlayerDetails();
    }

    //EFFECTS: initializes and adds a JTable object
    private void initializeTable() {
        table = new JTable();
        dtm = new DefaultTableModel(header,0);
        table.setModel(dtm);
        add(table);
    }

    //EFFECTS: reads sound file from filepath
    private void soundEffect(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(filepath);
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
