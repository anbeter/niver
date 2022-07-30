/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 8741417
 */
public class EntradaDados {

    public static String[] EntradaDeDados() throws FileNotFoundException, IOException {
        String[] adados = new String[5]; // array de dados
        List<String> dados;
        byte[] buffer = new byte[1000];
        InputStream in;
        PWSec krypt = new PWSec();
        try {
            in = new FileInputStream("config_server.conf");
            in.read(buffer);
            String temp = new String(buffer).trim().toString();
            dados =  Arrays.asList(temp.trim().split ("\n"));
            adados[0] = String.valueOf(dados.get(0).trim().toString()) ;
            adados[1] = String.valueOf(dados.get(1).trim().toString()) ;
            adados[2] = String.valueOf(dados.get(2).trim().toString()) ;
            adados[3] = String.valueOf(dados.get(3).trim().toString()) ; // usuário do banco
            adados[3] = krypt.decriptar(adados[3]);
            adados[4] = String.valueOf(dados.get(4).trim().toString()) ; // senha do banco
            adados[4] = krypt.decriptar(adados[4]);
            in.close();
            return adados;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
