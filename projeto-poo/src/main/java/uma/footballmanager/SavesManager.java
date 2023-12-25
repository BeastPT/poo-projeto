package uma.footballmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class SavesManager {
    private static String currentGameId;
    private final static Gson gson;
    private final static String EXTENSION = ".json";
    public static void createGame() {
        loadData("defaultGame");
    }

    static {
        currentGameId = null;
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonAdapter.LocalDateAdapter())
                .registerTypeAdapter(League.class, new JsonAdapter.LeagueAdapter())
                .registerTypeAdapter(Match.class, new JsonAdapter.MatchAdapter())
                .setPrettyPrinting()
                .create();

    }

    public static Game loadData(String fileName) {
        StringBuilder json = new StringBuilder();
        try {
            var path = Paths.get(System.getProperty("user.dir") + "\\projeto-poo\\saves\\"+fileName+EXTENSION);
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

    public static void saveGame(Game game) {
        String fileName = currentGameId + "-" + new Timestamp(System.currentTimeMillis()).getTime()+EXTENSION;
        var path = Paths.get(System.getProperty("user.dir") + "\\projeto-poo\\saves\\"+fileName);
        try {
            var file = Files.newBufferedWriter(
                    path,
                    Charset.defaultCharset(),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            file.write(gson.toJson(game));
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getGameId(String FileName) {
        String[] code = FileName.split(Pattern.quote("-"));
        if (code.length > 1) {
            return code[0];
        } else {
            return Utils.generateRandomString(10);
        }
    }

    public static Gson getGson() {
        return gson;
    }
}
