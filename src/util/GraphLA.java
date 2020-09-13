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


public class GraphLA <E>{
    private LinkedList<Vertex<E>> vertexes;
    private boolean directed;
    
    public GraphLA(boolean directed){
        this.directed = directed;
        vertexes = new LinkedList<>();
    }
    
    public boolean addVertex(E data){
        Vertex<E> v = new Vertex<>(data);
        return (data == null || vertexes.contains(v))?false:vertexes.add(v);
    }
    
    public boolean removeVertex(E data){
        if(data == null || vertexes.isEmpty()) return false;
        ListIterator<Vertex<E>> iv = vertexes.listIterator();
        while(iv.hasNext()){
            Vertex<E> v = iv.next();
            ListIterator<Edge<E>> ie = v.getEdges().listIterator();
            while(ie.hasNext()){
                Edge<E> e = ie.next();
                if(e.getVDestino().getData().equals(data))
                    ie.remove();
            }
        }
        Vertex<E> vi = new Vertex<>(data);
        return vertexes.remove(vi);
    }
    
    public boolean addEdge(E src, E dst, int peso ,E movie){
        if(src == null || dst == null) return false;
        Vertex<E> vs = searchVertex(src);
        Vertex<E> vd = searchVertex(dst);
        if(vs == null || vd == null) return false;
        Edge<E> e = new Edge<>(vs,vd,movie);
        if(!vs.getEdges().contains(e))
            vs.getEdges().add(e);
        if(!directed){
            Edge<E> ei = new Edge<>(vd,vs,movie);
            if(!vd.getEdges().contains(ei)) {
                vd.getEdges().add(ei);}    
            }
        
   
        return true;
}
    
    public boolean removeEdge(E src, E dst){
        if(src == null || dst == null) return false;
        Vertex<E> vs = searchVertex(src);
        Vertex<E> vd = searchVertex(dst);
        if(vs == null || vd == null) return false;
        Edge<E> e = new Edge<>(0,vs,vd);
        vs.getEdges().remove(e);
        if(!directed){
            e = new Edge<>(0,vd,vs);
            vd.getEdges().remove(e);
        }
        return true;
    }
    
    private Vertex<E> searchVertex(E data){
        for(Vertex<E> v : vertexes)
        {
            if(v.getData().equals(data))
                return v;
        }
        return null;
    }
    
    
    public int indegree(E data){
        if(data==null) return -1;
        int in=0;
        for (Vertex<E> vertex:vertexes){
            Vertex<E> test= new Vertex(data);
            if(!vertex.equals(test)){
                for(Edge<E> testE: vertex.getEdges()){
                    Edge<E> test2= new Edge<E>(0,vertex,test);
                    if(testE.equals(test2)) in ++;
                }
            }
        }
        return in;
    }
    
    public int outdegree(E data){
        if(data==null||vertexes.isEmpty()) return -1;
         Vertex<E> vertex=searchVertex(data);
         if(vertex!=null) return vertex.getEdges().size();
         return -1;
        
        
    }
    
    @Override
    public String toString(){return showVertexes()+"  "+showArcos();
}
    
    public String showVertexes(){
        StringBuilder result = new StringBuilder();
        result.append("vertexes=[");
        for (Vertex<E> vertex:vertexes){
            result.append(vertex.getData());
            result.append(",");
        }
        result.deleteCharAt(result.length()-1);
        result.append("]");
        return result.toString();
    
    
    }
    public String showArcos(){
        
        StringBuilder result = new StringBuilder();
        result.append("Arcos:[");
        for(Vertex<E> vertex : vertexes){
            for(Edge<E> edge:vertex.getEdges()){
               result.append("(origen:");
               result.append(edge.getVOrigen().getData());
               result.append(" destino:");
               result.append(edge.getVDestino().getData());
               result.append(" peso:");
               result.append(edge.getPeso());
               result.append("),");
            
            
            } 
        }
        result.deleteCharAt(result.length()-1);
        result.append("]");
        return result.toString();
    }
     public List<Edge<E>> bfs(E data){
        List<Edge<E>>result = new LinkedList<>();
        if(data ==null) return result;
        Vertex<E> v = searchVertex(data);
        if(v==null) return result;
        
        Queue<Vertex<E>> cola = new LinkedList<>();
        v.setVisited(true);
        cola.offer(v);
        while(!cola.isEmpty()){
            v=cola.poll();
            result.addAll(v.getEdges());
            for(Edge<E> e: v.getEdges()){
                if(!e.getVDestino().isVisited()){
                    e.getVDestino().setVisited(true);
                    cola.offer(e.getVDestino());
                }
                
            }
        }
        cleanVertexes();
        return result;
    }
    
    private void cleanVertexes(){
        for(Vertex<E> v: vertexes){
            v.setVisited(false);
        }
    }
    
    public List<Edge<E>> dfs(E data){
        List<Edge<E>> result = new LinkedList<>();
        if(data ==null) return result;
        Vertex<E> v = searchVertex(data);
        if(v==null) return result;
        
        
        Deque<Vertex<E>> pila = new LinkedList<>();
        v.setVisited(true);
        pila.push(v);
        while(!pila.isEmpty()){
            v=pila.pop();
            result.addAll(v.getEdges());
            for(Edge<E> e: v.getEdges()){
                if(!e.getVDestino().isVisited()){
                    e.getVDestino().setVisited(true);
                    pila.push(e.getVDestino());
                }
                
            }
        }
        cleanVertexes();
        System.out.println("dfs");
        System.out.println(result.size());
        return result;
    }
    
    public boolean isEmpty(){
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
            
       public GraphLA<E> mergeGraphs2(GraphLA<E> graph1, GraphLA<E> graph2){
       
       if(graph1.directed || graph2.directed) return null;
        GraphLA<E> result = new GraphLA(false);
        
        
        
        
        
        return result;
       }
            
    
    
  
    public int menorDistancia(E origen, E destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException();
        }
        Vertex<E> vo = searchVertex(origen);
        Vertex<E> vd = searchVertex(destino);
        if (vo == null || vd == null) {
            throw new NoSuchElementException();
        }
        dijkstra(origen);
        return vd.getDistancia();
    }

    public List<Edge<E>> caminoMinimo(E origen, E destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException();
        }
        Vertex<E> vo = searchVertex(origen);
        Vertex<E> vd = searchVertex(destino);
        if (vo == null || vd == null) {
            throw new NoSuchElementException();
        }
        dijkstra(origen);
        Vertex<E> ant = vd;
        List<Edge<E>> lista = new LinkedList<>();
        while (ant != null) {
            lista.addAll(ant.getEdges());
            ant = ant.getAntecesor();
            System.out.println(ant);
        }
        Collections.reverse(lista);
        return lista;
    }
    
 private void dijkstra(E origen) {
        if (origen != null) {
            cleanVertexes();
            Vertex<E> vo = searchVertex(origen);
            if (vo == null) {
                throw new NoSuchElementException();

            }
            PriorityQueue<Vertex<E>> cola = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2) -> v1.getDistancia() + v2.getDistancia());
            vo.setDistancia(0);
            cola.offer(vo);
            while (!cola.isEmpty()) {
                Vertex<E> vertex = cola.poll();
                vertex.setVisited(true);
                for (Edge<E> edge : vertex.getEdges()) {
                    Vertex<E> dest = edge.getVDestino();
                    if (!dest.isVisited() && dest.getDistancia() > edge.getPeso() + vertex.getDistancia()) {
                        dest.setDistancia(edge.getPeso() + vertex.getDistancia());
                        dest.setAntecesor(vertex);
                        cola.offer(dest);

                    }

                }

            }
}
 }}