/*
Author : Yong Zuo Jun 
Class  : DC02 D2
*/

package domain;

public class Receipt {
    String id;
    String orderid;
    String staffid;
    String memberid;
    String paytype;
    String date;
    String time;
    double total = 0;
    double payment = 0;
    public Receipt(){}
    
    public Receipt(String id,String orderid,String staffid, String memberid,String paytype, String date,String time, double total, double payment){
        this.id = id;

        this.orderid = orderid;
        this.staffid = staffid;
        this.memberid = memberid;
        this.paytype = paytype;
        this.date = date;
        this.time = time;
        this.total = total;
        this.payment = payment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
