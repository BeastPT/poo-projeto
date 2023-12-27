package uma.footballmanager;

import java.util.ArrayList;
import java.util.Scanner;


public class Coach extends Person implements IMenuData {
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
    
    public String tactic() {
        return tactic;
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
    public static Coach generateCoach() {
        Person person = Person.generatePerson();

        System.out.println("Digite a tática utilizada (por exemplo 4-3-3, 4-4-2):");
        Scanner sc = new Scanner(System.in);
        String tactic = sc.nextLine().trim();

        while (!isValidTactic(tactic)) {
            System.out.println("A tática é inválida. Por favor, digite novamente:");
            tactic = sc.nextLine().trim();
        }

        return new Coach(person.getName(), person.getFirstName(), person.getLastName(), person.getBirth(), person.getNationality(), person.getHeight(), person.getWeight(), tactic);
    }

    private static boolean isValidTactic(String tactic) {
        String[] parts = tactic.split("-");
        int sum = 0;

        for (String part : parts) {
            try {
                int number = Integer.parseInt(part);
                sum += number;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return sum == 10;
    }
}