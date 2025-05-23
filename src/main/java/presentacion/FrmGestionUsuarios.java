/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

/**
 *
 * @author USUARIO
 */
public class FrmGestionUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmGestionUsuarios
     */
    public FrmGestionUsuarios() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpGestionUsuarios = new javax.swing.JTabbedPane();
        jpUsuariosRegistrados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResumen = new javax.swing.JTable();
        jpNuevoModificar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmarPassword = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        cboEstado = new javax.swing.JComboBox<>();
        cboRol = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jtpGestionUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuario", "Contraseña", "Nombre", "Estado", "Última sesión", "Rol"
            }
        ));
        jScrollPane1.setViewportView(tablaResumen);

        javax.swing.GroupLayout jpUsuariosRegistradosLayout = new javax.swing.GroupLayout(jpUsuariosRegistrados);
        jpUsuariosRegistrados.setLayout(jpUsuariosRegistradosLayout);
        jpUsuariosRegistradosLayout.setHorizontalGroup(
            jpUsuariosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsuariosRegistradosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpUsuariosRegistradosLayout.setVerticalGroup(
            jpUsuariosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsuariosRegistradosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpGestionUsuarios.addTab("Usuarios registrados", jpUsuariosRegistrados);

        jLabel1.setText("Id:");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jLabel4.setText("<html>Confirmar contraseña:</html>");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Estado:");

        jLabel7.setText("Rol:");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccionar>", "Activo", "Inactivo" }));

        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccionar>", "Administrador", "Secretaria", "Docente" }));

        javax.swing.GroupLayout jpNuevoModificarLayout = new javax.swing.GroupLayout(jpNuevoModificar);
        jpNuevoModificar.setLayout(jpNuevoModificarLayout);
        jpNuevoModificarLayout.setHorizontalGroup(
            jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNuevoModificarLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtConfirmarPassword)
                    .addComponent(txtUsuario)
                    .addComponent(txtNombre)
                    .addComponent(cboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(346, Short.MAX_VALUE))
        );
        jpNuevoModificarLayout.setVerticalGroup(
            jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNuevoModificarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpNuevoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jtpGestionUsuarios.addTab("Nuevo / Modificar", jpNuevoModificar);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 12))); // NOI18N

        btnNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\nuevo (2).png")); // NOI18N
        btnNuevo.setText("Nuevo");

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\guardar (1).jpg")); // NOI18N
        btnGuardar.setText("Guardar");

        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\editar (1).png")); // NOI18N
        btnEditar.setText("Editar");

        btnActualizar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\ok (1).png")); // NOI18N
        btnActualizar.setText("Ok");

        btnEliminar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\baja (1).png")); // NOI18N
        btnEliminar.setText("Dar de baja");

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\cerrar (1).jpg")); // NOI18N
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addGap(12, 12, 12)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 12))); // NOI18N

        jLabel8.setText("Id:");

        btnBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Alyn\\OneDrive\\Documentos\\6° CICLO\\INTEGRADOR\\Colegio_V29\\Colegio\\src\\main\\java\\imagen\\buscar (1).png")); // NOI18N
        btnBuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtpGestionUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtpGestionUsuarios)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cboEstado;
    public javax.swing.JComboBox<String> cboRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel jpNuevoModificar;
    public javax.swing.JPanel jpUsuariosRegistrados;
    public javax.swing.JTabbedPane jtpGestionUsuarios;
    public javax.swing.JTable tablaResumen;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JPasswordField txtConfirmarPassword;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
