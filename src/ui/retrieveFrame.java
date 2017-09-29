/*
Author : Ong Boon Fong
Class  : DC02 D2
*/
package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jd.swing.util.*;
import com.jd.swing.custom.component.button.*;
import java.util.ArrayList;
import java.sql.*;
import da.FoodMenuDA;
import control.FoodMenuControl;
import domain.foodMenu;


public class retrieveFrame extends JFrame {
    private JPanel centerFlowPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(4,2));
    private JPanel SouthPanel = new JPanel(new GridLayout(1,2));
    
    private JTextField jtfFoodID = new JTextField();
    private JTextField jtfFoodName = new JTextField();
    private JTextField jtfFoodPrice = new JTextField();
    private JTextField jtfFoodType = new JTextField();
    private StandardButton retrieveBtn = new StandardButton("RETRIEVE",Theme.STANDARD_DARKGREEN_THEME);
    private StandardButton CancelBtn = new StandardButton("CANCEL",Theme.STANDARD_DARKGREEN_THEME);
    
    private FoodMenuDA menuDA = new FoodMenuDA();
    
    public retrieveFrame(){
        //Center
        centerPanel.add(new JLabel("Food ID:"));
        centerPanel.add(jtfFoodID);
        centerPanel.add(new JLabel("Food Name:"));
        centerPanel.add(jtfFoodName);
        jtfFoodName.setEditable(false);
        centerPanel.add(new JLabel("Food Price:"));
        centerPanel.add(jtfFoodPrice);
        jtfFoodPrice.setEditable(false);
        centerPanel.add(new JLabel("Food Type:"));
        centerPanel.add(jtfFoodType);
        jtfFoodType.setEditable(false);
        
         SouthPanel.add(retrieveBtn);
         SouthPanel.add(CancelBtn);
         retrieveBtn.addActionListener(new ConfirmListener());
         CancelBtn.addActionListener(new ConfirmListener());
        
         //Frame
         
         centerFlowPanel.add(centerPanel);
         centerPanel.setPreferredSize(new Dimension(400,200));
         centerFlowPanel.setBackground(Color.lightGray);
         centerPanel.setBackground(Color.lightGray);
         add(SouthPanel,BorderLayout.SOUTH);
         add(centerFlowPanel,BorderLayout.CENTER);
         setVisible(true);
         setTitle("RETRIEVE FOOD DATA");
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setSize(600,300);    
    }

    private class ConfirmListener implements ActionListener {
        @Override
         public void actionPerformed(ActionEvent e) {
           if(e.getSource() == retrieveBtn){
            String id = jtfFoodID.getText().toUpperCase();
            foodMenu foodmenu = menuDA.getRecord(id);
            if (foodmenu != null) {
                jtfFoodName.setText(foodmenu.getName());
                jtfFoodPrice.setText(Double.toString(foodmenu.getPrice()));
                jtfFoodType.setText(foodmenu.getType());
            } else {
                JOptionPane.showMessageDialog(null, "No such food code.", "RECORD NOT FOUND", JOptionPane.ERROR_MESSAGE);
            }
           }else if(e.getSource() == CancelBtn){
               dispose();
           }
    }
}
}
