/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;

public class Edge <E>{
    private int peso;
    private Vertex<E> vo;
    private Vertex<E> vd;
    private E movie;

    public Edge(Vertex<E> vo, Vertex<E> vd, E movie) {
        this.peso =1;
        this.vo = vo;
        this.vd = vd;
        this.movie = movie;
    }

    public Edge(int peso, Vertex<E> vo, Vertex<E> vd) {
        this.peso = peso;
        this.vo = vo;
        this.vd = vd;
    }

    public Vertex<E> getVo() {
        return vo;
    }

    public void setVo(Vertex<E> vo) {
        this.vo = vo;
    }

    public Vertex<E> getVd() {
        return vd;
    }

    public void setVd(Vertex<E> vd) {
        this.vd = vd;
    }

    public E getMovie() {
        return movie;
    }

    public void setMovie(E movie) {
        this.movie = movie;
    }



    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertex<E> getVOrigen() {
        return vo;
    }

    public void setVOrigen(Vertex<E> vo) {
        this.vo = vo;
    }

    public Vertex<E> getVDestino() {
        return vd;
    }

    public void setVDestino(Vertex<E> vd) {
        this.vd = vd;
    }

    @Override
    public int hashCode() {
        return 3;
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
        final Edge<E> other = (Edge<E>) obj;
        return Objects.equals(this.vo, other.vo) && Objects.equals(this.vd, other.vd);
    }

    @Override
    public String toString() {
        return "Edge{" + "peso=" + peso + ", vo=" + vo + ", vd=" + vd + ", movie=" + movie + '}';
    }
    
    
    
    
    
}
