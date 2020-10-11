package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player;

    @Test
    public void playerInfoTest() {
        player = new Player();
        player.setName("Messi");
        player.setPosition("ST");
        player.setGoals(100);
        player.setAssists(50);
        player.setAge(33);

        assertEquals("Player name: " + player.getName() + ". position: " + player.getPosition()
                + ". age: " + player.getAge() + ". GOALS: " + player.getGoals() +
                ". ASSISTS: " + player.getAssists(),player.playerInfo());

    }


}
