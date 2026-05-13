package se.su.inlupp;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PathImpl <T> implements Path<T>{
    private int totalWeight;
    private T end;
    private T start;
    private List<Edge<T>> edges;
    private List<T> nodes;

    public PathImpl (T start, List<Edge<T>> edges ){
        this.totalWeight = totalWeight;
        this.end = end;
        this.start = start;
        this.edges = edges;
        this.nodes = new LinkedList<>();

    }
    @Override
    public T getStart() {
        return start;
    }

    @Override
    public T getEnd() {

        return edges.getLast().getDestination();
    }

    @Override
    public int getTotalWeight() {
        for(Edge<T> e : edges)
            totalWeight += e.getWeight();

        return totalWeight;
    }

    @Override
    public List<Edge<T>> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    @Override
    public List<T> getNodes() {
        return List.of();
    }

    @Override
    public Iterator<Edge<T>> iterator() {
        return edges.iterator();
    }
    public String toString (){
        return "" + getEnd() + getStart() + getTotalWeight();
    }
}
