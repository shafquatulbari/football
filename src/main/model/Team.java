package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

    //EFFECTS: returns the name of the Team.
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: sets this.name to name.
    public void setName(String name) {
        this.name = name;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list", playersToJson());
        json.put("TeamName",name);
        return json;
    }

    // EFFECTS: returns players in this Team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
