/*
Author : Teo Jia Han
Class  : DC02 D2
*/
package control;

import da.StaffDA;
import domain.Staff;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class StaffControl {

    StaffDA staffDa;

    public StaffControl() {

        staffDa = new StaffDA();
    }

    public ResultSet getRecord() {
        return staffDa.getRecord();
    }

    public ResultSet selectRecord(String id) {
        return staffDa.selectRecord(id);
    }

    public DefaultTableModel createTable() {
        return staffDa.createTable();
    }
    
    public void deleteRecord(String id){
        staffDa.deleteRecord(id);
    }

   public void updateRecord(Staff s){
       staffDa.updateRecord(s);
   }
   
   public void createRecord(Staff s){
       staffDa.createRecord(s);
   }
   
   public String getStaffName(String staffid){
       return staffDa.getStaffName(staffid);
   }
}
