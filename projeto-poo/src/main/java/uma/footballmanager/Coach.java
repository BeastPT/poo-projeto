package uma.footballmanager;

import java.util.ArrayList;

public class Coach extends Person implements MenuData {
    private String tactic;
    private final ArrayList<CoachCareer> career;

    public Coach(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, String tactic, ArrayList<CoachCareer> career) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.tactic = tactic;
        this.career = career;
    }

    public Coach(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, String tactic) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.tactic = tactic;
        this.career = new ArrayList<>();
    }

    @Override
    public void showData() {
        System.out.println("Nome: " + getName());
        System.out.println("Data de Nascimento: " + getBirth().date().getDayOfMonth() + "/" + getBirth().date().getMonth() + "/" + getBirth().date().getYear());
        System.out.println("Nacionalidade: " + getNationality());
        System.out.println("Altura: " + getHeight());
        System.out.println("Peso: " + getWeight());
        System.out.println("Tatica utilizada: " + tactic);
        System.out.println("Carreira: " + career);
    }
}