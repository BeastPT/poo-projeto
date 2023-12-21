package uma.footballmanager;

public class FootballManager {
    public static void main(String[] args) {
        Game game = SavesManager.loadData("singleJson");
        //game.printLeagues();

        System.out.println(SavesManager.saveGame(game));
    }
}
