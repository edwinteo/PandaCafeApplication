/*
Author : Teo Jia Han 
Class  : DC02 D2
*/

package da;

import domain.Staff;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StaffDA {

    String loginpassword = "nbuser";
    String loginuser = "nbuser";
    String host = "jdbc:derby://localhost:1527/collegeDB";
    String tableName = "STAFF";
    PreparedStatement stmt;
    Statement st;
    Connection conn;
    DefaultTableModel tableModel;

    public StaffDA() {
        createConnection();

    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, loginuser, loginpassword);
            System.out.println("***TRACE : Connection established.(staffDa)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel createTable() {

        String queryStr = "Select ID,NAME,PASSWORD,PHNO,AGE,GENDER,DOB from " + tableName;
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
            String pass = "";
            while (rs.next()) {
                row = new Vector(c);
                for (int i = 1; i <= c; i++) {

                    if (rsmt.getColumnName(i).equals("PASSWORD")) {

                        count = rs.getString(i).length();

                        for (int x = 0; x < count; x++) {
                            pass = pass + "*";

                        }
                        row.add(pass);
                        pass = "";
                    } else {
                        row.add(rs.getString(i));

                    }

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

    public ResultSet getRecord() {
        String queryStr = "Select * from " + tableName;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public ResultSet selectRecord(String id) {
        String queryStr = "Select * from " + tableName + " where ID = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void deleteRecord(String id) {
        String queryStr = "DELETE FROM " + tableName + " WHERE ID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(Staff s) {
        String queryStr = "Update " + tableName + " set NAME = ? ,PHNO = ? , PASSWORD = ? , GENDER = ?, AGE = ? ,DOB = ? WHERE ID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getPhno());
            stmt.setString(3, s.getPassword());
            stmt.setString(4, s.getGender());
            stmt.setString(5, s.getAge());
            stmt.setString(6, s.getDob());
            stmt.setString(7, s.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createRecord(Staff s) {
        String queryStr = "Insert into " + tableName + " (ID,PASSWORD,NAME,PHNO,GENDER,AGE,DOB) VALUES (?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(queryStr, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, s.getId());
            stmt.setString(2, s.getPassword());
            stmt.setString(3, s.getName());
            stmt.setString(4, s.getPhno());
            stmt.setString(5, s.getGender());
            stmt.setString(6, s.getAge());
            stmt.setString(7, s.getDob());

            stmt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getStaffName(String staffid) {
        String queryStr = "Select name from staff where id = ?";
        ResultSet rs;
        String staffName = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staffid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                staffName = rs.getString("name");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "getstaffname " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return staffName;

    }

}
