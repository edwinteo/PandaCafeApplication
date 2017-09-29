/*
Author : Shim Wei Hean
Class  : DC02 D2
*/
package control;

import da.OrderDA;
import domain.Orderlist;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class OrderControl {

    private OrderDA orderDa;

    public OrderControl() {
        orderDa = new OrderDA();
    }

    public ArrayList<Orderlist> getOrderlist(String tableid) {
        return orderDa.getOrderlist(tableid);
    }

    public void createOrder(String tableid) {
        orderDa.createOrder(tableid);
    }

    public ResultSet getMenu() {
        return orderDa.getMenu();
    }

    public void createOrderList(String menuid, String orderid, String name, double price, int qty) {
        orderDa.createOrderList(menuid, orderid, name, price, qty);

    }

    public void changeTableStatusToSeated(String tableid) {
        orderDa.changeTableStatusToSeated(tableid);
    }

    public String getOrderId(String tableid) {
        return orderDa.getOrderId(tableid);
    }
    
     public void shutDown() {
         orderDa.shutDown();
     }
     
     public DefaultTableModel createTable() {
         return orderDa.createTable();
     }
     
     public void changeTableStatusToFree(String tableid) {
         orderDa.changeTableStatusToFree(tableid);
     }
}
