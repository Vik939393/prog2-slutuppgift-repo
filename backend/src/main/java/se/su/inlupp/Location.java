package se.su.inlupp;

public class Location {
    private String name;
    private LocationType type;
    private int weight;

    public Location(String name, String amount){
        this.name = name;
        this.type = LocationType.fromString(amount);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        switch (type) {
            case SMALL_AMOUNT_BERRIES:
                weight = 1;
                return "Small amount of berries in this location.";
            case MEDIUM_AMOUNT_BERRIES:
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
        return "Location: " + name + " - " + getType() + " Backend weight: " + weight + ".";
    }
    
    public static void main(String[] args) {

    }
}
