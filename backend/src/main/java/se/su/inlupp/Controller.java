package se.su.inlupp;




public class Controller {
    private ListGraph<Location> graph = new ListGraph<>();

    public void addNode(String name, String berryAmount) {
        graph.add(new Location(name, berryAmount));
    }

    public void removeNode(Location location) {
        graph.remove(location);
    }
    public void clear(){
        graph = new ListGraph<>();
    }

    public void connectNodes(String start, String end, String name, int distance) {
        for (Location s : graph) {
            if (start.equals(s.getName())) {
                for (Location e : graph) {
                    if (end.equals(e.getName())) {
                        graph.connect(s, e, name, distance);
                        return;
                    }
                }
            }
        }
    }

    public ListGraph<Location> getGraph() {
        return graph;
    }
}
