package dominio.entidades;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagenn extends JPanel {
    private final ImageIcon imagen;

    public Imagenn(String ruta) {
        imagen = new ImageIcon(getClass().getResource(ruta));
        setOpaque(false);
        setPreferredSize(new Dimension(imagen.getIconWidth(), imagen.getIconHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}

