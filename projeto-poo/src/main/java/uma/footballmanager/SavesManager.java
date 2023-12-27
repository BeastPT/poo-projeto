package uma.footballmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SavesManager {
    private static String currentGameId;
    private final static Gson gson;
    private final static String EXTENSION = ".json";
    private final static String PATH = System.getProperty("user.dir") + "\\projeto-poo\\saves\\";

    private final static String DEFAULT_GAME = "defaultGame";

    static {
        currentGameId = null;
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonAdapter.LocalDateAdapter())
                .registerTypeAdapter(League.class, new JsonAdapter.LeagueAdapter())
                .registerTypeAdapter(Match.class, new JsonAdapter.MatchAdapter())
                .setPrettyPrinting()
                .create();

    }
    /**
     * Cria um novo jogo com os dados do ficheiro default
     * @return Uma instância do Jogo
     */
    public static Game createGame() {
        return loadData(DEFAULT_GAME);
    }

    /**
     * Carrega o jogo a partir de um ficheiro com um certo nome
     * @param fileName String
     * @return Uma instância do Jogo
     */
    public static Game loadData(String fileName) {
        StringBuilder json = new StringBuilder();
        try {
            var path = Paths.get(PATH+fileName+EXTENSION);
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

    /**
     * Armazena o jogo na pasta predefinida
     * @param game Jogo a ser guardado
     */
    public static void saveGame(Game game) {
        String fileName = currentGameId + "-" + new Timestamp(System.currentTimeMillis()).getTime()+EXTENSION;
        var path = Paths.get(PATH+fileName);
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

    private static Long getGameTimestamp(String FileName) {
        String[] code = FileName.split(Pattern.quote("-"));
        if (code.length > 1) {
            return Long.parseLong(code[1]);
        } else {
            return 0L;
        }
    }

    /**
     * Retorna uma lista com os nomes dos ficheiros de saves
     * @return ArrayList<String>
     */
    private static ArrayList<String> getSavedGames() {
        var path = Paths.get(PATH);
        try {
            return Files.list(path)
                    .filter(p -> Files.isRegularFile(p) && p.getFileName().toString().trim().endsWith(EXTENSION) && !p.getFileName().toString().trim().startsWith(DEFAULT_GAME))
                    .map(p -> p.getFileName().toString().trim().replace(EXTENSION, ""))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna uma lista com os nomes dos ficheiros de saves ordenados por data
     * @return ArrayList<String>
     */
    public static ArrayList<String> getSortedSavedGames() {
        var val = getSavedGames();
        val.sort(Comparator.comparingLong(SavesManager::getGameTimestamp));
        return val;
    }

    /**
     * Mostra o menu de saves
     * @return ArrayList<String>
     */
    public static ArrayList<String> showSavedMenu() {
        var saves = getSortedSavedGames();
        var menu = new StringBuilder();
        if (saves.isEmpty()) {
            menu.append("Não existem saves\n");
        } else {
            menu.append("Escolha um save:\ni - [Game  Id] - [   Data do save  ]\n");
            for (int i = 0; i < saves.size(); i++) {
                String gameId = getGameId(saves.get(i));
                Long timestamp = getGameTimestamp(saves.get(i));
                var dateFormate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String value = dateFormate.format(new Date(timestamp));
                menu.append(i+1).append(" - ").append(gameId).append(" - ").append(value).append("\n");
            }
            menu.append(saves.size()+1).append(" - Voltar\n");

        }
        System.out.println(menu);
        return saves;
    }

    public static Gson getGson() {
        return gson;
    }
}
