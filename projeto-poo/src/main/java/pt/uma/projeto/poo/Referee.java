package pt.uma.projeto.poo;

import java.time.LocalDate;

public class Referee extends Person{
    private int quality;

    public Referee(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, String birthDate, int quality) {
        super(id, nick, firstName, lastName, nationality, height, weight, birthDate);
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
