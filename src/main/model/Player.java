package model;

public class Player {

    int age;
    String position;
    String name;
    int goals;
    int assists;


    public Player() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String playerInfo() {
        return "Player name: " + name + ". position: " + position
                + ". age:" + age + ". GOALS: " + goals + ". ASSISTS: " + assists;
    }
}
