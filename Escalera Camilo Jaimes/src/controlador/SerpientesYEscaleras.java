/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlador;

import java.util.Scanner;
import javax.swing.SwingUtilities;

import tablero.PantallaInicial;

/**
 *
 * @author monok
 */
public class SerpientesYEscaleras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Tablero t = new Tablero(10*10, 4);
//        // Agregar jugadores
//        for (int i = 0; i < t.getJugadores().length; i++) {
//            System.out.println("Introduce el nombre del jugador " + (i + 1) + ":");
//            String nombre = scanner.nextLine();
//            Jugador jugador = new Jugador(nombre);
//            t.agregarJugador(jugador);
//        }
//        // Serpientes
//        Serpiente s = new Serpiente(80, 55);
//        Serpiente s1 = new Serpiente(75, 73);
//        Serpiente s2 = new Serpiente(60, 40);
//        t.agregarSerpiente(s);
//        t.agregarSerpiente(s1);
//        t.agregarSerpiente(s2);
//        // Escaleras
//        Escalera e = new Escalera(50, 30);
//        Escalera e1 = new Escalera(45, 25);
//        Escalera e2 = new Escalera(63, 5);
//        t.agregarEscalera(e);
//        t.agregarEscalera(e1);
//        t.agregarEscalera(e2);
//        // Juego en sí
//        t.rellenarTablero();
//        t.imprimirJugadores();
//        t.agregarAlHistorial("Todos los jugadores empiezan en la posición 0");
//        while (t.ganador() == null) {
//            System.out.println("Es el turno de: " + t.getJugadores()[t.getTurnoActual()].getNombre());
//            System.out.println("Su posición es: " + t.getJugadores()[t.getTurnoActual()].getPosicionActual());
//            System.out.println("Presiona Enter para lanzar el dado...");
//            int lanzamiento = t.realizarLanzamiento();
//            scanner.nextLine(); // Espera a que el usuario presione Enter
//            String resultado = t.siguienteTurno(lanzamiento);
//            System.out.println("Su nueva posición es: " + t.getJugadores()[t.getTurnoActual()].getPosicionActual());
//            if (resultado != null) {
//                System.out.println(resultado);
//                break;
//            }
//        }
//        t.imprimirHistorialJuego();
        SwingUtilities.invokeLater(() -> {
            new PantallaInicial().setVisible(true);
        });
    }

}
