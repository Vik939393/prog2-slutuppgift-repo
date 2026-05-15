package se.su.inlupp;

public class EdgeImpl<T> implements Edge<T> {

    private T destination;
    private String name;
    private int weight;

    EdgeImpl(T destination, String name, int weight) {
        this.destination = destination;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(int weight) {
        if (weight < 0)
            throw new IllegalArgumentException();
        this.weight = weight;
    }

    @Override
    public T getDestination() {
        return this.destination;
    }

    @Override
    public String getName() {
        return name;
    }

    public String toString() {
        return "till " + destination + " med " + name + " tar " + weight;
    }
}
