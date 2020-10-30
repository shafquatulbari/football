package persistence;

import model.Player;
import model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(Player player, String name, int age, int assists, int goals) {
        assertEquals(name,player.getName());
        assertEquals(age,player.getAge());
        assertEquals(assists,player.getAssists());
        assertEquals(goals,player.getGoals());
    }

    protected void checkTeam(Team team, String name) {
        assertEquals(name,team.getName());
    }
}
