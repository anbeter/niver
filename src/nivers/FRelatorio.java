/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FRelatorio.java
 *
 * Created on 14/07/2009, 12:09:17
 */

package nivers;

import javax.swing.JOptionPane;

/**
 *
 * @author 8741417
 */
public class FRelatorio extends javax.swing.JFrame {

    /** Creates new form FRelatorio */
    public FRelatorio() {
        initComponents();
        setLocationRelativeTo(null); //CENTRALIZANDO PAINEL
    }

    public void limpar(){
        jTable1.selectAll();
        int[] aux = jTable1.getSelectedRows();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
        for(int i = (aux.length-1); i >= 0; --i){
            dtm.removeRow(aux[i]);
        }    
    }

    public void cartao_s_pronome_seq(String idnivers){
        String ano = CalcDatas.ano();
         int i = 0;
        if (!idnivers.equals(""))
             i = conectar.cartao_s_pronome_seq(idnivers,ano);
        else
            JOptionPane.showMessageDialog(null, "Nenhum aniversariante selecionado!", "..::Aniversariante não selecionado::..", JOptionPane.INFORMATION_MESSAGE);
    }

    public void carregarNomes(String qrAD){
        limpar();
        int qt=0;
        String p[][] = null;
        conectar cone = new conectar();
        int ic = cone.init();
        qt = cone.ret_qt_pessoas(qrAD);
        p = new String[qt][3];
        p = cone.ret_id_pessoas_data(qrAD); // passando a query
        cone.disconectar();
        int i =0;
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
        for (int ri=0; ri<qt; ri++){
//                p[ri][1] = conectar.converterData_en_pt(p[ri][1]);
//                p[ri][1] += "/2009";
                p[ri][1] = p_prox_nivers.aniv_escrito(p[ri][1]);
                dtm.addRow(new Object[]{p[ri][2], p[ri][0],p[ri][1],true });
      }
    }

