/*
Author : Yong Zuo Jun 
Class  : DC02 D2
*/

package da;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import domain.Receipt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author asus
 */
public class ReceiptDA {
    private String host = "jdbc:derby://localhost:1527/collegeDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String table = "RECEIPT";
    private Connection conn;
    private PreparedStatement stmt;
    DefaultTableModel tableModel;
    public ReceiptDA(){
        createConnection();
    }
    private void createConnection() {

        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE : Connection established.(Connected to ReceiptDA)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setReceiptInfo(String orderid,String staffid,String memberid,String paytype,String date,String time,double total,double payment){
        ResultSet rs = null;
        String queryStr = "INSERT INTO "+table+" VALUES(?,?,?,?,?,?,?,?,?)";
        int row = getReceiptID();
        try{
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1,String.format("T%03d",row+1));
            stmt.setString(2,orderid);
            stmt.setString(3,staffid);
            stmt.setString(4,memberid);
            stmt.setString(5,paytype);
            stmt.setString(6,date);
            stmt.setString(7,time);
            stmt.setDouble(8,total);
            stmt.setDouble(9,payment);
            stmt.executeUpdate();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "setReceiptInfo"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public int getReceiptID(){
        ResultSet rs = null;
        String queryStr = "Select * from RECEIPT";
        int row = 0;
        try{
            stmt = conn.prepareStatement(queryStr);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                row++;
            }
            
            return row;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "getreceiptid"+ex.getMessage());
        }
        return 0;
    }
    public Receipt getReceiptInfo(String orderid){
        ResultSet rs = null;
        String queryStr = "Select * from RECEIPT WHERE ORDERID = ?";
        Receipt receipt = null;
        try{
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, orderid);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                receipt = new Receipt(rs.getString("ID"),rs.getString("ORDERID"),rs.getString("STAFFID"),rs.getString("MEMBERID"),rs.getString("PAYTYPE"),rs.getString("REPDATE"),rs.getString("REPTIME"),rs.getDouble("TOTAL"),rs.getDouble("PAYMENT"));
            }
            
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "getreceiptid"+ex.getMessage());
        }
        return receipt;
    }
    
    public DefaultTableModel createTable() {

        String queryStr = "Select * from RECEIPT";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int c = rsmt.getColumnCount();
            Vector column = new Vector(c);
            for (int i = 1; i <= c; i++) {
                column.add(rsmt.getColumnName(i));
            }

            Vector data = new Vector();
            Vector row;
            int count = 0;

            while (rs.next()) {
                row = new Vector(c);
                for (int i = 1; i <= c; i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);
            }
            tableModel = new DefaultTableModel(data, column);
            return tableModel;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        return null;
    }
    
    public void shutDown() {
        if (conn != null) {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "shutdown " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
}
