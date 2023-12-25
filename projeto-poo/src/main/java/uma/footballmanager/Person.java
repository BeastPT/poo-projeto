package uma.footballmanager;

import com.google.gson.annotations.SerializedName;
import java.util.Scanner;

public class Person {
    private String name;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    private Birth birth;
    private String nationality;
    private Integer height;
    private Integer weight;

    public Person(String name, String firstName, String lastName, Birth birth, String nationality, Integer height, Integer weight) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Birth getBirth() {
        return birth;
    }

    public String getNationality() {
        return nationality;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public static Person generatePerson() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escreva o seu nick name:")
        String name = sc.nextLine():

        System.out.println("Escreva o seu ultimo nome:");
        String lastName = sc.nextLine();

        System.out.println("Escreva o seu primeiro nome:");
        String firstName = sc.nextLine();

        System.out.println("Escreva a sua data de nascimento (no formato YYYY-MM-DD):");
        String dateOfBirthString = sc.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);

        System.out.println("Escreva a sua nacionalidade:");
        String nationality = sc.nextLine();

        System.out.println("Digite qual a sua altura:");
        Integer height = sc.nextInt();

        System.out.println("Digite qual o seu peso:");
        Integer weight = sc.nextInt();

        sc.close();

        return new Person(name, firstName, lastName, birth, nationality, height, weight);
    }
}
