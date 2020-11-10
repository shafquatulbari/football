package model;

import org.json.JSONObject;
import persistence.Writable;

public class Player implements Writable, Comparable<Player> {
 //This class represents a Football Player's stats and has all the attributes and behaviours associated with it.
    int age;
    String name;
    int goals;
    int assists;

    //EFFECTS: creates an empty constructor for Player
    public Player() {

    }

    //EFFECTS: returns the age of the player.
    public int getAge() {
        return age;
    }

    //REQUIRES: age > 0
    //MODIFIES: this
    //EFFECTS: sets this.age to age.
    public void setAge(int age) {
        if (age < 0) {
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    //EFFECTS: returns the name of the player.
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: sets this.name to name.
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: returns the goals scored.
    public int getGoals() {
        return goals;
    }

    //MODIFIES: this
    //EFFECTS: sets this.goal to goal.
    public void setGoals(int goals) {
        this.goals = goals;
    }

    //EFFECTS: returns the no. of assists.
    public int getAssists() {
        return assists;
    }

    //MODIFIES: this
    //EFFECTS: sets this.assists to assists.
    public void setAssists(int assists) {
        this.assists = assists;
    }

    //EFFECTS: returns the String with all the stats of a player.
    public String playerInfo() {
        return "Player name: " + name + ". age: " + age + ". GOALS: " + goals + ". ASSISTS: " + assists;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name",name);
        json.put("Assists",assists);
        json.put("Goals",goals);
        json.put("Age",age);
        return json;
    }

    @Override
    public int compareTo(Player o) {
        if (this.getGoals() + this.getAssists() > o.getGoals() + o.getAssists()) {
            return 1;
        } else if (this.getGoals() + this.getAssists() < o.getGoals() + o.getAssists()) {
            return -1;
        }
        return 0;
    }

}
