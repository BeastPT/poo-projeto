package pt.uma.projeto.poo;

public class Stadium {
    private final int id;
    private String name;
    private final String city;
    private final String address;
    private int capacity;

    public Stadium(int id, String name, String city, String address, int capacity) {
        this.id = id;
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
