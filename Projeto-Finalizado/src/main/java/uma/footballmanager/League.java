package uma.footballmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class League implements IMenuData {
    private final String name;
    private final String country;
    private final ArrayList<Team> teams;
    private final ArrayList<Match> matches;
    private final ArrayList<Referee> referees;

    public League(String name, String country, ArrayList<Team> teams, ArrayList<Match> matches, ArrayList<Referee> referees) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = matches;
        this.referees = referees;
    }

    public League(String name, String country, ArrayList<Team> teams, ArrayList<Match> matches) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = matches;
        this.referees = new ArrayList<>();
    }

    public League(String name, ArrayList<Team> teams, String country, ArrayList<Referee> referees) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = new ArrayList<>();
        this.referees = referees;
    }

    public League(String name, String country, ArrayList<Team> teams) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = new ArrayList<>();
        this.referees = new ArrayList<>();
    }

    public League(String name, String country) {
        this.name = name;
        this.country = country;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.referees = new ArrayList<>();
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    public ArrayList<Match> getMatches() {
        return new ArrayList<>(matches);
    }

    public ArrayList<Match> getMatchesUnfinished() {
        return new ArrayList<>(matches.stream().filter(match -> !match.isFinished()).toList());
    }

    public ArrayList<Referee> getReferees() {
        return new ArrayList<>(referees);
    }


    public void replaceTeam(Team previousTeam, Team team) {
        int index = teams.indexOf(previousTeam);
        teams.set(index, team);
    }

    /**
     * Gera um jogo entre duas equipas e adiciona o jogo a lista de jogos
     * @param team1 Equipa 1
     * @param team2 Equipa 2
     */
    private void generateMatch(Team team1, Team team2) {
        if (team1.equals(team2)) return;

        LocalDate matchHDate = DateManager.getRandomDate();
        LocalDate matchVDate = DateManager.getRandomDate();

        Match match = new Match(team1, team2, matchHDate, Utils.getRandomInt(15, 21), 0, getReferee());
        addMatch(match);
        Match match2 = new Match(team2, team1, matchVDate, Utils.getRandomInt(15, 21), 0, getReferee());
        addMatch(match2);
    }

    /**
     * Gera todos os jogos entre todas equipas da liga
     */
    public void generateMatches() {
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team team1 = teams.get(i);
                Team team2 = teams.get(j);
                generateMatch(team1, team2);
            }
        }
        sortMatchByDate();
    }

    /**
     * Gera todos os jogos entre uma equipa e todas as outras equipas da liga
     * @param team Equipa
     */
    private void generateMatches(Team team) {
        for (Team team1 : teams) {
            generateMatch(team, team1);
        }
        sortMatchByDate();
    }

    /**
     * Ordena os jogos por data
     */
    private void sortMatchByDate() {
        matches.sort((o1, o2) -> {
            if (o1.getDate().isBefore(o2.getDate())) {
                return -1;
            } else if (o1.getDate().isAfter(o2.getDate())) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    @Override
    public void showData() {
        System.out.println("Nome da Liga: " + name);
        System.out.println("Pais: " + country);
        System.out.println("Equipas: ");
        System.out.println("Selecione uma das seguintes equipas: ");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println(i + 1 + " - " + team.getName() + " - " + team.getCoach().getName());
        }
        System.out.println(teams.size() + 1 + " - Voltar");
    }

    public void showMatchs(ArrayList<Match> sMatchs) {
        System.out.println("Jogos da Liga: " + name);
        System.out.println("Pais: " + country);
        System.out.println("Jogos: ");
        System.out.println("Selecione um dos seguintes jogos: ");
        if (sMatchs.isEmpty()) {
            System.out.println("Não existem jogos para mostrar");
        }
        for (int i = 0; i < sMatchs.size(); i++) {
            Match match = sMatchs.get(i);
            System.out.println(i + 1 + " - " + match.getHomeTeam().getName() + " - " + match.getVisitingTeam().getName());
        }

        System.out.println(sMatchs.size() + 1 + " - Voltar");
    }

    public void addReferee(Referee referee) {
        this.referees.add(referee);
    }

    /**
     * Coleta os jogos de uma equipa, se allMatchs for true, coleta todos os jogos, se não coleta apenas os jogos não terminados
     * @param team Equipa
     * @param allMatchs Todos os jogos
     * @return Lista de Jogos
     */
    public ArrayList<Match> getMatches(Team team, boolean allMatchs) {
        if (allMatchs)
            return matches.stream()
                    .filter(match -> match.getHomeTeam().equals(team) || match.getVisitingTeam().equals(team))
                    .collect(Collectors.toCollection(ArrayList::new));
        else
            return getMatchesUnfinished().stream()
                    .filter(match -> match.getHomeTeam().equals(team) || match.getVisitingTeam().equals(team))
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    public String getCountry() {
        return country;
    }

    public Team getTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    public Referee getReferee(String refereeName) {
        for (Referee referee : referees) {
            if (referee.getName().equals(refereeName)) {
                return referee;
            }
        }
        return null;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        generateMatches(team);
    }

    public void showReferees() {
        System.out.println("Árbitros da Liga: " + name);
        System.out.println("Pais: " + country);
        System.out.println("Árbitros: ");
        System.out.println("Selecione um dos seguintes árbitros: ");
        for (int i = 0; i < referees.size(); i++) {
            Referee referee = referees.get(i);
            System.out.println(i + 1 + " - " + referee.getName());
        }
        System.out.println(referees.size() + 1 + " - Voltar");
    }

    public Referee getReferee() {
        return referees.get(Utils.getRandomInt(0, referees.size() - 1));
    }

    public static League generateLeague() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome da liga:");
        String name = sc.nextLine();

        System.out.println("Digite o paÃ­s da liga:");
        String country = sc.nextLine();

        return new League(name, country);
    }
}