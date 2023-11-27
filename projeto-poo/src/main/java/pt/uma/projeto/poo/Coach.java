package pt.uma.projeto.poo;

import java.time.LocalDate;

public class Coach extends Person{

    private String tatic;

    public Coach(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, LocalDate birthDate, String tatic) {
        super(id, nick, firstName, lastName, nationality, height, weight, birthDate);
        this.tatic = tatic;
    }

    public String getTatic() {
        return tatic;
    }

    public void setTatic(String tatic) {
        this.tatic = tatic;
    }
}
