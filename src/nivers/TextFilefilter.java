/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

/**
 *
 * @author 8741417
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;


public class TextFilefilter extends JFrame implements ActionListener {
  TextFilefilter(String title) {
    super(title);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel p = new JPanel();

    JButton jb = new JButton("Open ...");
    jb.addActionListener(this);
    p.add(jb);

    jb = new JButton("Save ...");
    jb.addActionListener(this);
    p.add(jb);

    getContentPane().add(p);

    setSize(200, 65);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    int retVal;

    JFileChooser fc = new JFileChooser();

    if (e.getActionCommand().equals("Open ...")) {
      fc.addChoosableFileFilter(new TextFilter());
      retVal = fc.showOpenDialog(this);
    } else
      retVal = fc.showSaveDialog(this);

    if (retVal == JFileChooser.APPROVE_OPTION)
      System.out.println(fc.getSelectedFile().getName());
  }

  public static void main(String[] args) {
    new TextFilefilter("FileChooser Demo");
  }
}

class TextFilter extends FileFilter {
  public boolean accept(File f) {
    if (f.isDirectory())
      return true;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if (i > 0 && i < s.length() - 1)
      if (s.substring(i + 1).toLowerCase().equals("txt"))
        return true;

    return false;
  }

  public String getDescription() {
    return "Accepts txt files only.";
  }
}