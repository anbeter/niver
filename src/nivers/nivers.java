package nivers;

import java.awt.event.ActionListener;
//import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
//import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class nivers extends javax.swing.JFrame {
    public static int isn = 0; // inteiro para chamar ou não o painel go()
    private JPanel registros;
    private JPanel help_pronomes;
    private JPanel JPAniversariantes;
    public static String spu = new String();    // para saber se foi selecionado alguém no localizar
    public static conectar co = new conectar();
    private conectar proximosaniversariantes = new conectar();
    private String[][] aniversarinte_dias = null;
    private static int auxi = 0;
    public static nivers ni = new nivers();
    public static P_Intervalo pin;
    public static Intervalo interv;

//    @SuppressWarnings("static-access")
    public nivers() {
//        ni.setVisible(true);

        spu = null;
        initComponents();
        setLocationRelativeTo(null); //CENTRALIZANDO PAINEL
//        ImageIcon icon = new ImageIcon("imagens/aniversarios.png");
//        jLFigura.setIcon(icon);
        carregarPronome();
    }

    public void init (){
        spu = null;
        initComponents();
        setLocationRelativeTo(null); //CENTRALIZANDO PAINEL
        carregarPronome();
    }

    public String[] ret_selecionado(String aux2) {
        conectar cone = new conectar();
        String sel[] = null;
        sel = new String[14];
        int ic = cone.init();
//        String param = new String();
//        param = "WHERE nome = \'"+ aux2+"\'";
        sel = cone.ret_selecionado(aux2);
        cone.disconectar();
        return sel;
    }

    public void anteriorproximo (int p){
        if (0 == p)
            p = co.ret_ultimo();
        String[] st = new String[14];
        String paux = new String();
        String foto = new String();
        paux = String.valueOf(p);
        st = co.ret_selecionado("WHERE cod_aniversariante = "+ paux);
        //jTFCodPessoa.setText(st[0]);
        jTFCodPessoa.setText(paux);
        if((st != null) && (st[0] != null) && !(st[0].equals(""))){
            jTFNome.setText(st[3]);
            jTFCargo.setText(st[4]);
            st[5] = conectar.converterData_en_pt(st[5]);
            jFTFDataNiver.setText(st[5]);
            jTFDias.setText(st[10]);
            jTFFone.setText(st[6]);
            jTFEmail.setText(st[7]);
            if ((st[11].equals("null")) || (st[11].equals(""))) {
                jTFFoto.setText(null);
                ImageIcon icon = new ImageIcon("\\\\\\\\srvpdc\\\\fotos\\\\aniversarios.png");
                jLFigura.setIcon(icon);
            } else {
                jTFFoto.setText(st[11]);
                //foto = jTFFoto.getText().toString();
                //edivano asdfg
                //jLFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource(st[11]))); // NOI18N
                //jLFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("\\\\\\\\srvpdc\\\\fotos\\\\2.jpg"))); // NOI18N

                ImageIcon icon = new ImageIcon("\\\\\\\\srvpdc\\\\fotos\\\\"+paux+".jpg");
                jLFigura.setIcon(icon);
                jLFigura.repaint();
                this.repaint();
            }
            jTAEndereco.setText(st[12]);
            jTAObs.setText(st[13]);
            if (st[8].equals("1"))
                jRBMasc.setSelected(true);
            else
                jRBFem.setSelected(true);
            int i = 0;
            i = Integer.parseInt(st[2]);
            i--;
            jCBPronome.setSelectedIndex(i);
            i = Integer.parseInt(st[1]);
            i--;
            jCBTipo.setSelectedIndex(i);
        }
        else{
            this.limpar();
            jTFCodPessoa.setText(paux);
        }
    }

    public int atualizar() {
        String ano = CalcDatas.ano();
        String hoje = CalcDatas.hoje();
        hoje = hoje.substring(5, 10);
        String qr = "UPDATE aniversariante SET status_aviso = \'" + ano + "\' WHERE ( DATE_FORMAT( niver, '%m-%d' ) < \'" + hoje + "\'  )";
        conectar c = new conectar();
        int i = c.atualizar(qr);
        return i;
//        c.disconectar();  estou disconectando ao atualizar
    }

    public static void fechar() {
        if (javax.swing.JOptionPane.showConfirmDialog(null, "Deseja sair do sistema?", "Finalizar", javax.swing.JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }

    public static void visivel() {
        ni.setVisible(true);
    }

    public void formrelatorio(){
        FRelatorio fr = new FRelatorio();
        fr.setVisible(true);
    }

    public void minimizar() {
        if (0 == nivers.getAuxi()) {
            nivers.setAuxi(1);
            TrayManager.systray();
        }
        ni.setVisible(false);
    }

    void limpar() {
        ImageIcon icon = new ImageIcon("\\\\\\\\srvpdc\\\\fotos\\\\aniversarios.png");
        jLFigura.setIcon(icon);
        jTAEndereco.setText("");
        jTAObs.setText("");
        jTFCargo.setText("");
        jTFCodPessoa.setText("");
        jTFDias.setText("5");
        jTFEmail.setText("");
        jTFFone.setText("");
        jTFNome.setText("");
        jComboBoxData.setSelectedItem(null);
//String.valueOf(getJCBDataSaida().getSelectedItem().toString()
        spu = "";
        jFTFDataNiver.setText("");
        jTFFoto.setText("");
    }

    public void procurararquivo(){
        String caminho_arquivo = new String();
        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();
        FileFilter TextFilter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        arquivo.addChoosableFileFilter(TextFilter);
        if(arquivo.showOpenDialog(arquivo) == javax.swing.JFileChooser.APPROVE_OPTION){
            File file = arquivo.getSelectedFile();
            caminho_arquivo = file.getPath();
            getJTFFoto().setText(caminho_arquivo);
        }
        else
            JOptionPane.showMessageDialog(null, "Arquivo Inválido","Arquivo Inválido", JOptionPane.ERROR_MESSAGE);
//        return caminho_arquivo;
    }

    private String AtualizaDataPrivate(String s) {
        jComboBoxData.receberData(s);
        return s;
    }

    protected void AtualizaDataProtected(String s) {
        this.AtualizaDataPrivate(s);
    }

    public void AtualizaDataPublic(String s) {
        this.AtualizaDataProtected(s);
    }

    public void AtualizaDataPublicStatic(String s) {
        this.AtualizaDataPublic(s);
    }

    public void selecionar (String aux_selecionado){
        spu = aux_selecionado;
            if ((spu != null) && !(spu.equals(""))) {
                this.limpar();
                String sel[] = null;
                sel = new String[14];
                String spu_aux = "WHERE nome = \'" + spu + "\'";
                sel = this.ret_selecionado(spu_aux);
                jTFCodPessoa.setText(sel[0]);
                jCBTipo.setSelectedIndex((Integer.parseInt(sel[1])) - 1);
                jCBPronome.setSelectedIndex((Integer.parseInt(sel[2])) - 1);
                jTFNome.setText(sel[3]);
                jTFCargo.setText(sel[4]);
                sel[5] = conectar.converterData_en_pt(sel[5]);
                jFTFDataNiver.setText(sel[5]);
                jComboBoxData.receberData(sel[5]);
                jTFFone.setText(sel[6]);
                jTFEmail.setText(sel[7]);
                //JOptionPane.showMessageDialog(null, "sexo = "+sel[8]);
                if (sel[8].equals("1")) {
                    jRBMasc.setSelected(true);
                    jRBFem.setSelected(false);
                } else {
                    jRBFem.setSelected(true);
                    jRBMasc.setSelected(false);
                }
                if ((sel[11].equals("null")) || (sel[11].equals(""))) {
                    jTFFoto.setText(null);
                } else {
                    jTFFoto.setText(sel[11]);
                }
                jTFDias.setText(sel[10]);
                if ((sel[12].equals("null")) || (sel[12].equals(""))) {
                    jTAEndereco.setText(null);
                } else {
                    jTAEndereco.setText(sel[12]);
                }
                if ((sel[13].equals("null")) || (sel[13].equals(""))) {
                    jTAObs.setText(null);
                } else {
                    jTAObs.setText(sel[13]);
                }
            }
    else{
        }
        //    dias = "";
    }

    public void prox_nivers(){
        String dias = new String();
//dias = "";
        dias = String.valueOf(JOptionPane.showInputDialog(null, "Quantos dias para frente você gostaria de verificar?", "..::Próximos aniversariantes::..", JOptionPane.QUESTION_MESSAGE,null,null,"5"));
//        dias = String.valueOf( JOptionPane.showInputDialog(null, "quantos dias", "tt", JOptionPane.QUESTION_MESSAGE, null, null, "1"));


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

            spu = "";
            registros = null;
            registros = new p_prox_nivers(qr);
            JOptionPane.showMessageDialog(this, this.registros, "..:: Selecione um registro ::..", JOptionPane.PLAIN_MESSAGE);
            if ((spu != null) && !(spu.equals(""))) {
                String sel[] = null;
                sel = new String[14];
                String spu_aux = "WHERE nome = \'" + spu + "\'";
                sel = this.ret_selecionado(spu_aux);
                jTFCodPessoa.setText(sel[0]);
                jCBTipo.setSelectedIndex((Integer.parseInt(sel[1])) - 1);
                jCBPronome.setSelectedIndex((Integer.parseInt(sel[2])) - 1);
                jTFNome.setText(sel[3]);
                jTFCargo.setText(sel[4]);
                sel[5] = conectar.converterData_en_pt(sel[5]);
                jFTFDataNiver.setText(sel[5]);
                jComboBoxData.receberData(sel[5]);
                jTFFone.setText(sel[6]);
                jTFEmail.setText(sel[7]);
                if (sel[8].equals("1")) {
                    jRBMasc.setSelected(true);
                } else {
                    jRBFem.setSelected(true);
                }
                jTFDias.setText(sel[10]);
                if ((sel[11].equals("null")) || (sel[11].equals(""))) {
                    jTFFoto.setText(null);
                } else {
                    jTFFoto.setText(sel[11]);
                }
                if ((sel[12].equals("null")) || (sel[12].equals(""))) {
                    jTAEndereco.setText(null);
                } else {
                    jTAEndereco.setText(sel[12]);
                }
//                JOptionPane.showMessageDialog(null, sel[13]);
                if ((sel[13].equals("null")) || (sel[13].equals("")) || (null == sel[13])) {
                    jTAObs.setText(null);
                } else {
                    jTAObs.setText(sel[13]);
                }
            }
        }else
            dias = "";
    }

    public void localizar(){
        //String qr = "WHERE nome = \'"+jTFNome.getText()+"\'";
        String qr = this.qr_localizar("");
        spu = "";
        registros = null;
        registros = new painel_localizar(qr);
//        JOptionPane.showMessageDialog(null, "passou para o painel_localizar(qr) qr = "+qr);
        JOptionPane.showMessageDialog(this, this.registros, "..:: Selecione um registro ::..", JOptionPane.PLAIN_MESSAGE);
        if ((spu != null) && !(spu.equals(""))) {
//            JOptionPane.showMessageDialog(null, "entrou aki spou != null ou \"\"");
            String sel[] = null;
            sel = new String[14];
            String spu_aux = "WHERE nome = \'" + spu + "\'";
            sel = this.ret_selecionado(spu_aux);
            jTFCodPessoa.setText(sel[0]);
            jCBTipo.setSelectedIndex((Integer.parseInt(sel[1])) - 1);
            jCBPronome.setSelectedIndex((Integer.parseInt(sel[2])) - 1);
            jTFNome.setText(sel[3]);
            jTFCargo.setText(sel[4]);
            sel[5] = conectar.converterData_en_pt(sel[5]);
            jFTFDataNiver.setText(sel[5]);
            jComboBoxData.receberData(sel[5]);
            jTFFone.setText(sel[6]);
            jTFEmail.setText(sel[7]);
            if (sel[8].equals("1")) {
                jRBMasc.setSelected(true);
            } else {
                jRBFem.setSelected(true);
            }

            jTFDias.setText(sel[10]);
            if ((sel[12].equals("null")) || (sel[12].equals(""))) {
                jTAEndereco.setText(null);
            } else {
                jTAEndereco.setText(sel[12]);
            }
            if ((sel[13].equals("null")) || (sel[13].equals(""))) {
                jTAObs.setText(null);
            } else {
                jTAObs.setText(sel[13]);
            }
        }
        //else {}
    }

    public void carregarPronome() {
        int qt = 0;
        String p[] = null;
        conectar cone = new conectar();
        int ic = cone.init();
        qt = cone.ret_qt();
        p = new String[qt];
        p = cone.ret_pronome(qt);
        cone.disconectar();
        int i = 0;
        for (int ri = 0; ri < qt; ri++) {
            jCBPronome.addItem(p[ri]);
        }
    }

    public void pronomes(){
        help_pronomes = new PronomeDeTratamento();
        JOptionPane.showMessageDialog(this, this.help_pronomes, "..:: Utilização dos pronomes de tratamento ::..", JOptionPane.PLAIN_MESSAGE);
    }

    public boolean verificarextensao(String caminho){
        


        return true;
    }

    public BufferedImage redimensionaImagem(BufferedImage imagem, Integer dim1, Integer dim2) {
        BufferedImage novaImagem = new BufferedImage(dim1, dim2, BufferedImage.TYPE_INT_RGB);
        novaImagem.getGraphics().drawImage(imagem, 0, 0, dim1, dim2, rootPane);
        return novaImagem;
    }

    public void salvarImagem(BufferedImage imagem, File dir, String name) throws IOException {
        ImageIO.write(imagem, "jpg", new File(dir, name));
    }

    public void converterImagem(File file, File dirDest, String sufix, int w, int h) throws IOException, Exception {
        if (dirDest == null) {
            dirDest = file.getParentFile();
        }
        if (dirDest.exists() == false) {
            if (dirDest.mkdir() == false) {
                throw new RuntimeException("Não foi possível criar o diretório de destino.");
            }
        }
        BufferedImage imagem = ImageIO.read(file);
        salvarImagem(redimensionaImagem(imagem, w, h), dirDest, sufix + ".jpg");
    }

    public int copiarfoto(String foto_origem, String foto_destino)  throws FileNotFoundException, IOException {
//        JOptionPane.showMessageDialog(null, foto_origem);
        File file = new File(foto_origem);
        File dirDest = new File("\\\\srvpdc\\fotos\\");
        String sufixo = foto_destino;
        int w = 76;
        int h = 79;
        try {
            converterImagem(file, dirDest, sufixo, w, h);
        } catch (IOException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao copiar/redimensionar","Erro 0049\nnivers.java", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
/*
        try {
            // Create channel on the source
            FileChannel srcChannel = new FileInputStream(foto_origem).getChannel();
            // Create channel on the destination
            FileChannel dstChannel = new FileOutputStream("\\\\srvpdc\\fotos\\"+foto_destino+".jpg").getChannel();
            // Copy file contents from source to destination
            dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
            // Close the channels
            srcChannel.close();
            dstChannel.close();
//            JOptionPane.showMessageDialog(null, "Talvez tenha dado certo");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro 0048\nErro ao copiar foto!\nnivers.java");
        }
*/
        return 1;
    }

    public int salvar(String ano) {
        String param[] = null;
        int ai3 = 0;
        param = new String[13];
        if (!(jTFCargo.getText().equals(null)) && !(jTFCargo.getText().equals("")) && !(jTFNome.getText().equals(null)) && !(jTFNome.getText().equals("")) && !(jFTFDataNiver.getText().equals(null)) && !(jFTFDataNiver.getText().equals(""))) {
            param[0] = jTFCodPessoa.getText();                  // código
            param[1] = String.valueOf(jCBTipo.getSelectedIndex() + 1); // tipo de cartão
            param[2] = String.valueOf(jCBPronome.getSelectedIndex() + 1);   // Pronome de tratamento utilizado
            param[3] = jTFNome.getText();                       // Nome da pessoa
            param[4] = jTFCargo.getText();                      // Cargo
            param[5] = jFTFDataNiver.getText();
            param[5] = conectar.converterData_pt_en(param[5]);
            param[6] = jTFFone.getText();                       // telefone do aniversariante
            if (param[6].equals("")) {
                param[6] = "0";
            }
            param[7] = jTFEmail.getText();                      // e-mail do aniversariante
            param[8] = (jRBMasc.isSelected()) ? "1" : "2";          // Sexo do aniversariante
            if ((ano.equals(null)) || (ano.equals(""))) {
                param[9] = "2008";                                  // ano do último aviso
            } else {
                param[9] = ano;
            }
            if ((jTFDias.getText().equals("")) || (jTFDias.getText().equals(null))) {
                param[10] = "5";
            } else {
                param[10] = jTFDias.getText();                  // quantos dias antes para avisar?
            }

            String foto = null;                                   // foto do aniversariante

            foto = "";
            param[11] = jTAEndereco.getText();                       // endereço
            if (param[11].equals("null")) {
                param[11] = "";
            }
            param[12] = jTAObs.getText();                       // obs
            if (param[12].equals("null")) {
                param[12] = "";
            }
            String sql = null;
            String msg = null;
            conectar c = new conectar();
            if ((param[0].equals("")) || (param[0].equals(null))) {
                if(!(jTFFoto.getText().equals("")) && !(jTFFoto.getText().equals(null))){
                    int ft = c.ultimo();
                    if (0 != ft){
                        ft++;
                        foto = "\\\\\\\\srvpdc\\\\fotos\\\\"+String.valueOf(ft)+".jpg";
                    }                
                }
                sql = "INSERT INTO aniversariante (tipo_cartao_cod_tipo_cartao, tratamento_cod_tratamento, nome, cargo, niver, fone, email, sexo, status_aviso, dias, foto, endereco, obs) " +
                        "VALUES (" + param[1] + "," + param[2] + ",\'" + param[3] + "\',\'" + param[4] + "\',\'" + param[5] + "\',\'" + param[6] + "\',\'" + param[7] + "\'," + param[8] + "," + param[9] + "," + param[10] + ",\'" + foto + "\',\'" + param[11] + "\',\'" + param[12] + "\')";
                msg = "Cadastro inserido com sucesso!!!";
                ai3 = 2;
            } else {
                ai3 = 1;
                if (!(jTFFoto.getText().toString().equals("")) && !(jTFFoto.getText().toString().equals(null)))
                    foto = "\\\\\\\\srvpdc\\\\fotos\\\\"+ String.valueOf(jTFCodPessoa.getText().toString())+".jpg";
                sql = "UPDATE aniversariante set tipo_cartao_cod_tipo_cartao=" + param[1] + ", tratamento_cod_tratamento=" + param[2] + ", nome=\'" + param[3] + "\', cargo=\'" + param[4] + "\', niver=\'" + param[5] + "\', fone=\'" + param[6] + "\', email=\'" + param[7] + "\' , sexo=\'" + param[8] + "\' ,status_aviso=\'" + param[9] + "\' ,dias=\'" + param[10] + "\' ,foto=\'" + foto + "\' ,endereco=\'" + param[11] + "\' ,obs=\'" + param[12] + "\' where cod_aniversariante =" + param[0];
                msg = "Cadastro alterado com sucesso!!!";
            }
//            JOptionPane.showMessageDialog(null, sql);
            int ai = 0;
            if ((param[0].equals(null)) || (param[0].equals(""))); else {
                ai = Integer.parseInt(param[0]);
            }
            int ai2 = c.salvar(ai, sql, msg);
            if (0 != ai2) {
                if(!(jTFFoto.getText().equals("")) && !(jTFFoto.getText().equals(null))){
                    String fotoaux = new String();
                    String fotoaux2 = new String();
                    fotoaux = jTFFoto.getText().toString();
                    if (1 != ai3){  // se não for updade mas sim insert
                        fotoaux2 = String.valueOf(c.ultimo());
                        try {
                            this.copiarfoto(fotoaux, fotoaux2);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(nivers.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(nivers.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else // se não deu erro em salvar E foi update
                    {
                        fotoaux2 = String.valueOf(jTFCodPessoa.getText().toString());
                        try {
                            this.copiarfoto(fotoaux, fotoaux2);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(nivers.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(nivers.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                this.limpar();
//            JOptionPane.showMessageDialog(null, "c.salvar retornou: "+ String.valueOf(ai2));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campos: Nome, Aniversario e Cargo são obrigatórios!", "..::Campos em branco::..", JOptionPane.WARNING_MESSAGE);
        }
        return 1;
    }

    public String qr_localizar(String s) {
        String where = "WHERE";
        String query = "";
        if (!(jTFNome.getText().equals("")) && (jTFNome.getText() != null)) {
//            JOptionPane.showMessageDialog(null, "entrou em qr_localizar jtfnome = "+jTFNome.getText());
            query += where + " nome LIKE \'%" + jTFNome.getText() + "%\' ";
            where = " AND ";
        }
        return query;
    }

    public void cartao(){
        String ano = CalcDatas.ano();
//        int i = conectar.relatorio02("04-15","11-20","04-15","2009");
        String idnivers = new String();
        idnivers = String.valueOf(jTFCodPessoa.getText());
        int i = 0;
        if (!idnivers.equals(""))
             i = conectar.cartao(idnivers,ano);
        else
            JOptionPane.showMessageDialog(null, "Nenhum aniversariante selecionado!", "..::Aniversariante não selecionado::..", JOptionPane.INFORMATION_MESSAGE);
//        int i = conectar.relatorio01("query");
    }
/*
            PARA RODAR O PROGRAMA TECLE F6
*/

    public void cartao_s_pronome(){
        String ano = CalcDatas.ano();
//        int i = conectar.relatorio02("04-15","11-20","04-15","2009");
        String idnivers = new String();
        String tratamento = new String();
        tratamento = "Senhor";
        if (this.jRBFem.isSelected()){
            tratamento = "Senhora";
        }
//        JOptionPane.showMessageDialog(null, "tratamento = "+tratamento);
        idnivers = String.valueOf(jTFCodPessoa.getText());
        int i = 0;
        if (!idnivers.equals(""))
             i = conectar.cartao_s_pronome(idnivers,ano, tratamento);
        else
            JOptionPane.showMessageDialog(null, "Nenhum aniversariante selecionado!", "..::Aniversariante não selecionado::..", JOptionPane.INFORMATION_MESSAGE);
//        int i = conectar.relatorio01("query");
    }

    public void painel_go(){
        JPAniversariantes = null;
        String ano = CalcDatas.ano();
        JPAniversariantes = new p_aniversariantes_go(aniversarinte_dias, ano);
        JOptionPane.showMessageDialog(this, this.JPAniversariantes, "..:: Selecione um registro ::..", JOptionPane.PLAIN_MESSAGE);
        if ((spu != null) && !(spu.equals("")))
            this.setVisible(true);
//        return 1;
    }

    public void go(final String data_ini, final String data_fim) {
        ActionListener action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused" +
                    "") java.awt.event.ActionEvent e) {
                    //private String[][] aniversarinte_dias = null; //variável global
                    //proximosaniversariantes é do tipo conectar //variável global
                    //aniversarinte_dias = null;
                    String esteano = CalcDatas.ano();
                    aniversarinte_dias = proximosaniversariantes.ret_aniversariantes_dias(esteano);
                    spu = "";
//                    JOptionPane.showMessageDialog(null, "niver.java:go ( \n data_ini = " + data_ini+"\n data_fim = "+ data_fim);
                    /* isn global publica statica para chamar ou não o painel_go
                     <atribuida em conectar:ret_aniversariantes_dias>*/
                    if (1 == isn){
                        painel_go();
                    }
                    /* spu string com nome da pessoa atribuída nos painéis */
                    if ((spu != null) && !(spu.equals(""))) {
                        String sel[] = null;
                        sel = new String[14];
                        String spu_aux = "WHERE nome = \'" + spu + "\'";
                        sel = ret_selecionado(spu_aux);
                        jTFCodPessoa.setText(sel[0]);
                        jCBTipo.setSelectedIndex((Integer.parseInt(sel[1])) - 1);
                        jCBPronome.setSelectedIndex((Integer.parseInt(sel[2])) - 1);
                        jTFNome.setText(sel[3]);
                        jTFCargo.setText(sel[4]);
                        sel[5] = conectar.converterData_en_pt(sel[5]);
                        jFTFDataNiver.setText(sel[5]);
                        jComboBoxData.receberData(sel[5]);
                        jTFFone.setText(sel[6]);
                        jTFEmail.setText(sel[7]);
                        if (sel[8].equals("1")) {
                            jRBMasc.isSelected();
                        } else {
                            jRBFem.isSelected();
                        }

                        jTFDias.setText(sel[10]);
                        if ((sel[12].equals("null")) || (sel[12].equals(""))) {
                            jTAEndereco.setText(null);
                        } else {
                            jTAEndereco.setText(sel[12]);
                        }
                        if ((sel[13].equals("null")) || (sel[13].equals(""))) {
                            jTAObs.setText(null);
                        } else {
                            jTAObs.setText(sel[13]);
                        }
                    }

//                VerificarNivers.verificar(qr, 5);
            }
        };      // 5200 = 5 segundos e 2 décimos
                //20000 = 20 segundos
        Timer t = new Timer(7200000, action);    // 7200000 = duas horas
                                                // 5000 = cinco segundos
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialogAvi = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLTitulo = new javax.swing.JLabel();
        jLNome = new javax.swing.JLabel();
        jLEmail = new javax.swing.JLabel();
        jLCargo = new javax.swing.JLabel();
        jRBMasc = new javax.swing.JRadioButton();
        jRBFem = new javax.swing.JRadioButton();
        jLNiver = new javax.swing.JLabel();
        jLTelefone = new javax.swing.JLabel();
        jLSexo = new javax.swing.JLabel();
        jLTratamento = new javax.swing.JLabel();
        jLTipo = new javax.swing.JLabel();
        jBLimpar = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jBLocalizar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jTFNome = new javax.swing.JTextField();
        jTFCargo = new javax.swing.JTextField();
        jTFFone = new javax.swing.JTextField();
        jTFEmail = new javax.swing.JTextField();
        jCBTipo = new javax.swing.JComboBox();
        jCBPronome = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLFigura = new javax.swing.JLabel();
        jTFCodPessoa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAEndereco = new javax.swing.JTextArea();
        jLEndereco = new javax.swing.JLabel();
        jLDias = new javax.swing.JLabel();
        jTFDias = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jBPronomes = new javax.swing.JButton();
        jBSair = new javax.swing.JButton();
        jFTFDataNiver = new javax.swing.JFormattedTextField();
        jBIntervalo = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jComboBoxData = new comp.CalendarComboBox();
        jBCartao = new javax.swing.JButton();
        jBProximos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jBAnterior = new javax.swing.JButton();
        jBPróximo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTAObs = new javax.swing.JTextArea();
        jLObs = new javax.swing.JLabel();
        jBProcurar = new javax.swing.JButton();
        jTFFoto = new javax.swing.JTextField();
        jLFoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMCadastro = new javax.swing.JMenu();
        jMISalvar = new javax.swing.JMenuItem();
        jMILimpar = new javax.swing.JMenuItem();
        jMIExcluir = new javax.swing.JMenuItem();
        jMISair = new javax.swing.JMenuItem();
        jMEditar = new javax.swing.JMenu();
        jMIFormRelatorio = new javax.swing.JMenuItem();
        jMILocalizar = new javax.swing.JMenuItem();
        jMIPróximos = new javax.swing.JMenuItem();
        jMIIntervalo = new javax.swing.JMenuItem();
        jMICartao = new javax.swing.JMenuItem();
        jMIPassou = new javax.swing.JMenuItem();
        jMNavegar = new javax.swing.JMenu();
        jMIAnterior = new javax.swing.JMenuItem();
        jMIProximo = new javax.swing.JMenuItem();
        jMAjuda = new javax.swing.JMenu();
        jMIPronomes = new javax.swing.JMenuItem();
        jMISobre = new javax.swing.JMenuItem();

        jDialogAvi.setTitle("..:: Sobre o Help Desk ::..");
        jDialogAvi.setModal(true);
        jDialogAvi.setResizable(false);

        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText("              Cadastro de Aniversariantes - Versão 0.1 \n                             (\"java\" + MySQL)\n\n                               Copyleft 2009\n\n          Edivano Mittelstädt Martins de Sousa\n     Coordenador de Tecnologia da Informação\n    edivano@adtur.to.gov.br/edivano@gmail.com\n\n     Agência de Desenvolvimento Turístico - TO\n         Diretoria de Administração e Finanças\n    Coordenadoria de Tecnologia da Informação");
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jDialogAviLayout = new javax.swing.GroupLayout(jDialogAvi.getContentPane());
        jDialogAvi.getContentPane().setLayout(jDialogAviLayout);
        jDialogAviLayout.setHorizontalGroup(
            jDialogAviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
            .addGap(0, 315, Short.MAX_VALUE)
            .addGroup(jDialogAviLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jDialogAviLayout.setVerticalGroup(
            jDialogAviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
            .addGap(0, 238, Short.MAX_VALUE)
            .addGroup(jDialogAviLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setTitle("..:: Cadastro de Aniversariantes ::..");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLTitulo.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 36));
        jLTitulo.setText("Cadastro de Aniversariantes");
        jLTitulo.setFocusable(false);

        jLNome.setText("Nome:");

        jLEmail.setText("E-mail:");

        jLCargo.setText("Cargo:");

        buttonGroup1.add(jRBMasc);
        jRBMasc.setSelected(true);
        jRBMasc.setText("Masculino");

        buttonGroup1.add(jRBFem);
        jRBFem.setText("Feminino");

        jLNiver.setText("Aniversário:");

        jLTelefone.setText("Telefone:");

        jLSexo.setText("Sexo:");

        jLTratamento.setText("Pronome de Tratamento:");

        jLTipo.setText("Tipo do cartão:");

        jBLimpar.setText("Limpar");
        jBLimpar.setToolTipText("Limpar campos.");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        jBSalvar.setText("Salvar");
        jBSalvar.setToolTipText("Salvar aniversariantes.");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBLocalizar.setText("Localizar");
        jBLocalizar.setToolTipText("Localizar aniversariante por nome.");
        jBLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLocalizarActionPerformed(evt);
            }
        });

        jBExcluir.setText("Excluir");
        jBExcluir.setToolTipText("Excluir Aniversariante");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jCBTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Impresso", "E-mail" }));

        jLFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aniversarios.png"))); // NOI18N

        jTFCodPessoa.setEnabled(false);

        jTAEndereco.setColumns(20);
        jTAEndereco.setRows(4);
        jTAEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTAEnderecoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTAEndereco);

        jLEndereco.setText("Endereço:");

        jLDias.setFont(new java.awt.Font("Tahoma", 0, 9));
        jLDias.setText("Quantos dias antes começará o aviso?");

        jTFDias.setText("5");

        jLabel3.setText("Antecedência:");

        jBPronomes.setText("Pronomes");
        jBPronomes.setToolTipText("Mostrar dicas de pronomes.");
        jBPronomes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPronomesActionPerformed(evt);
            }
        });

        jBSair.setText("Sair");
        jBSair.setToolTipText("Sair do sistema.");
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });

        try {
            jFTFDataNiver.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jBIntervalo.setText("Pesquisar Intervalo");
        jBIntervalo.setToolTipText("Lista aniversariantes entre datas");
        jBIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIntervaloActionPerformed(evt);
            }
        });

        jBAtualizar.setText("Passou");
        jBAtualizar.setToolTipText("Não mostrar mais aviso para este aniversariante.");
        jBAtualizar.setFocusable(false);
        jBAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarActionPerformed(evt);
            }
        });

        jComboBoxData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDataItemStateChanged(evt);
            }
        });

        jBCartao.setText("Cartão");
        jBCartao.setToolTipText("Gerar cartão de aniversário para este aniversariante.");
        jBCartao.setFocusable(false);
        jBCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCartaoActionPerformed(evt);
            }
        });

        jBProximos.setText("Próx Aniversariantes");
        jBProximos.setToolTipText("Mostrar próximos aniversáriantes.");
        jBProximos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProximosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 8));
        jLabel1.setText("Somente números");

        jBAnterior.setText("Anterior");
        jBAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAnteriorActionPerformed(evt);
            }
        });

        jBPróximo.setText("Próximo");
        jBPróximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPróximoActionPerformed(evt);
            }
        });

        jTAObs.setColumns(20);
        jTAObs.setRows(1);
        jTAObs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTAObsKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTAObs);

        jLObs.setText("Observação:");

        jBProcurar.setText("Procurar");
        jBProcurar.setToolTipText("Foto tem que ser em formato \"jpg\"");
        jBProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProcurarActionPerformed(evt);
            }
        });

        jLFoto.setText("Foto:");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMCadastro.setText("Cadastro");

        jMISalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMISalvar.setText("Salvar");
        jMISalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISalvarActionPerformed(evt);
            }
        });
        jMCadastro.add(jMISalvar);

        jMILimpar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMILimpar.setText("Limpar");
        jMILimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMILimparActionPerformed(evt);
            }
        });
        jMCadastro.add(jMILimpar);

        jMIExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        jMIExcluir.setText("Excluir");
        jMIExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIExcluirActionPerformed(evt);
            }
        });
        jMCadastro.add(jMIExcluir);

        jMISair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        jMISair.setText("Sair");
        jMISair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISairActionPerformed(evt);
            }
        });
        jMCadastro.add(jMISair);

        jMenuBar1.add(jMCadastro);

        jMEditar.setText("Editar");
        jMEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMEditarActionPerformed(evt);
            }
        });

        jMIFormRelatorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMIFormRelatorio.setText("Form Relatório");
        jMIFormRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIFormRelatorioActionPerformed(evt);
            }
        });
        jMEditar.add(jMIFormRelatorio);

        jMILocalizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMILocalizar.setText("Localizar");
        jMILocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMILocalizarActionPerformed(evt);
            }
        });
        jMEditar.add(jMILocalizar);

        jMIPróximos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMIPróximos.setText("Próximos Aniversariantes");
        jMIPróximos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIPróximosActionPerformed(evt);
            }
        });
        jMEditar.add(jMIPróximos);

        jMIIntervalo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMIIntervalo.setText("Pesquissar Intervalos");
        jMIIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIIntervaloActionPerformed(evt);
            }
        });
        jMEditar.add(jMIIntervalo);

        jMICartao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMICartao.setText("Cartão");
        jMICartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICartaoActionPerformed(evt);
            }
        });
        jMEditar.add(jMICartao);

        jMIPassou.setText("Passou");
        jMIPassou.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIPassouActionPerformed(evt);
            }
        });
        jMEditar.add(jMIPassou);

        jMNavegar.setText("Navegar");

        jMIAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, 0));
        jMIAnterior.setText("Anterior");
        jMIAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAnteriorActionPerformed(evt);
            }
        });
        jMNavegar.add(jMIAnterior);

        jMIProximo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, 0));
        jMIProximo.setText("Próximo");
        jMIProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIProximoActionPerformed(evt);
            }
        });
        jMNavegar.add(jMIProximo);

        jMEditar.add(jMNavegar);

        jMenuBar1.add(jMEditar);

        jMAjuda.setText("Ajuda");

        jMIPronomes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        jMIPronomes.setText("Pronomes");
        jMIPronomes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIPronomesActionPerformed(evt);
            }
        });
        jMAjuda.add(jMIPronomes);

        jMISobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMISobre.setText("Sobre");
        jMISobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISobreActionPerformed(evt);
            }
        });
        jMAjuda.add(jMISobre);

        jMenuBar1.add(jMAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLocalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBProximos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(jBPronomes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSair)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLFigura, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(653, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBIntervalo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPróximo)
                .addContainerGap(444, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLFoto))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFDias, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLDias))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTFFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBProcurar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                        .addComponent(jBCartao)
                        .addGap(30, 30, 30)
                        .addComponent(jBAtualizar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLSexo)
                                    .addComponent(jLTratamento)
                                    .addComponent(jLEndereco)
                                    .addComponent(jLTipo))
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLObs)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCBPronome, 0, 481, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRBMasc)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRBFem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLTelefone)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFCodPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLNome))
                            .addComponent(jLNiver)
                            .addComponent(jLCargo)
                            .addComponent(jLEmail))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                            .addComponent(jTFCargo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                            .addComponent(jTFEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTFFone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFTFDataNiver, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxData, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74))
                            .addComponent(jLTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLFigura, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLTitulo)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNome)
                            .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCargo)
                            .addComponent(jTFCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTFCodPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLNiver)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTFDataNiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTelefone)
                        .addComponent(jTFFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEmail)
                    .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSexo)
                    .addComponent(jRBMasc)
                    .addComponent(jRBFem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTratamento)
                    .addComponent(jCBPronome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEndereco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLObs))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLDias))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLFoto)
                    .addComponent(jBProcurar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAtualizar)
                    .addComponent(jBCartao)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBSalvar)
                        .addComponent(jBLimpar)
                        .addComponent(jBLocalizar)
                        .addComponent(jBExcluir)
                        .addComponent(jBProximos))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBSair)
                        .addComponent(jBPronomes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBIntervalo)
                    .addComponent(jBAnterior)
                    .addComponent(jBPróximo))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        this.salvar("2008");
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        fechar(1);
        if (co != null) {
            try {
                co.disconectar();
            } finally {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão!");
            }
        }
    }//GEN-LAST:event_formWindowClosed

    private void jBLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLocalizarActionPerformed
        this.localizar();
    }//GEN-LAST:event_jBLocalizarActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        if (0 == JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro: " + jTFNome.getText() + " ?", "Titulo", JOptionPane.YES_NO_OPTION)) {
