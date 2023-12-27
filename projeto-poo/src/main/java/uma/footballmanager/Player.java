package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

public class Player extends Person implements IMenuData {
    @SerializedName("statistics")
    private PlayerStats stats;
    private Positions position;

    private boolean injured;

    public Player(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, PlayerStats stats, Positions position) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.stats = stats;
        this.position = position;
        this.injured = false;
    }

    public Player(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, PlayerStats stats, Positions position, boolean injured) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.stats = stats;
        this.position = position;
        this.injured = injured;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public Positions getPosition() {
        return position;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    @Override
    public void showData() {
        System.out.println("Nome do jogador: " + getName());
        System.out.println("Posição: " + getPosition());
        System.out.println("Lesão: " + isInjured());
        System.out.println("Estatísticas do jogador: " + getStats());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getName().equals(player.getName());
    }

     public static Player generatePlayer() {
        Person person = Person.generatePerson();
        Positions position = Positions.generetePosition();
        PlayerStats stats = PlayerStats.genereteEntity();

        return new Player(person.getName(), person.getFirstName(), person.getLastName(),person.getBirth(), person.getNationality(), person.getHeight(), person.getWeight(),stats, position);
    }


}

