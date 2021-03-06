package persistence;

import exceptions.NotPossibleAgeException;
import exceptions.NotPossibleGoalsOrAssistsException;
import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterReaderTest extends JsonTest{

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
            try {
                league = reader.read();
            } catch (NotPossibleAgeException e) {
                e.printStackTrace();
            } catch (NotPossibleGoalsOrAssistsException e) {
                e.printStackTrace();
            }
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
            try {
                player1.setAge(10);
            } catch (NotPossibleAgeException e) {
                //pass
            }
            team1.addPlayers(player1);
            league.addTeam(team1);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            try {
                league = reader.read();
            } catch (NotPossibleAgeException e) {
                e.printStackTrace();
            } catch (NotPossibleGoalsOrAssistsException e) {
                e.printStackTrace();
            }
            assertEquals(1, league.getTeams().size());
            checkPlayer(player1,"Messi",10,0,0);
            checkTeam(team1,"Barcelona");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            Team team = new Team();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom2.json");
            writer.open();
            writer.writeTeam(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom2.json");
            try {
                team = reader.readTeam();
            } catch (NotPossibleAgeException e) {
                e.printStackTrace();
            } catch (NotPossibleGoalsOrAssistsException e) {
                e.printStackTrace();
            }
            assertEquals(0, team.getPlayers().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
