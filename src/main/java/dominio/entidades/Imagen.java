package dominio.entidades;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Imagen extends javax.swing.JPanel{
    String ruta;
    
    //Constructor
    public Imagen(int x, int y, String ruta){
        this.setSize(x, y);
        this.ruta = ruta;
    }
    
    //método manual
    @Override
    public void paint(Graphics g){
        Dimension heigth = getSize();
        ImageIcon img = new ImageIcon(getClass().getResource(ruta));
        
        //Dibujamos la imagen
        g.drawImage(img.getImage(), 0, 0, heigth.width, heigth.height, null);
        setOpaque(false); //Para que no sea opaca
        super.paintComponent(g); //Para que se establezca la imagen
    }

}