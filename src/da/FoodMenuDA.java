/*
Author : Ong Boon Fong
Class  : DC02 D2
*/
package da;

import control.FoodMenuControl;
import domain.foodMenu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import ui.UI;
import java.util.ArrayList;
import com.jd.swing.custom.component.button.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;



public class FoodMenuDA {
    private String host = "jdbc:derby://localhost:1527/collegeDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName ="menu";
    private Connection conn;
    private PreparedStatement stmt;
     DefaultTableModel tableModel;
    public FoodMenuDA(){
         createConnection();
    }
    
     
    public void createRecord(String id,String name,String type,double price) {
        String createStr = "INSERT INTO MENU VALUES(?,?,?,?)";
        
        try {
            stmt = conn.prepareStatement(createStr);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, type);
            stmt.setString(4, Double.toString(price));
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Food successfully created", "Create Record",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        public foodMenu getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        foodMenu foodmenu = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            FoodMenuDA progDA = new FoodMenuDA();

            
            if(rs.next()) {
                String programmeCode = rs.getString("ID");
                foodmenu = new foodMenu(id,rs.getString("name"),rs.getString("type"),Double.parseDouble(rs.getString("price")));
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }   
        return foodmenu;
    }
        
     public void updateRecord(String id,String name,String type,double price) {
        String updateStr = "UPDATE "+tableName+" SET NAME =?,TYPE =?,PRICE = ? WHERE ID = ?";
        
        try {
            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3,Double.toString(price));
            stmt.setString(4, id);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Food updated successfully","Update record",JOptionPane.INFORMATION_MESSAGE);
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }  
    }
     
       public void deleteRecord(String id) {
        String deleteStr = "DELETE FROM "+tableName+" WHERE ID = ?";
        
        try {
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, id);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Food deleted", "Delete Record",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<foodMenu> getFoodDetail(){
        String queryStr = "SELECT * FROM " + tableName;
        foodMenu menu = null;
        ArrayList<foodMenu> foodList = new ArrayList<foodMenu>();
        try{
            stmt = conn.prepareCall(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                menu = new foodMenu(rs.getString("id"),rs.getString("name"),rs.getString("type"),Double.parseDouble(rs.getString("price")));
                foodList.add(menu);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return foodList;
    }
    
    public DefaultTableModel createTable(){
        String queryStr = "SELECT * FROM " + tableName;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(queryStr,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rs = stmt.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int c = rsmt.getColumnCount();
            Vector column = new Vector(c);
            
            for(int i=1;i<=c;i++){
                column.add(rsmt.getColumnClassName(i));
            }
            
            Vector data = new Vector();
            Vector row;
            
            while(rs.next()){
            row =new Vector(c);
            
            for(int i =1 ; i<=c;i++){
            row.add(rs.getString(i));
            }
              data.add(row);
            }
            
            tableModel = new DefaultTableModel(data,column);
            return tableModel;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
       return null;
    }
    
    
    private void createConnection() {
        
        try{
            conn = DriverManager.getConnection(host,user,password);
            System.out.println("***TRACE : Connection established.(Foodmenuda)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void shutDown() {
        if (conn != null)
            try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }    
}
