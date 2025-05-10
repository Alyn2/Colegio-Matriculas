package aplicacion;

import dominio.entidades.Hash;
import dominio.entidades.Usuario;
import persistencia.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.entidades.Validacion;
import presentacion.FrmGestionUsuarios;

public class CtrlGestionUsuarios implements ActionListener {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Validacion validacion = new Validacion();
    FrmGestionUsuarios viewUsuarios = new FrmGestionUsuarios();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlGestionUsuarios(FrmGestionUsuarios viewUsuario) {
        this.viewUsuarios = viewUsuario;
        //BOTONES
        this.viewUsuarios.btnNuevo.addActionListener(this);
        this.viewUsuarios.btnGuardar.addActionListener(this);
        this.viewUsuarios.btnEditar.addActionListener(this);
        this.viewUsuarios.btnActualizar.addActionListener(this);
        this.viewUsuarios.btnEliminar.addActionListener(this);
        this.viewUsuarios.btnCancelar.addActionListener(this);
        this.viewUsuarios.btnBuscar.addActionListener(this);

        viewUsuarios.txtId.setEnabled(false);
        viewUsuarios.btnGuardar.setEnabled(false);
        viewUsuarios.btnActualizar.setEnabled(false);
        viewUsuarios.btnCancelar.setEnabled(false);

        listar(viewUsuarios.tablaResumen); //para listar apenas ejecutemos el programa
    }

    public void iniciar() {
        viewUsuarios.setTitle("Gestion de Usuarios");
        viewUsuarios.setVisible(true);
    }

