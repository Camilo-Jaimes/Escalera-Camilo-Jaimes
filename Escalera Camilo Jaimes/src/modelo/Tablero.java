package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private Object[] juego;
    private List<Serpiente> serpientes;
    private List<Escalera> escaleras;
    private Jugador[] jugadores;
    private Dado dado;
    private List<String> historialMovimientos;
    private int turnoActual;

    public Tablero(int tamañoTablero, int cantidadJugadores) {
        if (tamañoTablero <= 0 || cantidadJugadores <= 0) {
            throw new IllegalArgumentException("El tamaño del tablero y la cantidad de jugadores deben ser mayores a 0.");
        }
        this.juego = new Object[tamañoTablero];
        this.serpientes = new ArrayList<>();
        this.escaleras = new ArrayList<>();
        this.jugadores = new Jugador[cantidadJugadores];
        this.dado = new Dado();
        this.historialMovimientos = new ArrayList<>();
        this.turnoActual = 0;  // Inicializar el turno actual
    }

    public Object[] getJuego() {
        return juego;
    }

    public List<Serpiente> getSerpientes() {
        return serpientes;
    }

    public List<Escalera> getEscaleras() {
        return escaleras;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public Dado getDado() {
        return dado;
    }

    public List<String> getHistorialMovimientos() {
        return historialMovimientos;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    // Método para verificar el tipo de casilla
    public int verificarCasilla(int posicion) {
        if (posicion <= 0 || posicion > juego.length) {
            throw new IllegalArgumentException("Posición fuera de los límites del tablero.");
        }
        Object casilla = juego[posicion - 1];
        if (casilla instanceof Serpiente) {
            return 1;
        } else if (casilla instanceof Escalera) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean agregarSerpiente(int cabeza, int cola) {
        if (cabeza <= 0 || cola <= 0 || cabeza >= juego.length || cola >= juego.length) {
            return false;
        }
        if (cola >= cabeza) {
            return false;
        }
        if (juego[cabeza - 1] != null || juego[cola - 1] != null) {
            return false;
        }
        Serpiente serpiente = new Serpiente(cabeza, cola);
        serpientes.add(serpiente);
        juego[cabeza - 1] = serpiente;
        return true;
    }

    public boolean agregarEscalera(int base, int cima) {
        if (base <= 0 || cima <= 0 || base >= juego.length || cima >= juego.length) {
            return false;
        }
        if (base >= cima) {
            return false;
        }
        if (juego[base - 1] != null || juego[cima - 1] != null) {
            return false;
        }
        Escalera escalera = new Escalera(cima, base);
        escaleras.add(escalera);
        juego[base - 1] = escalera;
        return true;
    }

    public void agregarJugador(String nombre) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] == null) {
                Jugador j = new Jugador(nombre);
                jugadores[i] = j;
                break;
            }
        }
    }

    public void agregarAlHistorial(String movimiento) {
        historialMovimientos.add(movimiento);
    }

    public void rellenarTablero() {
        for (int i = 0; i < juego.length; i++) {
            if (juego[i] == null) {
                juego[i] = i + 1;
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < juego.length; i++) {
            if (juego[i] instanceof Serpiente) {
                System.out.print("S ");
            } else if (juego[i] instanceof Escalera) {
                System.out.print("E ");
            } else {
                System.out.print(juego[i] + " ");
            }
        }
    }

    public String ganador() {
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.isGanador()) {
                return jugador.getNombre();
            }
        }
        return null;
    }

    public int realizarLanzamiento() {
        return dado.lanzar();
    }

    public String siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.length;
        return ganador() != null ? "El ganador es: " + ganador() : null;
    }

    public void moverJugador(int lanzamiento, Jugador jugadorActual) {
        if (jugadorActual == null) {
            return;
        }
        agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " ha lanzado el dado y ha obtenido " + lanzamiento);
        int nuevaPosicion = jugadorActual.getPosicionActual() + lanzamiento;

        if (nuevaPosicion <= juego.length) {
            jugadorActual.modificarPosicion(lanzamiento);
            agregarAlHistorial("El jugador ha hecho sus movimientos, su nueva posición es: " + jugadorActual.getPosicionActual());
        } else {
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " no puede avanzar más y permanece en la posición " + jugadorActual.getPosicionActual());
            return;
        }

        Object posicion = juego[jugadorActual.getPosicionActual() - 1];
        if (posicion instanceof Serpiente) {
            Serpiente serpiente = (Serpiente) posicion;
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " encontró una serpiente y descendió de la casilla "
                    + serpiente.getCabeza() + " a la casilla " + serpiente.getCola());
            jugadorActual.setPosicionActual(serpiente.getCola());
        } else if (posicion instanceof Escalera) {
            Escalera escalera = (Escalera) posicion;
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " encontró una escalera y ascendió de la casilla "
                    + escalera.getAbajo() + " a la casilla " + escalera.getArriba());
            jugadorActual.setPosicionActual(escalera.getArriba());
        }

        if (jugadorActual.getPosicionActual() == juego.length) {
            jugadorActual.setGanador(true);
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " ha ganado el juego");
        }
    }

    public String obtenerHistorialJuego() {
        StringBuilder historial = new StringBuilder();
        for (String movimiento : historialMovimientos) {
            historial.append(movimiento).append("\n");
        }
        return historial.toString();
    }

    public void imprimirHistorialJuegoConsola() {
        System.out.println("");
        for (String elemento : historialMovimientos) {
            System.out.println(elemento);
            System.out.println("");
        }
    }

    public void imprimirJugadores() {
        System.out.println("");
        for (Jugador jugador : jugadores) {
            if (jugador != null) {
                System.out.println(jugador.toString());
            }
        }
    }

    public int getDimension() {
        return getJuego().length;
    }
}
