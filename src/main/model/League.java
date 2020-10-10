package model;

import java.util.ArrayList;
import java.util.List;

public class League {

    private List<Team> teams;

    public League() {
        teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public List<Team> getTeams() {
        return teams;
    }


}
