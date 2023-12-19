package uma.footballmanager;

import java.util.ArrayList;

public class Coach extends Person{
    private String tactic;
    private ArrayList<CoachCareer> career;

    public Coach(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, String tactic, ArrayList<CoachCareer> career) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.tactic = tactic;
        this.career = career;
    }
}
