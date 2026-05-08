package se.su.inlupp;

import org.w3c.dom.Node;

import java.util.*;

public class ListGraph<T> implements Graph<T> {

    private final Map<T, Set<Edge<T>>> nodesWithEdges = new HashMap<>();

    @Override
    public void add(T node) {
        nodesWithEdges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void remove(T node) {
        if (nodesWithEdges.containsKey(node)) {
            for (T n : nodesWithEdges.keySet()) {
                nodesWithEdges.get(n).remove(node);
            }
            nodesWithEdges.remove(node);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasNode(T node) {
        return nodesWithEdges.containsKey(node);
    }

    @Override
    public void connect(T node1, T node2, String name, int weight) {
        if (nodesWithEdges.containsKey(node1) && nodesWithEdges.containsKey(node2)) {
            if (weight < 0) {
                throw new IllegalArgumentException();
            }
            for (Edge e : nodesWithEdges.get(node1)) {
                if (e.getDestination().equals(node2)) {
                    throw new IllegalStateException();
                }
            }
            for (Edge e : nodesWithEdges.get(node2)) {
                if (e.getDestination().equals(node1)) {
                    throw new IllegalStateException();
                }
            }
            Set<Edge<T>> oneEdges = nodesWithEdges.get(node1);
            Set<Edge<T>> twoEdges = nodesWithEdges.get(node2);

            oneEdges.add(new EdgeImpl<T>(node2, name, weight));
            twoEdges.add(new EdgeImpl<T>(node1, name, weight));
        } else {
            throw new NoSuchElementException();
        }
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

