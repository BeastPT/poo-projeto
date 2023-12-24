package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

public class Player extends Person implements MenuData {
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
        System.out.println("Estatisticas do jogador: " + getStats());
    }
}
