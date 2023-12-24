package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;

public class Game {
    private final ArrayList<League> leagues;
    @SerializedName("current_date")
    private LocalDate currentDate;
    @SerializedName("username")
    private String username;
    @SerializedName("user_league")
    private String userLeague;
    @SerializedName("user_team")
    private String userTeam;
    public Game(ArrayList<League> leagues, LocalDate currentDate) {
        this.leagues = leagues;
        this.currentDate = currentDate;
        // TODO: Caso seja um novo jogo, pedir o nome do utilizador, escolher a liga e a equipa (POR NOMES)
    }

    public Game(ArrayList<League> leagues, LocalDate currentDate, String username, String userLeague, String userTeam) {
        this.leagues = leagues;
        this.currentDate = currentDate;
        this.username = username;
        this.userLeague = userLeague;
        this.userTeam = userTeam;
    }

    public Game() {
        this.leagues = new ArrayList<>();
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
        return leagues;
    }
}