//            JOptionPane.showMessageDialog(null, "voce escolheu \"0\""); //SIM
            if (!(jTFCodPessoa.getText().equals("")) && (jTFCodPessoa.getText() != null)) {
                conectar c = new conectar();
                int i = c.excluir(Integer.parseInt(jTFCodPessoa.getText()));
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Não há nenhum registro selecionado para excluir!");
            }
        }
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBPronomesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPronomesActionPerformed
        this.pronomes();
    }//GEN-LAST:event_jBPronomesActionPerformed

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        nivers.fechar();
}//GEN-LAST:event_jBSairActionPerformed

    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed
        limpar();
    }//GEN-LAST:event_jBLimparActionPerformed

    private void jComboBoxDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDataItemStateChanged
        jFTFDataNiver.setText(String.valueOf(jComboBoxData.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBoxDataItemStateChanged

    private void jTAEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAEnderecoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.setKeyCode(KeyEvent.VK_BACK_SPACE);
            jTAEndereco.transferFocus();
//            jTFDias.requestFocus();
        }
//        else if ( evt.getKeyCode() == KeyEvent.VK_ENTER){
//        }
}//GEN-LAST:event_jTAEnderecoKeyPressed

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        if((!jTFCodPessoa.equals(null)) && (!jTFCodPessoa.getText().equals(""))){
        conectar conec = new conectar();
        conec.init();
        String param = new String();
        param = String.valueOf(jTFCodPessoa.getText());
        int i = conec.desmarcar(param);
/*
        int i = this.atualizar();
        if (1 == i)
            JOptionPane.showMessageDialog (null, "Atualização concluída!" );
*/
        conec.disconectar();        
        } else
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado!", "..:: Campos em branco ::..", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void jBIntervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIntervaloActionPerformed
        interv = new Intervalo();
        interv.setVisible(true);
}//GEN-LAST:event_jBIntervaloActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        int i = this.atualizar();
        if (1 == i){
            this.minimizar();
            ni.setVisible(true);
            ni.go("", "");
        }else{
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jBCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCartaoActionPerformed
        this.cartao_s_pronome();
//        this.cartaoteste();
}//GEN-LAST:event_jBCartaoActionPerformed

    private void jBProximosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProximosActionPerformed
        this.prox_nivers();
}//GEN-LAST:event_jBProximosActionPerformed

    private void jMISalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMISalvarActionPerformed
        this.salvar("2008");
    }//GEN-LAST:event_jMISalvarActionPerformed

    private void jMILimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMILimparActionPerformed
        limpar();
    }//GEN-LAST:event_jMILimparActionPerformed

    private void jMIExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIExcluirActionPerformed
        if (0 == JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro: " + jTFNome.getText() + " ?", "Titulo", JOptionPane.YES_NO_OPTION)) {
//            JOptionPane.showMessageDialog(null, "voce escolheu \"0\""); //SIM
            if (!(jTFCodPessoa.getText().equals("")) && (jTFCodPessoa.getText() != null)) {
                conectar c = new conectar();
                int i = c.excluir(Integer.parseInt(jTFCodPessoa.getText()));
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Não há nenhum registro selecionado para excluir!");
            }
        }
    }//GEN-LAST:event_jMIExcluirActionPerformed

    private void jMISairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMISairActionPerformed
        nivers.fechar();
    }//GEN-LAST:event_jMISairActionPerformed

    private void jMILocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMILocalizarActionPerformed
        this.localizar();
    }//GEN-LAST:event_jMILocalizarActionPerformed

    private void jMIPróximosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIPróximosActionPerformed
        this.prox_nivers();
    }//GEN-LAST:event_jMIPróximosActionPerformed

    private void jMIPronomesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIPronomesActionPerformed
        this.pronomes();
    }//GEN-LAST:event_jMIPronomesActionPerformed

    private void jMEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMEditarActionPerformed
        if((!jTFCodPessoa.equals(null)) && (!jTFCodPessoa.getText().equals(""))){
        conectar conec = new conectar();
        conec.init();
        String param = new String();
        param = String.valueOf(jTFCodPessoa.getText());
        int i = conec.desmarcar(param);
/*
        int i = this.atualizar();
        if (1 == i)
            JOptionPane.showMessageDialog (null, "Atualização concluída!" );
*/
        conec.disconectar();
        } else
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado!", "..:: Campos em branco ::..", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMEditarActionPerformed

    private void jMISobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMISobreActionPerformed
       jDialogAvi.setSize(315, 260);
       jDialogAvi.setLocationRelativeTo(null);
       jDialogAvi.setVisible(true);
    }//GEN-LAST:event_jMISobreActionPerformed

    private void jMICartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMICartaoActionPerformed
        this.cartao();
    }//GEN-LAST:event_jMICartaoActionPerformed

    private void jMIPassouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIPassouActionPerformed
        if((!jTFCodPessoa.equals(null)) && (!jTFCodPessoa.getText().equals(""))){
        conectar conec = new conectar();
        conec.init();
        String param = new String();
        param = String.valueOf(jTFCodPessoa.getText());
        int i = conec.desmarcar(param);
/*
        int i = this.atualizar();
        if (1 == i)
            JOptionPane.showMessageDialog (null, "Atualização concluída!" );
*/
        conec.disconectar();
        } else
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado!", "..:: Campos em branco ::..", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMIPassouActionPerformed

    private void jMIIntervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIIntervaloActionPerformed
        interv = new Intervalo();
        interv.setVisible(true);
    }//GEN-LAST:event_jMIIntervaloActionPerformed

    private void jBAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnteriorActionPerformed
        int iaux = 0;
        if (jTFCodPessoa.getText().isEmpty()){
//            JOptionPane.showMessageDialog(null, String.valueOf(iaux));
            this.anteriorproximo(iaux);
        } else{
            iaux = Integer.parseInt(jTFCodPessoa.getText().toString());
            iaux--;
            if (0 != iaux)
                this.anteriorproximo(iaux);
        }
    }//GEN-LAST:event_jBAnteriorActionPerformed

    private void jBPróximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPróximoActionPerformed
        int iaux = 0;
        int max = co.ret_ultimo();
        if (jTFCodPessoa.getText().isEmpty()){
//            JOptionPane.showMessageDialog(null, String.valueOf(iaux));
            this.anteriorproximo(1);
        } else{
            iaux = Integer.parseInt(jTFCodPessoa.getText().toString());
            iaux++;
            if (iaux <= max)
                if (0 != iaux)
                    this.anteriorproximo(iaux);
        }
