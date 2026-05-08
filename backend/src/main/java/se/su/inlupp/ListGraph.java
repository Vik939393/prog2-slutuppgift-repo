package se.su.inlupp;

import org.w3c.dom.Node;

import java.util.*;

public class ListGraph<T> implements Graph<T> {
  private final Map<T, Set<Edge>> nodesWithEdges = new HashMap<>();

  @Override
  public void add(T node) {
    nodesWithEdges.putIfAbsent(node, new HashSet<Edge>());
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

