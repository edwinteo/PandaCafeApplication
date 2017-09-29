/*
Author : Yong Zuo Jun 
Class  : DC02 D2
*/

package control;
import da.ReceiptDA;
import domain.Receipt;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author asus
 */
public class ReceiptControl {
    private ReceiptDA receiptDa;
    public ReceiptControl(){
        receiptDa = new ReceiptDA();
    }
    public void setReceiptInfo(String orderid,String staffid,String memberid,String paytype,String date,String time,double total,double payment){
        receiptDa.setReceiptInfo(orderid,staffid,memberid,paytype,date,time,total,payment);
    }
    public Receipt getReceiptInfo(String orderid){
        return receiptDa.getReceiptInfo(orderid);
    }
    
    public DefaultTableModel createTable() {
        return receiptDa.createTable();
    }
}
