/*
 Author : Ong Boon Fong and Shim Wei Hean
 Class  : DC02 D2
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jd.swing.util.*;
import com.jd.swing.custom.component.button.*;
import java.util.ArrayList;
import java.sql.*;
import da.FoodMenuDA;
import domain.foodMenu;
import ui.searchFrame;

import control.TableControl;
import domain.Orderlist;
import domain.TableDomain;
import control.OrderControl;
import control.StaffControl;
import da.OrderDA;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UI extends JFrame {

    String staffname = null;
    int comfirmCickCounter = 0;
    public static String specialid = null;
    //database for table
    TableControl tableControl;
    OrderControl orderControl;
    StaffControl staffControl;

    //DATABASE
    FoodMenuDA menuDA = new FoodMenuDA();
    searchFrame f1;
    JPanel flowNorthPanel = new JPanel();
    JPanel flowWestPanel = new JPanel();
    JPanel flowSouthPanel = new JPanel();
    JPanel flowEastPanel = new JPanel();
    JPanel flowTopLeftPanel = new JPanel(new GridLayout(1, 1));
    JPanel flowTopRightPanel = new JPanel(new GridLayout(1, 2));
    JPanel TopRightPanelStaff = new JPanel(new GridLayout(3, 1));
    JPanel TopRightPanelTime = new JPanel(new FlowLayout());

    JLabel date = new JLabel();
    JLabel time = new JLabel();

    //Top-right
    GradientButton pictureBtn = new GradientButton("", Theme.GRADIENT_BLACK_THEME, ButtonType.BUTTON_RECTANGULAR);

    //Top-left
    JPanel topPanel = new JPanel(new FlowLayout()); //SEARCH BUTTON
    JPanel topLeftPanel = new JPanel(); //Table NO.  
    String table;
    GradientButton TableNOBtn;

    //North
    JPanel NorthPanel = new JPanel(new GridLayout(1, 7));
    GradientButton searchBtn = new GradientButton("SEARCH", Theme.GRADIENT_GREEN_THEME);
    ArrayList<GradientButton> SwitchBtn = new ArrayList<GradientButton>();

    //West
    int x = 0;
    JPanel WestPanel = new JPanel(new GridLayout(6, 5, 10, 10));
    GlossyButton addBtn = new GlossyButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+<br>ADD NEW</html>", Theme.GLOSSY_MULTIBLUEGREEN_THEME);
    ArrayList<GlossyButton> FoodBtn = new ArrayList<GlossyButton>();
    ArrayList<GlossyButton> BeverageBtn = new ArrayList<GlossyButton>();
    ArrayList<GlossyButton> DessertBtn = new ArrayList<GlossyButton>();
    ArrayList<GlossyButton> SetBtn = new ArrayList<GlossyButton>();
    ArrayList<foodMenu> foodList;

    //South
    JPanel SouthPanel = new JPanel(new GridLayout(1, 4));
    StandardButton refreshBtn = new StandardButton("", Theme.STANDARD_DARKGREEN_THEME);
    ArrayList<StandardButton> CRUDBtn = new ArrayList<StandardButton>();

    //East
    private JLabel listName = new JLabel("ORDER LIST");
    private JTabbedPane tab = new JTabbedPane();
    private JPanel east2 = new JPanel(new FlowLayout());
    private JPanel east3 = new JPanel(new FlowLayout());
    private JPanel EastPanel = new JPanel(new BorderLayout());
    private JTextField[] textquantity = new JTextField[66];
    private JTextField[] textprice = new JTextField[66];
    private JTextField[] textname = new JTextField[66];
    private JButton[] add = new JButton[66];
    private JButton[] minus = new JButton[66];
    private JButton[] cancel = new JButton[66];
    private JPanel firstpanelArray = new JPanel(new GridLayout(22, 1));
    private JPanel firstpanelArray2 = new JPanel(new GridLayout(22, 1));
    private JPanel firstpanelArray3 = new JPanel(new GridLayout(22, 1));
    private JPanel firstpanelArray4 = new JPanel(new GridLayout(22, 1));
    private JPanel firstpanelArray5 = new JPanel(new GridLayout(22, 1));
    private JPanel firstpanelArray6 = new JPanel(new GridLayout(22, 1));
    private JPanel secondpanelArray = new JPanel(new GridLayout(22, 1));
    private JPanel secondpanelArray2 = new JPanel(new GridLayout(22, 1));
    private JPanel secondpanelArray3 = new JPanel(new GridLayout(22, 1));
    private JPanel secondpanelArray4 = new JPanel(new GridLayout(22, 1));
    private JPanel secondpanelArray5 = new JPanel(new GridLayout(22, 1));
    private JPanel secondpanelArray6 = new JPanel(new GridLayout(22, 1));
    private JPanel thirdpanelArray = new JPanel(new GridLayout(22, 1));
    private JPanel thirdpanelArray2 = new JPanel(new GridLayout(22, 1));
    private JPanel thirdpanelArray3 = new JPanel(new GridLayout(22, 1));
    private JPanel thirdpanelArray4 = new JPanel(new GridLayout(22, 1));
    private JPanel thirdpanelArray5 = new JPanel(new GridLayout(22, 1));
    private JPanel thirdpanelArray6 = new JPanel(new GridLayout(22, 1));
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    String number = null;
    double calculatedprice = 0;
    private StandardButton confirm = new StandardButton("CONFIRM ORDER", Theme.STANDARD_DARKGREEN_THEME);
    private StandardButton back = new StandardButton("BACK", Theme.STANDARD_DARKGREEN_THEME);
    private StandardButton payment = new StandardButton("PAYMENT", Theme.STANDARD_DARKGREEN_THEME);

    int[] a = new int[66];
    double[] oprice = new double[66];
    int empty = 0;
    double calculateprice;
    Boolean Exists = false;
    Boolean Full = false;
    private JPanel east = new JPanel(new FlowLayout());
    ImageIcon searchImg = new ImageIcon("src\\image\\search2.gif");
    ImageIcon refreshImg = new ImageIcon("src\\image\\refreshImg.png");
    ImageIcon pictureImg = new ImageIcon("src\\image\\8.jpg");

    public UI(String tablename) {
        //database table control
        tableControl = new TableControl();
        orderControl = new OrderControl();
        staffControl = new StaffControl();

        table = tablename;
        TableNOBtn = new GradientButton("" + table, Theme.GRADIENT_BROWN_THEME);

        flowNorthPanel.add(flowTopLeftPanel);

        flowNorthPanel.add(topPanel);                                         //search Button
        flowNorthPanel.add(NorthPanel);
        flowWestPanel.add(WestPanel);
        flowSouthPanel.add(SouthPanel);
        flowEastPanel.add(EastPanel);
        flowNorthPanel.add(flowTopRightPanel);

        //Top-Right
        pictureBtn.setPreferredSize(new Dimension(80, 100));
        flowTopRightPanel.add(pictureBtn);
        staffname = staffControl.getStaffName(LoginFrame.staffid);
        //flowTopRightPanel.add(new JLabel(String.format("Staff ID:%s ",LoginFrame.staffid)));
        TopRightPanelStaff.add(new JLabel(String.format("Staff ID:  %s ", LoginFrame.staffid)));
        TopRightPanelStaff.add(new JLabel(String.format("Staff Name:  %s ", staffname)));
        date.setHorizontalAlignment(SwingConstants.LEFT);
        TopRightPanelTime.add(date);
        time.setHorizontalAlignment(SwingConstants.LEFT);
        TopRightPanelTime.add(time);
        CurrentDate();
        TopRightPanelStaff.add(TopRightPanelTime);
        flowTopRightPanel.add(TopRightPanelStaff);
        pictureBtn.setIcon(pictureImg);

        //Top-Left
        topPanel.add(new JLabel("        "));
        topPanel.add(searchBtn);
        searchBtn.setPreferredSize(new Dimension(100, 95));
        flowTopLeftPanel.add(TableNOBtn);
        TableNOBtn.setPreferredSize(new Dimension(100, 95));
        searchBtn.addActionListener(new searchListener());
        searchBtn.setIcon(searchImg);

        //North
        SwitchBtn.add(new GradientButton("FOOD", Theme.GRADIENT_DARKGREEN_THEME));        //0-FOOD
        SwitchBtn.add(new GradientButton("BEVERAGE", Theme.GRADIENT_DARKGREEN_THEME));    //1-BEVERAGE
        SwitchBtn.add(new GradientButton("DESSERT", Theme.GRADIENT_DARKGREEN_THEME));     //2-DESSERT

        for (int i = 0; i < SwitchBtn.size(); i++) {
            NorthPanel.add(SwitchBtn.get(i));
            SwitchBtn.get(i).setPreferredSize(new Dimension(150, 80));
        }

        //WEST
        initializeModel();
        SwitchBtn.get(0).addActionListener(new SwitchListener());
        SwitchBtn.get(1).addActionListener(new SwitchListener());
        SwitchBtn.get(2).addActionListener(new SwitchListener());
        addBtn.addActionListener(new CRUDListener());

        //EAST
        for (int i = 0; i < 66; i++) {
            a[i] = 1;
        }

        for (int i = 0; i < 66; i++) {
            textquantity[i] = new JTextField(2);

            textquantity[i].setEditable(false);

            // textquantity[i].setVisible(false);
        }

        for (int i = 0; i < 66; i++) {
            textprice[i] = new JTextField(6);

            textprice[i].setEditable(false);
            // textprice[i].setVisible(false);
        }

        for (int i = 0; i < 66; i++) {
            textname[i] = new JTextField(14);
            textname[i].setEditable(false);

            // textname[i].setVisible(false);
        }

        for (int i = 0; i < 66; i++) {
            add[i] = new StandardButton("+", Theme.STANDARD_BLACK_THEME);

            add[i].setEnabled(false);

            add[i].setPreferredSize(new Dimension(50, 20));
            // add[i].setVisible(false);
        }

        for (int i = 0; i < 66; i++) {
            minus[i] = new StandardButton("-", Theme.STANDARD_BLACK_THEME);
            minus[i].setEnabled(false);
            minus[i].setPreferredSize(new Dimension(50, 20));
            //minus[i].setVisible(false);
        }

        for (int i = 0; i < 66; i++) {
            cancel[i] = new StandardButton("X", Theme.STANDARD_BLACK_THEME);
            cancel[i].setEnabled(false);
            cancel[i].setPreferredSize(new Dimension(50, 20));
            // cancel[i].setVisible(false);
        }

        for (int i = 0; i < 22; i++) {
            firstpanelArray.add(cancel[i]);
            firstpanelArray2.add(textname[i]);
            firstpanelArray3.add(add[i]);
            firstpanelArray4.add(textquantity[i]);
            firstpanelArray5.add(minus[i]);
            firstpanelArray6.add(textprice[i]);
        }

        for (int i = 22; i < 44; i++) {
            secondpanelArray.add(cancel[i]);
            secondpanelArray2.add(textname[i]);
            secondpanelArray3.add(add[i]);
            secondpanelArray4.add(textquantity[i]);
            secondpanelArray5.add(minus[i]);
            secondpanelArray6.add(textprice[i]);
        }

        for (int i = 44; i < 66; i++) {
            thirdpanelArray.add(cancel[i]);
            thirdpanelArray2.add(textname[i]);
            thirdpanelArray3.add(add[i]);
            thirdpanelArray4.add(textquantity[i]);
            thirdpanelArray5.add(minus[i]);
            thirdpanelArray6.add(textprice[i]);
        }

        //submit.addActionListener(new submitClick());
        for (int i = 0; i < 66; i++) {
            if (cancel[i].getActionListeners().length < 1) {
                cancel[i].addActionListener(new deleteClick());
                add[i].addActionListener(new listClick());
                minus[i].addActionListener(new listClick());
            }
        }

        buttonPanel.add(back);
        buttonPanel.add(confirm);
        buttonPanel.add(payment);

        east.add(firstpanelArray);
        east.add(firstpanelArray2);
        east.add(firstpanelArray3);
        east.add(firstpanelArray4);
        east.add(firstpanelArray5);
        east.add(firstpanelArray6);

        east2.add(secondpanelArray);
        east2.add(secondpanelArray2);
        east2.add(secondpanelArray3);
        east2.add(secondpanelArray4);
        east2.add(secondpanelArray5);
        east2.add(secondpanelArray6);

        east3.add(thirdpanelArray);
        east3.add(thirdpanelArray2);
        east3.add(thirdpanelArray3);
        east3.add(thirdpanelArray4);
        east3.add(thirdpanelArray5);
        east3.add(thirdpanelArray6);

        tab.add(east, "Page 1");
        tab.add(east2, "Page 2");
        tab.add(east3, "Page 3");
        EastPanel.add(listName, BorderLayout.NORTH);
        EastPanel.add(tab, BorderLayout.CENTER);
        EastPanel.add(buttonPanel, BorderLayout.SOUTH);
        backClick backclick = new backClick();
        back.addActionListener(backclick);
        confirm.addActionListener(new confirmClick());
        payment.addActionListener(new paymentClick());
        // if(FoodBtn.get(1).getActionListeners().length<1){
        for (int i = 0; i < FoodBtn.size(); i++) {
            FoodBtn.get(i).addActionListener(new orderClick());
        }
        for (int i = 0; i < BeverageBtn.size(); i++) {
            BeverageBtn.get(i).addActionListener(new orderClick());
        }
        for (int i = 0; i < DessertBtn.size(); i++) {
            DessertBtn.get(i).addActionListener(new orderClick());
        }
        //}
        AddorderListListener();

        //South
        CRUDBtn.add(new StandardButton("CREATE", Theme.STANDARD_DARKGREEN_THEME));
        CRUDBtn.add(new StandardButton("RETRIEVE", Theme.STANDARD_DARKGREEN_THEME));
        CRUDBtn.add(new StandardButton("UPDATE", Theme.STANDARD_DARKGREEN_THEME));
        CRUDBtn.add(new StandardButton("DELETE", Theme.STANDARD_DARKGREEN_THEME));
        CRUDBtn.get(0).addActionListener(new CRUDListener());
        CRUDBtn.get(1).addActionListener(new CRUDListener());

        if (CRUDBtn.get(2).getActionListeners().length < 1) {
            CRUDBtn.get(2).addActionListener(new CRUDListener());
        }
        CRUDBtn.get(3).addActionListener(new CRUDListener());
        flowSouthPanel.add(refreshBtn);
        refreshBtn.setIcon(refreshImg);
        refreshBtn.setPreferredSize(new Dimension(100, 80));
        refreshBtn.addActionListener(new refreshListener());

        for (int i = 0; i < CRUDBtn.size(); i++) {
            flowSouthPanel.add(CRUDBtn.get(i));
            CRUDBtn.get(i).setPreferredSize(new Dimension(180, 80));
        }
        getTableInfo(table);
        //Panel Adjustment
        flowNorthPanel.setBackground(Color.black);
        NorthPanel.setBackground(Color.black);
        topPanel.setBackground(Color.black);
        flowTopLeftPanel.setBackground(Color.black);
        flowTopRightPanel.setBackground(Color.LIGHT_GRAY);
        flowTopRightPanel.setPreferredSize(new Dimension(400, 100));
        NorthPanel.setPreferredSize(new Dimension(600, 100));
        flowWestPanel.setBackground(Color.white);
        WestPanel.setBackground(Color.darkGray);
        WestPanel.setPreferredSize(new Dimension(805, 550));
        flowSouthPanel.setBackground(Color.black);
        flowEastPanel.setBackground(Color.LIGHT_GRAY);
        EastPanel.setPreferredSize(new Dimension(440, 540));

        //Frame
        //f1 = new searchFrame();
        //f1.setVisible(false);
        add(flowNorthPanel, BorderLayout.NORTH);
        add(flowWestPanel, BorderLayout.WEST);
        add(flowSouthPanel, BorderLayout.SOUTH);
        add(flowEastPanel, BorderLayout.EAST);
        setVisible(true);
        setTitle("Panda Menu");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1280, 800);
    }

    private class refreshListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FoodBtn.clear();
            BeverageBtn.clear();
            DessertBtn.clear();
            initializeModel();
            AddorderListListener();
        }
    }

    private class searchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            f1 = new searchFrame();
            f1.shutdown();
        }
    }

    private class SwitchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == SwitchBtn.get(0)) {
                WestPanel.removeAll();
                for (int i = 0; i < FoodBtn.size(); i++) {
                    WestPanel.add(FoodBtn.get(i));
                    FoodBtn.get(i).setVisible(true);
                    x++;
                }
                WestPanel.add(addBtn);
            } else if (e.getSource() == SwitchBtn.get(1)) {
                WestPanel.removeAll();
                for (int i = 0; i < BeverageBtn.size(); i++) {
                    WestPanel.add(BeverageBtn.get(i));
                    BeverageBtn.get(i).setVisible(true);
                    x++;
                }
                WestPanel.add(addBtn);
            } else if (e.getSource() == SwitchBtn.get(2)) {
                WestPanel.removeAll();
                for (int i = 0; i < DessertBtn.size(); i++) {
                    WestPanel.add(DessertBtn.get(i));
                    DessertBtn.get(i).setVisible(true);

                    x++;
                }
                WestPanel.add(addBtn);
            }
            int count = x;
            for (int x = 0; x < (24 - count); x++) {
                WestPanel.add(new JLabel(""));
            }
            x = 0;
            repaint();
            revalidate();
        }
    }

    private void AddorderListListener() {
        for (int i = 0; i < FoodBtn.size(); i++) {
            FoodBtn.get(i).addActionListener(new orderClick());
        }
        for (int i = 0; i < BeverageBtn.size(); i++) {
            BeverageBtn.get(i).addActionListener(new orderClick());
        }
        for (int i = 0; i < DessertBtn.size(); i++) {
            DessertBtn.get(i).addActionListener(new orderClick());
        }
    }

    private class CRUDListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == CRUDBtn.get(0) || e.getSource() == addBtn) {
                createFrame Create = new createFrame();
            } else if (e.getSource() == CRUDBtn.get(1)) {
                retrieveFrame Retrieve = new retrieveFrame();
            } else if (e.getSource() == CRUDBtn.get(2)) {
                UpdateFrame Update = new UpdateFrame();
            } else if (e.getSource() == CRUDBtn.get(3)) {
                deleteFrame Delete = new deleteFrame();
            }
        }
    }

    private void initializeModel() {
        foodList = menuDA.getFoodDetail();
        for (int i = 0; i < foodList.size(); ++i) {
            if (foodList.get(i).getType().equals(SwitchBtn.get(0).getText())) {
                FoodBtn.add(new GlossyButton(String.format("<html>%s<br>RM%.2f</html>", foodList.get(i).getName(), foodList.get(i).getPrice()), Theme.GLOSSY_LIGHTGREEN_THEME));
            } else if (foodList.get(i).getType().equals(SwitchBtn.get(1).getText())) {
                BeverageBtn.add(new GlossyButton(String.format("<html>%s<br>RM%.2f</html>", foodList.get(i).getName(), foodList.get(i).getPrice()), Theme.GLOSSY_LIGHTGREEN_THEME));
            } else if (foodList.get(i).getType().equals(SwitchBtn.get(2).getText())) {
                DessertBtn.add(new GlossyButton(String.format("<html>%s<br>RM%.2f</html>", foodList.get(i).getName(), foodList.get(i).getPrice()), Theme.GLOSSY_LIGHTGREEN_THEME));
            }
        }
    }

    private class orderClick implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (!(textname[23].getText().equals(""))) {
                Full = true;
                JOptionPane.showMessageDialog(null, "Your Order List already FULL");
            } else {
                Full = false;
            }
            String[] result = e.getSource().toString().split(",");
            String[] menu = result[26].split("<br>");
            String name = menu[0].replaceAll("text=<html>", "");
            String price = menu[1].replaceAll("</html>", "").replaceAll("RM", "");

            //calculateprice = Double.parseDouble(price);
            for (int i = 0; i < 66; i++) {
                if (textname[i].getText().equals("")) {
                    empty = i;
                    break;
                } else if (textname[i].getText().equals(name)) {
                    //JOptionPane.showMessageDialog(null, "This Item already in Order List");
                    Exists = true;
                    break;
                }
            }
            if (Exists == false) {
                if (Full == false) {
                    add[empty].setEnabled(true);
                    cancel[empty].setEnabled(true);
                    minus[empty].setEnabled(true);
                    textname[empty].setText(name);
                    textquantity[empty].setText("" + 1);
                    textprice[empty].setText("RM " + price);
                }
            }

            Exists = false;

        }
    }

    private class listClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < 66; i++) {
                if (e.getSource() == add[i]) {
                    if (a[i] == 1) {

                        String price = textprice[i].getText();
                        price = price.replaceAll("RM", "");
                        oprice[i] = Double.parseDouble(price);
                    }
                    textquantity[i].setText("" + ++a[i]);
                    calculatedprice = oprice[i] * (a[i]);
                    textprice[i].setText(String.format("RM %.2f", calculatedprice));

                } else if (e.getSource() == minus[i]) {
                    int check = Integer.parseInt(textquantity[i].getText());
                    if (check > 1) {
                        if (a[i] == 1) {

                            String price = textprice[i].getText();
                            price = price.replaceAll("RM", "");
                            oprice[i] = Double.parseDouble(price);
                        }
                        textquantity[i].setText("" + --a[i]);
                        calculatedprice = oprice[i] * (a[i]);
                        textprice[i].setText(String.format("RM %.2f", calculatedprice));
                    } else {
                        JOptionPane.showMessageDialog(null, "Lowest Order Quantity must be 1");
                    }

                }
            }
        }
    }

    private class deleteClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 66; i++) {
                if (e.getSource() == cancel[i]) {
                    textname[i].setText(null);
                    textquantity[i].setText(null);
                    textprice[i].setText(null);

                    for (int h = i; h < 66; h++) {
                        if (h == 65) {
                            textname[h].setText(null);
                            textquantity[h].setText(null);
                            textprice[h].setText(null);
                            add[h].setEnabled(false);
                            cancel[h].setEnabled(false);
                            minus[h].setEnabled(false);
                        } else {
                            textname[h].setText(textname[h + 1].getText());
                            textquantity[h].setText(textquantity[h + 1].getText());
                            textprice[h].setText(textprice[h + 1].getText());
//                            add[h].setEnabled(false);
//                            cancel[h].setEnabled(false);
//                            minus[h].setEnabled(false);

                        }
                    }

                }
                if (textname[i].getText().equals("")) {
                    add[i].setEnabled(false);
                    minus[i].setEnabled(false);
                    cancel[i].setEnabled(false);
                }

            }

        }
    }

    private class confirmClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(null, "Are You Confirm Your Order?", "Confirm Order", JOptionPane.YES_NO_OPTION);
            
            comfirmCickCounter = 1;
            orderControl.changeTableStatusToSeated(table);
            orderControl.createOrder(table);
            specialid = orderControl.getOrderId(table);
            ResultSet rs = orderControl.getMenu();
            String z = null;

            try {

                for (int i = 0; i < 66; i++) {
                    if (!(textname[i].getText().equals(""))) {
                        while (rs.next()) {
                            if (textname[i].getText().equals(rs.getString("NAME"))) {
                                z = rs.getString("ID");
                            }
                        }
                        rs.first();
                        orderControl.createOrderList(z, specialid, textname[i].getText(), Double.parseDouble(textprice[i].getText().substring(3)), Integer.parseInt(textquantity[i].getText()));
                    }
                }

                JOptionPane.showMessageDialog(null, "Orders Registered , Sending to kitchen!", "Sucess!", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "comfirmclick" + ex.getMessage());
            }
        }
    }

    private class paymentClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (comfirmCickCounter == 1) {

                String orderid = orderControl.getOrderId(table);
                System.out.print(orderid);

                ReceiptOrderMainFrame pay = new ReceiptOrderMainFrame(orderid, table, staffname);
                orderControl.changeTableStatusToFree(table);
            } else {
                JOptionPane.showMessageDialog(null, "Please Comfirm Order Before Further Operation");
            }

        }
    }

    public void getTableInfo(String tableid) {

        TableDomain tableInfo = tableControl.getTableInfo(tableid);
        try{
            specialid = orderControl.getOrderId(tableid);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        if(specialid != null){
            if (tableInfo.getOccupyStatus().equals("Occupied") && tableInfo.getPayStatus().equals("Unpaid")) {


                ArrayList<Orderlist> orderlistInfo = orderControl.getOrderlist(specialid);
                for (int i = 0; i < orderlistInfo.size(); i++) {
                    textname[i].setText(orderlistInfo.get(i).getName());
                    textprice[i].setText("RM " + orderlistInfo.get(i).getPrice());
                    textquantity[i].setText("" + orderlistInfo.get(i).getQty());
                    if (!textname[i].getText().equals(null)) {
                        minus[i].setEnabled(true);
                        add[i].setEnabled(true);
                        cancel[i].setEnabled(true);
                    }

                }

            }
        }
    }

    private class backClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            orderControl.shutDown();
            dispose();
        }
    }

    public void CurrentDate() {

        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    date.setText("Date: " + year + "/" + (month + 1) + "/" + day);
                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);

                    time.setText("Time: " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " : " + String.format("%02d", second));
                    try {
                        sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        };

        clock.start();

    }

}
