package se.su.inlupp;


public class Controller {
    private ListGraph<Location> graph = new ListGraph<>();

    public void addNode(String name, String berryAmount) {
        graph.add(new Location(name, berryAmount));
    }

    public void removeNode(Location location) {
        graph.remove(location);
    }

    public void connectNodes(Location start, Location end, String name, int distance) {
        graph.connect(start, end, name, distance);
    }


}