    public void listar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel(); //para que se vea la tabla al ejecutar
        List<Usuario> lista = usuarioDao.listar(); //para que liste en la tabla
        Object[] objeto = new Object[7]; //[7]ponemos la cantidad de columnas

        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getUsuario();
            objeto[2] = lista.get(i).getPassword();
            objeto[3] = lista.get(i).getNombre();
            objeto[4] = lista.get(i).getEstado();
            objeto[5] = lista.get(i).getLast_session();
            //objeto[6] = lista.get(i).getRol().getId(); 
            objeto[6] = lista.get(i).getRol().getNombre(); //Usamos inner join en UsuarioDAO

            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewUsuarios.tablaResumen.setModel(modeloTabla);
    }

    public void guardar() {
        // Traer los campos a evaluar
        String pass = new String(viewUsuarios.txtPassword.getPassword());
        String passCon = new String(viewUsuarios.txtConfirmarPassword.getPassword());

        // Validaciones
        if (viewUsuarios.txtUsuario.getText().equals("") || pass.equals("")
                || passCon.equals("") || viewUsuarios.txtNombre.getText().equals("")
                || viewUsuarios.cboEstado.getSelectedIndex() == 0 || viewUsuarios.cboRol.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe llenar todos los campos");

        } else {
            //Si las dos contraseñas coinciden
            if (pass.equals(passCon)) {
                //Si no existe un usuario con el mismo nombre
                if (validacion.existeUsuario(viewUsuarios.txtUsuario.getText()) == 0) {
                    // Para el cifrado de las contraseñas en la BD
                    String nuevoPass = Hash.sha1(pass);

                    // Enviamos los datos al modelo para insertar a la BD.
                    usuario.setUsuario(viewUsuarios.txtUsuario.getText());
                    usuario.setPassword(nuevoPass); // Ya que va cifrado
                    usuario.setNombre(viewUsuarios.txtNombre.getText());
                    usuario.setEstado(viewUsuarios.cboEstado.getSelectedItem().toString());
                    usuario.getRol().setId(viewUsuarios.cboRol.getSelectedIndex());

                    // Debido a que registrar es booleano
                    if (usuarioDao.insertar(usuario)) {
                        JOptionPane.showMessageDialog(null, "Registro guardado");
                        limpiarCasilleros(); // Para que limpie los campos en caso se haya registrado
                        limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                        listar(viewUsuarios.tablaResumen);

                        viewUsuarios.jtpGestionUsuarios.setSelectedIndex(0);
                        viewUsuarios.btnGuardar.setEnabled(false);
                        viewUsuarios.btnNuevo.setEnabled(true);
                        viewUsuarios.btnEditar.setEnabled(true);
                        viewUsuarios.btnEliminar.setEnabled(true);
                        viewUsuarios.btnActualizar.setEnabled(false);
                        viewUsuarios.txtBuscar.setEnabled(true);
                        viewUsuarios.btnBuscar.setEnabled(true);
                        viewUsuarios.btnCancelar.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }
        }
    }

    public void llenarCasillerosEditar() {
        int fila = viewUsuarios.tablaResumen.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(viewUsuarios, "Debe seleccionar una fila");
            viewUsuarios.jtpGestionUsuarios.setSelectedIndex(0);
        } else {
            int id = Integer.parseInt((String) viewUsuarios.tablaResumen.getValueAt(fila, 0).toString());
            String user = (String) viewUsuarios.tablaResumen.getValueAt(fila, 1);
            String pass = (String) viewUsuarios.tablaResumen.getValueAt(fila, 2);
            String nom = (String) viewUsuarios.tablaResumen.getValueAt(fila, 3);
            String est = (String) viewUsuarios.tablaResumen.getValueAt(fila, 4);
            //int rol = (int)viewUsuarios.tablaResumen.getValueAt(fila, 6);
            String rol = (String) viewUsuarios.tablaResumen.getValueAt(fila, 6); //Usamos inner join en UsuarioDAO

            viewUsuarios.txtId.setText("" + id);
            viewUsuarios.txtUsuario.setText(user);
            viewUsuarios.txtPassword.setText(pass);
            viewUsuarios.txtConfirmarPassword.setText(pass);
            viewUsuarios.txtNombre.setText(nom);
            viewUsuarios.cboEstado.setSelectedItem(est);
            //viewUsuarios.cboRol.setSelectedIndex(rol);
            viewUsuarios.cboRol.setSelectedItem(rol);

            viewUsuarios.jtpGestionUsuarios.setSelectedIndex(1);

            viewUsuarios.btnGuardar.setEnabled(false);
            viewUsuarios.btnNuevo.setEnabled(false);
            viewUsuarios.btnEditar.setEnabled(false);
            viewUsuarios.btnEliminar.setEnabled(false);
            viewUsuarios.btnActualizar.setEnabled(true);
            viewUsuarios.btnCancelar.setEnabled(true);
            viewUsuarios.txtBuscar.setEnabled(false);
            viewUsuarios.btnBuscar.setEnabled(false);
        }
    }

    public void actualizar() {
        int id = Integer.parseInt(viewUsuarios.txtId.getText());
        String usu = viewUsuarios.txtUsuario.getText();
        String pass = new String(viewUsuarios.txtPassword.getPassword());
        String confiPass = new String(viewUsuarios.txtConfirmarPassword.getPassword());
        String nom = viewUsuarios.txtNombre.getText();
        String estado = viewUsuarios.cboEstado.getSelectedItem().toString();
        int rol = viewUsuarios.cboRol.getSelectedIndex();

        if (!pass.equals(confiPass)) {
            JOptionPane.showMessageDialog(viewUsuarios, "Las contraseñas no coinciden");
        } else {
            usuario.setId(id);
            usuario.setUsuario(usu);
            usuario.setPassword(pass);
            usuario.setNombre(nom);
            usuario.setEstado(estado);
            usuario.getRol().setId(rol);

            int respuesta = usuarioDao.modificar(usuario);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(viewUsuarios, "Usuario actualizado correctamente");
                limpiarCasilleros(); //limpiar casilleros textField
                limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                listar(viewUsuarios.tablaResumen);
                viewUsuarios.jtpGestionUsuarios.setSelectedIndex(0);
                viewUsuarios.btnGuardar.setEnabled(false);
                viewUsuarios.btnNuevo.setEnabled(true);
                viewUsuarios.btnEditar.setEnabled(true);
                viewUsuarios.btnEliminar.setEnabled(true);
                viewUsuarios.btnActualizar.setEnabled(false);
                viewUsuarios.btnCancelar.setEnabled(false);
                viewUsuarios.txtBuscar.setEnabled(true);
                viewUsuarios.btnBuscar.setEnabled(true);
                viewUsuarios.txtBuscar.setText("");

            } else {
                JOptionPane.showMessageDialog(viewUsuarios, "Error al actualizar Usuario");
            }
        }
    }

    public void darBaja() {
        int fila = viewUsuarios.tablaResumen.getSelectedRow();
        viewUsuarios.jtpGestionUsuarios.setSelectedIndex(0);

        if (fila == -1) {
            JOptionPane.showMessageDialog(viewUsuarios, "Debe seleccionar un Usuario");
        } else {
            int id = Integer.parseInt((String) viewUsuarios.tablaResumen.getValueAt(fila, 0).toString());
            if (usuarioDao.DarBaja(id)) {
                JOptionPane.showMessageDialog(viewUsuarios, "Usuario dado de baja correctamente");
                limpiarCasilleros(); //limpiar casilleros textField
                limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                listar(viewUsuarios.tablaResumen);
                viewUsuarios.txtBuscar.setEnabled(true);
                viewUsuarios.btnBuscar.setEnabled(true);
                viewUsuarios.txtBuscar.setText("");

            } else {
                JOptionPane.showMessageDialog(viewUsuarios, "No se puede dar de baja"
                        + " porque el Usuario ya esta inactivo");
                viewUsuarios.txtBuscar.setEnabled(true);
                viewUsuarios.btnBuscar.setEnabled(true);
                viewUsuarios.txtBuscar.setText("");
            }
        }
    }

    /*public void eliminar(){
        int fila = viewUsuario.tablaResumen.getSelectedRow();
                        
        if(fila == -1){
            JOptionPane.showMessageDialog(viewUsuario, "Debe seleccionar un Usuario");
        } else {
            int id = Integer.parseInt((String)viewUsuario.tablaResumen.getValueAt(fila, 0).toString());
            usuarioDao.Eliminar(id);
            JOptionPane.showMessageDialog(viewUsuario, "Usuario eliminado correctamente");
        }
    }*/
