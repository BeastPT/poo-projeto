package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;

public class Game {
    private final ArrayList<League> leagues;
    @SerializedName("current_date")
    private LocalDate currentDate;

    private User user;
    public Game(ArrayList<League> leagues, LocalDate currentDate) {
        this.leagues = leagues;
        this.currentDate = currentDate;
        // TODO: Caso seja um novo jogo, pedir o nome do utilizador, escolher a liga e a equipa (POR NOMES)
    }

    public Game(ArrayList<League> leagues, LocalDate currentDate, User user) {
        this.leagues = leagues;
        this.currentDate = currentDate;
        this.user = user;
        setUserAsCoach();
    }

    private void setUserAsCoach() {
        for (League league : this.leagues) {
            if (!league.getName().equals(this.user.getUserLeague())) continue;
            for (Team team : league.getTeams()) {
                if (team.getName().equals(this.user.getUserTeam())) {
                    team.setCoach(this.user);
                }
            }
        }
    }

    public Game() {
        this.leagues = new ArrayList<>();
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }

    public void printLeagues() {
        for (League league : this.leagues) {
            System.out.println(league.getName());
        }
    }

    public ArrayList<League> getLeagues() {
        return new ArrayList<>(leagues);
    }

    public LocalDate getCurrentDate() {
        return DateManager.getCurrentDate();
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}
