package ui;

import com.sun.xml.internal.ws.message.StringHeader;
import model.League;
import model.Player;
import model.Team;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Swing extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/League.json";
    private Team team;
    private League league;
    private Player player;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    int row;

    DefaultTableModel dtm;
    String [] header = new String[]{"Player Name","Age","Goal Scored","Assists"};
    private JTable table;

    JButton addPlayer = new JButton("Add Player and Set Stats");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JLabel age = new JLabel("Age:");
    JLabel goals = new JLabel("Goals:");
    JLabel assists = new JLabel("Assists");
    JLabel name = new JLabel("Name");

    JTextField nameText = new JTextField(10);
    JTextField assistText = new JTextField(3);
    JTextField goalText = new JTextField(3);
    JTextField ageText = new JTextField(3);

    public Swing() {

        super("League App");

        setSize(600,600);
        setResizable(true);
        setLayout(new FlowLayout());


        table = new JTable();
        team = new Team();
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

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int goal1 = Integer.parseInt(goalText.getText());
        int assist1 = Integer.parseInt(assistText.getText());
        String name1 = nameText.getText();

        if (e.getSource() == addPlayer) {
            player = new Player();
            player.setName(name1);
            player.setAssists(assist1);
            player.setGoals(goal1);
            team.addPlayers(player);
            displayPlayerDetails();
        }

    }

    public void displayPlayerDetails() {
        dtm.setRowCount(0);
        dtm.addRow(header);
        for (int i = 0; i < team.getPlayers().size(); i++) {
            Object [] obj = {team.getPlayers().get(i).getName(),team.getPlayers().get(i).getAge(),
                    team.getPlayers().get(i).getGoals(),team.getPlayers().get(i).getAssists()};
            dtm.addRow(obj);
        }

    }
}
