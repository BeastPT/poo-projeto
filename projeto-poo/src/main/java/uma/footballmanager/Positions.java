package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

public enum Positions {
    @SerializedName("Goalkeeper")
    GOALKEEPER,
    @SerializedName("Defender")
    DEFENDER,
    @SerializedName("Midfielder")
    MIDFIELDER,
    @SerializedName("Attacker")
    ATTACKER
}