//            JOptionPane.showMessageDialog(null, "vazia");
    }//GEN-LAST:event_jBPróximoActionPerformed

    private void jMIProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIProximoActionPerformed
        int iaux = 0;
        int max = co.ret_ultimo();
        if (jTFCodPessoa.getText().isEmpty()){
            this.anteriorproximo(1);
        } else{
            iaux = Integer.parseInt(jTFCodPessoa.getText().toString());
            iaux++;
            if (iaux <= max)
                if (0 != iaux)
                    this.anteriorproximo(iaux);
        }
    }//GEN-LAST:event_jMIProximoActionPerformed

    private void jMIAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAnteriorActionPerformed
        int iaux = 0;
        if (jTFCodPessoa.getText().isEmpty()){
//            JOptionPane.showMessageDialog(null, String.valueOf(iaux));
            this.anteriorproximo(iaux);
        } else{
            iaux = Integer.parseInt(jTFCodPessoa.getText().toString());
            iaux--;
            if (0 != iaux)
                this.anteriorproximo(iaux);
        }
    }//GEN-LAST:event_jMIAnteriorActionPerformed

    private void jMIFormRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIFormRelatorioActionPerformed
        this.formrelatorio();
    }//GEN-LAST:event_jMIFormRelatorioActionPerformed

    private void jTAObsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAObsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.setKeyCode(KeyEvent.VK_BACK_SPACE);
            jTAEndereco.transferFocus();
