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
    
    public static void mainMenu() {
        System.out.println("Create Menu");
        
        String[] menuOptions = {
                "Gerir o Jogo",
                "Carregar ou criar novo jogo",
                "Sair"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                leagueMenu();
                break;
            case 2:
                saveMenu();
                break;
            case 3:
                System.out.println("Sair do jogo.");
                System.exit(0);
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

     public static void saveMenu() {
         System.out.println("Save Menu ");
         
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
        System.out.println("League Menu "); 
         
        String[] menuOptions = {
                "Mostrar as Ligas",
                "Gerir todas as ligas",
                "Remover Liga",
               
                "Voltar"       
        };
     
        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                listLeagues();
                break;
            case 2:
                showLeagueMenu();
                break;
            case 3:
                removeLeague();
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }
     
     
    public static void removeLeague() {
        List<League> league = game.getLeagues();

    System.out.println("Escolha uma Liga para remover:");

    for (int i = 0; i < league.size(); i++) {
        League liga = league.get(i);
        System.out.println((i + 1) + " - " + liga.getName());
    }
    int ligaOption = getUserOption(league.size());
    if (ligaOption > 0 && ligaOption <= league.size()) {
        League selectedLiga = league.get(ligaOption - 1);
        
        System.out.println("Tem certeza que deseja remover a liga '" + selectedLiga.getName() + "'? (S/N)");

        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("S")) {
            league.remove(selectedLiga);
            System.out.println("Liga removida com sucesso.");
        } else {
            System.out.println("Operação de remoção cancelada.");
        }
    } else {
        System.out.println("Opção de liga inválida.");
    }
    
    System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
        char choice = sc.next().charAt(0);

        while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
            System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
            choice = sc.next().charAt(0);
        }

        if (choice == 'v' || choice == 'V') {
            removeLeague();
        } else if (choice == 's' || choice == 'S') {
            leagueMenu();
        } 
    }   
    public static void showLeagueMenu() {
        System.out.println("ShowLeague Menu ");
        String[] menuOptions = {
               "Mostrar Equipas das Ligas",
               "Gerir as equipas",
               "Mostrar todos os jogadores das Ligas",
               "Adicionar ou Remover Equipa",
               "Mostrar todos os jogos da Liga",// jogos de uma liga toda
               "Mostrar Arbitros das Ligas",
               "Voltar"
                
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                listTeams();
                break;
            case 2:
                teamMenu();
                break;
            case 3:
                listAll();
                break;
            case 4:
               // addRemoveTeam();
                break;
            case 5:
               // showAllMatches();
                break;
            case 6:
                listRef();
                break;
            case 7:
                leagueMenu();
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
        leagueMenu() ;
    }

     
     private static void listTeams() {
        List <League> leagues = game.getLeagues();

        System.out.println("====================================");
        System.out.println("=========== Listar Ligas ===========");
        System.out.println("====================================");       
        for (int i = 0; i < leagues.size(); i++) {
            System.out.println((i + 1) + " - " + leagues.get(i).getName());
        }
    
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
            showLeagueMenu();
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
            showLeagueMenu();
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
        System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
        char choice = sc.next().charAt(0);

        while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
            System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
            choice = sc.next().charAt(0);
        }

        if (choice == 'v' || choice == 'V') {
            listRef();
        } else if (choice == 's' || choice == 'S') {
            showLeagueMenu();
        } 

        
     }
     
     public static void teamMenu() {
         
         System.out.println("Team Menu ");
        String[] menuOptions = {
            "Gerir Jogadores ou criar",
            "Listar Jogogadores de cada equipa",
            "Remover ou adicionar jogadores a uma equipa",
            "Desempenho das equipas",
            "Gerir treinadores ou criar",
            "Gerir Estádio ou criar", 
            "Voltar"
        };

        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                playerMenu();
                break;
            case 2:
                listPlayers();
                break;
            case 3:
                //addRemoveTeam();
                break;
            case 4:
                //listMatches();
                break;
            case 5:
                //listPerformance();
                break;
            case 6:
                coachMenu();
                break;
            case 7:
                stadiumMenu();
                break;
            case 8:
                showLeagueMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }
     /* falta criar
    public static void list() {
    List<League> leagues = game.getLeagues();

    System.out.println("====================================");
    System.out.println("=========== Listar Ligas ===========");
    System.out.println("====================================");       
    for (int i = 0; i < leagues.size(); i++) {
        System.out.println((i + 1) + " - " + leagues.get(i).getName());
    }

    int selectedLeagueIndex = getUserOption(leagues.size()) - 1;

    if (selectedLeagueIndex >= 0 && selectedLeagueIndex < leagues.size()) {
        League selectedLeague = leagues.get(selectedLeagueIndex);
        List<Team> teams = selectedLeague.getTeams();

        System.out.println("===== Listar Equipas na Liga " + selectedLeague.getName() + " =====");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + " - " + teams.get(i).getName());
        }

        int selectedTeamIndex = getUserOption(teams.size()) - 1;

        if (selectedTeamIndex >= 0 && selectedTeamIndex < teams.size()) {
            Team selectedTeam = teams.get(selectedTeamIndex);

            List<Match> matches = selectedTeam.matches;

            if (matches.isEmpty()) {
                System.out.println("A equipa " + selectedTeam.getName() + " não tem registros de desempenho.");
            } else {
                System.out.println("Desempenho da Equipa " + selectedTeam.getName() + ":");
                for (Match match : matches) {
                    System.out.println(", Golos Marcados: " + match.getGoals() + ", Golos Sofridos: " + match.getSufferedGoals());
                }
            }
        } else {
            System.out.println("Índice de equipe inválido.");
        }
    } else {
        System.out.println("Índice de liga inválido.");
    }
}
*/
    
    public static void coachMenu() {
        System.out.println("Coach Menu ");
    String[] menuOptions = {
       "Ver treinadores das equipas",
       "Criar novo treinador",
       "Voltar"
    };
    int option = createMenu(menuOptions);
    switch (option) {
        case 1:
            listCoach();
            break;
        case 2:
            Coach.generateCoach();
            break;
        case 3:
            teamMenu();
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
           
            int leagueOption = getUserOption(leagues.size());
            
            if (leagueOption > 0 && leagueOption <= leagues.size()) {
                League selectedLeague = leagues.get(leagueOption - 1);
                System.out.println("Equipas na Liga " + selectedLeague.getName() + ":");

                List<Team> teams = selectedLeague.getTeams();
                for (int i = 0; i < teams.size(); i++) {
                    System.out.println((i + 1) + " - " + teams.get(i).getName());
                }
               
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

        for (int i = 0; i < leagues.size(); i++) {
            League league = leagues.get(i);
            System.out.println((i + 1) + " - " + league.getName());
        }
      
        int selectedLeagueIndex = getUserOption(leagues.size()) - 1;

    
        if (selectedLeagueIndex >= 0 && selectedLeagueIndex < leagues.size()) {
            League selectedLeague = leagues.get(selectedLeagueIndex);
            List<Team> teams = selectedLeague.getTeams();

            System.out.println("===== Escolher Equipa na Liga " + selectedLeague.getName() + " =====");

            for (int j = 0; j < teams.size(); j++) {
                Team team = teams.get(j);
                System.out.println((j + 1) + " - " + team.getName());
            }
         
            int selectedTeamIndex = getUserOption(teams.size()) - 1;

            if (selectedTeamIndex >= 0 && selectedTeamIndex < teams.size()) {
                Team selectedTeam = teams.get(selectedTeamIndex);
                Coach coach = selectedTeam.getCoach();

                  coach.showData();
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
                coachMenu();
            }
        }
     
      public static void playerMenu() {
         System.out.println("Player Menu ");
         
        String[] menuOptions = {
            "Criar Jogador",
            "Detalhes de cada jogador", 
            "Voltar"
        };
        int option = createMenu(menuOptions);
        switch (option) {
            case 1:
                Player.generatePlayer();
                break;
            case 2:
                playernameMenu();
                break;
            case 3:
                teamMenu();
                break;
            default:
                System.out.println("Introduza um número entre 1 e " + menuOptions.length);
                break;
        }
    }

  public static void stadiumMenu() {
      System.out.println("Stadium Menu ");
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
            teamMenu();
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
    
    System.out.println("Pressione 'v' para retornar à lista ou 's' para ir para o menu.");
    char choice = sc.next().charAt(0);

    while (choice != 'v' && choice != 'V' && choice != 's' && choice != 'S') {
          System.out.println("Por favor, pressione 's' para retornar à lista ou 'v' para ir para o menu.");
          choice = sc.next().charAt(0);
    }

    if (choice == 'v' || choice == 'V') {
        seeStadium();
    } else if (choice == 's' || choice == 'S') {
        stadiumMenu();
    }
}
      
    public static void playernameMenu() {
    List<League> leagues = game.getLeagues();

    System.out.println("====================================");
    System.out.println("=========== Listar Ligas ===========");
    System.out.println("====================================");
    for (int i = 0; i < leagues.size(); i++) {
        System.out.println((i + 1) + " - " + leagues.get(i).getName());
    }
  
    int selectedLeagueIndex = getUserOption(leagues.size()) - 1;

    if (selectedLeagueIndex >= 0 && selectedLeagueIndex < leagues.size()) {
        League selectedLeague = leagues.get(selectedLeagueIndex);
        List<Team> teams = selectedLeague.getTeams();

        System.out.println("===== Listar Equipas na Liga " + selectedLeague.getName() + " =====");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + " - " + teams.get(i).getName());
        }

        System.out.println("Escolha uma equipe:");
        int selectedTeamIndex = getUserOption(teams.size()) - 1;

        if (selectedTeamIndex >= 0 && selectedTeamIndex < teams.size()) {
            Team selectedTeam = teams.get(selectedTeamIndex);
            List<Player> players = selectedTeam.getPlayers();

            System.out.println("===== Listar Jogadores na Equipa " + selectedTeam.getName() + " =====");
            for (int i = 0; i < players.size(); i++) {
                System.out.println((i + 1) + " - " + players.get(i).getName());
            }

            System.out.println("Escolha um jogador:");
            int selectedPlayerIndex = getUserOption(players.size()) - 1;

            if (selectedPlayerIndex >= 0 && selectedPlayerIndex < players.size()) {
                Player player = players.get(selectedPlayerIndex);

                  player.showData();
                  
            } else {
                System.out.println("Índice de jogador inválido.");
            }
        } else {
            System.out.println("Índice de equipe inválido.");
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
        playernameMenu();
    } else if (choice == 's' || choice == 'S') {
        playerMenu();
    }
}

}