    public void intervalo_nivers(){

            String data_hoje = new String();
            String data_hoje2 = new String();
            String data_lim = new String();
            String data_lim2 = new String();

            data_hoje = String.valueOf(jFTFDataNiver.getText()); // dd-MM-yyyy
            data_hoje = conectar.converterData_pt_en(data_hoje); // yyyy-MM-dd

            data_lim = String.valueOf(jFTFDataNiver2.getText()); // dd-MM-yyyy
            data_lim = conectar.converterData_pt_en(data_lim); // yyyy-MM-dd

            data_hoje = data_hoje.substring(5, 10); // MM-dd
            data_lim = data_lim.substring(5, 10);   // MM-dd

            String mes_hj = new String();
            String dia_hj = new String();
            String mes_lim  = new String();
            String dia_lim = new String();

            mes_hj = data_hoje.substring(0,2);
            dia_hj = data_hoje.substring(3,5);
            mes_lim = data_lim.substring(0,2);
            dia_lim = data_lim.substring(3,5);

            int i_mes_hj = Integer.parseInt(mes_hj);
            int i_dia_hj = Integer.parseInt(dia_hj);
            int i_mes_lim = Integer.parseInt(mes_lim);
            int i_dia_lim = Integer.parseInt(dia_lim);

//            JOptionPane.showMessageDialog(null, "mes_hj = "+ String.valueOf(i_mes_hj) +"\ndia_hj = "+ String.valueOf(i_dia_hj)+"\nmes_lim = " +String.valueOf(i_mes_lim)+"\ndia_lim = "+ String.valueOf(i_dia_lim) );
          String qr = "WHERE (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_hoje+"' AND '"+data_lim+"' ) ";
          /*verificação se virou o ano ou não*/
          if (((i_mes_hj == i_mes_lim) && (i_dia_hj >= i_dia_lim)) || (i_mes_hj > i_mes_lim)){
                data_lim2 = "12-31";
                data_hoje2 = "01-01";
                qr = "WHERE (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_hoje+"' AND '"+data_lim2+"') OR (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_hoje2+"' AND '"+data_lim+"')";
            }
            carregarNomes(qr);

    }

    public void prox_nivers(){
        String dias = new String();
        dias = String.valueOf(jTFDias.getText().toString());
//        VERIFICAR AQUI SE ALTRAPASSA O FINAL DO ANO, CASO SIM, FAZER UM CONSULTAS COM "AND" PARA ATÉ O FINAL DO ANO E DO INÍCIO DO ANO ATÉ O LIMITE DA DATA.ALTRAPASSA
        if ((null != dias) && (!dias.equals(""))){
            int di = Integer.parseInt(dias);
            if (365 < di)
                di = 365;
            String data_hoje = new String();
            String data_hoje2 = new String();
            String data_lim = new String();
            String data_lim2 = new String();
            data_hoje = CalcDatas.hoje();   //yyyy-MM-dd
            data_lim = CalcDatas.soma_now_dia(di);
            data_hoje = data_hoje.substring(5, 10);
            data_lim = data_lim.substring(5, 10);

            String mes_hj = new String();
            String dia_hj = new String();
            String mes_lim  = new String();
            String dia_lim = new String();

            mes_hj = data_hoje.substring(0,2);
            dia_hj = data_hoje.substring(3,5);
            mes_lim = data_lim.substring(0,2);
            dia_lim = data_lim.substring(3,5);

            int i_mes_hj = Integer.parseInt(mes_hj);
            int i_dia_hj = Integer.parseInt(dia_hj);
            int i_mes_lim = Integer.parseInt(mes_lim);
            int i_dia_lim = Integer.parseInt(dia_lim);

//            JOptionPane.showMessageDialog(null, "mes_hj = "+ String.valueOf(i_mes_hj) +"\ndia_hj = "+ String.valueOf(i_dia_hj)+"\nmes_lim = " +String.valueOf(i_mes_lim)+"\ndia_lim = "+ String.valueOf(i_dia_lim) );
          String qr = "WHERE (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_hoje+"' AND '"+data_lim+"' ) ";
          /*verificação se virou o ano ou não*/
          if (((i_mes_hj == i_mes_lim) && (i_dia_hj >= i_dia_lim)) || (i_mes_hj > i_mes_lim)){
                data_lim2 = "12-31";
                data_hoje2 = "01-01";
                qr = "WHERE (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_hoje+"' AND '"+data_lim2+"') OR (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_hoje2+"' AND '"+data_lim+"')";
            }
            carregarNomes(qr);
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

        jBOk = new javax.swing.JButton();
        jBCancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLInicio = new javax.swing.JLabel();
        jFTFDataNiver = new javax.swing.JFormattedTextField();
        jComboBoxData = new comp.CalendarComboBox();
        jLFim = new javax.swing.JLabel();
        jFTFDataNiver2 = new javax.swing.JFormattedTextField();
        jComboBoxData2 = new comp.CalendarComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFDias = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jBImprimir = new javax.swing.JButton();
        jBInverte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("..:: Imprimir Cartões em Sequência ::..");
        setName("..:: Imprimir Cartões em Sequência ::.."); // NOI18N

        jBOk.setText("Pesquisar");
        jBOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBOkActionPerformed(evt);
            }
        });

        jBCancel.setText("Voltar");
        jBCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Aniversário", "Imprimir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
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
        jScrollPane1.setViewportView(jTable1);

        jLInicio.setText("Data de início");

        jComboBoxData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxData.setFocusable(false);
        jComboBoxData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDataItemStateChanged(evt);
            }
        });

        jLFim.setText("Data final");

        jComboBoxData2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxData2.setFocusable(false);
        jComboBoxData2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxData2ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pesquisar aniversariantes por intervalo de dias");

        jLabel2.setText("Digite a quantidade de dias para frente");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 8));
        jLabel4.setText("(Somente números)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pesquisar próximos aniversariantes");

        jBImprimir.setText("Imprimir");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });

        jBInverte.setText("Inverter Seleção");
        jBInverte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInverteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLInicio)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jFTFDataNiver, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBoxData, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(22, 22, 22)
                                            .addComponent(jLFim)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jFTFDataNiver2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBoxData2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFDias, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jBInverte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                        .addComponent(jBOk)
                        .addGap(8, 8, 8)
                        .addComponent(jBImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTFDataNiver2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFTFDataNiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLInicio)
                        .addComponent(jLFim)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBOk)
                    .addComponent(jBImprimir)
                    .addComponent(jBInverte)
                    .addComponent(jBCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBOkActionPerformed
        if (!(jFTFDataNiver2.getText().toString().equals("")) && !(jFTFDataNiver.getText().toString().equals("")))
            intervalo_nivers();
        else if (!(jTFDias.getText().toString().equals("")))
            prox_nivers();
}//GEN-LAST:event_jBOkActionPerformed

    private void jBCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelActionPerformed
        this.setVisible(false);
        nivers.ni.setVisible(true);
