package aplicacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.entidades.Apoderado;
import dominio.entidades.Validacion;
import persistencia.ApoderadoDAO;
import presentacion.FrmGestionApoderados;

public class CtrlGestionApoderados implements ActionListener{

    Apoderado apoderado = new Apoderado(); //modelo
    ApoderadoDAO apoderadoDao = new ApoderadoDAO(); //modelo
    FrmGestionApoderados viewApoderados = new FrmGestionApoderados(); //vista
    Validacion validacion = new Validacion();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlGestionApoderados(FrmGestionApoderados view) {
        this.viewApoderados = view;
        //BOTONES
        this.viewApoderados.btnNuevo.addActionListener(this);
        this.viewApoderados.btnGuardar.addActionListener(this);
        this.viewApoderados.btnEditar.addActionListener(this);
        this.viewApoderados.btnActualizar.addActionListener(this);
        this.viewApoderados.btnEliminar.addActionListener(this);
        this.viewApoderados.btnCancelar.addActionListener(this);
        this.viewApoderados.btnBuscar.addActionListener(this);
                
        viewApoderados.txtId.setEnabled(false);
        viewApoderados.btnGuardar.setEnabled(false);
        viewApoderados.btnActualizar.setEnabled(false);
        viewApoderados.btnCancelar.setEnabled(false);
        
        listar(viewApoderados.tablaResumen); //para listar apenas ejecutemos el programa
    }
    
    
    public void iniciar(){
        viewApoderados.setTitle("Gestion de Apoderados");
        viewApoderados.setVisible(true);
    }
    

