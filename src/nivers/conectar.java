package nivers;

//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class conectar {
    public static Connection con=null;
    static String server_end_cof = new String();
    static String porta_conf = new String();
    static String banco_conf = new String();
    static String user_conf = new String();
    static String passw_conf = new String();
    static public List<String> dados;
    private JasperPrint jasperPrint_;
    public conectar() {
    }

   public static int EntradaDeDados() {
        server_end_cof = "10.70.1.2" ; // ip do servidor
        porta_conf = "3306" ;   // porta do banco
        banco_conf = "aniversariantes" ; // banco utilizado
        user_conf = "helpdesk" ; // usuário do banco
        passw_conf = "acessosql" ; // senha do banco
        return 1;
   }

   public static Connection retCon(){
        int i = EntradaDeDados();
        if (0 == i){
            JOptionPane.showMessageDialog(null, "Erro na função EntradaDeDados!");
        }
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user_conf, passw_conf);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"Driver não encontrado");
        }
        catch(SQLException ex) //
        {
            JOptionPane.showMessageDialog(null,"Erro 0050\nErro ao conectar no banco\n"+ex.getMessage(),"..:: Erro ao conectar no banco ::..", JOptionPane.ERROR_MESSAGE);
        }
        return con;
   }
    @SuppressWarnings("empty-statement")
    public static int cartao(String idniver, String ano){
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                //con = DriverManager.getConnection(url, user_conf, passw_conf);
                //con = (Connection) DriverManager.getConnection(url, "helpdesk", "acessosql");
                con = (Connection) DriverManager.getConnection(url, user_conf, passw_conf);
            } catch (SQLException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
//               String aux_url = "E:\\Program_Java\\nivers\\consulta02.jrxml";
               String aux_url = "./cartao.jrxml";
               JasperDesign design = JasperManager.loadXmlDesign(aux_url);
               JasperReport jr = JasperManager.compileReport(design);
               HashMap parameters = new HashMap();
               parameters.put("idniver",idniver);
               parameters.put("ano",ano);
               JasperPrint impressao = JasperManager.fillReport(jr,parameters,con);
               JasperViewer jrviewer = new JasperViewer(impressao,false);
               jrviewer.setVisible(true);// show();
            }
            /*
                catch (Exception v) {
                    v.printStackTrace();
                }
             */
            catch (JRException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
         try {
             con.close();
         } catch(SQLException erro) {
         }
        return 1;
    }

    @SuppressWarnings("empty-statement")
    public static int cartao_s_pronome(String idniver, String ano, String tratamento){
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
        JasperPrint jasperPrint_;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con = (Connection) DriverManager.getConnection(url, user_conf, passw_conf);
            } catch (SQLException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
               //String aux_url = "\\\\srvpdc\\relatorios\\cartao_color.jrxml";
               String aux_url = "\\\\srvpdc\\relatorios\\cartao_color.jasper";
               JasperReport jr = (JasperReport)JRLoader.loadObject(aux_url);
/*
               JasperDesign design = JasperManager.loadXmlDesign(aux_url);
               JasperReport jr = JasperManager.compileReport(design);
*/
               HashMap parameters = new HashMap();
               parameters.put("idniver",idniver);
               parameters.put("ano",ano);
               parameters.put("tratamento",tratamento);
/*               jasperPrint_ = JasperFillManager.fillReport(jr, parameters, con);
               JasperViewer.viewReport(jasperPrint_, false);    // false significa que o programa não será fechado ao fechar relatório
*/
               JasperPrint impressao = JasperManager.fillReport(jr,parameters,con);
               JasperViewer jrviewer = new JasperViewer(impressao,false);    // false significa que o programa não será fechado ao fechar relatório
               jrviewer.setVisible(true);// show();
            }
