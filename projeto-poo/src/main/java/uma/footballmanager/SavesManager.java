package uma.footballmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class SavesManager {
    private static Integer currentGameId;
    private final static Gson gson;
    public static void createGame() {
        loadData("singleJson");
    }

    static {
        currentGameId = null;
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(League.class, new LeagueAdapter())
                .registerTypeAdapter(Match.class, new MatchAdapter())
                .create();
    }

    public static Game loadData(String fileName) {
        StringBuilder json = new StringBuilder();
        try {
            var path = Paths.get(System.getProperty("user.dir") + "\\projeto-poo\\saves\\"+fileName+".json");
            var file = Files.newBufferedReader(
                    path,
                    Charset.defaultCharset());
            String newLine;
            while ((newLine = file.readLine()) != null) {
                json.append(newLine);
            }
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentGameId = getGameId(fileName);
        return gson.fromJson(json.toString(), Game.class);
    }

    public static String saveGame(Game game) {
        return gson.toJson(game);
    }

    private static Integer getGameId(String FileName) {
        Pattern pattern = Pattern.compile("(\\d+)");
        var matcher = pattern.matcher(FileName);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }
}