public void buscar(JTable tabla) {
    this.modeloTabla = (DefaultTableModel) tabla.getModel();
    String nombreUsuario = viewUsuarios.txtBuscar.getText();
    if (nombreUsuario.matches("^[a-zA-Z ]+$")) {
        // Solo contiene caracteres, puedes realizar la búsqueda
        List<Usuario> usuarios = usuarioDao.buscar(nombreUsuario);

        if (usuarios == null || usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(viewUsuarios, "No se encontró ningún usuario con ese nombre.");
            viewUsuarios.btnCancelar.setEnabled(true);
        } else {
            for (Usuario usuario : usuarios) {
                Object[] objeto = new Object[7];

                objeto[0] = usuario.getId();
                objeto[1] = usuario.getUsuario();
                objeto[2] = usuario.getPassword();
                objeto[3] = usuario.getNombre();
                objeto[4] = usuario.getEstado();
                objeto[5] = usuario.getLast_session();
                objeto[6] = usuario.getRol().getNombre();

                modeloTabla.addRow(objeto);
            }
            viewUsuarios.tablaResumen.setModel(modeloTabla);
            viewUsuarios.btnCancelar.setEnabled(true);
        }
    } else {
        // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
        JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
        viewUsuarios.btnCancelar.setEnabled(true);
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewUsuarios.btnNuevo) {
            limpiarCasilleros();
            viewUsuarios.jtpGestionUsuarios.setSelectedIndex(1);
            viewUsuarios.btnGuardar.setEnabled(true);
            viewUsuarios.btnNuevo.setEnabled(false);
            viewUsuarios.btnEditar.setEnabled(false);
            viewUsuarios.btnEliminar.setEnabled(false);
            viewUsuarios.btnActualizar.setEnabled(false);
            viewUsuarios.txtBuscar.setEnabled(false);
            viewUsuarios.btnBuscar.setEnabled(false);
            viewUsuarios.btnCancelar.setEnabled(true);
            viewUsuarios.txtBuscar.setText("");
        }
        if (e.getSource() == viewUsuarios.btnGuardar) {
            guardar();
        }
        //Poner los datos que se selecciona en la tabla en los casilleros
        if (e.getSource() == viewUsuarios.btnEditar) {
            llenarCasillerosEditar();
        }
        if (e.getSource() == viewUsuarios.btnActualizar) {
            actualizar();
        }
        if (e.getSource() == viewUsuarios.btnEliminar) {
            darBaja();
        }
        if (e.getSource() == viewUsuarios.btnCancelar) {
            limpiarTabla();
            limpiarCasilleros();
            listar(viewUsuarios.tablaResumen);
            viewUsuarios.txtBuscar.setText("");
            viewUsuarios.jtpGestionUsuarios.setSelectedIndex(0);
            viewUsuarios.btnCancelar.setEnabled(false);
            viewUsuarios.btnGuardar.setEnabled(false);
            viewUsuarios.btnNuevo.setEnabled(true);
            viewUsuarios.btnEditar.setEnabled(true);
            viewUsuarios.btnEliminar.setEnabled(true);
            viewUsuarios.btnActualizar.setEnabled(false);
            viewUsuarios.txtBuscar.setEnabled(true);
            viewUsuarios.btnBuscar.setEnabled(true);
        }
        if (e.getSource() == viewUsuarios.btnBuscar) {
            limpiarTabla();
            buscar(viewUsuarios.tablaResumen);
        }
    }

    //MÉTODOS AUXILIAR
    public void limpiarCasilleros() {
        viewUsuarios.txtId.setText("");
        viewUsuarios.txtUsuario.setText("");
        viewUsuarios.txtPassword.setText("");
        viewUsuarios.txtConfirmarPassword.setText("");
        viewUsuarios.txtNombre.setText("");
        viewUsuarios.cboEstado.setSelectedIndex(0);
        viewUsuarios.cboRol.setSelectedIndex(0);
    }

    public void limpiarTabla() {
        for (int i = 0; i < viewUsuarios.tablaResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }
}
