package se.su.inlupp;


public class Controller {
    private ListGraph<Location> graph = new ListGraph<>();

    public void addNode(String name, String berryAmount) {
        graph.add(new Location(name, berryAmount));
    }

    public void removeNode(Location location) {
        graph.remove(location);
    }

    public void connectNodes(LocationNodeGui start, LocationNodeGui end, String name, int distance) {
        for (Location s : graph) {
            if (start.getName().equals(s.getName())) {
                for (Location e : graph) {
                    if (end.getName().equals(e.getName())) {
                        graph.connect(s, e, name, distance);
                        return;
                    }
                }
            }
        }
    }


}
