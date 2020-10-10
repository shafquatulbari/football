package ui;

import model.League;
import model.Player;
import model.Team;

import java.util.List;
import java.util.Scanner;

public class LeagueApp {
    private Team team;
    private League league;
    private Player player;

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

    public void decision() {
        Scanner in = new Scanner(System.in);
        displayMenu();
        int option = in.nextInt();
        processCommand(option);
    }


    private void displayMenu() {
        System.out.println("\nSelect any of the options");
        System.out.println("\t1--> Add team.");
        System.out.println("\t2-->select a team and add players to the team.");
        System.out.println("\t3-->select a team and view a list of the players on that team.");
        System.out.println("\t4-->select a player on a team and add a new statistic for that player.");
        System.out.println("\t5-->show all the teams.");
    }

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


    private void createAndAddTeam() {
        Scanner in = new Scanner(System.in);
        team = new Team();
        System.out.println("Name to create a team:");
        String name = in.nextLine();
        team.setName(name);
        league.addTeam(team);
    }

    private void selectTeamAndAddPlayers() {
        Scanner in = new Scanner(System.in);
        team = selectTeam();
        System.out.println("Add Player and name the player:");
        String name = in.nextLine();
        player = new Player();
        player.setName(name);
        team.addPlayers(player);

    }

    private void selectTeamAndViewPlayers() {
        team = selectTeam();
        showAllPlayers(team);
    }

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

    private void showAllPlayers(Team team) {
        List<Player> players = team.getPlayers();
        int i = 0;
        for (Player player: players) {
            i++;
            System.out.println(i + ". " + player.playerInfo() + " \n");
        }
    }

    private void showAllTeams() {
        List<Team> teams = league.getTeams();
        int i = 0;
        for (Team team: teams) {
            i++;
            System.out.println(i + ". " + team.getName() + " \n");
        }
    }


}
