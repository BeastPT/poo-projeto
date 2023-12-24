package uma.footballmanager;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.TreeMap;

public class MatchAdapter implements JsonSerializer<Match>, JsonDeserializer<Match> {
    @Override
    public JsonElement serialize(Match match, Type type, JsonSerializationContext context) {
        JsonObject Match = new JsonObject();
        Match.addProperty("home_team", match.getHomeTeam().getName());
        Match.addProperty("visiting_team", match.getVisitingTeam().getName());
        Match.addProperty("date", context.serialize(match.getDate()).getAsString());
        Match.addProperty("time", match.getMatchHour()+":"+match.getMatchMinute());
        Match.addProperty("finished", match.isFinished());
        JsonObject goals = new JsonObject();
        for (Integer minute : match.getGoals().keySet()) {
            goals.addProperty(minute.toString(), match.getGoals().get(minute));
        }
        JsonObject sufferedGoals = new JsonObject();
        for (Integer minute : match.getSufferedGoals().keySet()) {
            sufferedGoals.addProperty(minute.toString(), match.getSufferedGoals().get(minute));
        }
        Match.add("goals", goals);
        Match.add("suffered_goals", sufferedGoals);
        return Match;
    }

    @Override
    public Match deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject Match = jsonElement.getAsJsonObject();
        LocalDate date = context.deserialize(Match.get("date"), LocalDate.class);
        String[] time = Match.get("time").getAsString().split(":");
        int matchHour = Integer.parseInt(time[0]);
        int matchMinute = Integer.parseInt(time[1]);
        boolean finished = Match.get("finished").getAsBoolean();
        JsonObject goals = Match.get("goals").getAsJsonObject();
        JsonObject sufferedGoals = Match.get("suffered_goals").getAsJsonObject();
        TreeMap<Integer, String> goalsMap = new TreeMap<>();
        TreeMap<Integer, String> sufferedGoalsMap = new TreeMap<>();
        for (String minute : goals.keySet()) {
            goalsMap.put(Integer.parseInt(minute), goals.get(minute).getAsString());
        }
        for (String minute : sufferedGoals.keySet()) {
            sufferedGoalsMap.put(Integer.parseInt(minute), sufferedGoals.get(minute).getAsString());
        }

        return new Match(date, matchHour, matchMinute, finished, goalsMap, sufferedGoalsMap);
    }
}
