package presentacion;


import aplicacion.CtrlGestionAlumnos;
import aplicacion.CtrlLogin;
import aplicacion.CtrlGestionUsuarios;
import dominio.entidades.Imagen;

public class Home extends javax.swing.JFrame {
    
    //VistaGestionDocentes op01 = new VistaGestionDocentes();
    
    public Home() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        //jDesktopPane1.add(op01);
        
        //IMAGEN EN PANEL. DEBERÍA ESTAR EN CTRLHOME.JAVA
        int x = panelHome.getWidth();
        int y = panelHome.getHeight();
        
        //colocar el nombre y la ruta de la imagen
        String archivo = "/img/Home.png";
        
        //Instancia de la clase que hemos creado
        Imagen img = new Imagen(x, y, archivo);
        panelHome.add(img); //Enviamos la imagen
        panelHome.repaint(); //Mostramos la imagen en el JPanel
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        panelHome = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        subGestionUsuarios = new javax.swing.JMenuItem();
        subGestionDocentes = new javax.swing.JMenuItem();
        subGestionEstudiantes = new javax.swing.JMenuItem();
        menuProceso = new javax.swing.JMenu();
        subMatriculacion = new javax.swing.JMenuItem();
        menuDocentes = new javax.swing.JMenu();
        subAgregarNotas = new javax.swing.JMenuItem();
        subListarAlumnos = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenu();
        subCerrarSesion = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombre.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lblNombre.setText("*Nombre*");

        lblRol.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lblRol.setText("*Rol*");

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(480, Short.MAX_VALUE))
        );

        menuArchivo.setText("Archivo");

        subGestionUsuarios.setText("Gestión de Usuarios");
        subGestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGestionUsuariosActionPerformed(evt);
            }
        });
        menuArchivo.add(subGestionUsuarios);

        subGestionDocentes.setText("Gestión de Docentes");
        subGestionDocentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGestionDocentesActionPerformed(evt);
            }
        });
        menuArchivo.add(subGestionDocentes);

        subGestionEstudiantes.setText("Gestión de Alumnos");
        subGestionEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGestionEstudiantesActionPerformed(evt);
            }
        });
        menuArchivo.add(subGestionEstudiantes);

        jMenuBar1.add(menuArchivo);

        menuProceso.setText("Proceso");

        subMatriculacion.setText("Matriculación");
        menuProceso.add(subMatriculacion);

        jMenuBar1.add(menuProceso);

        menuDocentes.setText("Docentes");

        subAgregarNotas.setText("Agregar notas");
        menuDocentes.add(subAgregarNotas);

        subListarAlumnos.setText("Listar alumnos");
        subListarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subListarAlumnosActionPerformed(evt);
            }
        });
        menuDocentes.add(subListarAlumnos);

        jMenuBar1.add(menuDocentes);

        menuSalir.setText("Salir");

        subCerrarSesion.setText("Cerrar sesión");
        subCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCerrarSesionActionPerformed(evt);
            }
        });
        menuSalir.add(subCerrarSesion);

        jMenuBar1.add(menuSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionUsuariosActionPerformed
        /*VistaGestionUsuarios v = new VistaGestionUsuarios();
        CtrlGestionUsuarios c = new CtrlGestionUsuarios(v);
        
        v.setVisible(true);
        v.setLocationRelativeTo(null);*/
    }//GEN-LAST:event_subGestionUsuariosActionPerformed

    private void subListarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subListarAlumnosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subListarAlumnosActionPerformed

    private void subGestionDocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionDocentesActionPerformed
        /*
        VistaGestionDocentes vGD = new VistaGestionDocentes();
        CrtlGestionDocentes cGD = new CrtlGestionDocentes(vGD);
        
        cGD.iniciar();*/
    }//GEN-LAST:event_subGestionDocentesActionPerformed

    private void subGestionEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionEstudiantesActionPerformed
        /*VistaGestionAlumnos vGA= new VistaGestionAlumnos();
        CtrlGestionAlumnos cGA = new CtrlGestionAlumnos(vGA);
        
        cGA.iniciar();*/
    }//GEN-LAST:event_subGestionEstudiantesActionPerformed

    private void subCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCerrarSesionActionPerformed
        this.dispose();
        VistaLogin vL = new VistaLogin();
        CtrlLogin cL = new CtrlLogin(vL);
        
        cL.iniciar();
    }//GEN-LAST:event_subCerrarSesionActionPerformed

    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblRol;
    public javax.swing.JMenu menuArchivo;
    public javax.swing.JMenu menuDocentes;
    public javax.swing.JMenu menuProceso;
    public javax.swing.JMenu menuSalir;
    public javax.swing.JPanel panelHome;
    public javax.swing.JMenuItem subAgregarNotas;
    private javax.swing.JMenuItem subCerrarSesion;
    public javax.swing.JMenuItem subGestionDocentes;
    public javax.swing.JMenuItem subGestionEstudiantes;
    public javax.swing.JMenuItem subGestionUsuarios;
    public javax.swing.JMenuItem subListarAlumnos;
    public javax.swing.JMenuItem subMatriculacion;
    // End of variables declaration//GEN-END:variables
}
