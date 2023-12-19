package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

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
}
