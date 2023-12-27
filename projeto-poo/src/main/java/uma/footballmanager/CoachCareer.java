package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class CoachCareer {

    @SerializedName("team_name")
    private String team;
    private final LocalDate start;
    private LocalDate end;

    public CoachCareer(String team, LocalDate start, LocalDate end) {
        this.team = team;
        this.start = start;
        this.end = end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
