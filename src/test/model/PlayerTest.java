package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player1;
    Player player2;

    @Test
    public void playerInfoTest() {
        player1 = new Player();
        player1.setName("Messi");
        player1.setPosition("ST");
        player1.setGoals(100);
        player1.setAssists(50);
        player1.setAge(33);

        player2 = new Player();
        player2.setAge(-30);

        assertEquals("Player name: " + player1.getName() + ". position: " + player1.getPosition()
                + ". age: " + player1.getAge() + ". GOALS: " + player1.getGoals() +
                ". ASSISTS: " + player1.getAssists(),player1.playerInfo());
        assertEquals(0,player2.getAge());

    }


}
