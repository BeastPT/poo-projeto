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
    private int height;
    private int weight;
}
