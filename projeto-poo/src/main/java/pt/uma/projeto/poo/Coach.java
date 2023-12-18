package pt.uma.projeto.poo;

import java.util.ArrayList;

public class Coach extends Person{

    private String tactic;
    private ArrayList<CoachCareer> career;
    public Coach(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, String birthDate, String tactic, ArrayList<CoachCareer> career) {
        super(nick, firstName, lastName, nationality, height, weight, birthDate);
        this.tactic = tactic;
        this.career = career;
    }
    public Coach(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, String birthDate, String tactic) {
        super(nick, firstName, lastName, nationality, height, weight, birthDate);
        this.tactic = tactic;
        career = new ArrayList<>();
    }

    public String getTactic() {
        return tactic;
    }

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }
}
