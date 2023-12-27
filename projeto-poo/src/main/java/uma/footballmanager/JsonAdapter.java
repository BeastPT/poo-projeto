package uma.footballmanager;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class JsonAdapter {
    final static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public JsonElement serialize(final LocalDate date, final Type typeOfSrc,
                                     final JsonSerializationContext context) {
            return new JsonPrimitive(date.format(formatter));
        }

        @Override
        public LocalDate deserialize(final JsonElement json, final Type typeOfT,
                                     final JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }

    final static class LeagueAdapter implements JsonSerializer<League>, JsonDeserializer<League> {

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

            JsonArray referees = new JsonArray();
            for (Referee referee : league.getReferees()) {
                referees.add(context.serialize(referee, Referee.class));
            }
            League.add("referees", referees);

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

            JsonArray referees;
            if (League.has("referees")) {
                referees = League.get("referees").getAsJsonArray();
            } else {
                referees = new JsonArray();
            }

            ArrayList<Team> teamsList = new ArrayList<>();
            ArrayList<Match> matchesList = new ArrayList<>();
            ArrayList<Referee> refereeList = new ArrayList<>();

            for (JsonElement team : teams) {
                teamsList.add(context.deserialize(team, Team.class));
            }
            for (JsonElement referee : referees) {
                refereeList.add(context.deserialize(referee, Referee.class));
            }
            League league = new League(name, teamsList, country, refereeList);
            for (JsonElement match : matches) {
                Match newMatch = context.deserialize(match, Match.class);
                JsonObject matchObject = match.getAsJsonObject();
                newMatch.setHomeTeam(league.getTeam(matchObject.get("home_team").getAsString()));
                newMatch.setVisitingTeam(league.getTeam(matchObject.get("visiting_team").getAsString()));
                var referee = matchObject.get("referee_name");
                if (referee != null) {
                    newMatch.setReferee(league.getReferee(referee.getAsString()));
                }
                matchesList.add(newMatch);
            }
            return new League(name, country, teamsList, matchesList, refereeList);
        }

    }

    final static class MatchAdapter implements JsonSerializer<Match>, JsonDeserializer<Match> {
        @Override
        public JsonElement serialize(Match match, Type type, JsonSerializationContext context) {
            JsonObject Match = new JsonObject();
            if (match.getHomeTeam() != null) {
                Match.addProperty("home_team", match.getHomeTeam().getName());
            }
            if (match.getVisitingTeam() != null) {
                Match.addProperty("visiting_team", match.getVisitingTeam().getName());
            }
            Match.addProperty("date", context.serialize(match.getDate()).getAsString());
            Match.addProperty("time", match.getMatchHour() + ":" + match.getMatchMinute());
            Match.addProperty("finished", match.isFinished());
            String refereeName = null;
            if (match.getReferee() != null) {
                refereeName = match.getReferee().getName();
            }
            Match.addProperty("referee_name", refereeName);
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
}
