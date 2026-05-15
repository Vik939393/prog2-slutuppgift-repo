package se.su.inlupp;

import java.util.HashSet;
import java.util.Set;

public class DFSPathFinder<T> implements PathFinder<T> {

    Set<T> visited = new HashSet<>();

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {

  }
}

