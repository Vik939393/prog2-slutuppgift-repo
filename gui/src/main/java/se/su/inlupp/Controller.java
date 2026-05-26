package se.su.inlupp;


public class Controller {
    private Graph<Location> graph = new ListGraph<>();

    public void addNode(String name, String berryAmount) {
        graph.add(new Location(name, berryAmount));
    }

    public void removeNode(String name) {
        for (Location location : graph) {
            if (location.getName().equals(name)) {
                graph.remove(location);
                return;
            }
        }
    }
    public void clear(){
        graph = new ListGraph<>();
    }

    public void connectNodes(String start, String end, String name, int distance) {
        try {
            Location s = findNode(start);
            Location e = findNode(end);
            if (s != null && e != null) {
                graph.connect(s, e, name, distance);
            }
        } catch (Exception exception) {
            AlertHelper.showError(exception.getMessage());
        }
    }

    public Graph<Location> getGraph() {
        return graph;
    }

    private Location findNode(String name) {
        for (Location l : graph) {
            if (name.equals(l.getName())) {
                return l;
            }
        }
        return null;
    }
}
