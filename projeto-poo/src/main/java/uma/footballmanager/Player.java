package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

public class Player extends Person{
    @SerializedName("statistics")
    private PlayerStats stats;
    private Positions position;
}