/*
                catch (Exception v) {
                    v.printStackTrace();
                }
*/
            catch (JRException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
         try {
             con.close();
         } catch(SQLException erro) {
         }
        return 1;
    }

    @SuppressWarnings("empty-statement")
    public static int cartao_s_pronome_seq(String idniver, String ano){
//        JOptionPane.showMessageDialog(null, idniver);
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
        JasperPrint jasperPrint_;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con = (Connection) DriverManager.getConnection(url, user_conf, passw_conf);
            } catch (SQLException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
               String aux_url = "\\\\srvpdc\\relatorios\\cartao_color_seq.jasper";
//               String aux_url = "./cartao_color_seq.jasper";
//               String aux_url = "./cartao_color_seq.jrxml";
               JasperReport jr = (JasperReport)JRLoader.loadObject(aux_url);
/*
               JasperDesign design = JasperManager.loadXmlDesign(aux_url);
               JasperReport jr = JasperManager.compileReport(design);
*/
               HashMap parameters = new HashMap();
               parameters.put("ano",ano);
               parameters.put("idniver",idniver);
/*               jasperPrint_ = JasperFillManager.fillReport(jr, parameters, con);
               JasperViewer.viewReport(jasperPrint_, false);    // false significa que o programa não será fechado ao fechar relatório
*/
               JasperPrint impressao = JasperManager.fillReport(jr,parameters,con);
               JasperViewer jrviewer = new JasperViewer(impressao,false);    // false significa que o programa não será fechado ao fechar relatório
               jrviewer.setVisible(true);// show();
            }
/*
                catch (Exception v) {
                    v.printStackTrace();
                }
*/
            catch (JRException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
         try {
             con.close();
         } catch(SQLException erro) {
         }
        return 1;
    }

    public static int relatorio02(String data_ini, String data_fim, String data_hoje, String data_ano){
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
//        String url ="jdbc:mysql://localhost:3306/aniversariantes";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                //con = DriverManager.getConnection(url, user_conf, passw_conf);
                //con = (Connection) DriverManager.getConnection(url, "helpdesk", "acessosql");
                con = (Connection) DriverManager.getConnection(url, user_conf, passw_conf);
            } catch (SQLException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
               String aux_url = "consulta02.jrxml";
               JasperDesign design = JasperManager.loadXmlDesign(aux_url);
               JasperReport jr = JasperManager.compileReport(design);
               HashMap parameters = new HashMap();
               parameters.put("data_ini",data_ini);
               parameters.put("data_fim", data_fim);
               parameters.put("data_hoje", data_hoje);
               parameters.put("data_ano", data_ano);
               JasperPrint impressao = JasperManager.fillReport(jr,parameters,con);
               JasperViewer jrviewer = new JasperViewer(impressao,false);
               jrviewer.show();
            }
            /*
                catch (Exception v) {
                    v.printStackTrace();
                }

             */
            catch (JRException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
         try {
             con.close();
         } catch(SQLException erro) {
         }
        return 1;
    }

    public static int relatorio01(String qr){
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
//        String url ="jdbc:mysql://localhost:3306/aniversariantes";
        
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                //con = DriverManager.getConnection(url, user_conf, passw_conf);
                //con = (Connection) DriverManager.getConnection(url, "helpdesk", "acessosql");
                con = (Connection) DriverManager.getConnection(url, user_conf, passw_conf);
            } catch (SQLException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                JasperPrint print = JasperFillManager.fillReport("consulta02.jasper", null,con);
                JasperViewer.viewReport(print,false);
                //JasperReport jasper = (JasperReport) JRLoader.loadObject("E:\\Program_Java\\nivers\\consulta02.jasper");
                JasperReport jasper = (JasperReport) JRLoader.loadObject("consulta02.jasper");
            } catch (JRException ex) {
                Logger.getLogger(testeRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
         try {
             con.close();
         } catch(SQLException erro) {
         }
        return 1;
    }

    public int init() {
        int i = EntradaDeDados();
        if (0 == i){
            JOptionPane.showMessageDialog(null, "Erro na função EntradaDeDados!");
            return 4;
        }
        String url ="jdbc:mysql://"+server_end_cof+":"+porta_conf+"/"+banco_conf;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user_conf, passw_conf);
            return 1;
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"Driver não encontrado");
            return 2;
        }
        catch(SQLException ex) //
        {
            JOptionPane.showMessageDialog(null,"Erro 0000\nErro ao conectar no banco\n"+ex.getMessage(),"..:: Erro ao conectar no banco ::..", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return 3;
        }
    }

    public void disconectar(){
        if ( con != null){
            try {
                con.close();
            } catch(SQLException erro) {
            }
        }
    }

    public int desmarcar(String param_cod){
        int auxi = this.init();
        String ano = CalcDatas.ano();
        try
        {
            String qr = "UPDATE aniversariante SET status_aviso = '"+ano+"' WHERE cod_aniversariante ="+param_cod;
            Statement s = con.createStatement ( );
            s.execute(qr);
            s.close();
//            JOptionPane.showMessageDialog (null, "Registro excluido com sucesso!" );
        }
        catch ( SQLException e )
        {
            JOptionPane.showMessageDialog(null,"Não foi possível executar esta opção." +
                    "\nErro: 049\n"+e,"..:: Erro ao executar query! ::..",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 1;
    }

    public int ret_ultimo(){
        this.init();
        int i = 0;
        String sql = "SELECT cod_aniversariante FROM aniversariante ORDER BY cod_aniversariante DESC";
        try{
            Statement stmt4= con.createStatement();
            ResultSet rs = stmt4.executeQuery(sql);
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("cod_aniversariante"));
            }
            stmt4.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar listar ANIVERSARIANTES!\n" +
                    "conectar.java"+ex.getMessage());
        }
        this.disconectar();
        return i;
    }

    public int ret_qt(){    // retorna quantidadde de pronomes cadastrados
        this.init();
        String sql = "SELECT count(cod_tratamento) FROM tratamento";

        int i=0;
        try{
            Statement stmt4= con.createStatement();
            ResultSet rs = stmt4.executeQuery(sql);
            while (rs.next()) {
                i = Integer.parseInt(rs.getString("count(cod_tratamento)"));
            }
            stmt4.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar listar pronomes!\n"+ex.getMessage());
        }
        this.disconectar();
        return i;
    }
    // retorna quantidade de pessoas (aniversariantes) cadastrados
    public int ret_qt_pessoas(String param){
        if (param.equals(""))
            param = "WHERE 1";
        this.init();
        String sql = "SELECT count(cod_aniversariante) FROM aniversariante " + param;

        int i=0;
        try{
            Statement stmt2= con.createStatement();
            ResultSet rs = stmt2.executeQuery(sql);
            while (rs.next()) {
                i = Integer.parseInt(rs.getString("count(cod_aniversariante)"));
            }
            stmt2.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar listar aniversariantes!\n"+ex.getMessage());
        }
        this.disconectar();
        return i;
    }
    public String[] ret_pronome (int qt){
        String st[] = null;
        st = new String[qt];
        this.init();
        String sql = "SELECT cod_tratamento, pronome, descricao FROM tratamento";
        int i = 0;
        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                st[i] = rs.getString("pronome");
                i++;
            }
            stmt.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar listar pronomes!"+ex.getMessage());
        }
        this.disconectar();
        return st;
    }
    // retorna nome das pessoas cadastradas
    public String[] ret_pessoas(String param){
        if (param.equals(""))
            param = "WHERE 1";
        String st[] = null;
        int qt = this.ret_qt_pessoas("");
        st = new String[qt];
        this.init();
        String sql = "SELECT cod_aniversariante, nome, niver FROM aniversariante "+ param +" ORDER BY nome";
        int i = 0;
        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                st[i] = rs.getString("nome");
                i++;
            }
            stmt.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar listar aniversariantes!"+ex.getMessage());
        }
        this.disconectar();
        return st;
    }

