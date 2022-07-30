/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * painel_localizar.java
 *
 * Created on 25/03/2009, 08:59:28
 */

package nivers;

//import javax.swing.JOptionPane;

/**
 *
 * @author 8741417
 */
public class painel_localizar extends javax.swing.JPanel {

    /** Creates new form painel_localizar */
    public painel_localizar(String s) {
        initComponents();
        carregarNomes(s);
    }

    public void carregarNomes(String qrAD){
        int qt=0;
        String p[] = null;
        conectar cone = new conectar();
        int ic = cone.init();
        qt = cone.ret_qt_pessoas(qrAD);
        p = new String[qt];
        p = cone.ret_pessoas(qrAD);
        cone.disconectar();
        int i =0;
        for (int ri=0; ri<qt; ri++){
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
                dtm.addRow(new Object[]{p[ri]});
      }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSPLocalizar = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jSPLocalizar.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSPLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSPLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int linha = jTable1.getSelectedRow();
        int coluna = jTable1.getSelectedColumn();
        String aux2 = null;
        aux2= (String) jTable1.getValueAt(linha, coluna);
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
        nivers.spu = aux2;
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jSPLocalizar;
    public javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
