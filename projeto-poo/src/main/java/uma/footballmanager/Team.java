package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Team {
    private String name;
    private String code;
    @SerializedName("founded")
    private int foundationYear;
    private Stadium stadium;
    private Coach coach;
    private ArrayList<Player> players;
}
