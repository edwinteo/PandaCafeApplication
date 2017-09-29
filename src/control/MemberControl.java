/*
Author : Teo Jia Han
Class  : DC02 D2
*/
package control;

import da.MemberDA;
import domain.Member;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class MemberControl {

    MemberDA memberDa;

    public MemberControl() {
        memberDa = new MemberDA();
    }

    public ResultSet selectRecord(String id) {
        return memberDa.selectRecord(id);
    }

    public ResultSet getRecord() {
        return memberDa.getRecord();
    }

    public DefaultTableModel createTable() {
        return memberDa.createTable();
    }
    
    public void createRecord(Member m){
        memberDa.createRecord(m);
    }
    
    public void updateRecord(Member m){
        memberDa.updateRecord(m);
    }
    
    public void deleteRecord(String id){
        memberDa.deleteRecord(id);
        
    }
    public Member getMemberInfo(String memberid) {
        return memberDa.getMemberInfo(memberid);
    }

    public void setMemberLoyalPoints(String memberid, int loyalPoints) {
        memberDa.setMemberLoyalPoints(memberid, loyalPoints);
    }
    
    public boolean checkMemberStatus(String id) {
        return memberDa.checkMemberStatus(id);
    }

    public void renewMembership(String id) {
        memberDa.renewMembership(id);
    }
   
}
