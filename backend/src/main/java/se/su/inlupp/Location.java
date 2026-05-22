package se.su.inlupp;

public class Location {
    private String name;
    private LocationType type;
    private int weight;

    public Location(String name, LocationType type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        switch (type) {
            case SMALL_AMOUNT_BERRIES:
                weight = 1;
                return "Small amount of berries in this location.";
            case MIDDLE_AMOUNT_BERRIES:
                weight = 2;
                return "Medium amount of berries in this location";
            case BIG_AMOUNT_BERRIES:
                weight = 3;
                return "Lots of berries in this location.";
            default:
                return "Error";
        }
    }

    public String toString() {
        return "Location: " + name + " - " + getType();
    }
    
    public static void main(String[] args) {

    }
}
