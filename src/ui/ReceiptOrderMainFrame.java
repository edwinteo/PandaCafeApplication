/*
Author : Yong Zuo Jun 
Class  : DC02 D2
*/

package ui;

import domain.Orderlist;
import control.OrderControl;
import control.ReceiptControl;
import domain.Member;
import control.MemberControl;
import control.StaffControl;
import domain.Staff;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.MaskFormatter;
import com.jd.swing.custom.component.button.*;
import com.jd.swing.util.Theme;
import java.util.ArrayList;
import java.util.Date;
import domain.CCReceipt;
import domain.CashReceipt;
import domain.Receipt;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReceiptOrderMainFrame extends JFrame {

    //DataBase Elements
    String tableid;
    String orderid;
    MemberControl membercon = new MemberControl();
    ReceiptControl receiptcon = new ReceiptControl();
    OrderControl ordercon = new OrderControl();
    StaffControl staffControl;
    
    //NorthPanel
    
    JPanel TopRightPanelTime = new JPanel(new FlowLayout());
    JPanel TopRightPanelStaff = new JPanel(new GridLayout(3, 1));
    JPanel northRightPanel = new JPanel(new GridLayout(1, 2));
    JPanel flowNorthPanel = new JPanel(new BorderLayout());
    JPanel NRGPanel = new JPanel(new GridLayout(1,2));
    ImageIcon pictureImg = new ImageIcon("src\\image\\8.jpg");
    GradientButton pictureBtn = new GradientButton("", Theme.GRADIENT_BLACK_THEME, ButtonType.BUTTON_RECTANGULAR);
    JLabel staffDetailLabel = new JLabel("Staff Name: ");
    JLabel date = new JLabel();
    JLabel time = new JLabel();
    

    //West Panel
    JPanel storeTab1 = new JPanel(new GridLayout(1, 3));
    JPanel storeTab2 = new JPanel(new GridLayout(1, 3));
    JPanel storeTab3 = new JPanel(new GridLayout(1, 3));
    JTabbedPane tab = new JTabbedPane();
    JPanel flowWestPanel = new JPanel();
    JPanel borderWestPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel(new BorderLayout());
    JPanel westGridPanel = new JPanel(new GridLayout(1, 2));
    JPanel foodLabelGridPanel = new JPanel(new GridLayout(1, 2));
    JPanel foodQuantityPanel1 = new JPanel(new GridLayout(22, 1));
    JPanel foodIDGridPanel1 = new JPanel(new GridLayout(22, 1));
    JPanel foodCostGridPanel1 = new JPanel(new GridLayout(22, 1));
    JPanel foodQuantityPanel2 = new JPanel(new GridLayout(22, 1));
    JPanel foodIDGridPanel2 = new JPanel(new GridLayout(22, 1));
    JPanel foodCostGridPanel2 = new JPanel(new GridLayout(22, 1));
    JPanel foodQuantityPanel3 = new JPanel(new GridLayout(22, 1));
    JPanel foodIDGridPanel3 = new JPanel(new GridLayout(22, 1));
    JPanel foodCostGridPanel3 = new JPanel(new GridLayout(22, 1));
    JPanel flowFoodQuantityPanel1 = new JPanel(new FlowLayout());
    JPanel flowFoodIDPanel1 = new JPanel(new FlowLayout());
    JPanel flowFoodCostPanel1 = new JPanel(new FlowLayout());
    JPanel flowFoodQuantityPanel2 = new JPanel(new FlowLayout());
    JPanel flowFoodIDPanel2 = new JPanel(new FlowLayout());
    JPanel flowFoodCostPanel2 = new JPanel(new FlowLayout());
    JPanel flowFoodQuantityPanel3 = new JPanel(new FlowLayout());
    JPanel flowFoodIDPanel3 = new JPanel(new FlowLayout());
    JPanel flowFoodCostPanel3 = new JPanel(new FlowLayout());
    JPanel borderFoodIDPanel1 = new JPanel(new BorderLayout());
    JPanel borderFoodCostPanel1 = new JPanel(new BorderLayout());
    JPanel borderFoodQuantityPanel1 = new JPanel(new BorderLayout());
    JPanel borderFoodIDPanel2 = new JPanel(new BorderLayout());
    JPanel borderFoodCostPanel2 = new JPanel(new BorderLayout());
    JPanel borderFoodQuantityPanel2 = new JPanel(new BorderLayout());
    JPanel borderFoodIDPanel3 = new JPanel(new BorderLayout());
    JPanel borderFoodCostPanel3 = new JPanel(new BorderLayout());
    JPanel borderFoodQuantityPanel3 = new JPanel(new BorderLayout());

    //--West Panel Components--//
    ArrayList<Orderlist> foodlist;
    JLabel foodQuantityLabel1 = new JLabel("Quantity");
    JLabel foodIDLabel1 = new JLabel("ID");
    JLabel foodCostLabel1 = new JLabel("Cost");
    JLabel foodQuantityLabel2 = new JLabel("Quantity");
    JLabel foodIDLabel2 = new JLabel("ID");
    JLabel foodCostLabel2 = new JLabel("Cost");
    JLabel foodQuantityLabel3 = new JLabel("Quantity");
    JLabel foodIDLabel3 = new JLabel("ID");
    JLabel foodCostLabel3 = new JLabel("Cost");
    JTextField[] foodQuantityField = new JTextField[66];
    JTextField[] foodIDTextField = new JTextField[66];
    JTextField[] foodCostTextField = new JTextField[66];

    //Center Top Panel
    JPanel flowCenterTopPanel = new JPanel();
    JPanel centerTopPanel = new JPanel(new GridLayout(6, 2));

    //--Center Top Components--//
    double grossTotal = 0.00;
    double discount = 0.00;
    double total = 0.00;
    double remaining = 0.00;
    double payamount = 0.00;
    double gst = 0.00;
    Boolean paid = false;
    JLabel grossTotalLabel = new JLabel("GROSS TOTAL:");
    JLabel discountLabel = new JLabel("DISCOUNTS:");
    JLabel GSTLabel = new JLabel("GST(6%):");
    JLabel totalLabel = new JLabel("TOTAL:");
    JLabel paymentLabel = new JLabel("PAYMENT:");
    JLabel remainingLabel = new JLabel("REMAINING:");
    JTextField grossTotalTf = new JTextField("RM" + grossTotal);
    JTextField discountTf = new JTextField("RM" + discount);
    JTextField GSTTf = new JTextField("RM" + gst);
    JTextField totalTf = new JTextField("RM" + total);
    JTextField paymentTf = new JTextField("RM" + payamount);
    JTextField remainingTf = new JTextField("RM" + remaining);

    //Center South Panel
    JPanel flowCenterSouthPanel = new JPanel();
    JPanel CenterSouthPanel = new JPanel();
    JPanel flowCsContentPanel = new JPanel();
    JPanel CsContentPanel = new JPanel(new GridLayout(3, 2));
    JPanel memberPanel = new JPanel(new BorderLayout());

    //--Center South Components--//
    //MemberShip Login Components//
    Boolean memberexpired = false;
    int earnedLoyalPoints = 0;
    int loyalPoints = 0;
    double discountAmount = 0;
    String memberName = "";
    String memberID = "";
    Boolean identity = false;
    JLabel memberLoginLabel = new JLabel();
    JLabel memberIDLabel = new JLabel("MEMBER ID:");
    JLabel commentLabel = new JLabel(" ");
    JLabel memberNameLabel = new JLabel("MEMBER NAME:");
    JTextField memberIDTf = new JTextField();
    JTextField memberNameTf = new JTextField();
    GlossyButton manageLoyalPointBtn = new GlossyButton("MANAGE LOYAL POINT", Theme.GLOSSY_GREEN_THEME);
    GlossyButton memberLoginBtn = new GlossyButton("MEMBER LOGIN", Theme.GLOSSY_GREEN_THEME);
    //MemberShip Manage Loyal Points Components//
    Boolean usedLoyalPoints = false;
    JLabel currentLoyalPointsLabel = new JLabel("Current Loyal Points");
    JLabel exchangeLoyalPointsLabel = new JLabel("Convert How Much Loyal Points?");
    JTextField loyalPointsTf = new JTextField(loyalPoints + "LP");
    JTextField exchangeLoyalPointsTf = new JTextField();
    GlossyButton convertLoyalPointsBtn = new GlossyButton("Convert Loyal Points", Theme.GLOSSY_GREEN_THEME);
    GlossyButton backBtn = new GlossyButton("Back", Theme.GLOSSY_GREEN_THEME);

    //South Panel
    JPanel flowSouthPanel = new JPanel();
    JPanel southPanel = new JPanel();

    //East Panel
    JPanel flowEastPanel = new JPanel();
    JPanel eastPanel = new JPanel(new GridLayout(1, 2));

    //--Componet for east Panel--//
    String payType;
    boolean cardPayment = false;
    boolean cashPayment = false;
    GradientButton cardPaymentBtn = new GradientButton("CARD PAYMENT", Theme.GRADIENT_LIGHTGREEN_THEME);
    GradientButton cashPaymentBtn = new GradientButton("CASH PAYMENT", Theme.GRADIENT_LIGHTGREEN_THEME);

    //EastBtmPanel
    JPanel flowEastBtmPanel = new JPanel();
    JPanel boxEastPanel = new JPanel();
    JPanel flowPayBtnPanel = new JPanel(new FlowLayout());
    JPanel eastBtmCardPanel = new JPanel(new BorderLayout());
    JPanel eastBtmKeyPadPanel = new JPanel(new GridLayout(5, 3));

    //--EastBtm Component--//
    int paidCount = 1;
    //Button Pad Component//
    int ButtonPressedCount = 0;
    int fullStopBtnCount = 0;
    JLabel RMLabel = new JLabel("RM");
    JTextField DisplayCalcTf = new JTextField();
    GradientButton resetBtn = new GradientButton("RESET", Theme.GRADIENT_BLUEGRAY_THEME);
    StandardButton firstBtn = new StandardButton("1");
    StandardButton secondBtn = new StandardButton("2");
    StandardButton thirdBtn = new StandardButton("3");
    StandardButton fourthBtn = new StandardButton("4");
    StandardButton fifthBtn = new StandardButton("5");
    StandardButton sixthBtn = new StandardButton("6");
    StandardButton seventhBtn = new StandardButton("7");
    StandardButton eightBtn = new StandardButton("8");
    StandardButton nineBtn = new StandardButton("9");
    StandardButton fullStopBtn = new StandardButton(".");
    StandardButton zeroBtn = new StandardButton("0");
    StandardButton backSpaceBtn = new StandardButton("BackSpace");
    StandardButton makePaymentBtn = new StandardButton("Make Payment");
    //Card Component//
    String bankCardType = "";
    String accountNumber = "";
    JPanel cardPayContent = new JPanel(new BorderLayout());
    JPanel enterCardID = new JPanel(new FlowLayout());
    JPanel coverButton = new JPanel(new BorderLayout());
    JLabel first = new JLabel("-");
    JLabel second = new JLabel("-");
    JLabel third = new JLabel("-");
    JLabel totalPayment = new JLabel("");
    JFormattedTextField tf1;
    JFormattedTextField tf2;
    JFormattedTextField tf3;
    JFormattedTextField tf4;
    String[] cardType = {"MayBank", "PublicBank", "VisaCard", "CreditCard"};
    JComboBox cboCard = new JComboBox();

    public ReceiptOrderMainFrame(String orderid, String tableid,String staffname) {
        
        this.orderid = orderid;
        this.tableid = tableid;
        foodlist = ordercon.getOrderlist(orderid);
        for (int x = 0; x < foodlist.size(); x++) {
            double total = foodlist.get(x).getPrice() * foodlist.get(x).getQty();
            grossTotal += total;
        }
        grossTotalTf.setText("RM" + grossTotal);
        try {
            MaskFormatter masker = new MaskFormatter("####");

            tf1 = new JFormattedTextField(masker);
            tf1.setColumns(3);

            tf2 = new JFormattedTextField(masker);
            tf2.setColumns(3);

            tf3 = new JFormattedTextField(masker);
            tf3.setColumns(3);
            tf4 = new JFormattedTextField(masker);
            tf4.setColumns(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //West Panel
        DisplayOrderedItemList();                //Call a method to create OrderListItems
        flowWestPanel.add(westPanel);
        
        //North-right
        northRightPanel.setBackground(Color.LIGHT_GRAY);
        northRightPanel.setPreferredSize(new Dimension(400, 100));
        flowNorthPanel.add(northRightPanel,BorderLayout.EAST);
        pictureBtn.setIcon(pictureImg);
        pictureBtn.setPreferredSize(new Dimension(80, 100));
        northRightPanel.add(pictureBtn);
        TopRightPanelStaff.add(new JLabel(String.format("Staff ID:  %s ", LoginFrame.staffid)));
        TopRightPanelStaff.add(new JLabel(String.format("Staff Name:  %s ",staffname )));
        date.setHorizontalAlignment(SwingConstants.LEFT);
        TopRightPanelTime.add(date);
        time.setHorizontalAlignment(SwingConstants.LEFT);
        TopRightPanelTime.add(time);
        CurrentDate();
        TopRightPanelStaff.add(TopRightPanelTime);
        northRightPanel.add(TopRightPanelStaff);

       
       //Testing
       //NRGPanel.setPreferredSize(new Dimension(350,200));


        //Center Panel
        flowCenterTopPanel.add(centerTopPanel);

        //--Label and TextFields--//
        centerTopPanel.add(grossTotalLabel);
        centerTopPanel.add(grossTotalTf);
        grossTotalTf.setEditable(false);
        centerTopPanel.add(discountLabel);
        centerTopPanel.add(discountTf);
        discountTf.setEditable(false);
        centerTopPanel.add(GSTLabel);
        centerTopPanel.add(GSTTf);
        GSTTf.setEditable(false);
        centerTopPanel.add(totalLabel);
        centerTopPanel.add(totalTf);
        totalTf.setEditable(false);
        centerTopPanel.add(paymentLabel);
        centerTopPanel.add(paymentTf);
        paymentTf.setEditable(false);
        centerTopPanel.add(remainingLabel);
        centerTopPanel.add(remainingTf);
        remainingTf.setEditable(false);
        Calculate();

        //Center-South Panel (C.S.)
        flowCenterSouthPanel.add(CenterSouthPanel);
        flowCenterTopPanel.add(flowCenterSouthPanel);
        CenterSouthPanel.add(new JLabel("=============================="));
        CenterSouthPanel.add(memberLoginLabel);
        CenterSouthPanel.add(new JLabel("=============================="));

        //--Content for Membership Login--//
        flowCsContentPanel.add(memberPanel);                  //CS meaning is Center & South
        flowCenterTopPanel.add(flowCsContentPanel);
        DisplayMemberShip();
        memberPanel.add(CsContentPanel, BorderLayout.CENTER);
        memberPanel.add(commentLabel, BorderLayout.SOUTH);

        //EastPanel
        boxEastPanel.setLayout(new BoxLayout(boxEastPanel, BoxLayout.Y_AXIS));
        flowEastPanel.add(boxEastPanel);
        boxEastPanel.add(eastPanel);
        eastPanel.add(cardPaymentBtn);
        eastPanel.add(cashPaymentBtn);

        cardPaymentBtn.addActionListener(new PaymentListener());
        cashPaymentBtn.addActionListener(new PaymentListener());

        //--Cash Payment Calculator--//
        boxEastPanel.add(eastBtmKeyPadPanel);
        flowPayBtnPanel.add(makePaymentBtn);
        makePaymentBtn.addActionListener(new MakePayments());
        boxEastPanel.add(flowPayBtnPanel);

       //Adding Panel to JFrame
        //---West---//
        add(flowWestPanel, BorderLayout.WEST);
        flowWestPanel.setBackground(Color.DARK_GRAY);
        flowWestPanel.setPreferredSize(new Dimension(350, 100));
        westPanel.setPreferredSize(new Dimension(330, 600));

        //--Center--//
        add(flowCenterTopPanel, BorderLayout.CENTER);
        flowCenterTopPanel.setBackground(Color.lightGray);
        flowCenterTopPanel.setPreferredSize(new Dimension(200, 100));
        centerTopPanel.setPreferredSize(new Dimension(560, 300));
        CsContentPanel.setPreferredSize(new Dimension(550, 240));

        //--South--//
        add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(Color.black);
        southPanel.setPreferredSize(new Dimension(50, 50));

        //--East--//
        add(flowEastPanel, BorderLayout.EAST);
        flowEastPanel.setBackground(Color.darkGray);
        eastPanel.setBackground(Color.darkGray);
        flowEastPanel.setPreferredSize(new Dimension(350, 100));
        eastPanel.setPreferredSize(new Dimension(340, 50));

        //--EastBtm--//
        eastBtmKeyPadPanel.setPreferredSize(new Dimension(330, 350));

        //Frame
        add(flowNorthPanel, BorderLayout.NORTH);
        flowNorthPanel.setPreferredSize(new Dimension(100, 100));
        flowNorthPanel.setBackground(Color.BLACK);
        setSize(1280, 800);
        setTitle("Make Payment");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class PaymentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cardPaymentBtn) {
                cardPayment = true;
                cashPayment = false;
                cardpayment();
            } else if (e.getSource() == cashPaymentBtn) {
                cashPayment = true;
                cardPayment = false;
                keypad();
            }
        }
    }

    //Display MemberShip inside Center-South Panel

    public void DisplayMemberShip() {
        memberLoginLabel.setText("MEMBERSHIP LOGIN");
        CsContentPanel.removeAll();
        CsContentPanel.add(memberIDLabel);
        CsContentPanel.add(memberIDTf);
        CsContentPanel.add(memberNameLabel);
        CsContentPanel.add(memberNameTf);
        memberNameTf.setEditable(false);
        memberNameTf.setFocusable(false);
        CsContentPanel.add(manageLoyalPointBtn);
        CsContentPanel.add(memberLoginBtn);
        manageLoyalPointBtn.addActionListener(new ManageLoyalPoints());
        memberLoginBtn.addActionListener(new Verify());
        CsContentPanel.validate();
        CsContentPanel.repaint();
    }

    //Display OrderedItemList inside West Panel
    public void DisplayOrderedItemList() {
        flowFoodIDPanel1.add(foodIDLabel1);
        flowFoodCostPanel1.add(foodCostLabel1);
        flowFoodQuantityPanel1.add(foodQuantityLabel1);
        flowFoodIDPanel2.add(foodIDLabel2);
        flowFoodCostPanel2.add(foodCostLabel2);
        flowFoodQuantityPanel2.add(foodQuantityLabel2);
        flowFoodIDPanel3.add(foodIDLabel3);
        flowFoodCostPanel3.add(foodCostLabel3);
        flowFoodQuantityPanel3.add(foodQuantityLabel3);

        for (int i = 0; i < 22; i++) {
            foodIDTextField[i] = new JTextField(2);
            foodIDTextField[i].setVisible(true);
            foodIDTextField[i].setEditable(false);
            foodIDTextField[i].setFocusable(false);
        }
        for (int i = 22; i < 44; i++) {
            foodIDTextField[i] = new JTextField(2);
            foodIDTextField[i].setVisible(true);
            foodIDTextField[i].setEditable(false);
            foodIDTextField[i].setFocusable(false);
        }

        for (int i = 44; i < 66; i++) {
            foodIDTextField[i] = new JTextField(2);
            foodIDTextField[i].setVisible(true);
            foodIDTextField[i].setEditable(false);
            foodIDTextField[i].setFocusable(false);
        }

        for (int i = 0; i < 22; i++) {
            foodCostTextField[i] = new JTextField(10);
            foodCostTextField[i].setVisible(true);
            foodCostTextField[i].setEditable(false);
            foodCostTextField[i].setFocusable(false);
        }

        for (int i = 22; i < 44; i++) {
            foodCostTextField[i] = new JTextField(10);
            foodCostTextField[i].setVisible(true);
            foodCostTextField[i].setEditable(false);
            foodCostTextField[i].setFocusable(false);
        }

        for (int i = 44; i < 66; i++) {
            foodCostTextField[i] = new JTextField(10);
            foodCostTextField[i].setVisible(true);
            foodCostTextField[i].setEditable(false);
            foodCostTextField[i].setFocusable(false);
        }

        for (int i = 0; i < 22; i++) {
            foodQuantityField[i] = new JTextField(10);
            foodQuantityField[i].setVisible(true);
            foodQuantityField[i].setEditable(false);
            foodQuantityField[i].setFocusable(false);

        }
        for (int i = 22; i < 44; i++) {
            foodQuantityField[i] = new JTextField(10);
            foodQuantityField[i].setVisible(true);
            foodQuantityField[i].setEditable(false);
            foodQuantityField[i].setFocusable(false);

        }
        for (int i = 44; i < 66; i++) {
            foodQuantityField[i] = new JTextField(10);
            foodQuantityField[i].setVisible(true);
            foodQuantityField[i].setEditable(false);
            foodQuantityField[i].setFocusable(false);

        }
        for (int i = 0; i < 22; i++) {
            foodIDGridPanel1.add(foodIDTextField[i]);
            foodCostGridPanel1.add(foodCostTextField[i]);
            foodQuantityPanel1.add(foodQuantityField[i]);
        }

        for (int i = 22; i < 44; i++) {
            foodIDGridPanel2.add(foodIDTextField[i]);
            foodCostGridPanel2.add(foodCostTextField[i]);
            foodQuantityPanel2.add(foodQuantityField[i]);

        }
        for (int i = 44; i < 66; i++) {

            foodCostGridPanel3.add(foodCostTextField[i]);
            foodQuantityPanel3.add(foodQuantityField[i]);
            foodIDGridPanel3.add(foodIDTextField[i]);
        }
        for (int x = 0; x < foodlist.size(); x++) {
            foodIDTextField[x].setText(foodlist.get(x).getMenuid());
        }
        for (int x = 0; x < foodlist.size(); x++) {
            foodCostTextField[x].setText("" + foodlist.get(x).getPrice());
        }
        for (int x = 0; x < foodlist.size(); x++) {
            foodQuantityField[x].setText("" + foodlist.get(x).getQty());
        }
        borderFoodIDPanel1.add(flowFoodIDPanel1, BorderLayout.NORTH);
        borderFoodIDPanel1.add(foodIDGridPanel1, BorderLayout.CENTER);
        borderFoodQuantityPanel1.add(flowFoodQuantityPanel1, BorderLayout.NORTH);
        borderFoodQuantityPanel1.add(foodQuantityPanel1, BorderLayout.CENTER);
        borderFoodCostPanel1.add(flowFoodCostPanel1, BorderLayout.NORTH);
        borderFoodCostPanel1.add(foodCostGridPanel1, BorderLayout.CENTER);

        storeTab1.add(borderFoodIDPanel1);
        storeTab1.add(borderFoodQuantityPanel1);
        storeTab1.add(borderFoodCostPanel1);

        borderFoodIDPanel2.add(flowFoodIDPanel2, BorderLayout.NORTH);
        borderFoodIDPanel2.add(foodIDGridPanel2, BorderLayout.CENTER);
        borderFoodQuantityPanel2.add(flowFoodQuantityPanel2, BorderLayout.NORTH);
        borderFoodQuantityPanel2.add(foodQuantityPanel2, BorderLayout.CENTER);
        borderFoodCostPanel2.add(flowFoodCostPanel2, BorderLayout.NORTH);
        borderFoodCostPanel2.add(foodCostGridPanel2, BorderLayout.CENTER);

        storeTab2.add(borderFoodIDPanel2);
        storeTab2.add(borderFoodQuantityPanel2);
        storeTab2.add(borderFoodCostPanel2);

        borderFoodIDPanel3.add(flowFoodIDPanel3, BorderLayout.NORTH);
        borderFoodIDPanel3.add(foodIDGridPanel3, BorderLayout.CENTER);
        borderFoodQuantityPanel3.add(flowFoodQuantityPanel3, BorderLayout.NORTH);
        borderFoodQuantityPanel3.add(foodQuantityPanel3, BorderLayout.CENTER);
        borderFoodCostPanel3.add(flowFoodCostPanel3, BorderLayout.NORTH);
        borderFoodCostPanel3.add(foodCostGridPanel3, BorderLayout.CENTER);

        storeTab3.add(borderFoodIDPanel3);
        storeTab3.add(borderFoodQuantityPanel3);
        storeTab3.add(borderFoodCostPanel3);

        tab.add(storeTab1, "FIRST PAGE");
        tab.add(storeTab2, "SECOND PAGE");
        tab.add(storeTab3, "THIRD PAGE");
        westGridPanel.add(tab, BorderLayout.CENTER);
//        westGridPanel.add(foodIDGridPanel);
//        westGridPanel.add(foodCostGridPanel);
        westPanel.add(westGridPanel, BorderLayout.CENTER);
    }

    //Display MemberShip's MAnage Loyal Points
    public void DisplayManageLoyalPoints() {
        CsContentPanel.removeAll();
        CsContentPanel.add(currentLoyalPointsLabel);
        CsContentPanel.add(loyalPointsTf);
        loyalPointsTf.setEditable(false);
        loyalPointsTf.setFocusable(false);
        CsContentPanel.add(exchangeLoyalPointsLabel);
        CsContentPanel.add(exchangeLoyalPointsTf);
        CsContentPanel.add(convertLoyalPointsBtn);
        CsContentPanel.add(backBtn);
        convertLoyalPointsBtn.addActionListener(new UseLoyalPoints());
        backBtn.addActionListener(new Back());
        CsContentPanel.validate();
        CsContentPanel.repaint();
    }

    //For Display
    public void cardpayment() {
        eastBtmKeyPadPanel.removeAll();
        if (cboCard.getItemCount() < 4) {
            for (int i = 0; i < cardType.length; i++) {
                cboCard.addItem(cardType[i]);
            }
        }
        cardPayContent.add(cboCard, BorderLayout.NORTH);
        enterCardID.add(tf1);
        enterCardID.add(first);
        enterCardID.add(tf2);
        enterCardID.add(second);
        enterCardID.add(tf3);
        enterCardID.add(third);
        enterCardID.add(tf4);
        cardPayContent.add(enterCardID, BorderLayout.CENTER);

        eastBtmKeyPadPanel.add(cardPayContent);
        totalPayment.setText(String.format("RM" + "%.2f", total));
        repaint();
        revalidate();
    }

    public void keypad() {
        eastBtmKeyPadPanel.removeAll();
        if (firstBtn.getActionListeners().length < 1) {
            firstBtn.addActionListener(new ButtonPressed());
            secondBtn.addActionListener(new ButtonPressed());
            thirdBtn.addActionListener(new ButtonPressed());
            fourthBtn.addActionListener(new ButtonPressed());
            fifthBtn.addActionListener(new ButtonPressed());
            sixthBtn.addActionListener(new ButtonPressed());
            seventhBtn.addActionListener(new ButtonPressed());
            eightBtn.addActionListener(new ButtonPressed());
            nineBtn.addActionListener(new ButtonPressed());
            fullStopBtn.addActionListener(new ButtonPressed());
            zeroBtn.addActionListener(new ButtonPressed());
            backSpaceBtn.addActionListener(new ButtonPressed());
            resetBtn.addActionListener(new ResetAction());
        }
        eastBtmKeyPadPanel.add(RMLabel);
        eastBtmKeyPadPanel.add(DisplayCalcTf);
        eastBtmKeyPadPanel.add(resetBtn);
        eastBtmKeyPadPanel.add(firstBtn);
        eastBtmKeyPadPanel.add(secondBtn);
        eastBtmKeyPadPanel.add(thirdBtn);
        eastBtmKeyPadPanel.add(fourthBtn);
        eastBtmKeyPadPanel.add(fifthBtn);
        eastBtmKeyPadPanel.add(sixthBtn);
        eastBtmKeyPadPanel.add(seventhBtn);
        eastBtmKeyPadPanel.add(eightBtn);
        eastBtmKeyPadPanel.add(nineBtn);
        eastBtmKeyPadPanel.add(fullStopBtn);
        eastBtmKeyPadPanel.add(zeroBtn);
        eastBtmKeyPadPanel.add(backSpaceBtn);
        DisplayCalcTf.setEditable(false);
        repaint();
        revalidate();
    }

    //Calculation for Top panel
    public void Calculate() {
        if (paid == false) {
            discount += discountAmount;
            discountTf.setText(String.format("RM" + "%.2f", discount));
            total = grossTotal - discount;
            gst = (total * 6) / 100;
            total += gst;
            GSTTf.setText(String.format("RM" + "%.2f", gst));
            totalTf.setText(String.format("RM" + "%.2f", total));
        }
    }

    class MakePayments implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (cardPayment == true) {
                payType = "CARD";
                if (paid == false) {
                    if ((tf1.isEditValid() && tf2.isEditValid() && tf3.isEditValid() && tf4.isEditValid() )) {
                        if (JOptionPane.showConfirmDialog(null, "Comfirm this payment?", "Comfirm Payment", JOptionPane.YES_NO_OPTION) == 0) {
                            double paym;
                            paym = total;
                            remaining = paym - total;
                            paymentTf.setText(String.format("RM" + "%.2f", paym));
                            remainingTf.setText("RM" + remaining);
                            String accNum = tf1.getText() + tf2.getText() + tf3.getText() + tf4.getText();
                            accountNumber += accNum;
                            bankCardType += cardType[cboCard.getSelectedIndex()];
                            paid = true;
                            payamount = paym;
                            earnedLoyalPoints = (int)(payamount);
                            membercon.setMemberLoyalPoints(memberID, loyalPoints + earnedLoyalPoints);
                            Member member = membercon.getMemberInfo(memberID);
                            if (identity == true) {
                                loyalPointsTf.setText(member.getLp() + "");
                            }
                        }
                    }
                }
            } else if (cashPayment == true) {
                payType = "CASH";
                if (paid == false) {
                    try {
                        if (DisplayCalcTf.getText() != null) {
                            payamount += Double.parseDouble(DisplayCalcTf.getText());
                            remaining = payamount - total;
                            if (remaining >= 0) {

                                int reply = JOptionPane.showConfirmDialog(null, "Is the payment amount correct??", "Comfirm?", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                    paymentTf.setText("RM" + payamount);
                                    remainingTf.setText(String.format("RM" + "%.2f", remaining));
                                    paid = true;
                                    
                                    Member member = membercon.getMemberInfo(memberID);
                                    if (identity == true) {
                                        earnedLoyalPoints = (int)payamount;
                                        membercon.setMemberLoyalPoints(memberID, loyalPoints + earnedLoyalPoints);
                                        loyalPointsTf.setText(member.getLp() + "");
                                    }
                                    JOptionPane.showMessageDialog(null, "Payment Complete");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Payment Denied");
                                    DisplayCalcTf.setText("");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient Amount");
                                payamount = 0;
                                DisplayCalcTf.setText("");
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
            if (paid == true) {
                if (paidCount == 1) {
                    Date date = new Date();
                    String result[] = date.toString().split(" ");
                    String receiptdate = result[0] + "," + result[2] + "/" + result[1] + "/" + result[5];
                    String receipttime = result[3];
                    receiptcon.setReceiptInfo(orderid, LoginFrame.staffid, memberID, payType, receiptdate,receipttime, total,payamount);
                    System.out.println(orderid+ "))))))))))))))))");
                    JOptionPane.showMessageDialog(null, "Receipt Generated Successfully");
                    Receipt generateREP = receiptcon.getReceiptInfo(orderid);
                    System.out.println(generateREP.getOrderid() + "))))))))))))))))");
                    foodlist = ordercon.getOrderlist(tableid);
                    if(payType.equals("CARD")){
                        new CCReceipt(generateREP,grossTotal,discount,remaining,gst,foodlist);
                        
                    }

                    else if(payType.equals("CASH")){
                        new CashReceipt(generateREP,grossTotal,discount,remaining,gst,foodlist);
                        
                    }
                    paidCount++;
                } else {
                    JOptionPane.showMessageDialog(null, "The Payment already made", "Payment Already Made", JOptionPane.OK_OPTION);
                }
            }
        }
    }

    class ResetAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            DisplayCalcTf.setText("");
            fullStopBtnCount = 0;
            ButtonPressedCount = 0;
        }
    }

    class ButtonPressed implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (DisplayCalcTf.getText() != null) {
                if (paid == false) {
                    if (e.getSource() == fullStopBtn) {
                        if (ButtonPressedCount != 0 && fullStopBtnCount < 1) {
                            String ButtonSource;
                            ButtonSource = e.getSource().toString();
                            String result[] = ButtonSource.split("text=");
                            String number = result[1].substring(0, 1);
                            String v = DisplayCalcTf.getText() + number;
                            DisplayCalcTf.setText(v);
                            fullStopBtnCount++;
                        }
                    } else if (e.getSource() == backSpaceBtn) {
                        String numbers = DisplayCalcTf.getText();
                        int lengthOfDigits = numbers.length();
                        if (!(Character.isDigit(numbers.charAt(lengthOfDigits - 1)))) {
                            fullStopBtnCount = 0;
                        }
                        numbers = numbers.substring(0, lengthOfDigits - 1);
                        DisplayCalcTf.setText(numbers);
                    } else {
                        String ButtonSource;
                        ButtonSource = e.getSource().toString();
                        String result[] = ButtonSource.split("text=");
                        String number = result[1].substring(0, 1);
                        String v = DisplayCalcTf.getText() + number;
                        DisplayCalcTf.setText(v);
                        ButtonPressedCount++;
                    }
                }
            }
        }
    }

    //Action for UseLoyalPoints
    class UseLoyalPoints implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (usedLoyalPoints == false) {
                try {
                    if (exchangeLoyalPointsTf.getText() != "") {

                        if (Integer.parseInt(exchangeLoyalPointsTf.getText()) <= loyalPoints) {
                            discountAmount = Double.parseDouble(exchangeLoyalPointsTf.getText()) / 100;
                            commentLabel.setText(exchangeLoyalPointsTf.getText() + "LP " + " has already changed to RM" + discountAmount + " as Disccounts.");
                            Calculate();
                            loyalPoints = loyalPoints - Integer.parseInt(exchangeLoyalPointsTf.getText());
                            loyalPointsTf.setText(loyalPoints + "");
                            membercon.setMemberLoyalPoints(memberID, loyalPoints);
                            usedLoyalPoints = true;
                            convertLoyalPointsBtn.setVisible(false);
                            exchangeLoyalPointsTf.setEditable(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient LoyalPoints Amount!");
                            exchangeLoyalPointsTf.setText("");
                            exchangeLoyalPointsTf.requestFocus();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //Action to return to MemberShip Login Page
    class Back implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            DisplayMemberShip();
        }
    }

    //Action for MemberShip Manage LP Button
    class ManageLoyalPoints implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (identity == false) {
                commentLabel.setText("Please Login Before Further Operation");
            } else {
                memberLoginLabel.setText("Control Loyal Points");
                CsContentPanel.removeAll();
                CsContentPanel.setLayout(new GridLayout(3, 2));
                DisplayManageLoyalPoints();

            }
        }
    }

    //Action For MemberShip Login Button
    class Verify implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                if (memberIDTf.getText().equals("")) {
                    commentLabel.setText("Please Enter a Member ID");
                    throw new Exception("No such account");
                }
                Member member = membercon.getMemberInfo(memberIDTf.getText());
                if (member != null) {
                    memberexpired = membercon.checkMemberStatus(member.getId());

                    if (memberIDTf.getText().equals(member.getId())) {
                        if (memberexpired == true) {
                            int selection = JOptionPane.showConfirmDialog(null, "Do you want to Renew your membership?", "Membership Outdated", JOptionPane.YES_NO_OPTION);
                            if (selection == JOptionPane.YES_OPTION) {
                                membercon.renewMembership(member.getId());
                                memberexpired = false;
                            }
                        }
                        if (memberexpired == false) {
                            memberID = member.getId();
                            memberNameTf.setText(member.getName());
                            memberIDTf.setFocusable(false);
                            memberIDTf.setEditable(false);
                            commentLabel.setText("Welcome " + memberNameTf.getText());
                            identity = true;
                            loyalPoints = member.getLp();
                            memberLoginBtn.setVisible(false);
                            repaint();
                            loyalPointsTf.setText("" + loyalPoints);
                            discount = (grossTotal * 10) / 100;
                            Calculate();
                        }
                    }
                } else {
                    commentLabel.setText("Member ID Not Found");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    //To Hide Login Button
    public void Very() {
        if (identity == false) {
            memberLoginBtn.setVisible(true);
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
