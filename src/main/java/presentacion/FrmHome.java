package presentacion;

import aplicacion.CtrlGestionAlumnos;
import aplicacion.CtrlGestionApoderados;
import aplicacion.CtrlGestionCursos;
import aplicacion.CtrlGestionDocentes;
import aplicacion.CtrlGestionUsuarios;
import aplicacion.CtrlLogin;
import aplicacion.CtrlGestionGrados;
import aplicacion.CtrlMatriculacion;
import aplicacion.CtrlPagos;
import aplicacion.CtrlVisualizarNotas;
import javax.swing.JFrame;

public class FrmHome extends javax.swing.JFrame {

    FrmGestionDocentes viewDocentes;
    FrmGestionUsuarios viewUsuarios;
    FrmGestionAlumnos viewAlumnos;
    FrmGestionApoderados viewApoderados;
    FrmGestionCursos viewCursos;
    FrmGestionGrados viewgrados;
    FrmMatriculacion viewMatriculas;
    FrmvizualizarNotas viewVerNotas;
    FrmPagos viewPagos;

    public FrmHome() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpHome = new javax.swing.JDesktopPane();
        lblNombre = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        subGestionUsuarios = new javax.swing.JMenuItem();
        subGestionDocentes = new javax.swing.JMenuItem();
        subGestionAlumnos = new javax.swing.JMenuItem();
        subGestionApoderados = new javax.swing.JMenuItem();
        subGestionCursos = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuProceso = new javax.swing.JMenu();
        subMatriculacion = new javax.swing.JMenuItem();
        subPagos = new javax.swing.JMenuItem();
        subNotas = new javax.swing.JMenuItem();
        menuCerrarSesion = new javax.swing.JMenu();
        subSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jdpHome.setBackground(new java.awt.Color(235, 243, 250));

        lblNombre.setFont(new java.awt.Font("Stylus BT", 1, 24)); // NOI18N
        lblNombre.setText("**Nombre**");

        lblRol.setFont(new java.awt.Font("Stylus BT", 1, 24)); // NOI18N
        lblRol.setText("**Rol");

