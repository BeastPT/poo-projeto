package uma.footballmanager;

import java.util.Scanner;
import java.util.List;

public class Menu {
    private static final Scanner sc;
    private static Game game;
    
    static {
        sc = new Scanner(System.in);
        game = SavesManager.createGame();
       
    }
    public static void main(String[] args) {
        mainMenu();
    }

    public static int getUserOption(int maxValue) {
        int userOption;
        while (true) {
            //System.out.print("Digite o número da opção desejada:");
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
    public static void mainMenu() {
        
        String[] menuOptions = {
                "Ligas",
                "Equipas",
                "Jogadores",
                "Partidas",
                "Estádio",
                "Detalhes de um jogador",
                "Salvar jogo",
                "Sair"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                leagueMenu();
                break;
            case 2:
                teamMenu();
                break;
            case 3:
                playerMenu();
                break;
            case 4:
                matchMenu();
                break;
            case 5:
                stadiumMenu();
                break;
            case 6:
                playernameMenu();
                break;
            case 7:
                //saveMenu();
                break;
            case 8:
                System.out.println("Sair do jogo.");
                System.exit(0);
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
                break;
            case 2:
                SavesManager.saveGame(game);
                break;
            case 3:
                // TODO: Criar static menu na class SavesManager para listar os saves
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }
   
   
     public static void leagueMenu() {
        String[] menuOptions = {
                "Listar Ligas",
                "Listar Equipas",
                "Listar Jogadores",
                "Listar Jogos",
                "Listar Arbitros",
                "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                listLeagues();
                break;
            case 2:
                listTeams();
                break;
            case 3:
                listAll();
                break;
            case 4:
                listRef();
                break;
            case 5:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }
     
    private static void listLeagues() {
        List<League> leagues = game.getLeagues();

        System.out.println("==================================");
        System.out.println("========== Listar Ligas ==========");
        System.out.println("==================================");
        for (int i = 0; i < leagues.size(); i++) {
            System.out.println((i + 1) + " - " + leagues.get(i).getName());
        }

        System.out.println("Pressione 's' para retornar ao menu.");
        char choice = sc.next().charAt(0);

        while (choice != 's' && choice != 'S') {
            System.out.println("Por favor, pressione 's' para retornar ao menu.");
            choice = sc.next().charAt(0);
        }
        mainMenu();
    }

     
     private static void listTeams() {
        List <League> leagues = game.getLeagues();

        System.out.println("====================================");
        System.out.println("=========== Listar Ligas ===========");
        System.out.println("====================================");       
        for (int i = 0; i < leagues.size(); i++) {
            System.out.println((i + 1) + " - " + leagues.get(i).getName());
        }
        System.out.println("Escolha uma opção:");
        int selectedLeagueIndex = getUserOption(leagues.size()) - 1;

        if (selectedLeagueIndex >= 0 && selectedLeagueIndex < leagues.size()) {
            League selectedLeague = leagues.get(selectedLeagueIndex);
            List<Team> teams = selectedLeague.getTeams();

            System.out.println("===== Listar Equipas na Liga " + selectedLeague.getName() + " =====");
            for (int i = 0; i < teams.size(); i++) {
                System.out.println((i + 1) + " - " + teams.get(i).getName());
            }
        } else {
            System.out.println("Índice de liga inválido.");
        }
        
        System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
        char choice = sc.next().charAt(0);

        while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
            System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
            choice = sc.next().charAt(0);
        }

        if (choice == 'v' || choice == 'V') {
            listTeams();
        } else if (choice == 's' || choice == 'S') {
            leagueMenu();
        } 
    }     
     
     public static void listAll() {
        List<League> leagues = game.getLeagues();
            System.out.println("Escolha uma Liga:");

        for (int i = 0; i < leagues.size(); i++) {
            League league = leagues.get(i);
            System.out.println((i + 1) + " - " + league.getName());
        }
        int leagueOption = getUserOption(leagues.size());
        if (leagueOption > 0 && leagueOption <= leagues.size()) {
            League selectedLeague = leagues.get(leagueOption - 1);

            System.out.println("Jogadores na Liga " + selectedLeague.getName() + ":");
            
            for (Team team : selectedLeague.getTeams()) {
                List<Player> players = team.getPlayers();
                
                
            for (Player player : players) {
                    System.out.println("Nome: " + player.getName() + ", Posição: " + player.getPosition() +", equipa: " + team.getName());
                }
            }
        } else {
            System.out.println("Opção inválida de liga.");
        }
        
        System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
        char choice = sc.next().charAt(0);

        while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
            System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
            choice = sc.next().charAt(0);
        }

        if (choice == 'v' || choice == 'V') {
            listAll();
        } else if (choice == 's' || choice == 'S') {
            leagueMenu();
        } 
        
     }
     
     private static void listRef() {
        List<League> leagues = game.getLeagues();
           System.out.println("Escolha uma Liga:");

        for (int i = 0; i < leagues.size(); i++) {
            League league = leagues.get(i);
            System.out.println((i + 1) + " - " + league.getName());
        }

        int leagueOption = getUserOption(leagues.size());
        if (leagueOption > 0 && leagueOption <= leagues.size()) {
            League selectedLeague = leagues.get(leagueOption - 1);

            List<Referee> referees = selectedLeague.getReferees();

            System.out.println("Árbitros na Liga " + selectedLeague.getName() + ":");
            for (Referee referee : referees) {
                System.out.println("Nome: " + referee.getName() + ", Experiência: " + referee.getExperience());
            }
        }
     }
     
     public static void teamMenu() {
        String[] menuOptions = {
            "Criar Equipa",
            "Listar Jogadores",
            "Remover ou adicionar jogadores a uma equipa",
            "Listar Jogos",
            "Desempenho medio das equipas",
            "Mostrar treinador",
            "Estádio", // stadiumMenu()
            "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                // Criar equipa
                break;
            case 2:
                listPlayers();
                break;
            case 3:
                //addRemovePlayer();
                break;
            case 4:
                //listMatches();
                break;
            case 5:
                //listPerformance();
                break;
            case 6:
                listCoach();
                break;
            case 7:
                stadiumMenu();
                break;
            case 8:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

     
     private static void listPlayers() {
        List<League> leagues = game.getLeagues();

        if (!leagues.isEmpty()) {
            System.out.println("Escolha uma Liga:");

            for (int i = 0; i < leagues.size(); i++) {
                League league = leagues.get(i);
                System.out.println((i + 1) + " - " + league.getName());
            }
            System.out.println("Escolha uma opção:");
            int leagueOption = getUserOption(leagues.size());
            
            if (leagueOption > 0 && leagueOption <= leagues.size()) {
                League selectedLeague = leagues.get(leagueOption - 1);
                System.out.println("Equipas na Liga " + selectedLeague.getName() + ":");

                List<Team> teams = selectedLeague.getTeams();
                for (int i = 0; i < teams.size(); i++) {
                    System.out.println((i + 1) + " - " + teams.get(i).getName());
                }
                System.out.println("Escolha uma opção:");
                int teamOption = getUserOption(teams.size());
                
                if (teamOption > 0 && teamOption <= teams.size()) {
                    Team selectedTeam = teams.get(teamOption - 1);
                    List<Player> players = selectedTeam.getPlayers();

                    System.out.println("Jogadores da Equipa " + selectedTeam.getName() + ":");
                    for (Player player : players) {
                        System.out.println("Nome: " + player.getName() + ", Posição: " + player.getPosition());
                    }
                } else {
                    System.out.println("Opção inválida de equipa.");
                }
            } else {
                System.out.println("Opção inválida de liga.");
            }
        } else {
            System.out.println("Não há ligas disponíveis.");
        }
        
        System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
        char choice = sc.next().charAt(0);

        while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
            System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
            choice = sc.next().charAt(0);
        }

        if (choice == 'v' || choice == 'V') {
            listPlayers();
        } else if (choice == 's' || choice == 'S') {
            teamMenu();
        } 
    }
     
     
     private static void listCoach() {
        List<League> leagues = game.getLeagues();

        System.out.println("===================================");
        System.out.println("========== Escolher Liga ==========");
        System.out.println("===================================");   

    // Itera sobre as ligas e exibe opções ao usuário
        for (int i = 0; i < leagues.size(); i++) {
            League league = leagues.get(i);
            System.out.println((i + 1) + " - " + league.getName());
        }
        System.out.println("Escolha uma opção:");

        int selectedLeagueIndex = getUserOption(leagues.size()) - 1;

    
        if (selectedLeagueIndex >= 0 && selectedLeagueIndex < leagues.size()) {
            League selectedLeague = leagues.get(selectedLeagueIndex);
            List<Team> teams = selectedLeague.getTeams();

            System.out.println("===== Escolher Equipa na Liga " + selectedLeague.getName() + " =====");

            for (int j = 0; j < teams.size(); j++) {
                Team team = teams.get(j);
                System.out.println((j + 1) + " - " + team.getName());
            }
            System.out.println("Escolha uma opção:");
            int selectedTeamIndex = getUserOption(teams.size()) - 1;

            if (selectedTeamIndex >= 0 && selectedTeamIndex < teams.size()) {
                Team selectedTeam = teams.get(selectedTeamIndex);
                Coach coach = selectedTeam.getCoach();

                System.out.println("Treinador da Equipa " + selectedTeam.getName() + ": " + coach.getName());
            } else {
                System.out.println("Índice de equipa inválido.");
            }
            } else {
                System.out.println("Índice de liga inválido.");
            }

            System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
            char choice = sc.next().charAt(0);

            while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
                System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
                choice = sc.next().charAt(0);
            }

            if (choice == 'v' || choice == 'V') {
                listCoach();
            } else if (choice == 's' || choice == 'S') {
                teamMenu();
            }
        }
     
      public static void playerMenu() {
        String[] menuOptions = {
            "Criar Jogador",
            "Detalhes de um jogador", //playernameMenu
            "Listar Jogos",
            "Estatisticas",
            "Voltar"
        };
        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                break;
            case 2:
                playernameMenu();
                break;
            case 3:
                //listMatches();
                break;
            case 4:
                //listStats();
                break;
            case 5:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }
     
  public static void matchMenu() {
    String[] menuOptions = {
       "Criar Jogo",
       "Listar Jogos",
       "Listar Equipas",
       "Listar Jogadores",
       "Listar Arbitros",
       "Resultados anteriores",
       "Voltar"
    };

    int option = createMenu(menuOptions);
    switch (option) {
        case 1:
            // Criar jogo
            break;
        case 2:
            //listMatches();
            break;
        case 3:
            //listTeams();
            break;
        case 4:
            //listPlayers();
            break;
        case 5:
            //listRef();
            break;
        case 6:
            mainMenu();
            break;
        default:
            System.out.println("Introduza um número entre 1 e " + menuOptions.length);
            break;
    }
  }


  public static void stadiumMenu() {
    String[] menuOptions = {
       "Ver detalhes do estádio",
       "Criar novo estádio",
       "Voltar"
    };
    int option = createMenu(menuOptions);
    switch (option) {
        case 1:
            seeStadium();
            break;
        case 2:
            Stadium.generateStadium();
            break;
        case 3:
            mainMenu();
            break;
        default:
            System.out.println("Introduza um número entre 1 e " + menuOptions.length);
            break;
    }
  }

    private static void seeStadium() {
    List<League> leagues = game.getLeagues();
    if (leagues.isEmpty()) {
        System.out.println("Sem liga");
        return;
    }

    System.out.println("Escolha uma Liga:");

    for (int i = 0; i < leagues.size(); i++) {
        League league = leagues.get(i);
        System.out.println((i + 1) + " - " + league.getName());
    }

    int leagueOption = getUserOption(leagues.size());
    if (leagueOption > 0 && leagueOption <= leagues.size()) {
        League selectedLeague = leagues.get(leagueOption - 1);
        System.out.println("Equipas na Liga " + selectedLeague.getName() + ":");

        List<Team> teams = selectedLeague.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println((i + 1) + " - " + team.getName());
        }

        int teamOption = getUserOption(teams.size());
        if (teamOption > 0 && teamOption <= teams.size()) {
            Team selectedTeam = teams.get(teamOption - 1);
            Stadium stadium = selectedTeam.getStadium();

            if (stadium != null) {
                System.out.println("Estádio da Equipa " + selectedTeam.getName() + ":");
                System.out.println("Nome: " + stadium.getName());
                System.out.println("Localização: " + stadium.getCity());
                System.out.println("Capacidade: " + stadium.getCapacity());
            } else {
                System.out.println("A equipa selecionada não tem um estádio atribuído.");
            }
        } else {
            System.out.println("Equipa não existente");
        }
    } else {
        System.out.println("Não existe essa liga");
    }
}
      
      public static void playernameMenu() {
        String[] menuOptions = {
            "Número de jogos feitos",
            "Total de minutos jogados",
            "Número de jogos feitos",
            "Número de golos feitos",
            "Número de treinos feitos",
            "Número de assistências feitas",
            "Número de defensas feitas",//guarda redes
            "Prémios/recordes do jogador",
            "Número de cartões amarelos",
            "Número de cartões vermelhos",
            "Clubes anteriores",
            "Lesões",
            "Voltar",
        };
        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                //listMatches();
                break;
            case 2:
                //listMatches();
                break;
            case 3:
                //listMatches();
                break;
            case 4:
                //listMatches();
                break;
            case 5:
                //listMatches();
                break;
            case 6:
                //listMatches();
                break;
            case 7:
                //listMatches();
                break;
            case 8:
                //listMatches();
                break;
            case 9:
                //listMatches();
                break;
            case 10:
                //listMatches();
                break;
            case 11:
                //listMatches();
                break;
            case 12:
                //listMatches();
                break;
            case 13:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
      }  
}