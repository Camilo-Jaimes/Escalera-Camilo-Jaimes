/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author monok
 */
public class Jugador {

    private int posicionActual;
    private String nombre;
    private boolean ganador;

    public Jugador(String nombre) {
        this.posicionActual = 0;
        this.nombre = nombre;
        this.ganador = false;
    }

    public Jugador(int posicion) {
        this.posicionActual = posicion;
        this.nombre = nombre;
        this.ganador = false;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void modificarPosicion(int movimiento) {
        setPosicionActual(getPosicionActual() + movimiento);
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + '}';
    }
}
