/*
Author : Teo Jia Han 
Class  : DC02 D2
*/

package ui;

import com.jd.swing.custom.component.button.ButtonType;
import com.jd.swing.custom.component.button.GlossyButton;
import com.jd.swing.util.Theme;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import control.MemberControl;
import domain.Member;
import java.awt.Component;
import java.sql.ResultSet;
import javax.swing.table.TableCellRenderer;

public class MaintainMemberFrame extends JFrame {

    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel rightPanel = new JPanel(new BorderLayout());
    JPanel rightBottomPanel = new JPanel(new BorderLayout());
    JLabel lbName = new JLabel("Name: ");
    JLabel lbID = new JLabel("ID: ");
    JLabel lbAddr = new JLabel("Address: ");
    JLabel lbPhno = new JLabel("PhoneNo: ");
    JLabel lbDOB = new JLabel("DOB:");
    JLabel lbGender = new JLabel("Gender:");
    JLabel lbSearch = new JLabel("Specify word to search: ");
    JTextField tfName = new JTextField(15);
    JTextField tfID = new JTextField(15);
    JTextField tfSearch = new JTextField();
    JTextArea tfAddr = new JTextArea(4, 15);
    JTextField tfPhno = new JTextField(15);

    GlossyButton jbCreate = new GlossyButton("Create", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    GlossyButton jbUpdate = new GlossyButton("Update", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    GlossyButton jbDelete = new GlossyButton("Delete", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    GlossyButton jbClear = new GlossyButton("Clear", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);

    JComboBox cbDate = new JComboBox(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
    JComboBox cbMonth = new JComboBox(new DefaultComboBoxModel(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
    JComboBox cbYear = new JComboBox();
    JDateChooser dob = new JDateChooser();
    private ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton male = new JRadioButton("Male");
    JRadioButton female = new JRadioButton("Female");

    DefaultTableModel tableModel;
    JScrollPane scrollTable;
    JTable table;
    TableRowSorter<TableModel> sorter;

    MemberControl membercontrol;

    private String radioButtonString;
    private String message = null;
    private boolean EXIST = false;

    public MaintainMemberFrame() {
        //set main layout
        setLayout(new BorderLayout());
        //create member control
        membercontrol = new MemberControl();
        //add item into year combo box
        for (int i = 2015; i >= 1980; i--) {
            cbYear.addItem(i);
        }

        tfSearch.setColumns(20);
        tfAddr.setBorder(BorderFactory.createLineBorder(Color.gray));
        tfID.setEditable(false);
        tfName.setPreferredSize(new Dimension(200, 25));
        tfID.setPreferredSize(new Dimension(200, 25));
        tfAddr.setPreferredSize(new Dimension(200, 25));
        tfPhno.setPreferredSize(new Dimension(200, 25));
        dob.setPreferredSize(new Dimension(190, 25));
        dob.setDateFormatString("d-MMM-yyyy");
        tfAddr.setLineWrap(true);

        Border innerBorder = BorderFactory.createTitledBorder(null, "Member Information", TitledBorder.TOP, TitledBorder.CENTER, new Font("Comic Sans MS", Font.BOLD, 16), Color.RED);
        Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        GridBagConstraints gc = new GridBagConstraints();
        //=====================first row======================
        gc.weightx = 0;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;

        leftPanel.add(lbID, gc);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        leftPanel.add(tfID, gc);
        //=====================second row======================
        gc.gridwidth = 1;
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        leftPanel.add(lbName, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        leftPanel.add(tfName, gc);

        //=====================third row======================
        gc.gridy = 2;
        gc.gridx = 0;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        leftPanel.add(lbPhno, gc);

        gc.gridy = 2;
        gc.gridx = 1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        leftPanel.add(tfPhno, gc);

        //=====================fourth row======================
        gc.gridy = 3;
        gc.gridx = 0;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        leftPanel.add(lbDOB, gc);

        gc.gridwidth = 2;
        gc.gridy = 3;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        leftPanel.add(dob, gc);

        //=====================fifth row======================
        gc.gridwidth = 1;
        gc.gridy = 4;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        leftPanel.add(lbAddr, gc);
        gc.gridy = 4;
        gc.gridx = 1;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        leftPanel.add(tfAddr, gc);

        //=====================sixth row======================
        buttonGroup.add(male);
        buttonGroup.add(female);
        male.addActionListener(new RadioButtonListener());
        female.addActionListener(new RadioButtonListener());

        gc.gridwidth = 1;
        gc.gridy = 5;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        leftPanel.add(lbGender, gc);

        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx++;
        leftPanel.add(male, gc);

        gc.gridx++;
        gc.gridwidth = 1;
        leftPanel.add(female, gc);

        //=====================seventh row======================
        gc.weighty = 0.3;
        gc.weightx = 1;
        gc.gridy = 6;
        gc.gridwidth = 1;
        gc.gridx = 0;
        gc.insets = new Insets(10, 10, 0, 0);
        leftPanel.add(jbCreate, gc);

        gc.gridx = 1;
        gc.insets = new Insets(10, 10, 0, 0);
        leftPanel.add(jbUpdate, gc);
        gc.gridx = 2;
        gc.insets = new Insets(10, -10, 0, 0);
        leftPanel.add(jbDelete, gc);
        gc.gridx = 3;
        gc.insets = new Insets(10, -15, 0, 10);
        leftPanel.add(jbClear, gc);

        //get table
        tableModel = membercontrol.createTable();
        //create table
        table = new JTable(tableModel) {

            @Override
            public boolean isCellEditable(int Row, int Col) {
                return false;
            }
            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                if (data % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(Color.CYAN);
                }

                if (isCellSelected(data, columns)) {
                    c.setBackground(Color.GREEN);
                }

                return c;
            }

        };

        scrollTable = new JScrollPane(table);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //add scrolltable to frame
        rightPanel.add(scrollTable, BorderLayout.CENTER);

        //table settings
        table.setPreferredScrollableViewportSize(new Dimension(600, 340));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(30);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ResultSet rs = null;
                try {
                    rs = membercontrol.selectRecord(table.getValueAt(table.getSelectedRow(), 0).toString());
                    tfID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    tfName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    tfPhno.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    dob.setDate(new Date(table.getValueAt(table.getSelectedRow(), 4).toString()));
                    String gender = table.getValueAt(table.getSelectedRow(), 3).toString();
                    if (gender.equals("Male")) {
                        male.setSelected(true);
                        male.doClick();
                    } else {
                        female.setSelected(true);
                        female.doClick();
                    }
                    if (rs.next()) {
                        tfAddr.setText(rs.getString("ADDR"));
                    }
                } catch (Exception ex) {

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        rightBottomPanel.add(lbSearch, BorderLayout.WEST);
        rightBottomPanel.add(tfSearch, BorderLayout.CENTER);

        rightPanel.add(rightBottomPanel, BorderLayout.SOUTH);

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        jbCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tfID.setText("");
                int x;
                //String queryStr = "Insert into " + tableName + " (ID,NAME,ADDR,PHNO,GENDER,DOB,REGD,EXPD,STATUS) VALUES (?,?,?,?,?,?,?,?,?)";
                Member member = new Member();
                ResultSet rs = membercontrol.getRecord();

                try {
                    int row = 1;
                    while (rs.next()) {
                        row++;

                    }

                    String dateofbirth = "" + dob.getDate();
                    String result[] = dateofbirth.split(" ");

                    rs.last();
                    if (rs.next()) {
                        x = Integer.parseInt(rs.getString("ID").substring(1));
                        if (x > row - 1) {
                            row = row - 1;
                        }
                    }

                    member.setId(String.format("M%03d", row));
                    member.setName(tfName.getText());
                    member.setAddr(tfAddr.getText());
                    member.setPhno(tfPhno.getText());
                    member.setGender(radioButtonString);
                    member.setDob(result[2] + "-" + result[1] + "-" + result[5]);
                    String regDate = new Date().toString();
                    String result1[] = regDate.split(" ");
                    member.setRegd(result1[2] + "-" + result1[1] + "-" + result1[5]);
                    int year = (Integer.parseInt(result1[5])) + 1;
                    member.setExpd(result1[2] + "-" + result1[1] + "-" + year);
                    member.setStatus("Member");

                    message = "ID: " + String.format("M%03d", row);
                    message += "\nName: " + tfName.getText();
                    message += "\nPhoneNo: " + tfPhno.getText();
                    message += "\nAddress: " + tfAddr.getText();
                    message += "\nGender: " + radioButtonString;
                    message += "\nDOB: " + result[2] + "-" + result[1] + "-" + result[5];
                    message += "\nReg Date: " + result1[2] + "-" + result1[1] + "-" + result1[5];
                    message += "\n\nIs the information correct?";

                    int returnVal = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (returnVal == JOptionPane.YES_OPTION) {
                        Vector dbrow = new Vector();
                        dbrow.add(String.format("M%03d", row));
                        dbrow.add(tfName.getText());
                        dbrow.add(tfPhno.getText());
                        dbrow.add(radioButtonString);
                        dbrow.add(result[2] + "-" + result[1] + "-" + result[5]);
                        dbrow.add("0");
                        dbrow.add("Member");
                        dbrow.add(result1[2] + "-" + result1[1] + "-" + result1[5]);
                        dbrow.add(result1[2] + "-" + result1[1] + "-" + year);

                        membercontrol.createRecord(member);
                        JOptionPane.showMessageDialog(null, "Successfully created the record!", "Update record", JOptionPane.INFORMATION_MESSAGE);
                        tableModel.addRow(dbrow);
                        jbClear.doClick();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please ensure all fields are filled accordingly", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jbUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = membercontrol.getRecord();
                Member member = new Member();
                try {

                    if (tfName.getText().equals("") || tfAddr.getText().equals("") || tfPhno.getText().equals("") || !buttonGroup.getSelection().isSelected() || dob.getDate().toString().equals("")) {
                        throw new NullPointerException("Please ensure all fields are filled accordingly!");
                    } else {

                        while (rs.next()) {
                            if (rs.getString("ID").equals(tfID.getText())) {
                                EXIST = true;
                            }
                        }
                        if (EXIST == false) {
                            throw new IndexOutOfBoundsException("No such record!");
                        }
                        EXIST = false;

                        String dateofbirth = "" + dob.getDate();
                        String result[] = dateofbirth.split(" ");
                        member.setName(tfName.getText());
                        member.setAddr(tfAddr.getText());
                        member.setPhno(tfPhno.getText());
                        member.setGender(radioButtonString);
                        member.setDob(result[2] + "-" + result[1] + "-" + result[5]);
                        member.setId(tfID.getText());

                        message = "ID: " + tfID.getText();
                        message += "\nName: " + tfName.getText();
                        message += "\nPhoneNo: " + tfPhno.getText();
                        message += "\nAddress: " + tfAddr.getText();
                        message += "\nGender: " + radioButtonString;
                        message += "\nDOB: " + result[2] + "-" + result[1] + "-" + result[5];
                        message += "\n\nIs the information correct?";

                        int returnVal = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (returnVal == JOptionPane.YES_OPTION) {

                            membercontrol.updateRecord(member);
                            JOptionPane.showMessageDialog(null, "Successfully update the record!", "Update record", JOptionPane.INFORMATION_MESSAGE);

                            tableModel.setValueAt(tfName.getText(), table.getSelectedRow(), 1);
                            tableModel.setValueAt(tfPhno.getText(), table.getSelectedRow(), 2);
                            tableModel.setValueAt(radioButtonString, table.getSelectedRow(), 3);
                            tableModel.setValueAt(result[2] + "-" + result[1] + "-" + result[5], table.getSelectedRow(), 4);
                            jbClear.doClick();
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jbClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfID.setText("");
                tfAddr.setText("");
                tfPhno.setText("");
                dob.setCalendar(null);
                buttonGroup.clearSelection();
            }
        });

        jbDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = 0;
                int count = 0;
                ResultSet rs = membercontrol.getRecord();
                try {
                    while (rs.next()) {
                        row++;
                        if (rs.getString("ID").equals(tfID.getText())) {
                            EXIST = true;
                        }
                    }

                    for (int i = 0; i < row; i++) {
                        String x = (String) tableModel.getValueAt(i, 0);
                        if (tfID.getText().equals(x)) {
                            count = i;
                        }
                    }
                    if (EXIST == true) {
                        String message = "Do you want to delete this record?";
                        int returnVal = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (returnVal == JOptionPane.YES_OPTION) {
                            membercontrol.deleteRecord(tfID.getText());
                            JOptionPane.showMessageDialog(null, "Successfully delete the record!", "Removed", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.removeRow(count);
                            jbClear.doClick();
                        }
                        EXIST = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "No such record existed!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "hi", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        leftPanel.setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        setTitle("Maintain Member Information");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(1000, 460);
        setLocationRelativeTo(null);
    }

    private class RadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonString = e.getActionCommand();
        }

    }

    public void update() {
        String text = tfSearch.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfSearch.requestFocus();

    }

//    public static void main(String[] args) {
//        new MaintainMemberFrame();
//    }
}
