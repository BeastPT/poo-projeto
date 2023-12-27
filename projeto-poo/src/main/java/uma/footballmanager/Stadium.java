package uma.footballmanager;

import java.util.Scanner;

public class Stadium implements IMenuData {
    private final String name;
    private final String address;
    private final String city;
    private final Integer capacity;

    public Stadium(String name, String address, String city, Integer capacity) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }
    public String getName() {
        return name;
    }
    public Integer getCapacity(){
        return capacity;
    }

    @Override
    public void showData() {
        System.out.println("Nome do Estádio: " + name);
        System.out.println("Localidade: " + address);
        System.out.println("Cidade: " + city);
        System.out.println("Capacidade: " + capacity);
    }

    public static Stadium generateStadium() {
        Scanner sc = new Scanner(System.in);

        String name;
        while (true) {
            System.out.print("Nome do seu Estádio: ");
            name = sc.nextLine();

            if (!name.trim().isEmpty()) {
                break;
            } else {
                System.out.println("O nome do estádio não pode estar vazio.");
            }
        }

        String address;
        while (true) {
            System.out.print("Localidade do seu estádio: ");
            address = sc.nextLine();

            if (!address.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Nome da localidade não pode estar vazia.");
            }
        }

        String city;
        while (true) {
            System.out.print("Nome da Cidade do seu estádio: ");
            city = sc.nextLine();

            if (!city.trim().isEmpty()) {
                break;
            } else {
                System.out.println("O nome da cidade não pode estar vazio.");
            }
        }

        int capacity;
        while (true) {
            try {
                System.out.print("Capacidade do seu estádio (número inteiro): ");
                capacity = Integer.parseInt(sc.nextLine());
                if (capacity > 0) {
                    break;
                } else {
                    System.out.println("A capacidade deve ser um número inteiro positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro para a capacidade.");
            }
        }

        return new Stadium(name, address, city, capacity);
    }
}
