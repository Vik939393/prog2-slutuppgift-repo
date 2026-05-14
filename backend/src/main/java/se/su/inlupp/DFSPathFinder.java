package se.su.inlupp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    Map<T, T> connections = new HashMap<>();
      connect(graph, from, null, connections);
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

  private void connect (Graph<T> graph, T to, T from, Map<T, T> connections){
    connections.put(to, from);
    for (Edge<T> edge : graph.getEdgesFrom(to)){
      T destination = edge.getDestination();
      if(!connections.containsKey(destination)){
        connect(graph, destination, to, connections);
      }
    }



  }
}

