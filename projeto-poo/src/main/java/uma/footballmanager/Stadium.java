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

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public void showData() {
        System.out.println("Nome do Estadio: " + getName());
        System.out.println("Localidade: " + getAddress());
        System.out.println("Ciadde: " + getCity());
        System.out.println("Capacidae: " + getCapacity());
    }
}