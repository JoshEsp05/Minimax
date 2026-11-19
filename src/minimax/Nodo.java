
package minimax;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private Tablero tablero;
    private char jugador;
    private int valor;

    private int fila;
    private int columna;
    private int nivel;

    private int alfa;
    private int beta;

    private List<Nodo> hijos;

    private boolean podado;
    private int movimientosPodados;

    public Nodo(Tablero tablero, char jugador, int fila, int columna) {

        this.tablero = new Tablero(tablero);
        this.jugador = jugador;

        this.fila = fila;
        this.columna = columna;

        this.valor = 0;
        this.nivel = 0;

        this.alfa = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;

        this.hijos = new ArrayList<>();

        this.podado = false;
        this.movimientosPodados = 0;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public char getJugador() {
        return jugador;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAlfa() {
        return alfa;
    }

    public void setAlfa(int alfa) {
        this.alfa = alfa;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public void agregarHijo(Nodo hijo) {
        hijos.add(hijo);
    }

    public boolean isPodado() {
        return podado;
    }

    public void setPodado(boolean podado) {
        this.podado = podado;
    }

    public int getMovimientosPodados() {
        return movimientosPodados;
    }

    public void setMovimientosPodados(int movimientosPodados) {
        this.movimientosPodados = movimientosPodados;
    }
}
