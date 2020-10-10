package model;

import java.util.ArrayList;
import java.util.List;

public class League {
    //This class represents information of football League and behaviour and attributes associated with it.
    private List<Team> teams;

    //EFFECTS: constructs a League with an empty ArrayList.
    public League() {
        teams = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a team to the arraylist.
    public void addTeam(Team team) {
        teams.add(team);
    }

    //EFFECTS: returns the arraylist containing the team.
    public List<Team> getTeams() {
        return teams;
    }


}
