package se.su.inlupp;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class PathImpl <T> implements Path<T>{
    private int totalWeight;
    private T end;
    private T start;
    private final List<Edge<T>> edges;
    private final List<T> nodes;

    public PathImpl (T start, List<Edge<T>> edges ){
        this.totalWeight = getTotalWeight();
        this.end = edges.getLast().getDestination();
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

        return end;
    }

    @Override
    public int getTotalWeight() {
        if(edges != null) {
            for(Edge<T> e : edges){
                totalWeight += e.getWeight();
            }
        } else {
            return 0;
        }
        return totalWeight;

    }

    @Override
    public List<Edge<T>> getEdges() {

        return edges;
    }

    @Override
    public List<T> getNodes() {
        List<T> nodes = new LinkedList<>();
        nodes.add(start);
        for (Edge<T> e : edges){
            T node = e.getDestination();
            nodes.add(node);

        }

        return nodes;
    }

    @Override
    public Iterator<Edge<T>> iterator() {
        return edges.iterator();
    }
    public String toString (){
        return "" + getEnd() + getStart() + getTotalWeight();
    }

}
