/*
 Author : Yong Zuo Jun 
 Class  : DC02 D2
 */
package domain;

import control.MemberControl;
import da.OrderDA;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import domain.Member;
import ui.UI;
public class CCReceipt {

    ArrayList<Integer> qty = new ArrayList<Integer>();

    public CCReceipt(Receipt receipt, double grossTotal, double discount, double remaining, double gst, ArrayList<Orderlist> foodlist) {
        MemberControl memberControl = new MemberControl();
        System.out.println(receipt.getOrderid()+ "))))))))))))))))");
        String memberName;
        OrderDA od = new OrderDA();
        try {
            File file = new File("C:\\Users\\asus\\Desktop\\jhCode\\CCreceipt.txt");
            file.delete();
            FileWriter fw = new FileWriter("CCreceipt.txt", false);

            PrintWriter pw = new PrintWriter(fw);

            pw.printf("%60s", "RECEIPT");
            pw.println();
            pw.printf("%60s", "PANDA CAFE");
            pw.println();
            pw.printf("%60s", "TARUC COLLEGE,CANTEEN 1");
            pw.println();
            pw.printf("%s", "---------------------------------------------------------------------------------");
            pw.println();
            pw.printf("%50s%s", "PAY BY : ", receipt.getPaytype()); //PAYMENT TYPE

            pw.println();
            pw.printf("%s", "---------------------------------------------------------------------------------");
            pw.println();
            pw.printf("Order         : %s \t\t By Staff  : %s ", receipt.getId(), receipt.getStaffid()); //ORDER ID AND STAFF ID
            pw.println();
            pw.printf("Order Time : %s \t\t Date       : %s", receipt.getTime(), receipt.getDate());
            pw.println();
            pw.printf("%s", "---------------------------------------------------------------------------------");
            pw.println();
            Member member = memberControl.getMemberInfo(receipt.getMemberid());
            pw.printf("Member Name: %s", member.getName());
            pw.println();
            pw.printf("%s", "---------------------------------------------------------------------------------");
            pw.println();
            pw.printf("%s\t%s\t\t%s\t\t%s", "QTY", "FOOD ID", "FOOD NAME", "PRICE");
            pw.println();
            pw.printf("%s", "---------------------------------------------------------------------------------");
            pw.println();

            ArrayList<Orderlist> list = od.getOrderlist(receipt.getOrderid());
            for (int i = 0; i < list.size(); i++) {
                pw.printf("%d\t%s\t\t%s\t\t%.2f", list.get(i).getQty(), list.get(i).getMenuid(), list.get(i).getName(), list.get(i).getQty() * list.get(i).getPrice());
                pw.println();
            }

            pw.println();

            pw.printf("%s", "---------------------------------------------------------------------------------");
            pw.println();

            //SUB TOTAL
            pw.printf("SUB-TOTAL%20s: %.2f", "", grossTotal);
            pw.println();

            //DISCOUNT
            pw.printf("DISCOUNT%21s: %.2f", "", discount);
            pw.println();

            //GST
            pw.printf("Govt.Tax(GST)%17s: %.2f", "", gst);
            pw.println();

            //GRAND TOTAL
            pw.printf("GRAND TOTAL%14s: %.2f", "", receipt.getTotal());
            pw.println();

            //PAYMENT
            pw.printf("PAYMENT%22s: %.2f", "", receipt.getPayment());
            pw.println();

            //CHANGE DUE
            pw.printf("CHANGE DUE%16s: %.2f", "", remaining);
            pw.println();
            pw.println();
            pw.println();
            //SIGNATURE
            pw.printf("%55s", "SIGNATURE:");
            pw.println();
            pw.println();
            pw.printf("%60s", "__________________________");

            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You have an error.");
        }
       // od.shutDown();
    
    }

}
