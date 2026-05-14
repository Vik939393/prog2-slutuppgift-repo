package se.su.inlupp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    Map<T, T> connections = new HashMap<>();
    connections.put(from, null);
    LinkedList<T> queue = new LinkedList<>();

    queue.add(from);

    while (!queue.isEmpty()) {
      T current = queue.poll();
      for (Edge<T> edge : graph.getEdgesFrom(current)) {
        T next = edge.getDestination();
        if (!connections.containsKey(next)) {
          connections.put(next, current);
          queue.add(next);
        }
      }
    }


    List<Edge<T>> path = new LinkedList<>();

    if (connections.containsKey(to)) {
      T current = to;
      while (current != null && !current.equals(from)) {
        T previous = connections.get(current);
        Edge<T> edge = graph.getEdgeBetween(previous, current);
        path.addFirst(edge);
        current = previous;
      }

      return new PathImpl<>(from, path);
    }
      return null;

  }

}