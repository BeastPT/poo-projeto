package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

public class Player extends Person{
    @SerializedName("statistics")
    private PlayerStats stats;
    private Positions position;

    private boolean injured;

    public Player(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, PlayerStats stats, Positions position, boolean injured) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.stats = stats;
        this.position = position;
        this.injured = injured;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public Positions getPosition() {
        return position;
    }

    public boolean isInjured() {
        return injured;
    }
}