//            jTFDias.requestFocus();
        }

    }//GEN-LAST:event_jTAObsKeyPressed

    private void jBProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProcurarActionPerformed
        this.procurararquivo();
}//GEN-LAST:event_jBProcurarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!(jTFFoto.getText().equals("")) && !(jTFFoto.getText().equals(null))){
            JOptionPane.showMessageDialog(null, "foto != null");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                //new nivers().setVisible(true);
                ni.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBAnterior;
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBCartao;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBIntervalo;
    private javax.swing.JButton jBLimpar;
    private javax.swing.JButton jBLocalizar;
    private javax.swing.JButton jBProcurar;
    private javax.swing.JButton jBPronomes;
    private javax.swing.JButton jBProximos;
    private javax.swing.JButton jBPróximo;
    private javax.swing.JButton jBSair;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCBPronome;
    private javax.swing.JComboBox jCBTipo;
    private comp.CalendarComboBox jComboBoxData;
    private javax.swing.JDialog jDialogAvi;
    private javax.swing.JFormattedTextField jFTFDataNiver;
    private javax.swing.JLabel jLCargo;
    private javax.swing.JLabel jLDias;
    private javax.swing.JLabel jLEmail;
    private javax.swing.JLabel jLEndereco;
    private javax.swing.JLabel jLFigura;
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLNiver;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLObs;
    private javax.swing.JLabel jLSexo;
    private javax.swing.JLabel jLTelefone;
    private javax.swing.JLabel jLTipo;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLTratamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMAjuda;
    private javax.swing.JMenu jMCadastro;
    private javax.swing.JMenu jMEditar;
    private javax.swing.JMenuItem jMIAnterior;
    private javax.swing.JMenuItem jMICartao;
    private javax.swing.JMenuItem jMIExcluir;
    private javax.swing.JMenuItem jMIFormRelatorio;
    private javax.swing.JMenuItem jMIIntervalo;
    private javax.swing.JMenuItem jMILimpar;
    private javax.swing.JMenuItem jMILocalizar;
    private javax.swing.JMenuItem jMIPassou;
    private javax.swing.JMenuItem jMIPronomes;
    private javax.swing.JMenuItem jMIProximo;
    private javax.swing.JMenuItem jMIPróximos;
    private javax.swing.JMenuItem jMISair;
    private javax.swing.JMenuItem jMISalvar;
    private javax.swing.JMenuItem jMISobre;
    private javax.swing.JMenu jMNavegar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButton jRBFem;
    private javax.swing.JRadioButton jRBMasc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTAEndereco;
    private javax.swing.JTextArea jTAObs;
    private javax.swing.JTextField jTFCargo;
    private javax.swing.JTextField jTFCodPessoa;
    private javax.swing.JTextField jTFDias;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFFone;
    private javax.swing.JTextField jTFFoto;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