//  3    /* TERCEIRA FUNÇÃO QUE É CHAMADA (à partir da segunda) */
    //não fecha conexão
    /* Retorna a quantidade de registros com avisos (dias) diferentes */
    public int ret_qt_reg_dias(String ano){
        int i = 0;
        this.init();
        String sql = "SELECT count( DISTINCT dias ) as qt FROM aniversariante WHERE status_aviso < "+ano;
        try{
            Statement stmt4= con.createStatement();
            ResultSet rs = stmt4.executeQuery(sql);
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("qt"));
            }
            stmt4.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro 0044 \nErro ao tentar contar aniversariantes!\n"+ex.getMessage());
        }
//        this.disconectar();
        return i;
    }

//  2    /* SEGUNDA FUNÇÃO QUE É CHAMADA (à partir da primeira) */
    // não fecha conexão
    /* retorna os dias diferentes*/
    public int[] ret_qt_group_dias(String ano){
        //SELECT count( cod_aniversariante ) FROM aniversariante GROUP BY dias
        int ret[] = null;
        this.init();
        String sql = "SELECT DISTINCT( dias ) as contagem FROM aniversariante WHERE status_aviso < "+ano+" GROUP BY dias";
        int i = this.ret_qt_reg_dias(ano);
        ret = new int[i];
        int aux = 0;
//        JOptionPane.showMessageDialog(null, "Edivano: quantidade de dias != asdfg "+String.valueOf(i));
        try{
            Statement stmt5= con.createStatement();
            ResultSet rs = stmt5.executeQuery(sql);
//            ret = new String[i];
            while (rs.next()) {
                ret[aux] = Integer.parseInt( rs.getString("contagem"));
                aux++;
            }
            stmt5.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro 0041 \nconectar.java:ret_qr_group_dias\nErro ao tentar contar aniversariantes!\n"+ex.getMessage());
        }
//        this.disconectar();
        return ret;
    }

    public int ret_qt_g_dias(String ano){   // retornar a quantidade de registros com campo "dias" diferente
        this.init();
        String sql = "SELECT count( DISTINCT dias ) as qt FROM aniversariante WHERE status_aviso < "+ano;
        int i = 0;
        try{
            Statement stmt6= con.createStatement();
            ResultSet rs = stmt6.executeQuery(sql);
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("qt"));
            }
            stmt6.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro 0045 \nconectar.java:ret_qt_g_dias()\nErro ao tentar contar aniversariantes!\n"+ex.getMessage());
        }
        return i;
    }

