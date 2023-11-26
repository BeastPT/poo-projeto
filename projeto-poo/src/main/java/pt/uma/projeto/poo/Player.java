package pt.uma.projeto.poo;

import java.time.LocalDate;

public class Player extends Person{

    private boolean injured;
    private String position;
    private int attack;
    private int defense;

    public Player(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, LocalDate birthDate, String position) {
        super(id, nick, firstName, lastName, nationality, height, weight, birthDate);
        this.position = position;
    }
}
