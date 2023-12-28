package uma.footballmanager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final Scanner sc;
    private static Game game;

    static {
        sc = new Scanner(System.in);
        game = SavesManager.createGame();
    }

    public static void setGame(Game game) {
        Menu.game = game;
    }

    public static int getUserOption(int maxValue) {
        int userOption;
        while (true) {
            System.out.print("Digite o número da opção desejada:");
            if (sc.hasNextInt()) {
                userOption = sc.nextInt();
                if (userOption >= 1 && userOption <= maxValue) {
                    break;
                } else {
                    System.out.println("Introduza um número entre 1 e " + maxValue);
                }
            } else {
                System.out.println("O seu número tem de ser válido. Insira novamente o valor: ");
                sc.next();
            }
        }
        sc.nextLine();
        return userOption;
    }

    private static int createMenu(String[] menuOptions) {
        int maxValue = menuOptions.length;

        for (int i = 0; i < maxValue; i++) {
            System.out.println((i + 1) + " - " + menuOptions[i]);
        }
        return getUserOption(maxValue);
    }

    public static void initialMenu() {
        System.out.println("Menu inicial");

        String[] menuOptions = {
                "Carregar jogo",
                "Novo jogo",
                "Sair"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                savedGamesMenu(SavesManager.showSavedMenu(), true);
                System.out.println("Carregado com sucesso.");
                break;
            case 2:
                game = SavesManager.createGame();
                System.out.println("Jogo criado com sucesso.");
                mainMenu();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

    public static void mainMenu() {
        System.out.println("Menu principal");

        String[] menuOptions = {
                "Aceder as Ligas",
                //"Criar nova Liga",
                "Carregar ou criar novo jogo",
                "Sair"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                game.showData();
                int val = getUserOption(game.getLeagues().size() + 1);
                if (val == game.getLeagues().size() + 1) {
                    mainMenu();
                    break;
                }
                leagueMenu(game.getLeagues().get(val - 1));
                break;
//            case 2:
//                game.addLeague(League.generateLeague());
//                System.out.println("Liga adicionada com sucesso.");
//                mainMenu();
//                break;
            case 2:
                saveMenu();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

    public static void leagueMenu(League league) {
        System.out.println("Menu das Ligas");

        String[] menuOptions = {
                "Mostrar Equipas",
                "Mostrar todos os Jogos",
                "Mostrar Jogos por Realizar",
                "Mostrar Árbítros",
                "Adicionar Árbitro",
                "Adicionar Equipa",
                "Gerar Jogos",
                "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                league.showData();
                int val = getUserOption(league.getTeams().size() + 1);
                if (val == league.getTeams().size() + 1) {
                    leagueMenu(league);
                    break;
                }
                teamMenu(league.getTeams().get(val - 1), league);
                break;
            case 2:
                var matchs = league.getMatches();
                league.showMatchs(matchs);
                int val2 = getUserOption(matchs.size() + 1);
                if (val2 == matchs.size() + 1) {
                    leagueMenu(league);
                    break;
                }
                matchMenu(league.getMatches().get(val2 - 1), league);
                break;
            case 3:
                var matchs2 = league.getMatchesUnfinished();
                league.showMatchs(matchs2);
                int val3 = getUserOption(matchs2.size() + 1);
                if (val3 == matchs2.size() + 1) {
                    leagueMenu(league);
                    break;
                }
                matchMenu(league.getMatchesUnfinished().get(val3 - 1), league);
                break;
            case 4:
                league.showReferees();
                int val4 = getUserOption(league.getReferees().size() + 1);
                if (val4 == league.getReferees().size() + 1) {
                    leagueMenu(league);
                    break;
                }
                league.getReferees().get(val4 - 1).showData();
                leagueMenu(league);
                break;
            case 5:
                league.addReferee(Referee.generateReferee());
                System.out.println("Árbitro adicionada com sucesso.");
                break;
            case 6:
                league.addTeam(Team.generateTeam());
                System.out.println("Equipa adicionada com sucesso.");
                break;
            case 7:
                league.generateMatches();
                System.out.println("Jogos gerados com sucesso.");
                leagueMenu(league);
                break;
            case 8:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

    public static void teamMenu(Team team, League league) {
        System.out.println("Menu da Equipa");

        String[] menuOptions = {
                "Mostrar Jogadores",
                "Mostrar Jogos Realizados",
                "Mostrar Jogos por Realizar",
                "Substituir Equipa",
                "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                team.showData();
                int val = getUserOption(team.getPlayers().size());
                playerMenu(team.getPlayers().get(val - 1), team, league);
                break;
            case 2:
                var matcs = league.getMatches(team, true);
                league.showMatchs(matcs);
                int val2 = getUserOption(matcs.size() + 1);
                if (val2 == matcs.size() + 1) {
                    teamMenu(team, league);
                    break;
                }
                matchMenu(matcs.get(val2 - 1), league);
                break;
            case 3:
                var matcs2 = league.getMatches(team, false);
                league.showMatchs(matcs2);
                int val3 = getUserOption(matcs2.size() + 1);
                if (val3 == matcs2.size() + 1) {
                    teamMenu(team, league);
                    break;
                }
                matchMenu(matcs2.get(val3 - 1), league);
                break;
            case 4:
                var previousTeam = Team.generateTeam();
                league.replaceTeam(team, previousTeam);
                System.out.println("Equipa substituida com sucesso.");
                leagueMenu(league);
                break;
            case 5:
                leagueMenu(league);
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

    public static void matchMenu(Match match, League league) {
        System.out.println("Menu do Jogo");

        String[] menuOptions = {
                "Mostrar Informações do Jogo",
                "Simular jogo",
                "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                System.out.println(match.toString());
                matchMenu(match, league);
                break;
            case 2:
                match.simulateMatch();
                matchMenu(match, league);
                break;
            case 3:
                leagueMenu(league);
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

    public static void playerMenu(Player player, Team team, League league) {
        System.out.println("Menu do Jogador");

        String[] menuOptions = {
                "Mostrar Informações do Jogador",
                "Substituir Jogador",
                "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                player.showData();
                playerMenu(player, team, league);
                break;
            case 2:
                team.replacePlayer(player, Player.generatePlayer());
                teamMenu(team, league);
                break;
            case 3:
                teamMenu(team, league);
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }


    public static void saveMenu() {
        String[] menuOptions = {
                "Criar novo jogo",
                "Guardar",
                "Carregar",
                "Voltar"
        };

        int options = createMenu(menuOptions);

        switch (options) {
            case 1:
                game = SavesManager.createGame();
                System.out.println("Jogo criado com sucesso.");
                mainMenu();
                break;
            case 2:
                SavesManager.saveGame(game);
                System.out.println("Guardado com sucesso.");
                mainMenu();
                break;
            case 3:
                savedGamesMenu(SavesManager.showSavedMenu(), false);
                System.out.println("Carregado com sucesso.");
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

    private static void savedGamesMenu(ArrayList<String> savedGames, boolean isPrevInitial) {
        if (savedGames.isEmpty()) {
            if (isPrevInitial) {
                initialMenu();
            } else {
                saveMenu();
            }
            return;
        }
        int opt = getUserOption(savedGames.size() + 1);
        if (opt == savedGames.size() + 1) {
            if (isPrevInitial) {
                initialMenu();
            } else {
                saveMenu();
            }
        } else {
            String fileName = savedGames.get(opt - 1);
            game = SavesManager.loadData(fileName);
            mainMenu();
        }
    }
}
