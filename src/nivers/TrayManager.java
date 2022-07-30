package nivers;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.JOptionPane;
public class TrayManager
{  
     //public static chamados ch = new chamados();
     public TrayManager()  
     {
         
     }
     public static void systray(){
     {  
         final TrayIcon trayIcon;  

         if (SystemTray.isSupported()) {  

             SystemTray tray = SystemTray.getSystemTray();  
             Image image = Toolkit.getDefaultToolkit().getImage("imagens/aniversarios.png");
             /*MouseListener mouseListener = new MouseListener() {  
                 public void mouseClicked(MouseEvent e) {  
                     System.out.println("Tray Icon - Mouse clicked!");                   
                 }  
                public void mouseEntered(MouseEvent e) {  
                     System.out.println("Tray Icon - Mouse entered!");                   
                 }  
                 public void mouseExited(MouseEvent e) {  
                     System.out.println("Tray Icon - Mouse exited!");                   
                 }  
                 public void mousePressed(MouseEvent e) {  
                     System.out.println("Tray Icon - Mouse pressed!");                   
                 }  
                 public void mouseReleased(MouseEvent e) {  
                     System.out.println("Tray Icon - Mouse released!");                   
                 }
             };*/  
            ActionListener voltarListener = new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     //nivers.voltar();
                     nivers.ni.setVisible(true);
//                     login.ch.setVisible(true);
                 }  
            }; 
             ActionListener exitListener = new ActionListener() {  
                 public void actionPerformed(ActionEvent e) {
                     nivers.fechar();
//                     nivers.fechar(1);
                 }  
             };  
             PopupMenu popup = new PopupMenu();  
             MenuItem defaultItem = new MenuItem("Sair");  
             MenuItem restaurarItem = new MenuItem("Restaurar");
             defaultItem.addActionListener(exitListener);  
             restaurarItem.addActionListener(voltarListener);
             popup.add(defaultItem); 
             popup.add(restaurarItem);
             trayIcon = new TrayIcon(image, "Aniversariantes em execução...", popup);
             ActionListener actionListener = new ActionListener() {  
                 public void actionPerformed(ActionEvent e) {  
                     trayIcon.displayMessage("Action Event",   
                         "Clique com o botão direito do mouse!",  
                         TrayIcon.MessageType.INFO);  
                 }  
             };  
             trayIcon.setImageAutoSize(true);  
             trayIcon.addActionListener(actionListener);  
//             trayIcon.addMouseListener(mouseListener);  
             try {
                 tray.add(trayIcon);  
             } catch (AWTException ex) {  
                 ex.printStackTrace();  
             }  
         } else {  
                    JOptionPane.showMessageDialog(null,"System tray não suportado.","..:: Não suportado ::.." +
               "",JOptionPane.ERROR_MESSAGE); 
         }  
     }  
     }
     /** 
      * @param args the command line arguments 
      */  
     public static void main(String[] args)  
     {  
         TrayManager main = new TrayManager();  
     }  
       
 }  
// @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">




// </editor-fold>

    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify
    // End of variables declaration

