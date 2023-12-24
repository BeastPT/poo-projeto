package uma.footballmanager;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LeagueAdapter implements JsonSerializer<League>, JsonDeserializer<League> {

    @Override
    public JsonElement serialize(League league, Type type, JsonSerializationContext context) {
        JsonObject League = new JsonObject();
        League.addProperty("name", league.getName());
        League.addProperty("country", league.getCountry());
        JsonArray teams = new JsonArray();
        for (Team team : league.getTeams()) {
            teams.add(context.serialize(team, Team.class));
        }
        League.add("teams", teams);
        JsonArray matches = new JsonArray();
        for (Match match : league.getMatches()) {
            matches.add(context.serialize(match, Match.class));
        }
        League.add("matches", matches);
        return League;
    }
    @Override
    public League deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject League = jsonElement.getAsJsonObject();
        String name = League.get("name").getAsString();
        String country = League.get("country").getAsString();
        JsonArray teams;
        if (League.has("teams")) {
            teams = League.get("teams").getAsJsonArray();
        } else {
            teams = new JsonArray();
        }
        JsonArray matches;
        if (League.has("matches")) {
            matches = League.get("matches").getAsJsonArray();
        } else {
            matches = new JsonArray();
        }

        ArrayList<Team> teamsList = new ArrayList<>();
        ArrayList<Match> matchesList = new ArrayList<>();
        for (JsonElement team : teams) {
            teamsList.add(context.deserialize(team, Team.class));
        }
        League league = new League(name, country, teamsList);
        for (JsonElement match : matches) {
            Match newMatch = context.deserialize(match, Match.class);
            newMatch.setHomeTeam(league.getTeam(newMatch.getHomeTeam().getName()));
            newMatch.setVisitingTeam(league.getTeam(newMatch.getVisitingTeam().getName()));
            matchesList.add(newMatch);
        }
        return new League(name, country, teamsList, matchesList);
    }

}
