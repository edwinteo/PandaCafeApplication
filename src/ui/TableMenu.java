/*
Author : Shim Wei Hean & Ong Boon Fong
Class  : DC02 D2
*/
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import com.jd.swing.custom.component.button.*;
//import com.jd.swing.demo.button.*;
import com.jd.swing.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;

public class TableMenu extends JFrame {

    String loginpassword = "nbuser";
    String loginuser = "nbuser";
    String host = "jdbc:derby://localhost:1527/collegeDB";
    String tableName = "MEMBER";
    PreparedStatement stmt;
    Statement st;
    Connection conn;
    DefaultTableModel tableModel;

    Dimension d = new Dimension(150, 40);

    JPanel j1 = new JPanel(new FlowLayout());
    JPanel j2 = new JPanel(new GridLayout(3, 3, 50, 50));
    JPanel j3 = new JPanel(new FlowLayout());
    
    StandardButton b = new StandardButton("Search Receipt", Theme.STANDARD_DARKGREEN_THEME);
    StandardButton b0 = new StandardButton("Search Order List", Theme.STANDARD_DARKGREEN_THEME);
    StandardButton b1 = new StandardButton("Maintain Staff", Theme.STANDARD_DARKGREEN_THEME);
    StandardButton b2 = new StandardButton("Maintain Member", Theme.STANDARD_DARKGREEN_THEME);
    StandardButton b3 = new StandardButton("Exit", Theme.STANDARD_DARKGREEN_THEME);

    JButton[] tableButton = new JButton[9];

    public TableMenu() {
        b.setPreferredSize(d);
        b0.setPreferredSize(d);
        b1.setPreferredSize(d);
        b2.setPreferredSize(d);
        b3.setPreferredSize(d);

        Border innerBorder = BorderFactory.createTitledBorder("Table");
        Border outBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        j2.setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));

        ImageIcon img1 = new ImageIcon(getClass().getResource("/image/table1.gif"));
        ImageIcon img2 = new ImageIcon(getClass().getResource("/image/table2.gif"));
        ImageIcon img3 = new ImageIcon(getClass().getResource("/image/table3.gif"));
        ImageIcon img4 = new ImageIcon(getClass().getResource("/image/table4.gif"));
        ImageIcon img5 = new ImageIcon(getClass().getResource("/image/table5.gif"));
        ImageIcon img6 = new ImageIcon(getClass().getResource("/image/table6.gif"));
        ImageIcon img7 = new ImageIcon(getClass().getResource("/image/table7.gif"));
        ImageIcon img8 = new ImageIcon(getClass().getResource("/image/table8.gif"));
        ImageIcon img9 = new ImageIcon(getClass().getResource("/image/table9.gif"));

        Image newIMG1 = img1.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG2 = img2.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG3 = img3.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG4 = img4.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG5 = img5.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG6 = img6.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG7 = img7.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG8 = img8.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        Image newIMG9 = img9.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);

        ImageIcon adjustedPic1 = new ImageIcon(newIMG1);
        ImageIcon adjustedPic2 = new ImageIcon(newIMG2);
        ImageIcon adjustedPic3 = new ImageIcon(newIMG3);
        ImageIcon adjustedPic4 = new ImageIcon(newIMG4);
        ImageIcon adjustedPic5 = new ImageIcon(newIMG5);
        ImageIcon adjustedPic6 = new ImageIcon(newIMG6);
        ImageIcon adjustedPic7 = new ImageIcon(newIMG7);
        ImageIcon adjustedPic8 = new ImageIcon(newIMG8);
        ImageIcon adjustedPic9 = new ImageIcon(newIMG9);

        tableButton[0] = new JButton(adjustedPic1);
        tableButton[1] = new JButton(adjustedPic2);
        tableButton[2] = new JButton(adjustedPic3);
        tableButton[3] = new JButton(adjustedPic4);
        tableButton[4] = new JButton(adjustedPic5);
        tableButton[5] = new JButton(adjustedPic6);
        tableButton[6] = new JButton(adjustedPic7);
        tableButton[7] = new JButton(adjustedPic8);
        tableButton[8] = new JButton(adjustedPic9);

        for (int i = 0; i < 9; i++) {
            j2.add(tableButton[i]);
        }
        j3.add(b);
        j3.add(b0);
        j3.add(b1);
        j3.add(b2);
        j3.add(b3);

        add(j1, BorderLayout.NORTH);
        add(j2, BorderLayout.CENTER);
        add(j3, BorderLayout.SOUTH);
        
        for (int i = 0; i < 9; i++) {
            tableButton[i].addActionListener(new tableClick());
            tableButton[i].setBackground(Color.WHITE);
        }
        b.addActionListener(new ReceiptSearchClick());
        b0.addActionListener(new OrderSearchClick());
        b1.addActionListener(new staffClick());
        b2.addActionListener(new staffClick());
        b3.addActionListener(new staffClick());

        setSize(500, 500);
        j2.setBackground(Color.LIGHT_GRAY);
        j3.setBackground(Color.LIGHT_GRAY);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    class tableClick implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int x = 0;
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == tableButton[i]) {
                    x = i;

                }

            }
            String table = String.format("Table%d", x + 1);
            UI u1 = new UI(table);
            //u1.setAlwaysOnTop(true);

        }
    }

    class staffClick implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == b1){
                new MaintainStaffFrame();
            }
            if(e.getSource() == b2){
                new MaintainMemberFrame();
            }
            if (e.getSource() == b3) {
                System.exit(0);
            }
        }
    }
    
    class OrderSearchClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            searchOrderFrame sof = new searchOrderFrame();
        }
    }
    class ReceiptSearchClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            searchReceipyFrame srf = new searchReceipyFrame();
            srf.shutdown();
        }
    }
}
