package pt.uma.projeto.poo;

public class Stadium {
    private String name;
    private final String city;
    private final String address;
    private int capacity;

    public Stadium(String name, String city, String address, int capacity) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUpdatedCapacity() {
        return capacity + (int) (capacity*0.1);
    }

    public void updateCapacity() {
        capacity += (int) (capacity*0.1);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ", " + city + ", " + address + ", " + capacity;
    }
}
