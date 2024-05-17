package modelo;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Serpiente {

    private int cabeza;
    private int cola;
    private ImageIcon icon;

    public Serpiente(int cabeza, int cola) {
        this.cabeza = cabeza;
        this.cola = cola;
        this.icon = escalarImagen(new ImageIcon(getClass().getResource("/Imagenes/snake.png")));
    }

    public int getCabeza() {
        return cabeza;
    }

    public void setCabeza(int cabeza) {
        this.cabeza = cabeza;
    }

    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = escalarImagen(icon);
    }

    private ImageIcon escalarImagen(ImageIcon icon) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
