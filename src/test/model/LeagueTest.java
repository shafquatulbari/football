package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {

    League league;
    League league2;
    Team team1;
    Team team2;

    @BeforeEach
    public void setup() {
        league = new League();
        league2 = new League();
        team1 = new Team();
        team2 = new Team();
    }

    @Test
    public void addTeamTest() {
        assertTrue(league.getTeams().size()==0);
        league.addTeam(team1);
        assertTrue(league.getTeams().size()==1);
        league.addTeam(team2);
        assertTrue(league.getTeams().size()==2);
    }

    @Test
    public void getTeamsTest() {
        league.addTeam(team1);
        league.addTeam(team2);
        league2.addTeam(team1);
        league2.addTeam(team2);
        assertEquals(league.getTeams(),league2.getTeams());
    }
}
