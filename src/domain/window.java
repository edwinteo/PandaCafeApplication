/*
Author : Teo Jia Han 
Class  : DC02 D2
*/
package domain;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class window extends JWindow {

    JPanel panel = new JPanel();

    public window() {
        setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon(getClass().getResource("/image/panda.gif"));
        
        JLabel pandaLabel = new JLabel(img);
        
        panel.add(pandaLabel);

        add(panel);
        
        try {

            
            setSize(400, 420);
            setVisible(true);
            setLocationRelativeTo(null);
            Thread thread = new Thread();
            thread.sleep(3100);
            
            dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
