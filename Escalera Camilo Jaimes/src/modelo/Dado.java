/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author monok
 */
public class Dado {

    private int lanzamiento;

    public int getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(int lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    /**
     * Realiza el lanzamiento del dado
     * 
     */
    public int lanzar() {
        int resultado = 0;
        resultado = (int) (Math.random() * 6) + 1;
        return resultado;
    }
}
