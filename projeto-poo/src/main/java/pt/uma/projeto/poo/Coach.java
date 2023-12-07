package pt.uma.projeto.poo;

public class Coach extends Person{

    private String tactic;

    public Coach(int id, String nick, String firstName, String lastName, String nationality, int height, int weight, String birthDate, String tactic) {
        super(id, nick, firstName, lastName, nationality, height, weight, birthDate);
        this.tactic = tactic;
    }

    public String getTactic() {
        return tactic;
    }

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }
}
