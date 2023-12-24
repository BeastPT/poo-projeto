package uma.footballmanager;

public class Stadium implements MenuData {
    private String name;
    private String address;
    private String city;
    private Integer capacity;

    public Stadium(String name, String address, String city, Integer capacity) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    @Override
    public void showData() {
        System.out.println("Nome do Estadio: " + name);
        System.out.println("Localidade: " + address);
        System.out.println("Ciadde: " + city);
        System.out.println("Capacidae: " + capacity);
    }
}