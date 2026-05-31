// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;

import java.util.List;

public interface Path<T> extends Iterable<Edge<T>> {

  T getStart();

  T getEnd();

  int getTotalWeight();

  List<Edge<T>> getEdges();

  List<T> getNodes();
}

