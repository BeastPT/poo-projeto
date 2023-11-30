package pt.uma.projeto.poo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Player extends Person{

    private record  Injure(LocalDate when, LocalDate end) {}
    private boolean injured;

    private ArrayList<Injure> injuries;
    private String position;
    private int attack;
    private int defense;

    public Player(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, String birthDate, String position) {
        super(id, nick, firstName, lastName, nationality, height, weight, birthDate);
        this.position = position;
    }

    public void addInjury(int daysToEnd) {
        LocalDate currentDate = LocalDate.now();
        injuries.add(new Injure(currentDate, LocalDate.of(currentDate.getMonthValue(), currentDate.getMonthValue(), currentDate.getDayOfMonth()+daysToEnd)));
    }

    public boolean isInjured() {
        return injured;
    }

    public ArrayList<Injure> getInjuries() {
        return injuries;
    }

    public Injure getLastInjure() {
        return injuries.get(injuries.size()-1);
    }

    @Override
    public String toString() {
        return super.toString() + " com posição " + position + " e com " + attack + " de ataque e " + defense + " de defesa";
    }
}