        jdpHome.setLayer(lblNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpHome.setLayer(lblRol, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpHomeLayout = new javax.swing.GroupLayout(jdpHome);
        jdpHome.setLayout(jdpHomeLayout);
        jdpHomeLayout.setHorizontalGroup(
            jdpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpHomeLayout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(lblNombre)
                .addGap(230, 230, 230)
                .addComponent(lblRol)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jdpHomeLayout.setVerticalGroup(
            jdpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdpHomeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jdpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblRol))
                .addContainerGap(454, Short.MAX_VALUE))
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

        subGestionAlumnos.setText("Gestión de Alumnos");
        subGestionAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGestionAlumnosActionPerformed(evt);
            }
        });
        menuArchivo.add(subGestionAlumnos);

        subGestionApoderados.setText("Gestión de Apoderados");
        subGestionApoderados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGestionApoderadosActionPerformed(evt);
            }
        });
        menuArchivo.add(subGestionApoderados);

        subGestionCursos.setText("Gestión de Cursos");
        subGestionCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGestionCursosActionPerformed(evt);
            }
        });
        menuArchivo.add(subGestionCursos);

        jMenuItem1.setText("Gestion de Grados");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem1);

        jMenuBar1.add(menuArchivo);

        menuProceso.setText("Proceso");

        subMatriculacion.setText("Matriculación");
        subMatriculacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMatriculacionActionPerformed(evt);
            }
        });
        menuProceso.add(subMatriculacion);

        subPagos.setText("Pagos");
        subPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subPagosActionPerformed(evt);
            }
        });
        menuProceso.add(subPagos);

        subNotas.setText("Notas");
        subNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNotasActionPerformed(evt);
            }
        });
        menuProceso.add(subNotas);

        jMenuBar1.add(menuProceso);

        menuCerrarSesion.setText("Cerrar sesión");

        subSalir.setText("Salir");
        subSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subSalirActionPerformed(evt);
            }
        });
        menuCerrarSesion.add(subSalir);

        jMenuBar1.add(menuCerrarSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpHome)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpHome)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //PATRÓN SINGLETON
    private void subGestionDocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionDocentesActionPerformed
        if (viewDocentes == null || viewDocentes.isClosed()) {
            viewDocentes = new FrmGestionDocentes();
            CtrlGestionDocentes controlDocentes = new CtrlGestionDocentes(viewDocentes);

            jdpHome.add(viewDocentes); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewDocentes.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewDocentes.getHeight()) / 2;
            viewDocentes.setLocation(x, y);

            controlDocentes.iniciar();
        }
    }//GEN-LAST:event_subGestionDocentesActionPerformed

    private void subGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionUsuariosActionPerformed
        if (viewUsuarios == null || viewUsuarios.isClosed()) {
            viewUsuarios = new FrmGestionUsuarios();
            CtrlGestionUsuarios controlUsuarios = new CtrlGestionUsuarios(viewUsuarios);

            jdpHome.add(viewUsuarios); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewUsuarios.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewUsuarios.getHeight()) / 2;
            viewUsuarios.setLocation(x, y);

            controlUsuarios.iniciar();
        }
    }//GEN-LAST:event_subGestionUsuariosActionPerformed

    private void subGestionAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionAlumnosActionPerformed
        if (viewAlumnos == null || viewAlumnos.isClosed()) {
            viewAlumnos = new FrmGestionAlumnos();
            CtrlGestionAlumnos controlAlumnos = new CtrlGestionAlumnos(viewAlumnos);

            jdpHome.add(viewAlumnos); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewAlumnos.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewAlumnos.getHeight()) / 2;
            viewAlumnos.setLocation(x, y);

            controlAlumnos.iniciar();
        }
    }//GEN-LAST:event_subGestionAlumnosActionPerformed

    private void subSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subSalirActionPerformed
        this.dispose();
        VistaLogin vL = new VistaLogin();
        CtrlLogin cL = new CtrlLogin(vL);

        cL.iniciar();
    }//GEN-LAST:event_subSalirActionPerformed

    private void subGestionApoderadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionApoderadosActionPerformed
        if (viewApoderados == null || viewApoderados.isClosed()) {
            viewApoderados = new FrmGestionApoderados();
            CtrlGestionApoderados controlApoderados = new CtrlGestionApoderados(viewApoderados);

            jdpHome.add(viewApoderados); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewApoderados.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewApoderados.getHeight()) / 2;
            viewApoderados.setLocation(x, y);

            controlApoderados.iniciar();
        }
    }//GEN-LAST:event_subGestionApoderadosActionPerformed

    private void subGestionCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGestionCursosActionPerformed
        if (viewCursos == null || viewCursos.isClosed()) {
            viewCursos = new FrmGestionCursos();
            CtrlGestionCursos controlCursos = new CtrlGestionCursos(viewCursos);
            CtrlGestionGrados CG = new CtrlGestionGrados();
            CG.listarGrados(viewCursos.cb_IDGrado);

            jdpHome.add(viewCursos); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewCursos.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewCursos.getHeight()) / 2;
            viewCursos.setLocation(x, y);

            controlCursos.iniciar();
        }
    }//GEN-LAST:event_subGestionCursosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (viewgrados == null || viewgrados.isClosed()) {
            viewgrados = new FrmGestionGrados();
            CtrlGestionGrados controlGrados = new CtrlGestionGrados(viewgrados);

            jdpHome.add(viewgrados); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewgrados.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewgrados.getHeight()) / 2;
            viewgrados.setLocation(x, y);

            controlGrados.iniciar();
        }     }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void subMatriculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMatriculacionActionPerformed
        if (viewMatriculas == null || viewMatriculas.isClosed()) {
            viewMatriculas = new FrmMatriculacion();
            CtrlMatriculacion controlMatriculas = new CtrlMatriculacion(viewMatriculas);

            jdpHome.add(viewMatriculas); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewMatriculas.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewMatriculas.getHeight()) / 2;
            viewMatriculas.setLocation(x, y);

            controlMatriculas.iniciar();
        }
    }//GEN-LAST:event_subMatriculacionActionPerformed

    private void subPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subPagosActionPerformed
        if (viewPagos == null || viewPagos.isClosed()) {
            viewPagos = new FrmPagos();
            CtrlPagos controlPagos = new CtrlPagos(viewPagos);

            jdpHome.add(viewPagos); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewPagos.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewPagos.getHeight()) / 2;
            viewPagos.setLocation(x, y);

            controlPagos.iniciar();
        }
    }//GEN-LAST:event_subPagosActionPerformed

    private void subNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNotasActionPerformed
        if (viewVerNotas == null || viewVerNotas.isClosed()) {
            viewVerNotas = new FrmvizualizarNotas();
            CtrlVisualizarNotas controlVerNotas = new CtrlVisualizarNotas(viewVerNotas);

            jdpHome.add(viewVerNotas); //Agregamos al JDesktopPane

            //Para que aparezca en el centro
            int x = (jdpHome.getWidth() - viewVerNotas.getWidth()) / 2;
            int y = (jdpHome.getHeight() - viewVerNotas.getHeight()) / 2;
            viewVerNotas.setLocation(x, y);
            controlVerNotas.iniciar();
            controlVerNotas.llenarComboBox();

        }
    }//GEN-LAST:event_subNotasActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* COMENTADO PARA QUE NO SE EJECUTE
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHome().setVisible(true);
            }
        });
         */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jdpHome;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblRol;
    public javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuCerrarSesion;
    public javax.swing.JMenu menuProceso;
    public javax.swing.JMenuItem subGestionAlumnos;
    public javax.swing.JMenuItem subGestionApoderados;
    public javax.swing.JMenuItem subGestionCursos;
    public javax.swing.JMenuItem subGestionDocentes;
    public javax.swing.JMenuItem subGestionUsuarios;
    public javax.swing.JMenuItem subMatriculacion;
    public javax.swing.JMenuItem subNotas;
    public javax.swing.JMenuItem subPagos;
    private javax.swing.JMenuItem subSalir;
    // End of variables declaration//GEN-END:variables
}
