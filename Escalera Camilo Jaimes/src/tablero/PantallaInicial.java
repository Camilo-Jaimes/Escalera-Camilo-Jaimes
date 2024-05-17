package tablero;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author USUARIO
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Tablero;

public class PantallaInicial extends JFrame {

    private JLabel bienvenidaLabel;
    private JLabel instruccionesLabel;
    private JLabel jugadoresLabel;
    private JTextField jugadoresField;
    private JLabel casillasLabel;
    private JTextField casillasField;
    private JTextField serpientesField;
    private JLabel serpientesLabel;
    private JLabel escalerasLabel;
    private JTextField escalerasField;
    private JButton agregarSerpienteButton;
    private JButton agregarEscaleraButton;
    private JButton iniciarJuegoButton;

    private List<int[]> serpientes;
    private List<int[]> escaleras;

    public PantallaInicial() {
        super("Configuración del Juego");
        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 220)); // Color de fondo beige

        // Inicializar las listas
        serpientes = new ArrayList<>();
        escaleras = new ArrayList<>();

        // Crear componentes de la pantalla inicial
        bienvenidaLabel = new JLabel("¡Bienvenido al Juego!", SwingConstants.CENTER);
        bienvenidaLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        instruccionesLabel = new JLabel("Configura los detalles del juego", SwingConstants.CENTER);
        instruccionesLabel.setFont(new Font("Verdana", Font.ITALIC, 20));

        jugadoresLabel = new JLabel("Jugadores:");
        jugadoresField = new JTextField(5);
        casillasLabel = new JLabel("Tablero (e.g., 10 para 10x10):");
        casillasField = new JTextField(5);
        serpientesLabel = new JLabel("Serpiente (inicio-fin):");
        serpientesField = new JTextField(5);
        agregarSerpienteButton = new JButton("Agregar");

        escalerasLabel = new JLabel("Escalera (inicio-fin):");
        escalerasField = new JTextField(5);
        agregarEscaleraButton = new JButton("Agregar");
        iniciarJuegoButton = new JButton("Iniciar Juego");
        iniciarJuegoButton.setBackground(new Color(60, 179, 113)); // Color verde medio
        iniciarJuegoButton.setForeground(Color.WHITE);

        // Configurar el layout del JFrame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Añadir componentes al JFrame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(bienvenidaLabel, gbc);

        gbc.gridy = 1;
        add(instruccionesLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(jugadoresLabel, gbc);

        gbc.gridx = 1;
        add(jugadoresField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(casillasLabel, gbc);

        gbc.gridx = 1;
        add(casillasField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(serpientesLabel, gbc);

        gbc.gridx = 1;
        add(serpientesField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        add(agregarSerpienteButton, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        add(escalerasLabel, gbc);

        gbc.gridx = 1;
        add(escalerasField, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        add(agregarEscaleraButton, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(iniciarJuegoButton, gbc);

        // Acción del botón para agregar serpientes
        agregarSerpienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coordenadas = serpientesField.getText();
                if (!coordenadas.isEmpty()) {
                    String[] pos = coordenadas.split("-");
                    if (pos.length == 2) {
                        int inicioPos = Integer.parseInt(pos[0].trim());
                        int finPos = Integer.parseInt(pos[1].trim());
                        serpientes.add(new int[]{inicioPos, finPos});
                        serpientesField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa el formato inicio-fin (ej. 12-5).");
                    }
                }
            }
        });

        // Acción del botón para agregar escaleras
        agregarEscaleraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coordenadas = escalerasField.getText();
                if (!coordenadas.isEmpty()) {
                    String[] pos = coordenadas.split("-");
                    if (pos.length == 2) {
                        int inicioPos = Integer.parseInt(pos[0].trim());
                        int finPos = Integer.parseInt(pos[1].trim());
                        escaleras.add(new int[]{inicioPos, finPos});
                        escalerasField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa el formato inicio-fin (ej. 3-14).");
                    }
                }
            }
        });

        // Agregar ActionListener al botón de iniciar juego
        iniciarJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se capturan los datos
                int numJugadores = Integer.parseInt(jugadoresField.getText());
                int numCasillas = Integer.parseInt(casillasField.getText());
                Tablero t = new Tablero(numCasillas * numCasillas, numJugadores);

                // Agregar las serpientes al tablero
                for (int[] serpiente : serpientes) {
                    t.agregarSerpiente(serpiente[0], serpiente[1]);
                }

                // Agregar las escaleras al tablero
                for (int[] escalera : escaleras) {
                    t.agregarEscalera(escalera[0], escalera[1]);
                }

                t.rellenarTablero();
                t.imprimirTablero();

                // Crear y mostrar la ventana del tablero
                TableroJFrame tablero = new TableroJFrame(numCasillas, numCasillas, t);
                tablero.setVisible(true);

                // Crear y mostrar la ventana para ingresar nombres de jugadores
                VentanaNombresJugadores nombresJugadores = new VentanaNombresJugadores(numJugadores, t);
                nombresJugadores.setVisible(true);
                // Cerrar la ventana actual
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaInicial().setVisible(true);
            }
        });
    }
}

