package uma.footballmanager;

import java.util.Scanner;
import java.util.List;

public class Menu {
    private static final Scanner sc;
    private static final Game game;
    
    static {
        sc = new Scanner(System.in);
        game = SavesManager.createGame();
       
    }
    public static void main(String[] args) {
        mainMenu();
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

        int maxValue = menuOptions.length;
        int mainOption;

        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
            mainOption = getUserOption(maxValue);
            if (mainOption == 1) {
                leagueMenu();
            } else if (mainOption == 2) {
                teamMenu();
            } else if (mainOption == 3) {
                playerMenu();
            } else if (mainOption == 4) {
                matchMenu();
            } else if (mainOption == 5) {
                stadiumMenu();
            } else if (mainOption == 6) {
                playernameMenu();
            } else if (mainOption == 7) {
               // saveMenu();
            } else if (mainOption == maxValue) {
                System.out.println("Sair do jogo.");
                System.exit(0);
                break;
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }
        } while (mainOption != maxValue);
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
                System.out.println("O seu número tem de ser válido.");
                sc.next();
            }
        }
        sc.nextLine();
        return userOption;
    }
    
    /*
     public static void saveMenu() {
        String[] menuOptions = {
                "Guardar",
                "Carregar",
                "Voltar"
            };

        int maxValue = menuOptions.length;
        int mainOption;

        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }

        do {
            mainOption = getUserOption(maxValue);

            if (mainOption == 1) {
               //SavesManager.saveGame(game);
            } else if (mainOption == 2) {
               SavesManager.loadData(fileName);
            } else if (mainOption == maxValue) {
                System.out.println("Voltar");
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }

        } while (mainOption != maxValue);
    }
 */
   
   
     public static void leagueMenu() {
        String[] menuOptions = {
                "Listar Ligas",
                "Listar Equipas",
                "Listar Jogadores",
                "Listar Jogos",
                "Listar Arbitros",
                "Voltar"
        };
        int maxValue = menuOptions.length;
        int mainOption;
        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
                    
            mainOption = getUserOption(maxValue);

            if (mainOption == 1) {
               listLeagues();
            } else if (mainOption == 2) {
               listTeams();
            } else if (mainOption == 3) {
               listAll();
            } else if (mainOption == 4) {
                listRef();
            } else if (mainOption == maxValue) {
                mainMenu(); //voltar atras
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }
        } while (mainOption != maxValue);
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
        int maxValue = menuOptions.length;
        int mainOption;
        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
            mainOption = getUserOption(maxValue);
            //if (mainOption == 1) {
        //}
            if (mainOption == 2) {
                listPlayers();
            } else if (mainOption == 3) {
             
            } else if (mainOption == 4) {
              //
            } else if (mainOption == 5) {
              //
            } else if (mainOption == 6) {
              listCoach();
            } else if (mainOption == 7) {
              stadiumMenu();
            } else if (mainOption == maxValue) {
                mainMenu(); //voltar atras
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }

        } while (mainOption != maxValue);
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
        int maxValue = menuOptions.length;
        int mainOption;
        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
            mainOption = getUserOption(maxValue);
            if (mainOption == 1) {
                
            }else if (mainOption == 2) {
                playernameMenu();
            } else if (mainOption == 3) {
              
            } else if (mainOption == 4) {
              
            } else if (mainOption == maxValue) {
                mainMenu(); //voltar atras
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }

        } while (mainOption != maxValue);
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
        int maxValue = menuOptions.length;
        int mainOption;
        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
            mainOption = getUserOption(maxValue);
            if (mainOption == 1) {
                Player.generatePlayer();
            }else if (mainOption == 2) {
                
            } else if (mainOption == 3) {
              
            } else if (mainOption == 4) {
                
            } else if (mainOption == 5) { 
                     
            } else if (mainOption == maxValue) {
                mainMenu(); //voltar atras
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }
        } while (mainOption != maxValue);
      }  

      
      public static void stadiumMenu() {
        String[] menuOptions = {
           "Ver detalhes do estádio",
           "Criar novo estádio",
           "Voltar"
        };
        int maxValue = menuOptions.length;
        int mainOption;
        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
            mainOption = getUserOption(maxValue);
            if (mainOption == 1) {
                seeStadium();
            }else if (mainOption == 2) {
                Stadium.generateStadium();     
            } else if (mainOption == maxValue) {
                mainMenu(); //voltar atras
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }
        } while (mainOption != maxValue);
      }       
      
      private static void seeStadium() {
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
        } else {
        System.out.println("Sem liga");
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
        int maxValue = menuOptions.length;
        int mainOption;
        for (int i = 0; i < maxValue; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
        do {
            System.out.println("Escolha uma opção:");
            mainOption = getUserOption(maxValue);
            if (mainOption == 1) {
                Player.generatePlayer();
            }else if (mainOption == 2) {
                
            } else if (mainOption == 3) {
              
            } else if (mainOption == 4) {
                
            } else if (mainOption == 5) { 
                   
            } else if (mainOption == 6) { 
                
            } else if (mainOption == 7) { 
                
            } else if (mainOption == 8) { 
                
            } else if (mainOption == 9) { 
                
            } else if (mainOption == 10) { 
                
            } else if (mainOption == 11) { 
                
            } else if (mainOption == 12) {    
                
            } else if (mainOption == maxValue) {
                mainMenu(); //voltar atras
            } else {
                System.out.println("Introduza um número entre 1 e " + maxValue);
            }
        } while (mainOption != maxValue);
      }  
}