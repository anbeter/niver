/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

/**
*
* @author 8741417
*/
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
//import javax.swing.Box;
//import javax.swing.JPasswordField;

public class VerFotos extends JFrame
{
    private JButton[]JBFoto = new JButton[24];
    private JLabel[] JLFoto = new JLabel[24];

    private javax.swing.JButton botao;
    public JTextField[] tf = new JTextField[15];
    private javax.swing.JPanel jPanel1, jPanel2;

    public VerFotos()
    {
        super("VerFotos");
        String paux = new String();
        paux = "2";

        for (int i=0; i<24;i++){
            JBFoto[i] = new JButton();
            ImageIcon icon = new ImageIcon("\\\\\\\\srvpdc\\\\fotos\\\\"+paux+".jpg");
            JBFoto[i].setIcon(icon);
        }

        botao = new javax.swing.JButton("Botao mostrar dados ");
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DesisteMouseClicked(evt);
            }
        });
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Container cp = getContentPane();

        cp.setLayout( new BorderLayout() );
    // cp.setLayout(new GridLayout( tf.length, 1 ) );
        cp.setLayout(new GridLayout(1,0));

        for (int i = 0; i < tf.length ; i++)
        {
            tf[i] = new JTextField("dad "+i, 5);
            tf[i].setBackground(Color.yellow);
            tf[i].setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 4));
            jPanel1.add(tf[i]);
        }
        jPanel2.add(botao);
        for (int i=0; i<24; i++){
            jPanel2.add(JBFoto[i]);
        }

        jPanel2.setBackground(Color.BLUE);
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(Color.YELLOW, 6));
        cp.add( new JScrollPane( jPanel1 ),
        BorderLayout.EAST );
        cp.add( new JScrollPane( jPanel2 ),
        BorderLayout.CENTER );
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-400)/2, 400, 400);
    }

    private void DesisteMouseClicked(java.awt.event.MouseEvent evt) {
        JOptionPane.showMessageDialog(null,
        "Olha os dados -> "+ tf[0].getText()+"\n "+tf[1].getText(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(final String[] args) {
        VerFotos frame = new VerFotos();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}