    public void listar(JTable tabla){
        this.modeloTabla = (DefaultTableModel)tabla.getModel(); //para que se vea la tabla al ejecutar
        List<Apoderado> lista = apoderadoDao.listar(); //para que liste en la tabla
        Object[] objeto = new Object[10]; //[14]ponemos la cantidad de columnas
        
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getPrimerNombre();
            objeto[2] = lista.get(i).getSegundoNombre();
            objeto[3] = lista.get(i).getApellidoPaterno();
            objeto[4] = lista.get(i).getApellidoMaterno();
            objeto[5] = lista.get(i).getGenero();
            objeto[6] = lista.get(i).getDocumentoIdentidad().getTipoDocumento();
            objeto[7] = lista.get(i).getDocumentoIdentidad().getNumeroDocumento(); 
            objeto[8] = lista.get(i).getCelular();
            objeto[9] = lista.get(i).getCorreo(); 
            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewApoderados.tablaResumen.setModel(modeloTabla);
    }

    
    public void guardar(){
        // Validaciones
        if (viewApoderados.txtPrimerNombre.getText().equals("")|| viewApoderados.txtSegundoNombre.getText().equals("")
                || viewApoderados.cboTipoDocumento.getSelectedIndex()==0 || viewApoderados.cboGenero.getSelectedIndex()==0 
                || viewApoderados.txtCelular.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe llenar todos los campos");
            
        } else {
        
            if(apoderadoDao.existeApoderado(viewApoderados.txtPrimerNombre.getText(), viewApoderados.txtSegundoNombre.getText(), 
                                      viewApoderados.txtApellidoPaterno.getText(), viewApoderados.txtApellidoMaterno.getText()
                                        ,viewApoderados.txtNumeroDocumento.getText()) == 0){
                    if (validacion.esEmail(viewApoderados.txtCorreo.getText())) {
                    // Enviamos los datos al modelo para insertar a la BD.
                    String primerNombre = viewApoderados.txtPrimerNombre.getText();
                    String segundoNombre = viewApoderados.txtSegundoNombre.getText();
                    String apellidoPaterno = viewApoderados.txtApellidoPaterno.getText();
                    String apellidoMaterno = viewApoderados.txtApellidoMaterno.getText();
                    String genero = viewApoderados.cboGenero.getSelectedItem().toString();
                    String tipoDocumento = viewApoderados.cboTipoDocumento.getSelectedItem().toString();
                    String numeroDocumento = viewApoderados.txtNumeroDocumento.getText();
                    String celular = viewApoderados.txtCelular.getText();
                    String correo = viewApoderados.txtCorreo.getText();

                    apoderado.setPrimerNombre(primerNombre);
                    apoderado.setSegundoNombre(segundoNombre);
                    apoderado.setApellidoPaterno(apellidoPaterno);
                    apoderado.setApellidoMaterno(apellidoMaterno);
                    apoderado.setGenero(genero);
                    apoderado.getDocumentoIdentidad().setTipoDocumento(tipoDocumento);
                    apoderado.getDocumentoIdentidad().setNumeroDocumento(numeroDocumento);
                    apoderado.setCelular(celular);
                    apoderado.setCorreo(correo);

                    //Como es boolean esto devuelve verdadero
                    if (apoderadoDao.insertar(apoderado)) {
                        JOptionPane.showMessageDialog(null, "Apoderado guardado correctamente");
                        limpiarCasilleros(); // Para que limpie los campos en caso se haya registrado
                            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                            listar(viewApoderados.tablaResumen);

                            viewApoderados.jtpGestionApoderados.setSelectedIndex(0);
                            viewApoderados.btnGuardar.setEnabled(false);
                            viewApoderados.btnNuevo.setEnabled(true);
                            viewApoderados.btnEditar.setEnabled(true);
                            viewApoderados.btnEliminar.setEnabled(true);
                            viewApoderados.btnActualizar.setEnabled(false); 
                            viewApoderados.btnCancelar.setEnabled(false);
                            viewApoderados.txtBuscar.setEnabled(true);
                            viewApoderados.btnBuscar.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar Apoderado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El Apoderado ya existe");
            }
        }
    }
    
    
    public void llenarCasillerosEditar(){
        int fila = viewApoderados.tablaResumen.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(viewApoderados, "Debe seleccionar una fila");
            viewApoderados.jtpGestionApoderados.setSelectedIndex(0);
        } else {
            int id = Integer.parseInt((String)viewApoderados.tablaResumen.getValueAt(fila, 0).toString());
            String nom1 = (String)viewApoderados.tablaResumen.getValueAt(fila, 1);
            String nom2 = (String)viewApoderados.tablaResumen.getValueAt(fila, 2);
            String ape1 = (String)viewApoderados.tablaResumen.getValueAt(fila, 3);
            String ape2 = (String)viewApoderados.tablaResumen.getValueAt(fila, 4);
            String gen = (String)viewApoderados.tablaResumen.getValueAt(fila, 5);
            String tipoDo = (String)viewApoderados.tablaResumen.getValueAt(fila, 6);
            String numDo = (String)viewApoderados.tablaResumen.getValueAt(fila, 7);
            String cel = (String)viewApoderados.tablaResumen.getValueAt(fila, 8);
            String correo = (String)viewApoderados.tablaResumen.getValueAt(fila, 9);

            viewApoderados.txtId.setText(""+id);
            viewApoderados.txtPrimerNombre.setText(nom1);
            viewApoderados.txtSegundoNombre.setText(nom2);
            viewApoderados.txtApellidoPaterno.setText(ape1);
            viewApoderados.txtApellidoMaterno.setText(ape2);
            viewApoderados.cboGenero.setSelectedItem(gen);
            viewApoderados.cboTipoDocumento.setSelectedItem(tipoDo);
            viewApoderados.txtNumeroDocumento.setText(numDo);
            viewApoderados.txtCelular.setText(cel);
            viewApoderados.txtCorreo.setText(correo);

            viewApoderados.jtpGestionApoderados.setSelectedIndex(1);

            viewApoderados.btnGuardar.setEnabled(false);
            viewApoderados.btnNuevo.setEnabled(false);
            viewApoderados.btnEditar.setEnabled(false);
            viewApoderados.btnEliminar.setEnabled(false);
            viewApoderados.btnActualizar.setEnabled(true);
            viewApoderados.btnCancelar.setEnabled(true);
            viewApoderados.txtBuscar.setEnabled(false);
            viewApoderados.btnBuscar.setEnabled(false);
        }
    }

    
    
    public void actualizar(){
        if (validacion.esEmail(viewApoderados.txtCorreo.getText())) {
            int id = Integer.parseInt(viewApoderados.txtId.getText());
            String primerNombre = viewApoderados.txtPrimerNombre.getText();
            String segundoNombre = viewApoderados.txtSegundoNombre.getText();
            String apellidoPaterno = viewApoderados.txtApellidoPaterno.getText();
            String apellidoMaterno = viewApoderados.txtApellidoMaterno.getText();
            String genero = viewApoderados.cboGenero.getSelectedItem().toString();
            String tipoDocumento = viewApoderados.cboTipoDocumento.getSelectedItem().toString();
            String numeroDocumento = viewApoderados.txtNumeroDocumento.getText();
            String celular = viewApoderados.txtCelular.getText();
            String correo = viewApoderados.txtCorreo.getText();

            apoderado.setId(id);
            apoderado.setPrimerNombre(primerNombre);
            apoderado.setSegundoNombre(segundoNombre);
            apoderado.setApellidoPaterno(apellidoPaterno);
            apoderado.setApellidoMaterno(apellidoMaterno);
            apoderado.setGenero(genero);
            apoderado.getDocumentoIdentidad().setTipoDocumento(tipoDocumento);
            apoderado.getDocumentoIdentidad().setNumeroDocumento(numeroDocumento);
            apoderado.setCelular(celular);
            apoderado.setCorreo(correo);

            int respuesta = apoderadoDao.modificar(apoderado);

            if(respuesta == 1){
                JOptionPane.showMessageDialog(viewApoderados, "Apoderado actualizado correctamente");
                limpiarCasilleros(); //limpiar casilleros textField
                limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                listar(viewApoderados.tablaResumen);

                viewApoderados.jtpGestionApoderados.setSelectedIndex(0);
                viewApoderados.btnGuardar.setEnabled(false);
                viewApoderados.btnNuevo.setEnabled(true);
                viewApoderados.btnEditar.setEnabled(true);
                viewApoderados.btnEliminar.setEnabled(true);
                viewApoderados.btnActualizar.setEnabled(false); 
                viewApoderados.btnCancelar.setEnabled(false);
                viewApoderados.txtBuscar.setEnabled(true);
                viewApoderados.btnBuscar.setEnabled(true);
                viewApoderados.txtBuscar.setText("");

            } else {
                JOptionPane.showMessageDialog(viewApoderados, "Error al actualizar Apoderado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
        }
    }
    
    
    public void eliminar(){
        int fila = viewApoderados.tablaResumen.getSelectedRow();
                        
        if(fila == -1){
            JOptionPane.showMessageDialog(viewApoderados, "Debe seleccionar un docente");
            viewApoderados.jtpGestionApoderados.setSelectedIndex(0);
        } else {
            int id = Integer.parseInt((String)viewApoderados.tablaResumen.getValueAt(fila, 0).toString());
            apoderadoDao.Eliminar(id);
            JOptionPane.showMessageDialog(viewApoderados, "Alumno eliminado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewApoderados.tablaResumen);
            viewApoderados.txtBuscar.setEnabled(true);
            viewApoderados.btnBuscar.setEnabled(true);
            viewApoderados.txtBuscar.setText("");
        }
    }
    
    
    public void buscar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel();
        String apeBuscar = viewApoderados.txtBuscar.getText();
        if (apeBuscar.matches("^[a-zA-Z ]+$")) {
            // Solo contiene caracteres, puedes realizar la búsqueda
            List<Apoderado> apoderados = apoderadoDao.buscar(apeBuscar);

            if (apoderados == null || apoderados.isEmpty()) {
                JOptionPane.showMessageDialog(viewApoderados, "No se encontró ningún Apoderado con ese apellido.");
                viewApoderados.btnCancelar.setEnabled(true);
            } else {
                for (Apoderado apoderado : apoderados) {
                    Object[] objeto = new Object[10];

                    objeto[0] = apoderado.getId();
                    objeto[1] = apoderado.getPrimerNombre();
                    objeto[2] = apoderado.getSegundoNombre();
                    objeto[3] = apoderado.getApellidoPaterno();
                    objeto[4] = apoderado.getApellidoMaterno();
                    objeto[5] = apoderado.getGenero();
                    objeto[6] = apoderado.getDocumentoIdentidad().getTipoDocumento();
                    objeto[7] = apoderado.getDocumentoIdentidad().getNumeroDocumento();
                    objeto[8] = apoderado.getCelular();
                    objeto[9] = apoderado.getCorreo();

                    modeloTabla.addRow(objeto);
                }
                viewApoderados.tablaResumen.setModel(modeloTabla);
            }
        } else {
            // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
            JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
            viewApoderados.btnCancelar.setEnabled(true);
        }
    } 
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewApoderados.btnNuevo){
            limpiarCasilleros();
            viewApoderados.jtpGestionApoderados.setSelectedIndex(1);
            
            viewApoderados.btnGuardar.setEnabled(true);
            viewApoderados.btnNuevo.setEnabled(false);
            viewApoderados.btnEditar.setEnabled(false);
            viewApoderados.btnEliminar.setEnabled(false);
            viewApoderados.btnActualizar.setEnabled(false);
            viewApoderados.txtBuscar.setEnabled(false);
            viewApoderados.btnBuscar.setEnabled(false);
            viewApoderados.btnCancelar.setEnabled(true);
            viewApoderados.txtBuscar.setText("");
        }
        if(e.getSource() == viewApoderados.btnGuardar){
            guardar();
        }
        //Poner los datos que se selecciona en la tabla en los casilleros
        if(e.getSource() == viewApoderados.btnEditar){ 
            llenarCasillerosEditar();
        }
        if(e.getSource() == viewApoderados.btnActualizar){
            actualizar();
        }
        if(e.getSource() == viewApoderados.btnEliminar){
            eliminar();
        }
        if(e.getSource() == viewApoderados.btnCancelar){
            limpiarTabla();
            limpiarCasilleros();
            listar(viewApoderados.tablaResumen);
            viewApoderados.txtBuscar.setText("");
            viewApoderados.jtpGestionApoderados.setSelectedIndex(0);
            viewApoderados.btnCancelar.setEnabled(false);
            viewApoderados.btnGuardar.setEnabled(false);
            viewApoderados.btnNuevo.setEnabled(true);
            viewApoderados.btnEditar.setEnabled(true);
            viewApoderados.btnEliminar.setEnabled(true);
            viewApoderados.btnActualizar.setEnabled(false);
            viewApoderados.txtBuscar.setEnabled(true);
            viewApoderados.btnBuscar.setEnabled(true);
        }
        if(e.getSource() == viewApoderados.btnBuscar){
            limpiarTabla();
            buscar(viewApoderados.tablaResumen);
            viewApoderados.btnCancelar.setEnabled(true);
        }
        /*if(e.getSource() == viewDocentes.btnSalir){
            viewDocentes.dispose();
        }*/
    }
    
    
    public void limpiarCasilleros(){
        viewApoderados.txtId.setText("");
        viewApoderados.txtPrimerNombre.setText("");
        viewApoderados.txtSegundoNombre.setText("");
        viewApoderados.txtApellidoPaterno.setText("");
        viewApoderados.txtApellidoMaterno.setText("");
        viewApoderados.cboGenero.setSelectedIndex(0);
        viewApoderados.cboTipoDocumento.setSelectedIndex(0);
        viewApoderados.txtNumeroDocumento.setText("");
        viewApoderados.txtCelular.setText("");
        viewApoderados.txtCorreo.setText("");
    }
    
    public void limpiarTabla(){
        for (int i = 0; i < viewApoderados.tablaResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i=i-1;
        } 
    }
    
}
