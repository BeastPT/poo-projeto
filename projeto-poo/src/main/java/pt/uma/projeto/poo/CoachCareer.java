package pt.uma.projeto.poo;

import java.time.LocalDate;

public class CoachCareer {
    private final String teamName;
    private final LocalDate startDate;
    private LocalDate endDate;

    public CoachCareer(String teamName, LocalDate startDate) {
        this.teamName = teamName;
        this.startDate = startDate;
    }

    public CoachCareer(String teamName, LocalDate startDate, LocalDate endDate) {
        this.teamName = teamName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void endDate(){
        this.endDate = LocalDate.now();
    }
}
