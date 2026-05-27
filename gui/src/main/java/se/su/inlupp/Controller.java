package se.su.inlupp;


public class Controller {
    private Graph<Location> graph = new ListGraph<>();
    private PathFinder<Location> pathFinder = new BFSPathFinder<>();

    public void addNode(String name, String berryAmount) {
        Location locationToBeAdded = new Location(name, berryAmount);
        for (Location l : graph.getNodes()) {
            if (locationToBeAdded.equals(l)) {
                AlertHelper.showError("Two locations can not have the same name");
                return;
            }
        }
        graph.add(locationToBeAdded);

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

    public Path<Location> findPath(String from, String to) {
        Location locationFrom = findNode(from);
        Location locationTo = findNode(to);
        if (locationFrom == null ||locationTo == null) {
            return null;
        }
        return pathFinder.findPath(graph, locationFrom, locationTo);
    }

    public void setPathFinder(PathFinder<Location> pathFinder) {
        this.pathFinder = pathFinder;
    }
}
