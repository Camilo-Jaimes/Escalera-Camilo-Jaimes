package modelo;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Escalera {
    private int arriba;
    private int abajo;
    private ImageIcon icon;

    public Escalera(int arriba, int abajo) {
        this.arriba = arriba;
        this.abajo = abajo;
        this.icon = escalarImagen(new ImageIcon(getClass().getResource("/Imagenes/ladder.png")));
    }

    public int getArriba() {
        return arriba;
    }

    public void setArriba(int arriba) {
        this.arriba = arriba;
    }

    public int getAbajo() {
        return abajo;
    }

    public void setAbajo(int abajo) {
        this.abajo = abajo;
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
