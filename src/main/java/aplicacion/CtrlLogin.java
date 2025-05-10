package aplicacion;

import presentacion.VistaLogin;
import presentacion.Home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import dominio.entidades.Hash;
import dominio.entidades.Usuario;
import persistencia.UsuarioDAO;
import dominio.entidades.Validacion;
import javax.swing.JFrame;
import presentacion.FrmHome;

public class CtrlLogin implements ActionListener{
    private VistaLogin v;
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private Validacion validacion =  new Validacion();

    public CtrlLogin(VistaLogin vistaLogin) {
        this.v = vistaLogin;
        
        v.btnIngresar.addActionListener(this);
    }

    
    public void iniciar(){
        v.setTitle("Login");
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }
    
    
    public void validacionLogin() {
        //Para el last_session (fecha y hora que el usuario inicio sesion)
        Date date = new Date();                     //formaro MySQL
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Convertir el password a un String
        String pass = new String(v.txtPassword.getPassword());
        
        /*Evaluar si los campos estan vacíos
            Cuando el usuario y el password no sean igual a nada*/
        if(!v.txtUsuario.getText().equals("") && !pass.equals("")){
            //Para el cifrado de las contraseñas en la BD
            String nuevoPass = Hash.sha1(pass);
            //vamos a enviar al modelo esos 2 parámetros
            usuario.setUsuario(v.txtUsuario.getText());
            usuario.setPassword(nuevoPass); //Ya que va cifrado
            usuario.setLast_session(fechaHora.format(date)); 

            // Verificamos si estos datos coinciden con los de la BD (user, password, estado)
            if (validacion.login(usuario)) {
                // Cerramos esta ventana porque entraremos al formulario de Home
                v.dispose();

                // Entramos al formulario de Home
                FrmHome vistaHome = new FrmHome();
                CtrlHome frmHome = new CtrlHome(vistaHome);
                frmHome.setUsuario(usuario);
                
                vistaHome.setExtendedState(JFrame.MAXIMIZED_BOTH);
                vistaHome.setVisible(true);
                                    
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos o usuario inactivo");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == v.btnIngresar){
            validacionLogin();
        }
    }
}
