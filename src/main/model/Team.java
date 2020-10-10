package model;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private List<Player> players;

    public Team() {
        players = new ArrayList<>();
    }

    public void addPlayers(Player player) {
        players.add(player);
    }


    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