//  1    /*      PRIMEIRA FUNÇÃO QUE É CHAMADA         */
    // retorna o nome e a data de aniversário dos registros dentro do prazo 
    public String[][] ret_aniversariantes_dias(String ano){

        /* variáveis para verificação se altrapassou para o próximo ano*/
        String mes_hj = new String();
        String dia_hj = new String();
        String mes_lim  = new String();
        String dia_lim = new String();
        /****************************************************************/

        String ret[][] = null;
        int receb[] = null;
        String ano2 = CalcDatas.ano();
        receb = this.ret_qt_group_dias(ano); // recebe array de dias diferentes de avisos
        String data_ini = new String();
        String data_ini2 = new String();
        data_ini2 = "01-01";
        data_ini = CalcDatas.hoje();

        data_ini = data_ini.substring(5, 10);
        String[] data_fim = null;   // para cada sql será uma data diferente
        String data_fim2 = null;   // para cada sql será uma data diferente quando ultrapassar o ano
        data_fim2 = "12-31";
        String[] sql = null;
        int i = this.ret_qt_reg_dias(ano);  // recebe a quantidade de registros com avisos (dias) diferentes
        int i4 = this.ret_qt_pessoas(""); // se todos fizerem aniversário amanhã :P // recebe qtd total de aniversariantes cadastrados
        ret = new String[i4][2];
        int i3 = 0;
        int iqtreg = 0;     // quantidade de registros que irão aparecer na tabela
        this.init();
        String ande = new String(); // para não repetir resultado na sql
        ande = "";
        nivers.isn = 0;
        if (receb != null){
            data_fim = new String[i];
            sql = new String[i];    // vetor de sql. uma para cada data final
//            int[] il = new int[i];  // vetor de int. para campos dias distintos da tab aniversariante
            Statement stmt = null;
            for (int k=0; k<i; k++)
            {
//                il[k] = receb[k];          // pego qual é o dia a somar (antecedência)

                data_fim[k] = CalcDatas.soma_now_dia(receb[k]);    // somo antecedência mais hoje
                data_fim[k] = data_fim[k].substring(5, 10);

                mes_hj = data_ini.substring(0,2);
                dia_hj = data_ini.substring(3,5);
                mes_lim = data_fim[k].substring(0,2);
                dia_lim = data_fim[k].substring(3,5);

                int i_mes_hj = Integer.parseInt(mes_hj);
                int i_dia_hj = Integer.parseInt(dia_hj);
                int i_mes_lim = Integer.parseInt(mes_lim);
                int i_dia_lim = Integer.parseInt(dia_lim);

                sql[k] = "SELECT cod_aniversariante, nome, DATE_FORMAT( niver, '%m-%d' ) AS niver FROM aniversariante WHERE (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_ini+"' AND '"+data_fim[k]+"' ) AND status_aviso < "+ano2+" "+ande+" ORDER BY niver";
//                JOptionPane.showMessageDialog(null, String.valueOf(k)+"ª query da consulta:\n"+sql[k]);
                if (((i_mes_hj == i_mes_lim) && (i_dia_hj >= i_dia_lim)) || (i_mes_hj > i_mes_lim)){
                    sql[k] = "SELECT cod_aniversariante, nome, DATE_FORMAT( niver, '%m-%d' ) AS niver FROM aniversariante WHERE (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_ini+"' AND '"+data_fim2+"') OR (DATE_FORMAT( niver, '%m-%d' ) BETWEEN '"+data_ini2+"' AND '"+data_fim[k]+"') AND status_aviso < "+ano2+" "+ande+" ORDER BY niver";
                }
                try{
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql[k]);
                    while (rs.next()) {
                        // ande para não repetir aniversariantes
                        ande += "AND cod_aniversariante <> " + rs.getString("cod_aniversariante")+" ";
                        ret[i3][0] = rs.getString("nome");
                        ret[i3][1] =  rs.getString("niver");
                        i3++;
                        iqtreg++;   // quantidade de registros que irão aparecer na tabela
                        nivers.isn = 1; // aparecer o painel com aniversariantes
                    }
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Erro 0046\n" +
                    "conectar.java:ret_aniversariantes_dias\n" +
                    "Erro ao tentar listar aniversariantes!\n"+ex.getMessage());
                }
            }
            p_aniversariantes_go.qt2 =iqtreg;
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(conectar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.disconectar();
        return ret;
    }

    public String[][] ret_id_pessoas_data(String param){
        if (param.equals(""))
            param = "WHERE 1";
        String st[][] = null;
        int qt = this.ret_qt_pessoas("");
        st = new String[qt][3];
        this.init();
        String sql = "SELECT cod_aniversariante AS ID, nome, DATE_FORMAT( niver, '%m-%d' ) AS niver FROM aniversariante " + param +" ORDER BY niver";
//        JOptionPane.showMessageDialog(null, sql);
        int i = 0;
        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                st[i][0] = rs.getString("nome");
                st[i][1] =  rs.getString("niver");
                st[i][2] =  rs.getString("ID");
                i++;
            }
            stmt.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro 0047\n" +
                    "conectar.java:ret_pessoas_data\n" +
                    "Erro ao tentar listar aniversariantes!\n"+ex.getMessage());
        }
