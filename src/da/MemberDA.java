/*
Author : Teo Jia Han 
Class  : DC02 D2
*/

package da;

import domain.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MemberDA {

    String loginpassword = "nbuser";
    String loginuser = "nbuser";
    String host = "jdbc:derby://localhost:1527/collegeDB";
    String tableName = "MEMBER";
    PreparedStatement stmt;
    Statement st;
    Connection conn;
    DefaultTableModel tableModel;

    public MemberDA() {
        createConnection();
    }

    public ResultSet selectRecord(String id) {
        String queryStr = "Select * from " + tableName + " where ID = ?";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
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

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, loginuser, loginpassword);
            System.out.println("***TRACE : Connection established.(Connected to MemberDA)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel createTable() {

        String queryStr = "Select ID,NAME,PHNO,GENDER,DOB,LP,STATUS,REGD,EXPD from " + tableName;
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

    public void createRecord(Member m) {
        String queryStr = "Insert into " + tableName + " (ID,NAME,ADDR,PHNO,GENDER,DOB,REGD,EXPD,STATUS) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, m.getId());
            stmt.setString(2, m.getName());
            stmt.setString(3, m.getAddr());
            stmt.setString(4, m.getPhno());
            stmt.setString(5, m.getGender());
            stmt.setString(6, m.getDob());
            stmt.setString(7, m.getRegd());
            stmt.setString(8, m.getExpd());
            stmt.setString(9, m.getStatus());

            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(Member m) {
        String queryStr = "Update " + tableName + " set NAME = ? , ADDR=? , PHNO = ? , GENDER = ? , DOB = ? where ID = ?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, m.getName());
            stmt.setString(2, m.getAddr());
            stmt.setString(3, m.getPhno());
            stmt.setString(4, m.getGender());
            stmt.setString(5, m.getDob());
            stmt.setString(6, m.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
    public Member getMemberInfo(String memberid) {
        ResultSet rs = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        Member member = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, memberid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                member = new Member(rs.getString("ID"), rs.getString("NAME"), rs.getString("ADDR"), rs.getString("PHNO"), rs.getInt("LP"), rs.getString("GENDER"), rs.getString("EXPD"), rs.getString("REGD"), rs.getString("DOB"), rs.getString("STATUS"));
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "getMemberID" + ex.getMessage());
            ex.printStackTrace();
        }
        member = new Member("");
        return member;
    }

    public void setMemberLoyalPoints(String memberid, int loyalPoints) {
        ResultSet rs = null;
        String queryStr = "UPDATE " + tableName + " set LP = ?" + " WHERE ID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, loyalPoints);
            stmt.setString(2, memberid);
            stmt.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "setMemberLoyalPoints" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public boolean checkMemberStatus(String id) {
        ResultSet rs = null;
        String queryStr = "SELECT REGD,EXPD FROM MEMBER WHERE ID = ?";
        String queryStatus = "UPDATE MEMBER SET STATUS = ? WHERE ID = ?";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("d-MMM-yyyy");
                String regDate = rs.getString("REGD");
                String expDate = rs.getString("EXPD");
                try {
                    Date regD = format.parse(regDate);
                    Date expD = format.parse(expDate);

                    if (expD.compareTo(regD) >= 1) {
                        stmt = conn.prepareStatement(queryStatus);
                        stmt.setString(1, "Expired");
                        stmt.setString(2, id);
                        stmt.executeUpdate();

                        return true;
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Date parse exception " + ex.getMessage());
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "check member status " + e.getMessage());
        }
        return false;
    }
    
    public void renewMembership(String id){
        String queryStr = "UPDATE MEMBER SET EXPD = ? , REGD = ? , STATUS = ? WHERE ID = ? ";
        try{
        stmt = conn.prepareStatement(queryStr);
        
        String regDate = new Date().toString();
        String result[] = regDate.split(" ");
        
        stmt.setString(1, result[2]+ "-" + result[1]+ "-" + result[5]);
        int year = (Integer.parseInt(result[5])) + 1 ;
        stmt.setString(2, result[2]+ "-" + result[1]+ "-" + year);
        stmt.setString(3, "Member");
        stmt.setString(4, id);
        stmt.executeUpdate();
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "renewmembership " + ex.getMessage());
        }
    }
}
