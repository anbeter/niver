/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * p_prox_nivers.java
 *
 * Created on 24/04/2009, 17:15:24
 */

package nivers;

/**
 *
 * @author 8741417
 */
public class p_prox_nivers extends javax.swing.JPanel {

    /** Creates new form p_prox_nivers */
    public p_prox_nivers(String s) {
        initComponents();
        carregarNomes(s);
    }

    public static String aniv_escrito(String d){
        String aux1,aux2 = new String();
        aux1 = d.substring(0, 2);   // mm-dd    <mm>
        aux2 = d.substring(3,5);    // mm-dd    <dd>
        d = aux2+"/"+aux1;          // dd-mm
        return d;   //yyyy-mm-dd
    }

    public void carregarNomes(String qrAD){
        int qt=0;
        String p[][] = null;
        conectar cone = new conectar();
        int ic = cone.init();
        qt = cone.ret_qt_pessoas(qrAD);
        p = new String[qt][2];
        p = cone.ret_pessoas_data(qrAD); // passando a query
        cone.disconectar();
        int i =0;
        for (int ri=0; ri<qt; ri++){
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTProximos.getModel();
//                p[ri][1] = conectar.converterData_en_pt(p[ri][1]);
//                p[ri][1] += "/2009";
                p[ri][1] = p_prox_nivers.aniv_escrito(p[ri][1]);
                dtm.addRow(new Object[]{p[ri][0],p[ri][1]});
      }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTProximos = new javax.swing.JTable();

        jTProximos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Aniversário"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTProximos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTProximosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTProximos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTProximosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTProximosMouseClicked
        int linha = jTProximos.getSelectedRow();
        //int coluna = jTProximos.getSelectedColumn();
        int coluna = 0;
        String aux2 = null;
        aux2= (String) jTProximos.getValueAt(linha, coluna);
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTProximos.getModel();
        nivers.spu = aux2;
    }//GEN-LAST:event_jTProximosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTProximos;
    // End of variables declaration//GEN-END:variables

}
