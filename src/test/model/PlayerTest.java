package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    Player player;

    @Test
    public void playerInfoTest() {
        player = new Player();
        player.setName("Messi");
        player.setPosition("ST");

        assertTrue( player.playerInfo().contains("Player name: Messi. position: ST."));
    }


}