//        this.disconectar();
        return st;
    }

    public String[][] ret_pessoas_data(String param){
        if (param.equals(""))
            param = "WHERE 1";
        String st[][] = null;
        int qt = this.ret_qt_pessoas("");
        st = new String[qt][2];
        this.init();
        String sql = "SELECT cod_aniversariante, nome, DATE_FORMAT( niver, '%m-%d' ) AS niver FROM aniversariante " + param +" ORDER BY niver";
//        JOptionPane.showMessageDialog(null, sql);
        int i = 0;
        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                st[i][0] = rs.getString("nome");
                st[i][1] =  rs.getString("niver");
                i++;
            }
            stmt.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro 0047\n" +
                    "conectar.java:ret_pessoas_data\n" +
                    "Erro ao tentar listar aniversariantes!\n"+ex.getMessage());
        }
//        this.disconectar();
        return st;
    }

    public String[] ret_selecionado(String param){ // retorna todos os dados de um registro
        if (param.equals(""))
            param = "WHERE 1";
        String st[] = null;
        st = new String[14];
        this.init();
        String sql = "SELECT cod_aniversariante, " +
                "tipo_cartao_cod_tipo_cartao, " +
                "tratamento_cod_tratamento, nome, cargo, " +
                "niver, fone, email, sexo, status_aviso, " +
                "dias, foto, endereco, obs FROM aniversariante "+ param;
        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                st[0] = rs.getString("cod_aniversariante");
                st[1] = rs.getString("tipo_cartao_cod_tipo_cartao");
                st[2] = rs.getString("tratamento_cod_tratamento");
                st[3] = rs.getString("nome");
                st[4] = rs.getString("cargo");
                st[5] = rs.getString("niver");
                st[6] = rs.getString("fone");
                st[7] = rs.getString("email");
                st[8] = rs.getString("sexo");
                st[9] = rs.getString("status_aviso");
                st[10] = rs.getString("dias");
                st[11] = rs.getString("foto");
                st[12] = rs.getString("endereco");
                st[13] = rs.getString("obs");
            }
            stmt.close();
        }
        catch (SQLException ex) {
            for(int i = 0; i < 14; i++)
                st[i] ="";
            JOptionPane.showMessageDialog(null,"Erro ao tentar encontrar aniversariantes!"+ex.getMessage());
        }
        this.disconectar();
        return st;
    }

    public String[] ret_proximos_nivers(String qr){
        String st[] = null;
        this.init();    // iniciar conexão
        try
        {
            Statement s = con.createStatement ( );
            s.execute(qr);
            s.close();
            JOptionPane.showMessageDialog (null, "Próximos nivers!" );
        }
        catch ( SQLException e )
        {
            JOptionPane.showMessageDialog(null,"Não foi possível encontrar registro." +
                    "\nErro: 030\n"+e,"..:: Erro ao executar query! ::..",JOptionPane.ERROR_MESSAGE);
        }
        this.disconectar();
        return st;
    }

    @SuppressWarnings("empty-statement")
    public static String converterData_pt_en (String data_pt){
        String data = new String();
        List<String> valor;
        valor =  Arrays.asList(data_pt.trim().split ("/"));
        data = String.valueOf(valor.get(2).trim().toString());
        data += "-"+ String.valueOf(valor.get(1).trim().toString());;
        data += "-"+ String.valueOf(valor.get(0).trim().toString());;
        return data;
    }
    @SuppressWarnings("empty-statement")
    public static String converterData_en_pt (String data_en){
        String data = new String();
        List<String> valor;
        valor =  Arrays.asList(data_en.trim().split ("-"));
        data = String.valueOf(valor.get(2).trim().toString());
        data += "/"+ String.valueOf(valor.get(1).trim().toString());;
        data += "/"+ String.valueOf(valor.get(0).trim().toString());;
        return data;
    }
    // int código (para update, String query, String parâmetros para qr, String mensagem de inserido ou alterado com sucesso)
    public int ultimo (){
        int auxi = this.init();
        int retorno = 0;
        try {
            retorno = 1;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT max( cod_aniversariante ) AS ultimo FROM aniversariante");
            if (rs.next()) {
                retorno = Integer.parseInt(rs.getString("ultimo"));
//                JOptionPane.showMessageDialog(null, String.valueOf(retorno));
            }
        }catch (SQLException ex) {
            Logger.getLogger(conectar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Não foi possível realizar operação." +
                    "\nErro: 008\n"+ex,"..:: Erro ao executar query! ::..",JOptionPane.ERROR_MESSAGE);
//                        JOptionPane.showMessageDialog(null,"Não foi possível realizar operação.");
            if (con != null)
                this.disconectar();
            return retorno;
        }
        return retorno;
    
    }
    public int salvar (int i, String sql, String msg){  /* i = 0    ou i = cod_aniversariante */
        int auxi = this.init();
        int retorno = 0;

//if (0 == i) // se for alterar registro
//            JOptionPane.showMessageDialog(null, sql);
        if (0 != i){ // se for alterar registro
            if (1 == JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar este registro?", "" +
                    "Alterar",JOptionPane.YES_NO_OPTION))
            {
                if (con != null){ 
                    this.disconectar();
                    return retorno;
                }
            }
        }
        try {
            retorno = this.ultimo();
            Statement st = con.createStatement();
            st.execute(sql);// execute(sql);
            st.close();
            JOptionPane.showMessageDialog(null,msg);
        } catch (SQLException ex) {
            Logger.getLogger(conectar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Não foi possível realizar operação." +
                    "\nErro: 008\n"+ex,"..:: Erro ao executar query! ::..",JOptionPane.ERROR_MESSAGE);
//                        JOptionPane.showMessageDialog(null,"Não foi possível realizar operação.");
            if (con != null)
                this.disconectar();
            return retorno;
        }
        this.disconectar();
        return retorno;
    }

    public int atualizar (String qr){
        int auxi = this.init();
        if (1 != auxi){
            System.exit(auxi);
            return 0;
        }else{
            try
            {
                Statement s = con.createStatement ( );
                s.execute(qr);
                s.close();
                this.disconectar();
    //            JOptionPane.showMessageDialog (null, "Atualização concluída!" );
            }
            catch ( SQLException e )
            {
                JOptionPane.showMessageDialog(null,"Não foi possível Atualizar o banco." +
                        "\nErro: 010\nArquivo: \"conectar.java\""+e,"..:: Erro ao Atualizar! ::..",JOptionPane.ERROR_MESSAGE);
                this.disconectar();
                return 0;
            }
            this.disconectar();
            return 1;
        }
    }

    public int excluir (int p){
        int auxi = this.init();
        try
        {
            String qr = "delete from aniversariante where cod_aniversariante =" + p;
            Statement s = con.createStatement ( );
            s.execute(qr);
            s.close();
            JOptionPane.showMessageDialog (null, "Registro excluido com sucesso!" );
        }
        catch ( SQLException e )
        {
            JOptionPane.showMessageDialog(null,"Não foi possível excluir registro." +
                    "\nErro: 009\n"+e,"..:: Erro ao executar query! ::..",JOptionPane.ERROR_MESSAGE);
        }
        return 1;
    }
}
