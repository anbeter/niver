/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * p_aniversariantes_go.java
 *
 * Created on 27/04/2009, 16:52:39
 */

package nivers;

/**
 *
 * @author 8741417
 */
public class p_aniversariantes_go extends javax.swing.JPanel {
    // quantidade de linhas que irá ter na tabela
    public static int qt2;  // o valor será (foi) atribuido em conectar.ret_aniversariantes_dias
    /** Creates new form p_aniversariantes_go */
    public p_aniversariantes_go(String[][] param, String s) {
        initComponents();
        carregarNomes(param,s);
    }

    public void carregarNomes(String[][] param, String ano){
//        int qt=0;
        conectar cone = new conectar();
        //qt = cone.ret_qt_g_dias(ano);
        int i =0;

        for (int ri=0; ri<qt2; ri++){
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTProximos.getModel();
//                p[ri][1] = conectar.converterData_en_pt(p[ri][1]);
//                p[ri][1] += "/2009";
//                param[ri][1] = p_prox_nivers.aniv_escrito(param[ri][1]);
                dtm.addRow(new Object[]{param[ri][0],param[ri][1]});
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTProximosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTProximosMouseClicked
        int linha = jTProximos.getSelectedRow();
        int coluna = jTProximos.getSelectedColumn();
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
