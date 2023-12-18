package uma.footballmanager;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;

import java.time.LocalDate;

public class CoachCareer {

    @SerializedName("team_name")
    private String team;
    private LocalDate start;
    private LocalDate end;
}
