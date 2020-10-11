package model;

import javafx.print.PageLayout;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    Team team1;
    Team team2;
    Player p1;
    Player p2;

    @BeforeEach
    public void setup() {
    team1 = new Team();
    team2 = new Team();
    p1 = new Player();
    p2 = new Player();
    }

    @Test
    public void addPlayerTest() {
        team1.addPlayers(p1);
        team1.addPlayers(p2);
        assertTrue(team1.getPlayers().size()==2);
    }

    @Test
    public void getPlayerTest() {
        team1.addPlayers(p1);
        team1.addPlayers(p2);
        team2.addPlayers(p1);
        team2.addPlayers(p2);
        assertEquals(team1.getPlayers(),team2.getPlayers());
    }

    public void TeamNameTest() {
        team1.setName("Juventus");
        assertEquals("Juventus",team1.getName());
    }
}