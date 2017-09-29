/*
Author : Shim Wei Hean & Ong Boon Fong
Class  : DC02 D2
*/
package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import domain.TableDomain;

public class TableDA {
    
    private String host = "jdbc:derby://localhost:1527/collegeDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "TABLES";
    private Connection conn;
    private PreparedStatement stmt;
    
    public TableDA() {
        createConnection();
    }
    
    public TableDomain getTableInfo(String tableid) {
        ResultSet rs = null;
        TableDomain tableinfo;
        try {
            String queryStr = "SELECT * FROM "+tableName+ " WHERE ID = ?";
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1,tableid);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                tableinfo = new TableDomain(rs.getString("ID"), rs.getString("OCCUPYSTATUS"), rs.getString("PAYSTATUS"));
                return tableinfo;
            }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"table"+ ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    private void createConnection() {
        
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE : Connection established.(tableda)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
