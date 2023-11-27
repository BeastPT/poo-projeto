package pt.uma.projeto.poo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Player extends Person{

    public class Injure {

        private final LocalDate when;
        private final LocalDate end;

        public Injure(LocalDate when, LocalDate end) {
            this.when = when;
            this.end = end;
        }

        public LocalDate getWhen() {
            return when;
        }

        public LocalDate getEnd() {
            return end;
        }
    }
    private boolean injured;

    private ArrayList<Injure> injuries;
    private String position;
    private int attack;
    private int defense;

    public Player(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, LocalDate birthDate, String position) {
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

    public Injure getLastInjure() {
        return injuries.get(injuries.size()-1);
    }
}
