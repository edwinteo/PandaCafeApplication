/*
Author : Yong Zuo Jun 
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
import da.ReceiptDA;
import domain.Receipt;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.PatternSyntaxException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class searchReceipyFrame extends JFrame {
        ReceiptDA receiptDA = new ReceiptDA();
        private JPanel southPanel = new JPanel(new GridLayout(1,3));
        private JPanel northPanel = new JPanel();
        private JPanel flowSouthPanel = new JPanel();
        private JPanel flowNorthPanel = new JPanel();
        JScrollPane scrollTable;
        TableRowSorter<TableModel> sorter;
        JLabel lbSearch = new JLabel("Search: ");
        JTextField tfSearch = new JTextField();
        JButton jbSearch = new JButton("Search");
        JButton backBtn = new JButton("Back");
   
    public searchReceipyFrame() {
        flowSouthPanel.add(southPanel);
        flowNorthPanel.add(northPanel);
        JTable foodTable = new JTable(receiptDA.createTable());
        
        //tabke settings
        foodTable.setPreferredScrollableViewportSize(new Dimension(400, 250));
        foodTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
         
         sorter = new TableRowSorter<TableModel>(foodTable.getModel());
         foodTable.setRowSorter(sorter);
         
         //add table for scroll Pane
         scrollTable = new JScrollPane(foodTable);
         scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         

         
         tfSearch.addKeyListener(new KeyAdapter(){
             @Override
             public void keyPressed(KeyEvent e){
                 if(e.getKeyCode() == KeyEvent.VK_ENTER){
                     jbSearch.doClick();
                 }
             }
         });
         
         jbSearch.addActionListener(new SearchBtnListener());
         
         northPanel.add(scrollTable,BorderLayout.NORTH);
         
        
        //Frame
        southPanel.setPreferredSize(new Dimension(100,100));
        flowSouthPanel.setPreferredSize(new Dimension(100,100));
        flowNorthPanel.setPreferredSize(new Dimension(800,200));
        add(scrollTable);
        southPanel.add(lbSearch);
        southPanel.add(tfSearch);
        southPanel.add(jbSearch);
        southPanel.add(backBtn);
        backBtn.addActionListener(new backListener());
        add(southPanel,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("TestTableSortFilterSortFilter");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public void shutdown(){
        receiptDA.shutDown();
    }
    
    
    private class backListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
           // setVisible(false);
            shutdown();
            dispose();
        }
    }

    private  class SearchBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = tfSearch.getText().toUpperCase();
            
            if(text.trim().length() == 0){
                sorter.setRowFilter(null);
            }else{
                try{
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }catch(PatternSyntaxException pse){
                  JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }
            tfSearch.requestFocus();
            tfSearch.setText("");
        }
    }
}
