package aplicacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.entidades.Docente;
import persistencia.DocenteDAO;
import dominio.entidades.Validacion;
import presentacion.FrmGestionDocentes;

public class CtrlGestionDocentes implements ActionListener{

    Docente docente = new Docente(); //modelo
    DocenteDAO docenteDao = new DocenteDAO(); //modeValidacionUsuarioidacion valValidacionUsuarionew Validacion();
    FrmGestionDocentes viewDocentes = new FrmGestionDocentes(); //vista
    Validacion validacion = new Validacion();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlGestionDocentes(FrmGestionDocentes view) {
        this.viewDocentes = view;
        //BOTONES
        this.viewDocentes.btnNuevo.addActionListener(this);
        this.viewDocentes.btnGuardar.addActionListener(this);
        this.viewDocentes.btnEditar.addActionListener(this);
        this.viewDocentes.btnActualizar.addActionListener(this);
        this.viewDocentes.btnEliminar.addActionListener(this);
        this.viewDocentes.btnCancelar.addActionListener(this);
        this.viewDocentes.btnBuscar.addActionListener(this);
                
        viewDocentes.txtId.setEnabled(false);
        viewDocentes.btnGuardar.setEnabled(false);
        viewDocentes.btnActualizar.setEnabled(false);
        viewDocentes.btnCancelar.setEnabled(false);
        
        listar(viewDocentes.tablaResumen); //para listar apenas ejecutemos el programa
    }
    
    
    public void iniciar(){
        viewDocentes.setTitle("Gestion de Docentes");
        viewDocentes.setVisible(true);
    }
    
    
    public void listar(JTable tabla){
        this.modeloTabla = (DefaultTableModel)tabla.getModel(); //para que se vea la tabla al ejecutar
        List<Docente> lista = docenteDao.listar(); //para que liste en la tabla
        Object[] objeto = new Object[11]; //[11]ponemos la cantidad de columnas
        
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getPrimerNombre();
            objeto[2] = lista.get(i).getSegundoNombre();
            objeto[3] = lista.get(i).getApellidoPaterno();
            objeto[4] = lista.get(i).getApellidoMaterno();
            objeto[5] = lista.get(i).getGenero();
            objeto[6] = lista.get(i).getDocumentoIdentidad().getTipoDocumento();
            objeto[7] = lista.get(i).getDocumentoIdentidad().getNumeroDocumento(); 
            objeto[8] = lista.get(i).getTitulo();
            objeto[9] = lista.get(i).getTelefono();
            objeto[10] = lista.get(i).getCorreo();
            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewDocentes.tablaResumen.setModel(modeloTabla);
    }
    
    
    public void guardar(){
        if (viewDocentes.txtPrimerNombre.getText().equals("")|| viewDocentes.txtApellidoPaterno.getText().equals("")
                || viewDocentes.txtTitulo.getText().equals("") || viewDocentes.cboGenero.getSelectedIndex()==0) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe llenar todos los campos");

        } else {        
            if(validacion.existeDocente(viewDocentes.txtPrimerNombre.getText(), viewDocentes.txtSegundoNombre.getText(), 
                                        viewDocentes.txtApellidoPaterno.getText(), viewDocentes.txtApellidoMaterno.getText(),
                                        viewDocentes.txtNumeroDocumento.getText())== 0){
                if (validacion.esEmail(viewDocentes.txtCorreo.getText())) {
                    // Enviamos los datos al modelo para insertar a la BD.
                    String primerNombre = viewDocentes.txtPrimerNombre.getText();
                    String segundoNombre = viewDocentes.txtSegundoNombre.getText();
                    String apellidoPaterno = viewDocentes.txtApellidoPaterno.getText();
                    String apellidoMaterno = viewDocentes.txtApellidoMaterno.getText();
                    String genero = viewDocentes.cboGenero.getSelectedItem().toString();
                    String tipoDocumento = viewDocentes.cboTipoDocumento.getSelectedItem().toString();
                    String numeroDocumento = viewDocentes.txtNumeroDocumento.getText();
                    String titulo = viewDocentes.txtTitulo.getText();
                    String telefono = viewDocentes.txtTelefono.getText();
                    String correo = viewDocentes.txtCorreo.getText();

                    docente.setPrimerNombre(primerNombre);
                    docente.setSegundoNombre(segundoNombre);
                    docente.setApellidoPaterno(apellidoPaterno);
                    docente.setApellidoMaterno(apellidoMaterno);
                    docente.setGenero(genero);
                    docente.getDocumentoIdentidad().setTipoDocumento(tipoDocumento);
                    docente.getDocumentoIdentidad().setNumeroDocumento(numeroDocumento);
                    docente.setTitulo(titulo);
                    docente.setTelefono(telefono);
                    docente.setCorreo(correo);
                    
                    if (docenteDao.insertar(docente)) {
                        JOptionPane.showMessageDialog(null, "Docente guardado correctamente");
                        limpiarCasilleros(); // Para que limpie los campos en caso se haya registrado
                        limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                        listar(viewDocentes.tablaResumen);
                        
                        viewDocentes.jtpGestionDocentes.setSelectedIndex(0);
                        viewDocentes.btnGuardar.setEnabled(false);
                        viewDocentes.btnNuevo.setEnabled(true);
                        viewDocentes.btnEditar.setEnabled(true);
                        viewDocentes.btnEliminar.setEnabled(true);
                        viewDocentes.btnActualizar.setEnabled(false);
                        viewDocentes.txtBuscar.setEnabled(true);
                        viewDocentes.btnBuscar.setEnabled(true);
                        viewDocentes.btnCancelar.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar Docente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Este docente ya existe");
            }
        }
    }
    
    
    public void llenarCasillerosEditar(){
        int fila = viewDocentes.tablaResumen.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(viewDocentes, "Debe seleccionar una fila");
                viewDocentes.jtpGestionDocentes.setSelectedIndex(0);
            } else {
                int id = Integer.parseInt((String)viewDocentes.tablaResumen.getValueAt(fila, 0).toString());
                String nom1 = (String)viewDocentes.tablaResumen.getValueAt(fila, 1);
                String nom2 = (String)viewDocentes.tablaResumen.getValueAt(fila, 2);
                String ape1 = (String)viewDocentes.tablaResumen.getValueAt(fila, 3);
                String ape2 = (String)viewDocentes.tablaResumen.getValueAt(fila, 4);
                String gen = (String)viewDocentes.tablaResumen.getValueAt(fila, 5);
                String tipoDo = (String)viewDocentes.tablaResumen.getValueAt(fila, 6);
                String numDo = (String)viewDocentes.tablaResumen.getValueAt(fila, 7);
                String tit = (String)viewDocentes.tablaResumen.getValueAt(fila, 8);
                String tel = (String)viewDocentes.tablaResumen.getValueAt(fila, 9);
                String cor = (String)viewDocentes.tablaResumen.getValueAt(fila, 10);
                
                viewDocentes.txtId.setText(""+id);
                viewDocentes.txtPrimerNombre.setText(nom1);
                viewDocentes.txtSegundoNombre.setText(nom2);
                viewDocentes.txtApellidoPaterno.setText(ape1);
                viewDocentes.txtApellidoMaterno.setText(ape2);
                viewDocentes.cboGenero.setSelectedItem(gen);
                viewDocentes.cboTipoDocumento.setSelectedItem(tipoDo);                
                viewDocentes.txtNumeroDocumento.setText(numDo);
                viewDocentes.txtTitulo.setText(tit);
                viewDocentes.txtTelefono.setText(tel);
                viewDocentes.txtCorreo.setText(cor);
                
                viewDocentes.jtpGestionDocentes.setSelectedIndex(1);
                         
                viewDocentes.btnGuardar.setEnabled(false);
                viewDocentes.btnNuevo.setEnabled(false);
                viewDocentes.btnEditar.setEnabled(false);
                viewDocentes.btnEliminar.setEnabled(false);
                viewDocentes.btnActualizar.setEnabled(true);
                viewDocentes.btnCancelar.setEnabled(true);
                viewDocentes.txtBuscar.setEnabled(false);
                viewDocentes.btnBuscar.setEnabled(false);
            }
    }
    
    
    public void actualizar(){
        if (validacion.esEmail(viewDocentes.txtCorreo.getText())) {
            int id = Integer.parseInt(viewDocentes.txtId.getText());
            String nombre1 = viewDocentes.txtPrimerNombre.getText();
            String nombre2 = viewDocentes.txtSegundoNombre.getText();
            String apellido1 = viewDocentes.txtApellidoPaterno.getText();
            String apellido2 = viewDocentes.txtApellidoMaterno.getText();
            String genero = viewDocentes.cboGenero.getSelectedItem().toString();
            String tipoDocumento = viewDocentes.cboTipoDocumento.getSelectedItem().toString();
            String numeroDocumento = viewDocentes.txtNumeroDocumento.getText();
            String titulo = viewDocentes.txtTitulo.getText();
            String telefono = viewDocentes.txtTelefono.getText();
            String correo = viewDocentes.txtCorreo.getText();

            docente.setId(id);
            docente.setPrimerNombre(nombre1);
            docente.setSegundoNombre(nombre2);
            docente.setApellidoPaterno(apellido1);
            docente.setApellidoMaterno(apellido2);
            docente.setGenero(genero);
            docente.getDocumentoIdentidad().setTipoDocumento(tipoDocumento);
            docente.getDocumentoIdentidad().setNumeroDocumento(numeroDocumento);
            docente.setTitulo(titulo);
            docente.setTelefono(telefono);
            docente.setCorreo(correo);

            int respuesta = docenteDao.modificar(docente);

            if(respuesta == 1){
                JOptionPane.showMessageDialog(viewDocentes, "Docente actualizado correctamente");
                limpiarCasilleros(); //limpiar casilleros textField
                limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                listar(viewDocentes.tablaResumen);
                viewDocentes.jtpGestionDocentes.setSelectedIndex(0);
                viewDocentes.btnGuardar.setEnabled(false);
                viewDocentes.btnNuevo.setEnabled(true);
                viewDocentes.btnEditar.setEnabled(true);
                viewDocentes.btnEliminar.setEnabled(true);
                viewDocentes.btnActualizar.setEnabled(false); 
                viewDocentes.btnCancelar.setEnabled(false);
                viewDocentes.txtBuscar.setEnabled(true);
                viewDocentes.btnBuscar.setEnabled(true);
                viewDocentes.txtBuscar.setText("");

            } else {
                JOptionPane.showMessageDialog(viewDocentes, "Error al actualizar docente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
        }
    }
    
    
    public void eliminar(){
        int fila = viewDocentes.tablaResumen.getSelectedRow();
        viewDocentes.jtpGestionDocentes.setSelectedIndex(0);
                        
        if(fila == -1){
            JOptionPane.showMessageDialog(viewDocentes, "Debe seleccionar un docente");
        } else {
            int id = Integer.parseInt((String)viewDocentes.tablaResumen.getValueAt(fila, 0).toString());
            docenteDao.Eliminar(id);
            JOptionPane.showMessageDialog(viewDocentes, "Docente eliminado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewDocentes.tablaResumen);
            viewDocentes.txtBuscar.setEnabled(true);
            viewDocentes.btnBuscar.setEnabled(true);
            viewDocentes.txtBuscar.setText("");
        }
    }
    
    
public void buscar(JTable tabla) {
    this.modeloTabla = (DefaultTableModel) tabla.getModel();
    String apeBuscar = viewDocentes.txtBuscar.getText();
    if (apeBuscar.matches("^[a-zA-Z ]+$")) {
        // Solo contiene caracteres, puedes realizar la búsqueda
        List<Docente> docentes = docenteDao.buscar(apeBuscar);

        if (docentes == null || docentes.isEmpty()) {
            JOptionPane.showMessageDialog(viewDocentes, "No se encontró ningún docente con ese apellido.");
            viewDocentes.btnCancelar.setEnabled(true);
        } else {
            for (Docente docente : docentes) {
                Object[] objeto = new Object[11];

                objeto[0] = docente.getId();
                objeto[1] = docente.getPrimerNombre();
                objeto[2] = docente.getSegundoNombre();
                objeto[3] = docente.getApellidoPaterno();
                objeto[4] = docente.getApellidoMaterno();
                objeto[5] = docente.getGenero();
                objeto[6] = docente.getDocumentoIdentidad().getTipoDocumento();
                objeto[7] = docente.getDocumentoIdentidad().getNumeroDocumento();
                objeto[8] = docente.getTitulo();
                objeto[9] = docente.getTelefono();
                objeto[10] = docente.getCorreo();

                modeloTabla.addRow(objeto);
            }
            viewDocentes.tablaResumen.setModel(modeloTabla);
        }
    } else {
        // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
        JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
        viewDocentes.btnCancelar.setEnabled(true);
    }
}

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewDocentes.btnNuevo){
            limpiarCasilleros();
            viewDocentes.jtpGestionDocentes.setSelectedIndex(1);
            viewDocentes.btnGuardar.setEnabled(true);
            viewDocentes.btnNuevo.setEnabled(false);
            viewDocentes.btnEditar.setEnabled(false);
            viewDocentes.btnEliminar.setEnabled(false);
            viewDocentes.btnActualizar.setEnabled(false);
            viewDocentes.txtBuscar.setEnabled(false);
            viewDocentes.btnBuscar.setEnabled(false);
            viewDocentes.btnCancelar.setEnabled(true);
            viewDocentes.txtBuscar.setText("");
        }
        if(e.getSource() == viewDocentes.btnGuardar){
            guardar();           
        }
        //Poner los datos que se selecciona en la tabla en los casilleros
        if(e.getSource() == viewDocentes.btnEditar){ 
            llenarCasillerosEditar();
        }
        if(e.getSource() == viewDocentes.btnActualizar){
            actualizar();  
        }
        if(e.getSource() == viewDocentes.btnEliminar){
            eliminar();
        }
        if(e.getSource() == viewDocentes.btnCancelar){
            limpiarTabla();
            limpiarCasilleros();
            listar(viewDocentes.tablaResumen);
            viewDocentes.txtBuscar.setText("");
            viewDocentes.jtpGestionDocentes.setSelectedIndex(0);
            viewDocentes.btnCancelar.setEnabled(false);
            viewDocentes.btnGuardar.setEnabled(false);
            viewDocentes.btnNuevo.setEnabled(true);
            viewDocentes.btnEditar.setEnabled(true);
            viewDocentes.btnEliminar.setEnabled(true);
            viewDocentes.btnActualizar.setEnabled(false);
            viewDocentes.txtBuscar.setEnabled(true);
            viewDocentes.btnBuscar.setEnabled(true);
        }
        if(e.getSource() == viewDocentes.btnBuscar){
            limpiarTabla();
            buscar(viewDocentes.tablaResumen);
        }
        /*if(e.getSource() == viewDocentes.btnSalir){
            viewDocentes.dispose();
        }*/
    }
    
    
    public void limpiarCasilleros(){
        viewDocentes.txtId.setText("");
        viewDocentes.txtPrimerNombre.setText("");
        viewDocentes.txtSegundoNombre.setText("");
        viewDocentes.txtApellidoPaterno.setText("");
        viewDocentes.txtApellidoMaterno.setText("");
        viewDocentes.cboGenero.setSelectedIndex(0);
        viewDocentes.cboTipoDocumento.setSelectedIndex(0);
        viewDocentes.txtNumeroDocumento.setText("");
        viewDocentes.txtTitulo.setText("");
        viewDocentes.txtTelefono.setText("");
        viewDocentes.txtCorreo.setText("");
    }
    
    public void limpiarTabla(){
        for (int i = 0; i < viewDocentes.tablaResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i=i-1;
        }
    }
    
    

}