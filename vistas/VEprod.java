/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controladores.Cprod;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

/**
 *
 * @author elise
 */
public class VEprod extends javax.swing.JFrame {

    private Cprod cp;
    private int id;

    public VEprod() {
        initComponents();
        llenarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_proddb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btn_recuperar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos - borrar ");

        jPanel1.setBackground(new java.awt.Color(237, 174, 73));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 400));

        lbl_proddb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_proddb.setForeground(new java.awt.Color(209, 16, 58));
        lbl_proddb.setText("Productos dados de baja");

        tabla.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setRowHeight(40);
        jScrollPane1.setViewportView(tabla);

        btn_recuperar.setBackground(new java.awt.Color(224, 224, 224));
        btn_recuperar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_recuperar.setForeground(new java.awt.Color(209, 16, 58));
        btn_recuperar.setText("Recuperar");
        btn_recuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recuperarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_recuperar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(lbl_proddb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_proddb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_recuperar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_recuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recuperarActionPerformed
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            Producto prod = new Producto();

            prod.setId_producto(Integer.parseInt(tabla.getValueAt(fila, 0).toString()));
            prod.setDescripcion(tabla.getValueAt(fila, 1).toString());
            prod.setStock(Float.parseFloat(tabla.getValueAt(fila, 2).toString()));
            prod.setPrecio(Float.parseFloat(tabla.getValueAt(fila, 3).toString()));
            cp.recuperarProducto(prod);
            llenarTabla();
            JOptionPane.showMessageDialog(null, "Se recuperó correctamente el producto");
        } else {
            JOptionPane.showMessageDialog(null, "No se selecionó ningún producto");
        }
    }//GEN-LAST:event_btn_recuperarActionPerformed

    public void llenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        cp = new Cprod();
        ArrayList<Producto> list_prod = cp.enviarProductos();
        modelo.addColumn("Id_producto");
        modelo.addColumn("Descripción");
        modelo.addColumn("Stock");
        modelo.addColumn("Precio");
        tabla.setModel(modelo);
        String[] fila = new String[4];
        for (int i = 0; i < list_prod.size(); i++) {
            fila[0] = String.valueOf(list_prod.get(i).getId_producto());
            fila[1] = list_prod.get(i).getDescripcion();
            fila[2] = String.valueOf(list_prod.get(i).getStock());
            fila[3] = String.valueOf(list_prod.get(i).getPrecio());
            modelo.addRow(fila);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VEprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VEprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VEprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VEprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VEprod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_recuperar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_proddb;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}