package uma.footballmanager;

import java.util.ArrayList;

public class User extends Coach {
    private String userLeague;
    private String userTeam;

    private int gamesWon;
    private int gamesLost;
    private int gamesDraw;

    public User(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, String tactic, String userLeague, String userTeam) {
        super(name, firstName, lastName, birth, nationality, height, weight, tactic);
        this.userLeague = userLeague;
        this.userTeam = userTeam;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesDraw = 0;
    }

    public User(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, String tactic, ArrayList<CoachCareer> career, String userLeague, String userTeam, int gamesWon, int gamesLost, int gamesDraw) {
        super(name, firstName, lastName, birth, nationality, height, weight, tactic, career);
        this.userLeague = userLeague;
        this.userTeam = userTeam;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesDraw = gamesDraw;
    }

    public String getUserLeague() {
        return userLeague;
    }

    public void setUserLeague(String userLeague) {
        this.userLeague = userLeague;
    }

    public String getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    public void addVitory() {
        this.gamesWon++;
    }

    public void addDraw() {
        this.gamesDraw++;
    }

    public void addLost() {
        this.gamesLost++;
    }
}
