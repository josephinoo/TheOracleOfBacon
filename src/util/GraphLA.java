/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GraphLA<E> {

    private LinkedList<Vertex<E>> vertexes;
    private boolean directed;

    public GraphLA(boolean directed) {
        this.directed = directed;
        vertexes = new LinkedList<>();
    }

    public boolean addVertex(E data) {
        Vertex<E> v = new Vertex<>(data);
        return (data == null || vertexes.contains(v)) ? false : vertexes.add(v);
    }

    public boolean removeVertex(E data) {
        if (data == null || vertexes.isEmpty()) {
            return false;
        }
        ListIterator<Vertex<E>> iv = vertexes.listIterator();
        while (iv.hasNext()) {
            Vertex<E> v = iv.next();
            ListIterator<Edge<E>> ie = v.getEdges().listIterator();
            while (ie.hasNext()) {
                Edge<E> e = ie.next();
                if (e.getVDestino().getData().equals(data)) {
                    ie.remove();
                }
            }
        }
        Vertex<E> vi = new Vertex<>(data);
        return vertexes.remove(vi);
    }

    public boolean addEdge(E src, E dst, int peso, E movie) {
        if (src == null || dst == null) {
            return false;
        }
        Vertex<E> vs = searchVertex(src);
        Vertex<E> vd = searchVertex(dst);
        if (vs == null || vd == null) {
            return false;
        }
        Edge<E> e = new Edge<>( vs, vd, movie);
        if (!vs.getEdges().contains(e)) {
            vs.getEdges().add(e);
        }
        if (!directed) {
            Edge<E> ei = new Edge<>( vd, vs, movie);
            if (!vd.getEdges().contains(ei)) {
                vd.getEdges().add(ei);
            }
        }

        return true;
    }

    public boolean removeEdge(E src, E dst) {
        if (src == null || dst == null) {
            return false;
        }
        Vertex<E> vs = searchVertex(src);
        Vertex<E> vd = searchVertex(dst);
        if (vs == null || vd == null) {
            return false;
        }
        Edge<E> e = new Edge<>(0, vs, vd);
        vs.getEdges().remove(e);
        if (!directed) {
            e = new Edge<>(0, vd, vs);
            vd.getEdges().remove(e);
        }
        return true;
    }

    private Vertex<E> searchVertex(E data) {
        for (Vertex<E> v : vertexes) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    public int indegree(E data) {
        if (data == null) {
            return -1;
        }
        int in = 0;
        for (Vertex<E> vertex : vertexes) {
            Vertex<E> test = new Vertex(data);
            if (!vertex.equals(test)) {
                for (Edge<E> testE : vertex.getEdges()) {
                    Edge<E> test2 = new Edge<E>(0, vertex, test);
                    if (testE.equals(test2)) {
                        in++;
                    }
                }
            }
        }
        return in;
    }

    public int outdegree(E data) {
        if (data == null || vertexes.isEmpty()) {
            return -1;
        }
        Vertex<E> vertex = searchVertex(data);
        if (vertex != null) {
            return vertex.getEdges().size();
        }
        return -1;

    }

    @Override
    public String toString() {
        return  showVertexes() + showArcos();
    }

    public String showVertexes() {
        StringBuilder result = new StringBuilder();
        result.append("vertexes=[");
        for (Vertex<E> vertex : vertexes) {
            result.append(vertex.getData());
            result.append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();

    }

    public String showArcos() {

        StringBuilder result = new StringBuilder();
        result.append("Arcos:[");
        for (Vertex<E> vertex : vertexes) {
            for (Edge<E> edge : vertex.getEdges()) {
                result.append("(origen:");
                result.append(edge.getVOrigen().getData());
                result.append(" destino:");
                result.append(edge.getVDestino().getData());
                result.append(" peso:");
                result.append(edge.getPeso());
                result.append("), \n");

            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    public void bfs(E origen, E destino) {
        cleanVertexes();

        List<Edge<E>> result = new LinkedList<>();
        if (origen == null) {
            throw new IllegalStateException();
        }
        Vertex<E> v = searchVertex(origen);
        if (v == null) {
            throw new IllegalStateException();
        }
        Queue<Vertex<E>> cola = new LinkedList<>();
        v.setVisited(true);
        cola.offer(v);
        do{
            v = cola.poll();
            result.addAll(v.getEdges());
            for (Edge<E> e : v.getEdges()) {
                if (!e.getVDestino().isVisited()) {
                    e.getVDestino().setVisited(true);
                    e.getVDestino().setAntecesor(e);
                    cola.offer(e.getVDestino());
                }
            }
        }
        while (!cola.isEmpty() && !v.getData().equals(destino));
    }

    private void cleanVertexes() {
        for (Vertex<E> v : vertexes) {
            v.setVisited(false);
        }
    }

    public void dfs(E origen,E destino) {
        cleanVertexes();

        Vertex<E> v = searchVertex(origen);
        if (v == null) {
            throw new IllegalStateException();
        }
        Deque<Vertex<E>> pila = new LinkedList<>();
        v.setVisited(true);
        pila.push(v);
        do{
            v = pila.pop();
            for (Edge<E> e : v.getEdges()) {
                if (!e.getVDestino().isVisited()) {
                    e.getVDestino().setVisited(true);        
                    e.getVDestino().setAntecesor(e);
                    pila.push(e.getVDestino());
                }

            }
        }
        while (!pila.isEmpty() && !v.getData().equals(destino));
    }
    
    public void validateEntryNotNull(E origen, E destino){
        if (origen == null || destino == null) {
            throw new IllegalArgumentException();
        }
    }
    
    public List<Edge<E>> camino(E origen, E destino,String tipo){
        validateEntryNotNull(origen,destino);
        Vertex<E> vo = searchVertex(origen);
        Vertex<E> vd = searchVertex(destino);
        if (vo == null || vd == null) {
            throw new NoSuchElementException();
        }
        if(tipo.equals("bfs"))
            bfs(origen,destino);
        else if(tipo.equals("dfs"))
            dfs(origen,destino);
        else
            dijkstra(origen);
        Vertex<E> ant = vd;
        List<Edge<E>> lista = new LinkedList<>();
        System.out.println(tipo);

        while (ant.getAntecesor() != null) {
            Edge<E> edgeAnt = ant.getAntecesor();
            System.out.println(edgeAnt);
            lista.add(edgeAnt);
            ant = edgeAnt.getVOrigen();
        }
        Collections.reverse(lista);
        return lista;
    }
    

    public boolean isEmpty() {
        return vertexes.isEmpty();
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public LinkedList<Vertex<E>> getVertexes() {
        return vertexes;
    }

    public void setVertexes(LinkedList<Vertex<E>> vertexes) {
        this.vertexes = vertexes;
    }

    private void dijkstra(E origen) {
        if(origen == null){
            throw new IllegalArgumentException();
        }
        cleanVertexes();
        Vertex<E> v = searchVertex(origen);
        v.setDistancia(0);
        PriorityQueue<Vertex<E>> cola = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2)
                -> v1.getDistancia() - v2.getDistancia());
        cola.offer(v);
        
        while (!cola.isEmpty()) {
            v = cola.poll();
            v.setVisited(true);
            for (Edge<E> e : v.getEdges()) {
                if (!e.getVDestino().isVisited()) {
                    if (v.getDistancia() + 1 < e.getVDestino().getDistancia()) {
                        e.getVDestino().setDistancia(v.getDistancia() + 1);
                        e.getVDestino().setAntecesor(e);
                        cola.offer(e.getVDestino());
                    }
                }
            }
        }
        
    }
}
