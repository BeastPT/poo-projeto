package pt.uma.projeto.poo;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private int id;
    private String nick;
    private String firstName;
    private String lastName;
    private String nationality;
    private int height;
    private int weight;
    private LocalDate birthDate;
    private int age;

    private void setAge() {
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Person(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, LocalDate birthDate) {
        this.id = id;
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
        setAge();
    }
}
