/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

/**
 *
 * @author USER
 */
public class FrmvizualizarNotas extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmGestionNotas
     */
    public FrmvizualizarNotas() {
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

        jtpVisualizarNotas = new javax.swing.JTabbedPane();
        jgestionNotas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNotas = new javax.swing.JTable();
        jIngresoNotas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCcurso = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTbim1 = new javax.swing.JTextField();
        jTbim2 = new javax.swing.JTextField();
        jTbim3 = new javax.swing.JTextField();
        jTbim4 = new javax.swing.JTextField();
        jBregistrar = new javax.swing.JButton();
        jBeditar = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jBmostrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBListar = new javax.swing.JButton();
        jCboAlumnos = new javax.swing.JComboBox<>();
        jBExcelGenerator = new javax.swing.JButton();
        jBnuevo = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);

        jTableNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso", "Bimestre1", "Bimestre2", "Bimestre3", "Bimestre 4", "Promedio", "Condicion"
            }
        ));
        jScrollPane1.setViewportView(jTableNotas);

        javax.swing.GroupLayout jgestionNotasLayout = new javax.swing.GroupLayout(jgestionNotas);
        jgestionNotas.setLayout(jgestionNotasLayout);
        jgestionNotasLayout.setHorizontalGroup(
            jgestionNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 721, Short.MAX_VALUE)
            .addGroup(jgestionNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jgestionNotasLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        jgestionNotasLayout.setVerticalGroup(
            jgestionNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
            .addGroup(jgestionNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jgestionNotasLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );

        jtpVisualizarNotas.addTab("Notas", jgestionNotas);

        jLabel3.setText("Curso:");

        jLabel4.setText("Bimestre 1:");

        jCcurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCcurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCcursoActionPerformed(evt);
            }
        });

        jLabel5.setText("Bimestre 2:");

        jLabel6.setText("Bimestre 3:");

        jLabel7.setText("Bimestre 4:");

        jTbim4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTbim4ActionPerformed(evt);
            }
        });

        jBregistrar.setText("Registrar");

        jBeditar.setText("Editar");

        jBcancelar.setText("Cancelar");

        jBmostrar.setText("Mostrar");

        javax.swing.GroupLayout jIngresoNotasLayout = new javax.swing.GroupLayout(jIngresoNotas);
        jIngresoNotas.setLayout(jIngresoNotasLayout);
        jIngresoNotasLayout.setHorizontalGroup(
            jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIngresoNotasLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jIngresoNotasLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTbim4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jIngresoNotasLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTbim3))
                    .addGroup(jIngresoNotasLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTbim2))
                    .addGroup(jIngresoNotasLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTbim1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jIngresoNotasLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBeditar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBcancelar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBmostrar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBregistrar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(95, 95, 95))
        );
        jIngresoNotasLayout.setVerticalGroup(
            jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIngresoNotasLayout.createSequentialGroup()
                .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jIngresoNotasLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jBregistrar)
                        .addGap(18, 18, 18)
                        .addComponent(jBmostrar)
                        .addGap(26, 26, 26)
                        .addComponent(jBeditar)
                        .addGap(30, 30, 30)
                        .addComponent(jBcancelar))
                    .addGroup(jIngresoNotasLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jCcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTbim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTbim2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTbim3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jIngresoNotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jTbim4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(234, Short.MAX_VALUE))
        );

        jtpVisualizarNotas.addTab("Ingresar nueva Nota", jIngresoNotas);

        jLabel1.setText("Alumno:");

        jBListar.setText("listar");

        jCboAlumnos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jBExcelGenerator.setText("Generar Excel");

        jBnuevo.setText("Nuevo / Modificar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBnuevo)
                    .addComponent(jBExcelGenerator)
                    .addComponent(jBListar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jCboAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCboAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jBListar)
                .addGap(28, 28, 28)
                .addComponent(jBExcelGenerator)
                .addGap(37, 37, 37)
                .addComponent(jBnuevo)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpVisualizarNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtpVisualizarNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTbim4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTbim4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTbim4ActionPerformed

    private void jCcursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCcursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCcursoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBExcelGenerator;
    public javax.swing.JButton jBListar;
    public javax.swing.JButton jBcancelar;
    public javax.swing.JButton jBeditar;
    public javax.swing.JButton jBmostrar;
    public javax.swing.JButton jBnuevo;
    public javax.swing.JButton jBregistrar;
    public javax.swing.JComboBox<String> jCboAlumnos;
    public javax.swing.JComboBox<String> jCcurso;
    private javax.swing.JPanel jIngresoNotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableNotas;
    public javax.swing.JTextField jTbim1;
    public javax.swing.JTextField jTbim2;
    public javax.swing.JTextField jTbim3;
    public javax.swing.JTextField jTbim4;
    private javax.swing.JPanel jgestionNotas;
    public javax.swing.JTabbedPane jtpVisualizarNotas;
    // End of variables declaration//GEN-END:variables
}
