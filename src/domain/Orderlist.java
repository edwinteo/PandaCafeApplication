/*
Author : Shim Wei Hean
Class  : DC02 D2
*/
package domain;

public class Orderlist {
    private String menuid;
    private String orderid;
    private String name;
    private double price;
    private int qty;
    
    public Orderlist(String menuid, String orderid, String name ,double price , int qty){
            this.menuid = menuid;
            this.orderid = orderid;
            this.name = name;
            this.price = price;
            this.qty  = qty;
    }
    
    public Orderlist(){
        
    }
    
    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    
}
