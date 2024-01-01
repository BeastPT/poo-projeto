package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Scanner;

public class Person implements IMenuData {
    private final String name;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    private final Birth birth;
    private final String nationality;
    private final Integer height;
    private final Integer weight;

    public Person(String name, String firstName, String lastName, Birth birth, String nationality, Integer height, Integer weight) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
    }

    public Person(String name, String firstName, String lastName, Birth birth, String nationality) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.nationality = nationality;
        this.height = null;
        this.weight = null;
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

        System.out.println("Escreva o seu nickname:");
        String name = sc.nextLine();

        while (name.trim().isEmpty()) {
            System.out.println("Nome inválido. Insira novamente o seu nickname:");
            name = sc.nextLine();
        }

        System.out.println("Escreva o seu primeiro nome:");
        String firstName = sc.nextLine();

        while (firstName.trim().isEmpty()) {
            System.out.println("Primeiro nome inválido. Insira novamente o seu primeiro nome:");
            firstName = sc.nextLine();
        }

        System.out.println("Escreva o seu ultimo nome:");
        String lastName = sc.nextLine();

        while (lastName.trim().isEmpty()) {
            System.out.println("Ultimo nome inválido. Insira novamente o seu ultimo nome:");
            lastName = sc.nextLine();
        }

        System.out.println("Escreva a sua data de nascimento (no formato AAAA-MM-DD):");
        String dateString = sc.nextLine();

        LocalDate date = null;
        while (date == null) {
            try {
                date = LocalDate.parse(dateString);
            } catch (Exception e) {
                System.out.println("Data de nascimento inválida. Por favor, insira novamente (no formato AAAA-MM-DD):");
                dateString = sc.nextLine();
            }
        }

        System.out.println("Escreva o país de nascimento:");
        String country = sc.nextLine();

        while (country.trim().isEmpty()) {
            System.out.println("Inválido, coloque novamente o pais onde nasceu");
            country = sc.nextLine();
        }

        System.out.println("Escreva o local de nascimento:");
        String place = sc.nextLine();

        while (place.trim().isEmpty()) {
            System.out.println("Inválido, coloque novamente o local onde nasceu:");
            place = sc.nextLine();
        }

        Birth birth = new Birth(date, country, place);

        System.out.println("Escreva a sua nacionalidade:");
        String nationality = sc.nextLine();

        while (nationality.trim().isEmpty()) {
            System.out.println("Nacionalidade incorreta. Insira novamente a sua nacionalidade:");
            nationality = sc.nextLine();
        }

        System.out.println("Digite qual é a sua altura (cm) em numero inteiro:");
        Integer height = null;

        while (height == null) {
            try {
                height = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Altura inválida. Por favor coloque a sua altura em numero inteiro por exemplo(170):");
                sc.nextLine();
            }
        }

        System.out.println("Digite qual o seu peso:");
        Integer weight = null;

        while (weight == null) {
            try {
                weight = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Por favor insira o seu peso corretamente em número inteiro:");
                sc.nextLine();
            }
        }

        return new Person(name, firstName, lastName, birth, nationality, height, weight);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void showData() {
        System.out.println("Nome: " + name);
        System.out.println("Primeiro nome: " + firstName);
        System.out.println("Ultimo nome: " + lastName);
        System.out.println("Data de nascimento: " + birth.date());
        System.out.println("Local de nascimento: " + birth.place());
    }
}
