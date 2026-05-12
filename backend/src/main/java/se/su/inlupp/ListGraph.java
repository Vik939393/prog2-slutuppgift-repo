package se.su.inlupp;

import org.w3c.dom.Node;

import java.util.*;

public class ListGraph<T> implements Graph<T> {

    private final Map<T, Set<Edge<T>>> nodesWithEdges;

    public ListGraph() {
        this.nodesWithEdges = new HashMap<>();
    }

    @Override
    public void add(T node) {
        nodesWithEdges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void remove(T node) {
        if (hasNode(node)) {
            for (T n : nodesWithEdges.keySet()) {
                if (getEdgeBetween(n, node) != null) {
                    disconnect(n, node);
                }
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
        if (hasNode(node1) && hasNode(node2)) {
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
        Set<Edge<T>> toRemove = new HashSet<>();
        if (hasNode(node1) && hasNode(node2)) {
            for (Edge<T> e : nodesWithEdges.get(node1)) {
                if (e.getDestination().equals(node2)) {
                    toRemove.add(e);
                }
            }
            for (Edge<T> e : nodesWithEdges.get(node2)) {
                if (e.getDestination().equals(node1)) {
                    toRemove.add(e);
                }
            }
            if (toRemove.isEmpty()) {
                throw new IllegalStateException();
            } else {
                nodesWithEdges.get(node1).removeAll(toRemove);
                nodesWithEdges.get(node2).removeAll(toRemove);
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void setConnectionWeight(T node1, T node2, int weight) {

        if (hasNode(node1) && hasNode(node2)) {
            if (weight < 0) {
                throw new IllegalArgumentException();
            }
            for (Edge e : nodesWithEdges.get(node1)) {
                if (e.getDestination().equals(node2)) {
                    e.setWeight(weight);
                }
            }
            for (Edge e : nodesWithEdges.get(node2)) {
                if (e.getDestination().equals(node1)) {
                    e.setWeight(weight);
                }
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Set<T> getNodes() {
        return Collections.unmodifiableSet(nodesWithEdges.keySet());
    }

    @Override
    public Collection<Edge<T>> getEdgesFrom(T node) {
        if (hasNode(node)) {
            return Collections.unmodifiableSet(nodesWithEdges.get(node));
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public Edge<T> getEdgeBetween(T node1, T node2) {
        if (hasNode(node1) && hasNode(node2)) {
            for (Edge<T> e : nodesWithEdges.get(node1)) {
                if (e.getDestination().equals(node2)) {
                    return e;
                }
            }
        } else {
            throw new NoSuchElementException();
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return nodesWithEdges.keySet().iterator();
    }
}

