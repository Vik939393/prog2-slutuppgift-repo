// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;

public interface PathFinder<T> {

  Path<T> findPath(Graph<T> graph, T from, T to);
}

