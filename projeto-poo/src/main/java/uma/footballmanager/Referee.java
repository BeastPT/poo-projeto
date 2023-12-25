package uma.footballmanager;

public class Referee extends Person {
    private int experience;
    public Referee(String name, String firstName, String lastName, Birth birth, String nationality, Integer height, Integer weight, int experience) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.experience = experience;
    }

    // TODO: ARMAZENAMENTO DOS REFEREES
    // Provavelmente cria-los numa array na League e depois referencia-los nos Matchs
}
