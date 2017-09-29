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
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginDA {

    String loginpassword = "nbuser";
    String loginuser = "nbuser";
    String host = "jdbc:derby://localhost:1527/collegeDB";
    String tableName = "Staff";
    PreparedStatement stmt;
    Connection conn;

    public LoginDA(){
        createConnection();
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, loginuser, loginpassword);
            System.out.println("***TRACE : Connection established.(loginda)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Staff getRecord(String id) {
        String queryStr = "Select * from " + tableName + " where ID = ? ";
        ResultSet rs = null;
        Staff staff = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);

            rs = stmt.executeQuery();
            
            if(rs.next()){
                staff = new Staff(id,rs.getString("name"),rs.getString("password"),rs.getString("phno"),rs.getString("gender"),rs.getString("age"),rs.getString("dob"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        return staff;
    }

}
