package persistence;

import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            League league = new League();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeague() {
        try {
            League league = new League();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            league = reader.read();
            assertEquals(0, league.getTeams().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLeague() {
        try {
            League league = new League();
            Team team1 = new Team();
            team1.setName("Barcelona");
            Player player1 = new Player();
            player1.setName("Messi");
            player1.setAge(10);
            team1.addPlayers(player1);
            league.addTeam(team1);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            league = reader.read();
            assertEquals(1, league.getTeams().size());
            checkPlayer(player1,"Messi",10,0,0);
            checkTeam(team1,"Barcelona");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