//        System.exit(0);
}//GEN-LAST:event_jBCancelActionPerformed

    private void jComboBoxDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDataItemStateChanged
        jFTFDataNiver.setText(String.valueOf(jComboBoxData.getSelectedItem().toString()));
        String dataini = jFTFDataNiver.getText();
        dataini = conectar.converterData_pt_en(dataini);
        dataini = CalcDatas.soma_data_dia(dataini, 1);
        dataini = conectar.converterData_en_pt(dataini);
//        JOptionPane.showMessageDialog(null, dataini);
        if (jFTFDataNiver2.getText().equals("")){
            //jFTFDataNiver2.setText(String.valueOf(jComboBoxData.getSelectedItem().toString()));
            jComboBoxData2.receberData(dataini);
            jFTFDataNiver2.setText(dataini);
        }
}//GEN-LAST:event_jComboBoxDataItemStateChanged

    private void jComboBoxData2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxData2ItemStateChanged
        jFTFDataNiver2.setText(String.valueOf(jComboBoxData2.getSelectedItem().toString()));
}//GEN-LAST:event_jComboBoxData2ItemStateChanged

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed
        String param_id = new String();
        param_id = "";
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
        int linhas = dtm.getRowCount();
        int aux = 0;
        for (int i = 0; i < linhas; i++){
            if (dtm.getValueAt(i, 3).equals(true)){
                if(0 == aux){
                    param_id += String.valueOf(dtm.getValueAt(i, 0));
                    aux++;
                } else{
                    param_id += ","+String.valueOf(dtm.getValueAt(i, 0));
                }
            }
        }
        if (0 != aux)
            this.cartao_s_pronome_seq(param_id);
    }//GEN-LAST:event_jBImprimirActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int linha = jTable1.getSelectedRow();
        int coluna = jTable1.getSelectedColumn();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
/*
        int linha = jTable1.getSelectedRow();
        int[] arraylinhas = new int[linha];
        arraylinhas = jTable1.getSelectedRows();
        for (int i = 0; i < linha; i++){
            JOptionPane.showMessageDialog(null, String.valueOf(arraylinhas[i]));
        }
*/
        if (3 == coluna){
            if (dtm.getValueAt(linha, 3).equals(true)){
                dtm.setValueAt(false, linha, 3);
            } else
                dtm.setValueAt(true, linha, 3);
        }
        nivers.spu = (String) jTable1.getValueAt(linha, 1);
        if (dtm.getValueAt(linha, 3).equals(true)){
            dtm.setValueAt(false, linha, 3);
        } else
            dtm.setValueAt(true, linha, 3);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jBInverteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInverteActionPerformed
        int qtlinha = jTable1.getRowCount();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)jTable1.getModel();
        for (int i = 0; i < qtlinha; i++){
            if (dtm.getValueAt(i, 3).equals(true)){
                dtm.setValueAt(false, i, 3);
            } else
                dtm.setValueAt(true, i, 3);
        }
}//GEN-LAST:event_jBInverteActionPerformed

/*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRelatorio().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancel;
    private javax.swing.JButton jBImprimir;
    private javax.swing.JButton jBInverte;
    private javax.swing.JButton jBOk;
    private comp.CalendarComboBox jComboBoxData;
    private comp.CalendarComboBox jComboBoxData2;
    private javax.swing.JFormattedTextField jFTFDataNiver;
    private javax.swing.JFormattedTextField jFTFDataNiver2;
    private javax.swing.JLabel jLFim;
    private javax.swing.JLabel jLInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTFDias;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