//    private javax.swing.JFileChooser jFileChooser1;

    public javax.swing.ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    public void setButtonGroup1(javax.swing.ButtonGroup buttonGroup1) {
        this.buttonGroup1 = buttonGroup1;
    }

    public javax.swing.JButton getJBExcluir() {
        return jBExcluir;
    }

    public void setJBExcluir(javax.swing.JButton jBExcluir) {
        this.jBExcluir = jBExcluir;
    }

    public javax.swing.JButton getJBLimpar() {
        return jBLimpar;
    }

    public void setJBLimpar(javax.swing.JButton jBLimpar) {
        this.jBLimpar = jBLimpar;
    }

    public javax.swing.JButton getJBLocalizar() {
        return jBLocalizar;
    }

    public void setJBLocalizar(javax.swing.JButton jBLocalizar) {
        this.jBLocalizar = jBLocalizar;
    }

    public javax.swing.JButton getJBSalvar() {
        return jBSalvar;
    }

    public void setJBSalvar(javax.swing.JButton jBSalvar) {
        this.jBSalvar = jBSalvar;
    }

    public javax.swing.JComboBox getJCBPronome() {
        return jCBPronome;
    }

    public void setJCBPronome(javax.swing.JComboBox jCBPronome) {
        this.jCBPronome = jCBPronome;
    }

    public javax.swing.JComboBox getJCBTipo() {
        return jCBTipo;
    }

    public void setJCBTipo(javax.swing.JComboBox jCBTipo) {
        this.jCBTipo = jCBTipo;
    }

    public javax.swing.JLabel getJLCargo() {
        return jLCargo;
    }

    public void setJLCargo(javax.swing.JLabel jLCargo) {
        this.jLCargo = jLCargo;
    }

    public javax.swing.JLabel getJLEmail() {
        return jLEmail;
    }

    public void setJLEmail(javax.swing.JLabel jLEmail) {
        this.jLEmail = jLEmail;
    }

    public javax.swing.JLabel getJLFigura() {
        return jLFigura;
    }

    public void setJLFigura(javax.swing.JLabel jLFigura) {
        this.jLFigura = jLFigura;
    }

    public javax.swing.JLabel getJLNiver() {
        return jLNiver;
    }

    public void setJLNiver(javax.swing.JLabel jLNiver) {
        this.jLNiver = jLNiver;
    }

    public javax.swing.JLabel getJLNome() {
        return jLNome;
    }

    public void setJLNome(javax.swing.JLabel jLNome) {
        this.jLNome = jLNome;
    }

    public javax.swing.JLabel getJLSexo() {
        return jLSexo;
    }

    public void setJLSexo(javax.swing.JLabel jLSexo) {
        this.jLSexo = jLSexo;
    }

    public javax.swing.JLabel getJLTelefone() {
        return jLTelefone;
    }

    public void setJLTelefone(javax.swing.JLabel jLTelefone) {
        this.jLTelefone = jLTelefone;
    }

    public javax.swing.JLabel getJLTipo() {
        return jLTipo;
    }

    public void setJLTipo(javax.swing.JLabel jLTipo) {
        this.jLTipo = jLTipo;
    }

    public javax.swing.JLabel getJLTitulo() {
        return jLTitulo;
    }

    public void setJLTitulo(javax.swing.JLabel jLTitulo) {
        this.jLTitulo = jLTitulo;
    }

    public javax.swing.JLabel getJLTratamento() {
        return jLTratamento;
    }

    public void setJLTratamento(javax.swing.JLabel jLTratamento) {
        this.jLTratamento = jLTratamento;
    }
    /*
    public javax.swing.JProgressBar getJProgressBar1() {
    return jProgressBar1;
    }
    public void setJProgressBar1(javax.swing.JProgressBar jProgressBar1) {
    this.jProgressBar1 = jProgressBar1;
    }
     */

    public javax.swing.JRadioButton getJRBFem() {
        return jRBFem;
    }

    public void setJRBFem(javax.swing.JRadioButton jRBFem) {
        this.jRBFem = jRBFem;
    }

    public javax.swing.JRadioButton getJRBMasc() {
        return jRBMasc;
    }

    public void setJRBMasc(javax.swing.JRadioButton jRBMasc) {
        this.jRBMasc = jRBMasc;
    }

    public javax.swing.JSeparator getJSeparator1() {
        return jSeparator1;
    }

    public void setJSeparator1(javax.swing.JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public javax.swing.JTextField getJTFCargo() {
        return jTFCargo;
    }

    public void setJTFCargo(javax.swing.JTextField jTFCargo) {
        this.jTFCargo = jTFCargo;
    }

    public javax.swing.JTextField getJTFEmail() {
        return jTFEmail;
    }

    public void setJTFEmail(javax.swing.JTextField jTFEmail) {
        this.jTFEmail = jTFEmail;
    }

    public javax.swing.JTextField getJTFFone() {
        return jTFFone;
    }

    public void setJTFFone(javax.swing.JTextField jTFFone) {
        this.jTFFone = jTFFone;
    }

    public javax.swing.JTextField getJTFNome() {
        return jTFNome;
    }

    public void setJTFNome(javax.swing.JTextField jTFNome) {
        this.jTFNome = jTFNome;
    }

    public javax.swing.JTextField getJTFCodPessoa() {
        return jTFCodPessoa;
    }

    public void setJTFCodPessoa(javax.swing.JTextField jTFCodPessoa) {
        this.jTFCodPessoa = jTFCodPessoa;
    }

    public javax.swing.JTextArea getJTAEndereco() {
        return jTAEndereco;
    }

    public void setJTAEndereco(javax.swing.JTextArea jTAEndereco) {
        this.jTAEndereco = jTAEndereco;
    }

    public javax.swing.JTextField getJTFDias() {
        return jTFDias;
    }

    public void setJTFDias(javax.swing.JTextField jTFDias) {
        this.jTFDias = jTFDias;
    }

    public javax.swing.JComboBox getJComboBoxData() {
        return jComboBoxData;
    }

    public javax.swing.JFormattedTextField getJFTFDataNiver() {
        return jFTFDataNiver;
    }

    public void setJFTFDataNiver(javax.swing.JFormattedTextField jFTFDataNiver) {
        this.jFTFDataNiver = jFTFDataNiver;
    }

    public static int getAuxi() {
        return auxi;
    }

    public static void setAuxi(int aAuxi) {
        auxi = aAuxi;
    }

    public javax.swing.JTextArea getJTAObs() {
        return jTAObs;
    }
    public void setJTAObs(javax.swing.JTextArea jTAObs) {
        this.jTAObs = jTAObs;
    }

    /**
     * @return the jTFFoto
     */
    public javax.swing.JTextField getJTFFoto() {
        return jTFFoto;
    }

    /**
     * @param jTFFoto the jTFFoto to set
     */
    public void setJTFFoto(javax.swing.JTextField jTFFoto) {
        this.jTFFoto = jTFFoto;
    }
    /*    public static int getAuxi2() {
    return auxi2;
    }
    public static void setAuxi2(int aAuxi2) {
    auxi2 = aAuxi2;
    }*/
}
