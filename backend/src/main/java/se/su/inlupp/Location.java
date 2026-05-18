package se.su.inlupp;

public class Location {
    private String name;
    private LocationType type;

    public Location(String name, LocationType type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        switch (type) {
            case FOREST:
                return "Forest";
            case NATIONAL_PARK:
                return "National park";
            case PARK:
                return "Park";
            case FARM:
                return "Farm";
            default:
                return "Error";
        }
    }

    public String toString() {
        return "Location: " + name + " - Location type: " + getType();
    }
    
    public static void main(String[] args) {
        Location slottsparken = new Location("Slottsparken", LocationType.PARK);
        Location yellowstone = new Location("Yellowstone", LocationType.NATIONAL_PARK);
        System.out.println(slottsparken);
        System.out.println(yellowstone);
    }
}
