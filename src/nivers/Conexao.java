package nivers;

import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import com.mysql.jdbc.CommunicationsException;



/**
 * Conexao.java<br/>
 * Representa um conexao com um banco de dados
 * @author  Marcos Vinícius Soares
 */
public class Conexao {

    public static Connection con=null;
    static String server_end_cof = new String();
    static String porta_conf = new String();
    static String banco_conf = new String();
    static String user_conf = new String();
    static String passw_conf = new String();
    static public List<String> dados;

    private Connection conn_;
    private Statement stmt_;

    public Conexao() {
    }

        /**
         * Cria uma nova conexão
         */
    public Conexao(String servidor, String porta, String bd, String usuario, String senha)
        throws CommunicationsException, SQLException, ClassNotFoundException {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://"+servidor+":"+porta+"/"+bd;
            // conexão com o banco de dados
            try {
                Class.forName(driver);
                conn_ = DriverManager.getConnection(url, usuario, senha);
            }
            catch (CommunicationsException exception) {
                throw exception;
            }
            catch (SQLException exception) {
                throw exception;
            }
            catch (ClassNotFoundException exception) {
                throw exception;
            }

            // criação do statement
            try {
                stmt_ = conn_.createStatement();
            }
            catch (SQLException exception) {
                throw exception;
            }
        }

        /**
         * Executa o SQL passado como parâmetro, podendo ser um INSERT, UPDATE ou DELETE
         */
        public synchronized void executeUpdate(String sql) throws SQLException {
            try {
                stmt_.executeUpdate(sql);
            }
            catch (SQLException exception) {
                throw exception;
            }
        }

        /**
         * Executa o SQL passado como parâmetro, retornando um objeto ResultSet
         */
        public synchronized ResultSet executeQuery(String sql) throws SQLException {
            try {
                return stmt_.executeQuery(sql);
            }
            catch (SQLException exception) {
                throw exception;
            }
        }

        /**
         * Fecha a conexao com o banco de dados
         */
        public void fecharConexao() throws SQLException {
            try {
                stmt_.close();
            }
            catch (SQLException exception) {
                throw exception;
            }
        }

        /**
         * Retorna o maior número de um campo da tabela, se ele for um inteiro.
         */
        public int retornaMax(String tabela, String campo) throws SQLException {
            try {
                String sql = "SELECT max(" + campo + ") as valorMaximo FROM " + tabela;
                ResultSet rs = executeQuery(sql);
                rs.next();
                return (rs.getInt("valorMaximo"));
            }
            catch (SQLException exception) { throw exception; }
        }

        /**
         * Retorna o objeto Connection desta conexão
         */
        public Connection getConnection() {
            return this.conn_;
        }

}