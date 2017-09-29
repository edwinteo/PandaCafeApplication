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
import ui.UI;


public class createFrame extends JFrame {
    private JPanel centerFlowPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(4,2));
    private JPanel SouthPanel = new JPanel(new GridLayout(1,2));
    
    private JTextField jtfFoodID = new JTextField();
    private JTextField jtfFoodName = new JTextField();
    private JTextField jtfFoodPrice = new JTextField();
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private JComboBox FoodTypeComboBox = new JComboBox(model);
    private StandardButton createOKBtn = new StandardButton("CREATE",Theme.STANDARD_DARKGREEN_THEME);
    private StandardButton CancelBtn = new StandardButton("CANCEL",Theme.STANDARD_DARKGREEN_THEME);
    
     private FoodMenuDA menuDA = new FoodMenuDA();
    
    public createFrame(){
        
        //COMBO BOX
        model.addElement("FOOD");
        model.addElement("BEVERAGE");
        model.addElement("DESSERT");
        
        //Center
        centerPanel.add(new JLabel("Food ID:"));
        centerPanel.add(jtfFoodID);
        centerPanel.add(new JLabel("Food Name:"));
        centerPanel.add(jtfFoodName);
        centerPanel.add(new JLabel("Food Price:"));
        centerPanel.add(jtfFoodPrice);
        centerPanel.add(new JLabel("Food Type:"));
        centerPanel.add(FoodTypeComboBox);
        
        //South
         SouthPanel.add(createOKBtn);
         SouthPanel.add(CancelBtn);
         createOKBtn.addActionListener(new ConfirmListener());
         CancelBtn.addActionListener(new ConfirmListener());
         
         //Frame
         centerFlowPanel.add(centerPanel);
         centerPanel.setPreferredSize(new Dimension(400,200));
         centerFlowPanel.setBackground(Color.lightGray);
         centerPanel.setBackground(Color.lightGray);
         add(SouthPanel,BorderLayout.SOUTH);
         add(centerFlowPanel,BorderLayout.CENTER);
         setVisible(true);
         setTitle("CREATE FOOD DATA");
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setSize(600,300);  
    } 

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == createOKBtn){
            String id = jtfFoodID.getText().toUpperCase();
            String name = jtfFoodName.getText().toUpperCase();
            String type = String.valueOf(FoodTypeComboBox.getSelectedItem());
            String price = jtfFoodPrice.getText();
            try{
            menuDA.createRecord(id,name,type,Double.parseDouble(price));
            }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
           }else if(e.getSource() == CancelBtn){
               dispose();
           }
        }
    } 
}
