package pt.uma.projeto.poo;

public class Team {
    private int id;
    private String name;
    private String code;
    private String city;
    private int foundationYear;
    private Stadium stadium;
    private Coach coach;
    private Player[] players = new Player[11];

    public Team(int id, String name, String code, String city, int foundationYear, Stadium stadium, Coach coach, Player[] players) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
        this.foundationYear = foundationYear;
        this.stadium = stadium;
        this.coach = coach;
        this.players = players;
    }
}
