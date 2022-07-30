/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author 8741417
 */
public class manipular_arquivo {
    public static int escrever_arquivo(String a, String arq) throws FileNotFoundException, IOException{
        String arquivoNome = arq;
        FileWriter writer = new FileWriter(new File(arq),true);
        PrintWriter saida = new PrintWriter(writer);
        saida.println(a);
        saida.close();
        writer.close();
        return 1;
    }
}