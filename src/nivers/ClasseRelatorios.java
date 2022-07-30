/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

//import net.sf.jasperreports.engine.JasperPrint;
import java.sql.*;
import java.net.URL;
import java.util.HashMap;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
//import conexao.Conexao;

/**
 *
 * @author 8741417
 */

public class ClasseRelatorios {


/*
 * Relatorio.java<br>
 *
 * <p>A classe Relatorio deve ser utilizada para emissão de relatórios. Após a
 * criação de algum relatório é possível exibí-lo na tela, exportá-lo para um
 * arquivo pdf/html ou enviá-lo direto para impressão.</p>
 *
 * <p>A classe manipula relatórios desenvolvidos utilizando-se a ferramenta
 * iReport e utiliza a ferramenta JasperReports para emissão dos relatórios</p>
 *
 * @author  Marcos Vinícius Soares
 */

    /** Representa o relatório gerado. */
    private JasperPrint jasperPrint_;

    /**
     * Cria um novo Relatorio.
     *
     * @param  conn  Conexão com o banco de dados.
     * @param  parameters  Parâmetros a serem exibidos no relatório.
     * @param  localRelatorio  Localização do relatório.
     * @throws  JRException  Caso o relatório não seja encontrado ou haja
     * algum problema com ele, uma exceção é gerada.
     */
    public ClasseRelatorios(Conexao conn, HashMap parameters, URL localRelatorio) throws JRException{

        try {
            // O objeto JasperReport representa o objeto JasperDesign (arquivo .jrxml) compilado.
            // Ou seja, o arquivo .jasper
            JasperReport jr = (JasperReport)JRLoader.loadObject(localRelatorio);
            // JasperPrint representa o relatório gerado.
            // É criado um JasperPrint a partir de um JasperReport, contendo o relatório preenchido.
            this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, conn.getConnection());
        } catch (JRException e) { throw e; }
    }

    /**
     * Cria um novo Relatorio.
     *
     * @param  conn  Conexão com o banco de dados.
     * @param  parameters  Parâmetros a serem exibidos no relatório.
     * @param  localRelatorio  Localização do relatório.
     * @throws  JRException  Caso o relatório não seja encontrado ou haja
     * algum problema com ele, uma exceção é gerada.
     */
    public ClasseRelatorios(Conexao conn, HashMap parameters, String localRelatorio) throws JRException{
        try {
            // O objeto JasperReport representa o objeto JasperDesign (arquivo .jrxml) compilado.
            // Ou seja, o arquivo .jasper
            JasperReport jr = (JasperReport)JRLoader.loadObject(localRelatorio);
            // JasperPrint representa o relatório gerado.
            // É criado um JasperPrint a partir de um JasperReport, contendo o relatório preenchido.
            this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, conn.getConnection());
        } catch (JRException e) { throw e; }
    }
    /**
     * Cria um novo Relatorio.
     *
     * @param  conn  Conexão com o banco de dados.
     * @param  sql  Expressão SQL (SELECT...) a ser utilizada para preenchimento do relatório
     * @param  parameters  Parâmetros a serem exibidos no relatório.
     * @param  localRelatorio  Localização do relatório.
     * @throws  JRException  Caso o relatório não seja encontrado ou haja
     * algum problema com ele, uma exceção é gerada.
     * @throws  SQLException  Caso exista alguma divergência ou problema com
     * a Expressão SQL passada como parâmetro, uma exceção é gerada.
     */
    public ClasseRelatorios(Conexao conn, String sql, HashMap parameters, URL localRelatorio) throws SQLException, JRException {
        try {
            // O objeto JasperReport representa o objeto JasperDesign (arquivo .jrxml) compilado.
            // Ou seja, o arquivo .jasper
            JasperReport jr = (JasperReport)JRLoader.loadObject(localRelatorio);
            // Resultado da consulta
            ResultSet rs = conn.executeQuery(sql);
            // JRResultSetDataSource é uma implementaçao de JRDataSource, o qual é requerido
            // como parametro para preencher o relatório criado.
            // Ele armazena o dados do ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            // JasperPrint representa o relatório gerado.
            // É criado um JasperPrint a partir de um JasperReport, contendo o
            // relatório preenchido.
            this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, jrRS);
            rs.close();
        }
        catch (SQLException e) { throw e; }
        catch (JRException e) { throw e; }
    }
    /**
     * Cria um novo Relatorio.
     *
     * @param  conn  Conexão com o banco de dados.
     * @param  sql  Expressão SQL (SELECT...) a ser utilizada para preenchimento do relatório
     * @param  parameters  Parâmetros a serem exibidos no relatório.
     * @param  localRelatorio  Localização do relatório.
     * @throws  JRException  Caso o relatório não seja encontrado ou haja
     * algum problema com ele, uma exceção é gerada.
     * @throws  SQLException  Caso exista alguma divergência ou problema com
     * a Expressão SQL passada como parâmetro, uma exceção é gerada.
     */
    // EDIVANO edivano asdfg ESTA AKI
    public ClasseRelatorios(Conexao conn, String sql, HashMap parameters, String localRelatorio) throws SQLException, JRException {
        try {
            // O objeto JasperReport representa o objeto JasperDesign (arquivo .jrxml) compilado.
            // Ou seja, o arquivo .jasper
            JasperReport jr = (JasperReport)JRLoader.loadObject(localRelatorio);
            // Resultado da consulta
            ResultSet rs = conn.executeQuery(sql);
            // JRResultSetDataSource é uma implementaçao de JRDataSource, o qual é requerido
            // como parametro para preencher o relatório criado.
            // Ele armazena o dados do ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            // JasperPrint representa o relatório gerado.
            // É criado um JasperPrint a partir de um JasperReport, contendo o
            // relatório preenchido.
            this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, jrRS);
            rs.close();
        }
        catch (SQLException e) { throw e; }
        catch (JRException e) { throw e; }
    }
    /**
     * Cria um novo Relatorio.
     *
     * @param  parameters  Parâmetros a serem exibidos no relatório.
     * @param  localRelatorio  Localização do relatório.
     * @throws  JRException  Caso o relatório não seja encontrado ou haja
     * algum problema com ele, uma exceção é gerada.
     */
    public ClasseRelatorios(HashMap parameters, URL localRelatorio) throws JRException {
        try {
            // O objeto JasperReport representa o objeto JasperDesign (arquivo .jrxml) compilado.
            // Ou seja, o arquivo .jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(localRelatorio);
            // JREmptyDataSource é uma implementaçao de JRDataSource, o qual é requerido
            // como parametro para preencher o relatório criado.
            // Ele armazena o dados do ResultSet, que, neste caso, é vazio
            JREmptyDataSource jrEDS = new JREmptyDataSource();
            // Jasper Print representa o relatório gerado.
            // É criado um JasperPrint a partir de um JasperReport, contendo o
            // relatório preenchido.
            this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, jrEDS);
        } catch (JRException e) { throw e; }
    }

    /**
     * Cria um novo Relatorio
     *
     * @param  parameters  Parâmetros a serem exibidos no relatório.
     * @param  localRelatorio  Localização do relatório.
     * @throws  JRException  Caso o relatório não seja encontrado ou haja
     * algum problema com ele, uma exceção é gerada.
     */
    public ClasseRelatorios(HashMap parameters, String localRelatorio) throws JRException {

        try {

            // O objeto JasperReport representa o objeto JasperDesign (arquivo .jrxml) compilado.
            // Ou seja, o arquivo .jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(localRelatorio);

            // JREmptyDataSource é uma implementaçao de JRDataSource, o qual é requerido
            // como parametro para preencher o relatório criado.
            // Ele armazena o dados do ResultSet, que, neste caso, é vazio
            JREmptyDataSource jrEDS = new JREmptyDataSource();

            // Jasper Print representa o relatório gerado.
            // É criado um JasperPrint a partir de um JasperReport, contendo o
            // relatório preenchido.
            this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, jrEDS);

        } catch (JRException e) { throw e; }
    }

    /**
     * Exibe o relatório na tela.
     */
    public void exibirRelatorio() {
        // emite o relatório na tela
        // false indica que a aplicação não será finalizada caso o relatório seja fechado
        JasperViewer.viewReport(this.jasperPrint_, false);
    }

    /**
     * Grava o relatório em um arquivo de formato pdf.
     *
     * @param  caminhoDestino  Caminho onde o arquivo será gravado.
     */
    public void exportaParaPdf(String caminhoDestino) throws JRException {

        try {
            // Gera o arquivo PDF
            JasperExportManager.exportReportToPdfFile(this.jasperPrint_, caminhoDestino);
        } catch (JRException e) { throw e; }

    }

    /**
     * Grava o relatório em um arquivo de formato html.
     *
     * @param  caminhoDestino  Caminho onde o arquivo será gravado.
     */
    public void exportaParaHtml(String caminhoDestino) throws JRException {
        try {
            // Gera o arquivo PDF
            JasperExportManager.exportReportToHtmlFile(this.jasperPrint_, caminhoDestino);
        } catch (JRException e) { throw e; }
    }

    /**
     * Envia o relatório para impressão, exibindo uma caixa de dialogo de impressão ou não.
     *
     * @param  exibeCaixaDialogo  Boolean indicando se será exibida uma caixa de diálogo ou não.
     */
    public void imprimir(boolean exibeCaixaDialogo) throws JRException {
        try {
            // Imprime o relatório
            // o segundo parâmetro indica se existirá uma caixa de dialogo antes ou nao
            JasperPrintManager.printReport(this.jasperPrint_, exibeCaixaDialogo);
        } catch (JRException e) { throw e; }

    }

}
