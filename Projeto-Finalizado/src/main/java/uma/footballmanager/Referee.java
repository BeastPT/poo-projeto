package uma.footballmanager;

import java.util.Scanner;

public class Referee extends Person implements IMenuData {
    private final int experience;

    public Referee(String name, String firstName, String lastName, Birth birth, String nationality, Integer height, Integer weight, int experience) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public void showData() {
        super.showData();
        System.out.println("Experiência: " + experience);
        System.out.println("1 - Voltar");
    }

    /**
     * Gera um árbitro com base nos dados inseridos pelo utilizador
     * @return Árbitro
     */
    public static Referee generateReferee() {
        Person person = Person.generatePerson();

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a experiência do árbitro (0-100):");
        int experience = sc.nextInt();
        sc.nextLine();


        return new Referee(person.getName(), person.getFirstName(), person.getLastName(), person.getBirth(), person.getNationality(), person.getHeight(), person.getWeight(), experience);
    }
}
