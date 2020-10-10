package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
//This class represents information of football team and behaviour and attributes associated with it.
    private String name;
    private List<Player> players;

    //EFFECTS: constructs a Team with an empty ArrayList.
    public Team() {
        players = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add players to the arraylist.
    public void addPlayers(Player player) {
        players.add(player);
    }

    //EFFECTS: returns the arraylist containing the players.
    public List<Player> getPlayers() {
        return players;
    }

    //EFFECTS: returns the name of the player.
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: sets this.name to name.
    public void setName(String name) {
        this.name = name;
    }
}
