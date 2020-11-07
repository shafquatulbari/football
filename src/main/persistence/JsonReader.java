package persistence;

import model.League;
import model.Player;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads League from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads Team from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team readTeam() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }



    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses League from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        League league = new League();
        addTeams(league, jsonObject);
        return league;
    }

    // EFFECTS: parses Team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        Team team = new Team();
        addPlayers(team, jsonObject);
        return team;
    }

    // MODIFIES: league
    // EFFECTS: parses teams from JSON object and adds them to League
    private void addTeams(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(league, nextTeam);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses Team from JSON object and adds it to League
    private void addTeam(League league, JSONObject jsonObject) {
        String name = jsonObject.getString("TeamName");
        Team team = new Team();
        team.setName(name);
        league.addTeam(team);
        addPlayers(team,jsonObject);
    }

    // MODIFIES: team
    // EFFECTS: parses Players from JSON object and adds them to Team
    private void addPlayers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(team, nextPlayer);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses Player from JSON object and adds it to League
    private void addPlayer(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        int age = jsonObject.getInt("Age");
        int assists = jsonObject.getInt("Assists");
        int goals = jsonObject.getInt("Goals");
        Player player = new Player();
        player.setAge(age);
        player.setGoals(goals);
        player.setAssists(assists);
        player.setName(name);
        team.addPlayers(player);
    }

}
