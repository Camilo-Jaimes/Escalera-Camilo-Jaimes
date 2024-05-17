package tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import modelo.Tablero;

public class VentanaNombresJugadores extends JFrame {

    private JLabel tituloLabel;
    private JLabel mensajeLabel;
    private JTextField nombreField;
    private JButton continuarButton;
    private int numJugadores; 
    private String[] nombresJugadores; 
    private int jugadorActual; 
    private Tablero t;

    public VentanaNombresJugadores(int numJugadores, Tablero tablero) {
        super("Insertar Nombres de Jugadores");

        this.numJugadores = numJugadores;
        this.nombresJugadores = new String[numJugadores];
        this.jugadorActual = 0;
        this.numJugadores = 0;
        this.t = tablero; 

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200); 
        setLocationRelativeTo(null);
        setUndecorated(true); 
        getContentPane().setBackground(new Color(153, 204, 255)); 

        int actual = jugadorActual+1;
        tituloLabel = new JLabel("Inserta el nombre del jugador " + actual, SwingConstants.CENTER);
        tituloLabel.setForeground(Color.WHITE); 
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setForeground(Color.RED); 
        nombreField = new JTextField(20);

        continuarButton = new JButton("Continuar") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(102, 204, 255)); 
                } else {
                    g.setColor(new Color(0, 255, 0)); 
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); 
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.WHITE); 
                g.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30); 
            }
        };
        continuarButton.setForeground(Color.WHITE); 
        continuarButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        continuarButton.setFocusPainted(false); 

        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(4, 1, 10, 10)); // GridLayout de 4 filas
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
        panelCentral.setBackground(new Color(153, 204, 255)); // Fondo de color azul claro
        panelCentral.add(tituloLabel);
        panelCentral.add(mensajeLabel);
        panelCentral.add(nombreField);
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setOpaque(false); 
        panelBoton.add(continuarButton);
        panelCentral.add(panelBoton);

        add(panelCentral, BorderLayout.CENTER);

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nombreField.getText().isEmpty()) {
                    nombresJugadores[jugadorActual] = nombreField.getText();
                    t.agregarJugador(nombresJugadores[jugadorActual]);
                    tituloLabel.setText("Inserta el nombre del jugador " + (jugadorActual+2));
                    nombreField.setText(""); // Limpiar el campo de texto
                    nombreField.requestFocusInWindow(); // Colocar el puntero en el campo de texto
                    jugadorActual++;
                    t.imprimirJugadores();

                    if (jugadorActual < numJugadores) {
//                        // Actualizar el título para el próximo jugador
//                        int actual = jugadorActual + 1;
//                        tituloLabel.setText("Inserta el nombre del jugador " + actual);
//                        nombreField.setText(""); // Limpiar el campo de texto
//                        nombreField.requestFocusInWindow(); // Colocar el puntero en el campo de texto
                    } else {
                        // Todos los nombres han sido ingresados
                        JOptionPane.showMessageDialog(null, "Todos los nombres han sido ingresados.");
                        for (int i = 0; i < nombresJugadores.length; i++) {
                            System.out.println("Jugador " + (i + 1) + ": " + nombresJugadores[i]);
                        }
                        dispose(); // Cerrar la ventana de ingreso de nombres
                    }
                } else {
                    mensajeLabel.setText("El nombre no puede estar vacío.");
                }
            }
        });

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
    }
}


