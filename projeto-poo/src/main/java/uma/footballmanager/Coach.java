package uma.footballmanager;

import java.util.ArrayList;

public class Coach extends Person implements MenuData {
    private String tactic;
    private ArrayList<CoachCareer> career;

    public Coach(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, String tactic, ArrayList<CoachCareer> career) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.tactic = tactic;
        this.career = career;
    }

    @Override
    public void showData() {
        System.out.println("Nome: " + name);
        System.out.println("Data de Nascimento: " + birth);
        System.out.println("Nacionalidade: " + nationality);
        System.out.println("Altura: " + height);
        System.out.println("Peso: " + weight);
        System.out.println("Tatica utilizada: " + tactic);
        System.out.println("Carreira: " + career);
    }
}