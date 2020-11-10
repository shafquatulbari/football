package model;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player1;
    Player player2;

    @Test
    public void playerInfoTest() {
        player1 = new Player();
        player1.setName("Messi");
        player1.setGoals(100);
        player1.setAssists(50);
        player1.setAge(33);

        player2 = new Player();
        player2.setAge(-30);

        assertEquals("Player name: " + player1.getName()
                + ". age: " + player1.getAge() + ". GOALS: " + player1.getGoals() +
                ". ASSISTS: " + player1.getAssists(),player1.playerInfo());
        assertEquals(0,player2.getAge());

    }

    @Test
    public void highestGoals() {
        Team t1 = new Team();
        player1 = new Player();
        player2 = new Player();
        Player player3 = new Player();
        player3.setGoals(5);
        player3.setAssists(1);
        player2.setGoals(5);
        player2.setAssists(1);
        player1.setGoals(10);
        player1.setAssists(10);
        t1.addPlayers(player1);
        t1.addPlayers(player3);
        t1.addPlayers(player2);
        int result1 = player1.compareTo(player2);
        int result2 = player2.compareTo(player3);
        int result3 = player2.compareTo(player1);
        assertTrue(result1 == 1);
        assertTrue(result2 == 0);
        assertTrue(result3 == -1);

    }

}
