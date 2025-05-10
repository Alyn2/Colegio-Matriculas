package aplicacion;

import dominio.entidades.Imagen;
import dominio.entidades.Usuario;
import presentacion.FrmHome;

public class CtrlHome {
    private FrmHome vistaHome;

    public CtrlHome(FrmHome vistaHome) {
        this.vistaHome = vistaHome;
        this.vistaHome.setVisible(true);
        
        /* FALTA HACER QUE FUNCIONE CON MVC
        //IMAGEN DENTRO DE UN JPANEL
        int x = vistaHome.panelHome.getWidth();
        int y = vistaHome.panelHome.getHeight();
        //colocar el nombre y la ruta de la imagen
        String archivo = "/img/Home.png";
        //Instancia de la clase que hemos creado
        Imagen img = new Imagen(x, y, archivo);
        vistaHome.add(img); //Enviamos la imagen
        vistaHome.repaint(); //Mostramos la imagen en el JPanel
        */
    }
public void setUsuario(Usuario modelo) {
        vistaHome.lblNombre.setText(modelo.getNombre());
        vistaHome.lblRol.setText(modelo.getRol().getNombre());

        if (modelo.getRol().getId() == 1) {
            // ADMINISTRADOR
            // Realiza todas las acciones.
        } else if (modelo.getRol().getId() == 2) {
            // SECRETARIA
            // Realiza todas las acciones excepto el item de:
            vistaHome.subGestionUsuarios.setVisible(false);
        } else if(modelo.getRol().getId() == 3){ // Un usuario no podrá ver todo el menú Gestión incluyendo sus items del menú.
            vistaHome.menuArchivo.setVisible(false);
            vistaHome.subMatriculacion.setVisible(false);
            vistaHome.subPagos.setVisible(false);
        }
    }
    
}
