package uma.footballmanager;

import java.time.LocalDate;

public class DateManager {
    private static LocalDate currentDate;
    private static Game game;

    static {
        currentDate = LocalDate.of(2023, 8, 18);
    }

    private static void setCurrentDate(LocalDate date) {
        currentDate = date;
    }

    public static void setGame(Game game) {
        DateManager.game = game;
        setCurrentDate(game.getCurrentDate());
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static LocalDate addWeek() {
        currentDate = currentDate.plusWeeks(1);
        game.setCurrentDate(currentDate);
        return currentDate;
    }

    public static LocalDate getRandomDate() {
        LocalDate endSeason = LocalDate.of(2024, 5, 31);
        var dates = currentDate.datesUntil(endSeason).toList();
        return dates.get(Utils.getRandomInt(0, dates.size()-1));
    }
}
