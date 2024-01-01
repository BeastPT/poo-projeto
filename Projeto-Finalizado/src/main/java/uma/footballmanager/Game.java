package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;

public class Game implements IMenuData {
    private final ArrayList<League> leagues;
    @SerializedName("current_date")
    private LocalDate currentDate;

    private User user;

    public Game(ArrayList<League> leagues, LocalDate currentDate) {
        this.leagues = leagues;
        this.currentDate = currentDate;
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

    public ArrayList<League> getLeagues() {
        return new ArrayList<>(leagues);
    }

    public LocalDate getCurrentDate() {
        return DateManager.getCurrentDate();
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public void showData() {
        System.out.println("Data atual: " + DateManager.getCurrentDate());
        System.out.println("Ligas:");
        for (int i = 0; i < leagues.size(); i++) {
            League cLeague = leagues.get(i);
            System.out.println(i + 1 + " - " + cLeague.getName() + " - " + cLeague.getCountry());
        }
        System.out.println(leagues.size() + 1 + " - " + "Voltar");
    }
}
