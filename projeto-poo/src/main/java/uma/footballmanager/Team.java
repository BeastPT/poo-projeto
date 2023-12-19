package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Team {
    private String name;
    private String code;
    @SerializedName("founded")
    private Integer foundationYear;
    private Stadium stadium;
    private Coach coach;
    private ArrayList<Player> players;

    public Team(String name, String code, Integer foundationYear, Stadium stadium, Coach coach, ArrayList<Player> players) {
        this.name = name;
        this.code = code;
        this.foundationYear = foundationYear;
        this.stadium = stadium;
        this.coach = coach;
        this.players = players;
    }
}
