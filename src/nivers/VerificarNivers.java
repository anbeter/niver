/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

import javax.swing.JOptionPane;

/**
 *
 * @author 8741417
 */
public class VerificarNivers {
    public static int verificar(String qr, int dias){

        JOptionPane.showMessageDialog(null, "hehehe!!!");
/*
        1   -   fazer consulta para retornar todos os dias de antecedências agrupando por "dias" se status_aviso < ano
        2   -   fazer consultas de acordo com o número de resultados acima
        String qr = "SELECT * FROM aniversariante WHERE ( DATE_FORMAT( niver, '%m-%d' ) BETWEEN \'"+hoje+"\' AND \'"+dt_aviso+"\' ) AND ( DATE_FORMAT( niver, '%m-%d' ) < \'"+hoje+"\'  ) AND status_aviso < "+ano;
        //SELECT * FROM aniversariante WHERE DATE_FORMAT( niver, '%m-%d' ) BETWEEN '04-25' AND '11-30'
*/
           conectar co = new conectar();
           int i = co.init();
           co.ret_proximos_nivers(qr);

           co.disconectar();

        return 1;
    }

}
