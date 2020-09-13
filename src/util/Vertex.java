/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.LinkedList;
import java.util.Objects;

public class Vertex<E> {

    private E data;
    private LinkedList<Edge<E>> edges;
    private boolean visited;
    public int distancia;
    private Edge<E> antecesor;

    public Edge<E> getAntecesor() {
        return antecesor;
    }
    
    
    public boolean addEdge(Edge<E> edge) {
        return edges.add(edge);
    }

    public void setAntecesor(Edge<E> antecesor) {
        this.antecesor = antecesor;
    }

    public Vertex(E data) {
        this.data = data;
        edges = new LinkedList<>();
        distancia = Integer.MAX_VALUE;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public LinkedList<Edge<E>> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge<E>> edges) {
        this.edges = edges;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<E> other = (Vertex<E>) obj;
        return Objects.equals(this.data, other.data);
    }

    @Override
    public String toString() {
        return "Vertex{" + "data=" + data + '}';
    }
    

}
