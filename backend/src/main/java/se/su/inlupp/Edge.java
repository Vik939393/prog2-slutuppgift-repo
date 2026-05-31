// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;

public interface Edge<T> {

  int getWeight();

  void setWeight(int weight);

  T getDestination();

  String getName();
}
