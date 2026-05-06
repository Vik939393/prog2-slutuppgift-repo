package se.su.inlupp;

import java.util.*;

public class ListGraph<T> implements Graph<T> {

    private final Map<T, Set<Edge>> nodesWithEdges = new HashMap<>();

    @Override
    public void add(T node) {
        nodesWithEdges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void remove(T node) {
        if (nodesWithEdges.containsKey(node)) {
            /*Set<Edge> setOfEdges = nodesWithEdges.get(node);
            nodesWithEdges.remove(node);

            for (T key : nodesWithEdges.keySet()) {
                for (Edge e : setOfEdges) {
                    if (nodesWithEdges.get(key).contains(e)) {
                       nodesWithEdges.get(key).remove(e);
                    }
                }
            }*/
            //Du vill kolla om en kants destination är den nod du håller på att ta bort.
            nodesWithEdges.remove(node); //Tar bort åt ett håll

            for (T key : nodesWithEdges.keySet()) {
                Set<Edge> toRemove = new HashSet<>();
                for (Edge e : nodesWithEdges.get(key)) {
                    if (e.getDestination().equals(node)) {
                        toRemove.add(e);
                    }
                }
                nodesWithEdges.get(key).removeAll(toRemove);
            }
        } else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasNode(T node) {
        double random = Math.random();
        if (random > 10) {
            return false;
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'hasNode'");
        }

    }

    @Override
    public void connect(T node1, T node2, String name, int weight) {
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    @Override
    public void disconnect(T node1, T node2) {
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }

    @Override
    public void setConnectionWeight(T node1, T node2, int weight) {
        throw new UnsupportedOperationException("Unimplemented method 'setConnectionWeight'");
    }

    @Override
    public Set<T> getNodes() {
        throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
    }

    @Override
    public Collection<Edge<T>> getEdgesFrom(T node) {
        throw new UnsupportedOperationException("Unimplemented method 'getEdgesFrom'");
    }

    @Override
    public Edge<T> getEdgeBetween(T node1, T node2) {
        throw new UnsupportedOperationException("Unimplemented method 'getEdgeBetween'");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

}

