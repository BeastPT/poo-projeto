package uma.footballmanager;

import java.util.Scanner;

public class Menu {
    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        int UserOption;
        do {
            mostrarMainMenu();
            UserOption = getUserOption();

            switch (UserOption) {
                case 1 -> leagueMenu();
                case 2 -> teamMenu();
                case 3 -> playerMenu();
                case 4 -> matchMenu();
                case 5 -> stadiumMenu();
                case 6 -> playernameMenu();
                case 7 -> saveMenu();
                case 8 -> System.out.println("Sair do jogo.");
                default -> System.out.println("Insira um número de 1 a 8.");
            }
        } while (UserOption != 8);
    }

    public static int getUserOption() {
        int UserOption;
        while (true) {
            System.out.print("Digite a opção que deseja");
            if (sc.hasNextInt()) {
                UserOption = sc.nextInt();
                if (UserOption >= 1 && UserOption <= 8) {
                    break;
                } else {
                    System.out.println("Insira um número de 1 a 8");
                }
            } else {
                System.out.println("O seu número tem de ser valido.");
                sc.next();
            }
        }
        sc.nextLine();
        return UserOption;
    }

    /**
     *
     */
    public static void mostrarMainMenu(){
        System.out.println("1 - Ligas");
        System.out.println("2 - Equipas");
        System.out.println("3 - Jogadores");
        System.out.println("4 - Partidas");
        System.out.println("5 - Estádio");
        System.out.println("6 - Detalhes de um jogador");
        System.out.println("7 - Salvar jogo");
        System.out.println("8 - Sair");
    }

    //menu de save
    public static void saveMenu(){
        System.out.println("1 - Guardar");
        System.out.println("2 - Carregar");
        System.out.println("3 - Voltar");
    }

    //menu da liga
    public static void leagueMenu(){
        System.out.println("1 - Criar Liga");
        System.out.println("2 - Listar Ligas");
        System.out.println("3 - Listar Equipas");
        System.out.println("4 - Listar Jogadores");
        System.out.println("5 - Listar Jogos");
        System.out.println("6 - Listar Arbitros");
        System.out.println("7 - Listar Estatisticas");
        System.out.println("8 - Patricionadores das ligas");
        System.out.println("9 - Historico de vencedores das ligas");
        System.out.println("10 - Voltar");
    }

    //menu da equipa
    public static void teamMenu(){
        System.out.println("1 - Criar Equipa");
        System.out.println("2 - Listar Equipas");
        System.out.println("3 - Listar Jogadores");
        System.out.println("4 - Remover ou adicionar jogadores a uma equipa");
        System.out.println("5 - Listar Jogos");
        System.out.println("6 - Listar Arbitros");
        System.out.println("7 - Desempenho medio das equipas");
        System.out.println("8 - Titulos das equipas");
        System.out.println("9 - Criar novo estádio");
        System.out.println("10 - Listar Estatisticas");
        System.out.println("11 - Listar treinadores");
        System.out.println("12 - Estádio"); // stadiumMenu()
        System.out.println("13 - Voltar");
    }

    //menu dos jogadores
    public static void playerMenu(){
        System.out.println("1 - Criar Jogador");
        System.out.println("2 - Historico de transferências");
        System.out.println("3 - Detalhes de um jogador"); //playernameMenu
        System.out.println("4 - Listar Jogadores");
        System.out.println("5 - Listar Equipas");
        System.out.println("6 - Listar Jogos");
        System.out.println("7 - Listar Arbitros");
        System.out.println("8 - Listar Estatisticas");
        System.out.println("9 - Voltar");
    }

    //menu da partidas
    public static void matchMenu() {
        System.out.println("1 - Criar Jogo");
        System.out.println("2 - Listar Jogos"); //incluindo data e horas dos jogos, estadios e condiçoes climaticas previstas dos jogos
        System.out.println("3 - Listar Equipas");
        System.out.println("4 - Listar Jogadores");
        System.out.println("5 - Listar Arbitros");
        System.out.println("6 - Listar Estatisticas");
        System.out.println("7 - Resultados anteriores");
        System.out.println("8 - Voltar");
    }

    //menu do estadio
    public static void stadiumMenu() {
        System.out.println("1 - Ver estádio");//nao sei se este é para deixar pq em baixo ja aparece a opçao para ver o preço, capacidade, etc
        System.out.println("2 - Mudar nome");
        System.out.println("3 - Melhorar estádio");
        System.out.println("4 - Localização do estádio"); //cidade, país
        System.out.println("5 - Capacidade do estádio");
        System.out.println("6 - Qualidade do gramado");
        System.out.println("7 - Titulos conquistados no estádio");
        System.out.println("8 - Preço de entrada");
        System.out.println("9 - Patrocinadores da equipa");
        System.out.println("10 - Historico de incidentes de segurança");
        System.out.println("11 - Ano de construção do estádio");
        System.out.println("12 - Criar novo estádio");
        System.out.println("13 - Voltar");
    }

    //menu do jogador especifico
    public static void playernameMenu(){
        System.out.println("1 - Número de jogos feitos");
        System.out.println("2 - Total de minutos jogados");
        System.out.println("3 - Número de jogos feitos");
        System.out.println("4 - Número de golos feitos");
        System.out.println("5 - Número de treinos feitos");
        System.out.println("6 - Número de assistências feitas");
        System.out.println("7 - Número de defensas feitas");//guarda redes
        System.out.println("8 - Prémios/recordes do jogador");
        System.out.println("9 - Número de cartões amarelos");
        System.out.println("10 - Número de cartões vermelhos");
        System.out.println("11 - Clubes anteriores");
        System.out.println("12 - Lesões");
        System.out.println("13 - Valor das transferências");
        System.out.println("14 - Voltar");
    }
}