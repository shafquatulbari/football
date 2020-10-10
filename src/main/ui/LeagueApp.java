package ui;

import model.League;
import model.Player;
import model.Team;

import java.util.List;
import java.util.Scanner;

public class LeagueApp {
    //This class helps us to run the application from the MAIN class and is contained in the UI package.
    //Took help from the TellerApp to get idea for some of the methods implemented below.
    private Team team;
    private League league;
    private Player player;

    //EFFECTS: Constructs League App which creates a league object so that the application runs and
    // other methods can be executed inside.
    public  LeagueApp() {
        Scanner in = new Scanner(System.in);
        league = new League();
        System.out.println("quit or play? q/p");
        String decision = in.next();
        while (!decision.equals("q")) {
            decision();
            System.out.println("quit or keep playing? q/p");
            decision = in.next();
        }

    }

    //EFFECTS: takes input and decides what to do from the display menu.
    public void decision() {
        Scanner in = new Scanner(System.in);
        displayMenu();
        int option = in.nextInt();
        processCommand(option);
    }

    //EFFECTS: shows all the user stories and what the user wants to do.
    private void displayMenu() {
        System.out.println("\nSelect any of the options");
        System.out.println("\t1--> Add team.");
        System.out.println("\t2-->select a team and add players to the team.");
        System.out.println("\t3-->select a team and view a list of the players on that team.");
        System.out.println("\t4-->select a player on a team and add a new statistic for that player.");
        System.out.println("\t5-->show all the teams.");
    }

    //EFFECTS: selects the options from the user stories.
    private void processCommand(int option) {
        if (option == 1) {
            createAndAddTeam();
        } else if (option == 2) {
            selectTeamAndAddPlayers();
        } else if (option == 3) {
            selectTeamAndViewPlayers();
        } else if (option == 4) {
            selectPlayerAndAddStats();
        } else if (option == 5) {
            showAllTeams();
        } else {
            System.out.println("Selection not valid.");
        }
    }

    //EFFECTS: creates a team, names it, and adds it to an arraylist(inside a method in the League class).
    private void createAndAddTeam() {
        Scanner in = new Scanner(System.in);
        team = new Team();
        System.out.println("Name to create a team:");
        String name = in.nextLine();
        team.setName(name);
        league.addTeam(team);
    }

    //EFFECTS: selects a team and add a player with name to the team.
    private void selectTeamAndAddPlayers() {
        Scanner in = new Scanner(System.in);
        team = selectTeam();
        System.out.println("Add Player and name the player:");
        String name = in.nextLine();
        player = new Player();
        player.setName(name);
        team.addPlayers(player);

    }

    //EFFECTS: selects a team and shows all the players in the team.
    private void selectTeamAndViewPlayers() {
        team = selectTeam();
        showAllPlayers(team);
    }

    //EFFECTS: selects a player and modify its stats.
    private void selectPlayerAndAddStats() {
        Scanner in = new Scanner(System.in);
        player = selectPlayer();
        System.out.println("Let's start adding stat with number of goals:");
        player.setGoals(in.nextInt());
        System.out.println("Number of assists:");
        player.setAssists(in.nextInt());
        System.out.println("Position:");
        player.setPosition(in.nextLine());
        System.out.println("Age of Player:");
        player.setAge(in.nextInt());

    }

    //EFFECTS: selects and returns that Team.
    private Team selectTeam() {
        Scanner in = new Scanner(System.in);
        System.out.println("Select a team name:");
        String name = in.nextLine();
        for (Team team : league.getTeams()) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return team;
    }

    //EFFECTS: selects and returns that player.
    private Player selectPlayer() {
        Scanner in = new Scanner(System.in);
        System.out.println("Select a player name:");
        String name = in.nextLine();
        for (Player player: team.getPlayers()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return player;
    }

    //EFFECTS: prints out all the players stats of a team.
    private void showAllPlayers(Team team) {
        List<Player> players = team.getPlayers();
        int i = 0;
        for (Player player: players) {
            i++;
            System.out.println(i + ". " + player.playerInfo() + " \n");
        }
    }

    //EFFECTS: prints out all the teams in the league.
    private void showAllTeams() {
        List<Team> teams = league.getTeams();
        int i = 0;
        for (Team team: teams) {
            i++;
            System.out.println(i + ". " + team.getName() + " \n");
        }
    }


}
