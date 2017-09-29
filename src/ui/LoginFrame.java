/*
Author : Teo Jia Han 
Class  : DC02 D2 
*/


package ui;

import com.jd.swing.custom.component.button.ButtonType;
import com.jd.swing.custom.component.button.GlossyButton;
import com.jd.swing.util.Theme;
import control.LoginControl;
import domain.Staff;
import domain.window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class LoginFrame extends JFrame {

    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;

    private JTextField id;
    private JPasswordField password;
    private GlossyButton login;
    private GlossyButton cancel;
    private JLabel pandaLabel;
    
    public static String staffid;
    
    private LoginControl loginControl;

    public LoginFrame() {
        super("Panda Cafe Login");
        loginControl = new LoginControl();
        
        Date m1 = new Date(1992, 10, 02);
        Date m2 = new Date(1996, 10, 02);
     
        //setlayout for frame.
        setLayout(new BorderLayout());
        //panels
        p1 = new JPanel(new BorderLayout());
        p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p3 = new JPanel(new BorderLayout());
        p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p5 = new JPanel(new GridBagLayout());
        p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //scale image
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource("/image/AnimationPanda.gif"));
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(scaledImage);

        //create button/textfield/imagelabel
        pandaLabel = new JLabel(img);
        id = new JTextField(10);
        password = new JPasswordField(10);
        login = new GlossyButton("Login", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
        cancel = new GlossyButton("Cancel", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
        login.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        cancel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        //gridbaglayout components
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;

        JLabel pdLabel = new JLabel();
        pdLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        pdLabel.setText("Panda Café");
        pdLabel.setForeground(Color.LIGHT_GRAY);

        p6.add(pdLabel);

        //First row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 10);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        nameLabel.setText("ID:");
        p5.add(nameLabel, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(10, 5, 0, 10);
        p5.add(id, gc);

        //Second row
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(20, 5, 0, 10);
        JLabel passwordLabel = new JLabel();
        passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        passwordLabel.setText("Password:");
        p5.add(passwordLabel, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(20, 5, 0, 10);
        p5.add(password, gc);

        Border innerBorder = BorderFactory.createTitledBorder(null, "Authentication", TitledBorder.TOP, TitledBorder.CENTER, new Font("Comic Sans MS", Font.BOLD, 16), Color.RED);

        Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        rootPane.setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));

        //add button
        //GlossyButton btn = new GlossyButton("Glossy Button"); 
        p2.add(login);
        p2.add(cancel);
        //add label
        p4.add(pandaLabel);
        //set/add layout
        p1.add(p5, BorderLayout.CENTER);
        p1.add(p6, BorderLayout.NORTH);
        p3.add(p1, BorderLayout.EAST);
        p3.add(p4, BorderLayout.CENTER);
        add(p3, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String code = id.getText();
                staffid = id.getText();
                Staff login = loginControl.getRecord(code);

                if (login != null) {
                    if (password.getText().equals(login.getPassword())) {
                        //menu();
                        JOptionPane.showMessageDialog(null, "Login success!");
                        TableMenu menu = new TableMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Password. Please try again!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account doesnt exist.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        //jframe setup  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 320);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        new window();
        new LoginFrame();
    }

}
