/*
Author : Shim Wei Hean
Class  : DC02 D2
*/
package da;

import domain.Orderlist;
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
import ui.UI;

public class OrderDA {

    private String host = "jdbc:derby://localhost:1527/collegeDB";
    private String user = "nbuser";
    private String password = "nbuser";

    private String tableOrder = "ORDERS";
    private String tableOrderList = "ORDERLIST";
    private String table = "TABLES";
    private Connection conn;
    private PreparedStatement stmt;
    private PreparedStatement stmt2;
    private PreparedStatement stmt3;
    private ResultSet rs;
    

    public OrderDA() {
        createConnection();
    }

    private void createConnection() {

        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE : Connection established.(Connected to OrderDA)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "createConnection "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createOrder(String tableid) {
        String queryStr = "Insert into " + tableOrder + " VALUES (?,?)";

        String deleteOrderlistQuery = "DELETE FROM ORDERLIST WHERE ORDERID = ?";
        String deleteOrderQuery = "DELETE FROM ORDERS WHERE ID = ?";
        int orderRowCount = getOrderRow();

        try {

            if (UI.specialid == null) {
                UI.specialid = String.format("R%03d", orderRowCount + 1);
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, String.format("R%03d", orderRowCount + 1));
                stmt.setString(2, tableid);
                stmt.executeUpdate();
            } else {
                stmt = conn.prepareStatement(deleteOrderlistQuery);
                stmt.setString(1, UI.specialid);
                stmt.executeUpdate();
                
                stmt2 = conn.prepareStatement(deleteOrderQuery);
                stmt2.setString(1, UI.specialid);
                stmt2.executeUpdate();
                
                stmt3 = conn.prepareStatement(queryStr);
                stmt3.setString(1, UI.specialid);
                stmt3.setString(2, tableid);
                stmt3.executeUpdate();
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "createorder" + ex.getMessage());
        }

    }

    public void createOrderList(String menuid, String orderid, String name, double price, int qty) {
        String queryStr = "Insert into " + tableOrderList + " VALUES(?,?,?,?,?)";
        try {

            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, menuid);
            stmt.setString(2, orderid);
            stmt.setString(3, name);
            stmt.setDouble(4, price);
            stmt.setInt(5, qty);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "createorderlist" + ex.getMessage());
        }
    }

    public ArrayList<Orderlist> getOrderlist(String orderid ) {
        ResultSet rs = null;
        String queryStr = "SELECT * FROM ORDERLIST WHERE ORDERID = ? ";
        Orderlist orderlist;
        ArrayList<Orderlist> arrayOrderlist = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, orderid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderlist = new Orderlist(rs.getString("MENUID"), rs.getString("ORDERID"), rs.getString("NAME"), rs.getDouble("PRICE"), rs.getInt("QTY"));
                arrayOrderlist.add(orderlist);
            }
            return arrayOrderlist;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "getorderlist" + ex.getMessage());
        }
        return null;
    }

    public int getOrderRow() {
        String queryStr = "Select * from " + tableOrder;
        ResultSet rs = null;
        int row = 0;
        try {
            stmt = conn.prepareStatement(queryStr);
            rs = stmt.executeQuery();
            while (rs.next()) {
                row++;
            }

            return row;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "getorderrow" + ex.getMessage());
        }
        return row;
    }

    public ResultSet getMenu() {
        ResultSet rs = null;
        String queryStr = "SELECT * FROM MENU";

        try {
            stmt = conn.prepareStatement(queryStr, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = stmt.executeQuery();

            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "getorderId" + e.getMessage());
        }

        return rs;
    }

    public void changeTableStatusToSeated(String tableid) {
        String queryStr = "UPDATE TABLES SET OCCUPYSTATUS = ? , PAYSTATUS = ? WHERE ID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, "Occupied");
            stmt.setString(2, "Unpaid");
            stmt.setString(3, tableid);
            stmt.executeUpdate();
            System.out.print(tableid+"wtf");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "changetablestatus" + ex.getMessage());
        }
    }

    public String getOrderId(String tableid) {
        String queryStr = "SELECT O.ID FROM ORDERS O, TABLES T WHERE O.TABLEID = ? AND T.ID = O.TABLEID AND T.OCCUPYSTATUS = ? AND T.PAYSTATUS = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, tableid);
            stmt.setString(2, "Occupied");
            stmt.setString(3, "Unpaid");
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("ID");
                System.out.print(id+"error here");
                return id;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "getorderid" + ex.getMessage());
        }
        return null;
    }
    
    public DefaultTableModel createTable() {
        DefaultTableModel tableModel = null;
        String queryStr = "Select * from orderlist";
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
    
    public void changeTableStatusToFree(String tableid) {
        String queryStr = "UPDATE TABLES SET OCCUPYSTATUS = ? , PAYSTATUS = ? WHERE ID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);

            stmt.setString(1, "Free");
            stmt.setString(2, "Paid");
            stmt.setString(3, tableid);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "changetablestatus" + ex.getMessage());
        }
    }
    
   
}
