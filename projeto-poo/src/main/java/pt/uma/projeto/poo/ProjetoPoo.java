package pt.uma.projeto.poo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.time.LocalDate;
import java.util.Arrays;

public class ProjetoPoo {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        // create for me a simple league with teams, players, coach, whatever for debugging
        var league = new League(1, "Liga NOS");
        var stadium = new Stadium(1, "Estádio do Dragão", "Porto", "Rua do Dragão", 50000);
        var coach = new Coach(1, "coach1", "José", "Mourinho", "Portuguese", 180, 80, "1960-05-20", "4-4-2");
        var team = new Team(1, "FC Porto", "FCP", 1888, stadium, coach);
        var player = new Player(1, "player1", "João", "Fernandes", "Portuguese", 180, 80, "1999-05-20", "Attacker", 76);
        var player2 = new Player(2, "player2", "Antonio", "Franciscano", "Portuguese", 170, 33, "2001-06-05", "Attacker", 55);
        var player3 = new Player(3, "player3", "Alberto", "Norb", "Portuguese", 133, 70, "1989-05-31", "Attacker", 33);
        team.addPlayer(player);
        team.addPlayer(player2);
        team.addPlayer(player3);

        try {
            System.out.println(gson.toJson(team));
        } catch (JsonIOException ex) {
            System.out.println("Error writing file");
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}
