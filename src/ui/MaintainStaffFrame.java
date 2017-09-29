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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

import control.StaffControl;
import domain.Staff;
import java.awt.Component;
import java.sql.ResultSet;
import javax.swing.table.TableCellRenderer;

public class MaintainStaffFrame extends JFrame {

    JPanel westGridBag = new JPanel(new GridBagLayout());
    JPanel westPanel = new JPanel(new BorderLayout());
    JPanel eastPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel rightPanelMain = new JPanel();
    JLabel inputPassword = new JLabel("Please enter manager password:");
    JLabel lbName = new JLabel("Name: ");
    JLabel lbID = new JLabel("ID: ");
    JLabel lbPassword = new JLabel("Password: ");
    JLabel lbPhoneno = new JLabel("PhoneNo: ");
    JLabel lbSearch = new JLabel("Specify word to search: ");
    JLabel lbGender = new JLabel("Gender: ");
    JLabel lbAge = new JLabel("Age: ");
    JLabel lbDOB = new JLabel("DOB: ");
    JTextField tfName = new JTextField();
    JTextField tfID = new JTextField();
    JTextField tfPhone = new JTextField();
    JTextField tfSearch = new JTextField();

    JPasswordField tfPassword = new JPasswordField();
    JPasswordField tfManagerPassword = new JPasswordField();
    JComboBox cbAge = new JComboBox();
    private ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton male = new JRadioButton("Male");
    JRadioButton female = new JRadioButton("Female");
    JDateChooser dob = new JDateChooser();
    private GlossyButton jbCreate = new GlossyButton("Create", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    private GlossyButton jbDelete = new GlossyButton("Delete", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    private GlossyButton jbUpdate = new GlossyButton("Update", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    private GlossyButton jbClear = new GlossyButton("Clear", ButtonType.BUTTON_ROUNDED, Theme.GLOSSY_LIME_THEME, Theme.GLOSSY_LIME_THEME);
    JTable table;
    JScrollPane scrollTable;
    DefaultTableModel tableModel;
    TableRowSorter<TableModel> sorter;

    int NAME_COLUMN = 1;
    int PASSWORD_COLUMN = 2;
    int PHNO_COLUMN = 3;
    int ID_COLUMN = 0;
    int GENDER_COLUMN = 5;
    int AGE_COLUMN = 4;
    int DOB_COLUMN = 6;

    String message = null;
    boolean EDITED = false;
    boolean EXIST = false;
    String MANAGER_PASS = "123";
    String radioButtonString;

    StaffControl staffcontrol;

    public MaintainStaffFrame() {
        staffcontrol = new StaffControl();
        //set layout for frame
        setLayout(new BorderLayout());

        //textfield settings
        tfName.setPreferredSize(new Dimension(200, 25));
        tfPassword.setPreferredSize(new Dimension(200, 25));
        tfPhone.setPreferredSize(new Dimension(200, 25));
        tfID.setPreferredSize(new Dimension(200, 25));
        tfName.setColumns(15);
        tfPassword.setColumns(15);
        tfPhone.setColumns(15);
        tfID.setColumns(15);
        tfSearch.setColumns(20);
        tfID.setEditable(false);
        dob.setPreferredSize(new Dimension(190, 25));
        dob.setDateFormatString("d-MMM-yyyy");

        //add age into combo box age
        cbAge.addItem("-");
        for (int i = 21; i <= 55; i++) {
            cbAge.addItem("" + i);
        }

        //add radio button to button group
        buttonGroup.add(male);
        buttonGroup.add(female);

        //border settings
        Border innerBorder = BorderFactory.createTitledBorder(null, "Staff Information", TitledBorder.TOP, TitledBorder.CENTER, new Font("Comic Sans MS", Font.BOLD, 16), Color.RED);
        Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        //get table
        tableModel = staffcontrol.createTable();
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

        //table settings
        table.setPreferredScrollableViewportSize(new Dimension(500, 350));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(35);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    tfName.setText(table.getValueAt(table.getSelectedRow(), NAME_COLUMN).toString());
                    tfID.setText(table.getValueAt(table.getSelectedRow(), ID_COLUMN).toString());
                    ResultSet rs = staffcontrol.selectRecord(tfID.getText());
                    if (rs.next()) {
                        tfPassword.setText(rs.getString("PASSWORD"));
                    }
                    tfPhone.setText(table.getValueAt(table.getSelectedRow(), PHNO_COLUMN).toString());
                    cbAge.setSelectedIndex(Integer.parseInt(table.getValueAt(table.getSelectedRow(), AGE_COLUMN).toString()) - 20);
                    dob.setDate(new Date(table.getValueAt(table.getSelectedRow(), DOB_COLUMN).toString()));
                    String gender = table.getValueAt(table.getSelectedRow(), GENDER_COLUMN).toString();
                    if (gender.equals("Male")) {
                        male.setSelected(true);
                        male.doClick();
                    } else {
                        female.setSelected(true);
                        female.doClick();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });

        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        //add table for scrollPane
        scrollTable = new JScrollPane(table);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //add scrolltable to frame
        eastPanel.add(scrollTable, BorderLayout.CENTER);
        //add search lb,tf,jb in panel
        bottomPanel.add(lbSearch, BorderLayout.WEST);
        bottomPanel.add(tfSearch, BorderLayout.CENTER);

        //add search panel to frame
        eastPanel.add(bottomPanel, BorderLayout.SOUTH);
        // add panel to mail panel
        rightPanelMain.add(eastPanel);
        //add main panel to frame
        add(rightPanelMain, BorderLayout.EAST);

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

        //gridbag layout settings
        GridBagConstraints gc = new GridBagConstraints();

        //====================first row====================
        gc.weightx = 1;
        gc.weighty = 0.3;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;

        westGridBag.add(lbID, gc);

        gc.gridx++;

        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        westGridBag.add(tfID, gc);

        gc.gridwidth = 1;

        //====================second row====================
        gc.weightx = 1;
        gc.weighty = 0.3;
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        westGridBag.add(lbName, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridwidth = 2;
        westGridBag.add(tfName, gc);

        //====================third row====================
        gc.weightx = 1;
        gc.weighty = 0.3;
        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridwidth = 1;
        westGridBag.add(lbPassword, gc);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        westGridBag.add(tfPassword, gc);

        //====================fourth row====================
        gc.weightx = 1;
        gc.weighty = 0.3;
        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridwidth = 1;
        westGridBag.add(lbPhoneno, gc);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        westGridBag.add(tfPhone, gc);

        //====================fifth row====================
        gc.gridwidth = 1;
        gc.gridy = 4;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        westGridBag.add(lbAge, gc);

        gc.gridy = 4;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        westGridBag.add(cbAge, gc);
        //====================sixth row====================
        gc.gridwidth = 1;
        gc.gridy = 5;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        westGridBag.add(lbDOB, gc);
        gc.gridwidth = 2;
        gc.gridy = 5;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        westGridBag.add(dob, gc);

        //====================seventh row====================
        gc.gridwidth = 1;
        gc.gridy = 6;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        westGridBag.add(lbGender, gc);

        gc.gridwidth = 1;
        gc.gridy = 6;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        westGridBag.add(male, gc);
        male.addActionListener(new RadioButtonListener());

        gc.gridwidth = 1;
        gc.gridy = 6;
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        westGridBag.add(female, gc);
        female.addActionListener(new RadioButtonListener());

        //====================eighth row====================
        gc.weighty = 0.3;
        gc.weightx = 1;
        gc.gridy = 7;
        gc.gridwidth = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0, 10, 0, 0);
        westGridBag.add(jbCreate, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 10, 0, 0);
        westGridBag.add(jbUpdate, gc);
        gc.gridx = 2;
        gc.insets = new Insets(0, -10, 0, 0);
        westGridBag.add(jbDelete, gc);
        gc.gridx = 3;
        gc.insets = new Insets(0, -15, 0, 10);
        westGridBag.add(jbClear, gc);

        westGridBag.setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));
        add(westGridBag, BorderLayout.WEST);

        jbCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                tfID.setText("");
                //staffcontrol.updateTableID();
                Staff staff = new Staff();
                ResultSet rs = staffcontrol.getRecord();
                int x;

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
                    staff.setId(String.format("S%03d", row));
                    staff.setPassword(tfPassword.getText());
                    staff.setName(tfName.getText());
                    staff.setPhno(tfPhone.getText());
                    staff.setGender(radioButtonString);
                    staff.setAge("" + cbAge.getSelectedItem());
                    staff.setDob(result[2] + "-" + result[1] + "-" + result[5]);

                    message = "ID: " + String.format("S%03d", row);
                    message += "\nName: " + tfName.getText();
                    message += "\nPassword: " + tfPassword.getText().replaceAll("[a-zA-Z0-9~!@#$%^&*()_+-=]", "*");
                    message += "\nPhoneNo: " + tfPhone.getText();
                    message += "\nGender: " + radioButtonString;
                    message += "\nAge: " + cbAge.getSelectedItem();
                    message += "\nDOB: " + result[2] + "-" + result[1] + "-" + result[5];
                    message += "\n\nIs the information correct?";

                    int returnVal = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (returnVal == JOptionPane.YES_OPTION) {
                        Vector dbrow = new Vector();
                        dbrow.add(String.format("S%03d", row));
                        dbrow.add(tfName.getText());
                        dbrow.add(tfPassword.getText().replaceAll("[a-zA-Z0-9~!@#$%^&*()_+-=]", "*"));
                        dbrow.add(tfPhone.getText());

                        dbrow.add(cbAge.getSelectedItem());
                        dbrow.add(radioButtonString);
                        dbrow.add(result[2] + "-" + result[1] + "-" + result[5]);

                        staffcontrol.createRecord(staff);
                        JOptionPane.showMessageDialog(null, "Successfully created the record!", "Update record", JOptionPane.INFORMATION_MESSAGE);
                        tableModel.addRow(dbrow);

                    }
                    jbClear.doClick();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please ensure all fields are filled!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        jbUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = staffcontrol.selectRecord(tfID.getText());
                ResultSet rs1 = staffcontrol.getRecord();
                Staff staff = new Staff();

                try {
                    if (tfName.getText().equals("") || tfPassword.getText().equals("") || tfPhone.getText().equals("") || !buttonGroup.getSelection().isSelected() || dob.getDate().toString().equals("") || cbAge.getSelectedIndex() == 0) {
                        throw new NullPointerException("Please ensure all fields are filled accordingly!");
                    } else {
                        while (rs1.next()) {
                            if (rs1.getString("ID").equals(tfID.getText())) {
                                EXIST = true;
                            }
                        }
                        if (EXIST == false) {
                            throw new IndexOutOfBoundsException("No such record!");
                            // JOptionPane.showMessageDialog(null, "No such record!", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                        EXIST = false;

                        staff.setName(tfName.getText());
                        staff.setPhno(tfPhone.getText());

                        if (rs.next()) {

                            if (rs.getString("PASSWORD").equals(tfPassword.getText())) {
                                staff.setPassword(tfPassword.getText());

                            } else {
                                Object[] ob = {inputPassword, tfManagerPassword};
                                int result = JOptionPane.showConfirmDialog(null, ob, "Password!", JOptionPane.OK_CANCEL_OPTION);
                                if (result == 0) {
                                    String pass = tfManagerPassword.getText();
                                    if (pass.equals(MANAGER_PASS)) {
                                        staff.setPassword(tfPassword.getText());
                                        tfManagerPassword.setText("");
                                        EDITED = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Wrong Password!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                        String dateofbirth = "" + dob.getDate();
                        String result[] = dateofbirth.split(" ");

                        staff.setGender(radioButtonString);
                        staff.setAge("" + cbAge.getSelectedItem());
                        staff.setDob(result[2] + "-" + result[1] + "-" + result[5]);
                        staff.setId(tfID.getText());

                        if (EDITED == true) {
                            message = "ID: " + tfID.getText();
                            message += "\nName: " + tfName.getText();
                            message += "\nPassword: " + tfPassword.getText();
                            message += "\nPhoneNo: " + tfPhone.getText();
                            message += "\nGender: " + radioButtonString;
                            message += "\nAge: " + cbAge.getSelectedItem();
                            message += "\nDOB: " + result[2] + "-" + result[1] + "-" + result[5];
                            message += "\n\nIs the information correct?";
                        } else {
                            message = "ID: " + tfID.getText();
                            message += "\nName: " + tfName.getText();
                            message += "\nPassword: " + tfPassword.getText().replaceAll("[a-zA-Z0-9~!@#$%^&*()_+-=]", "*");
                            message += "\nPhoneNo: " + tfPhone.getText();
                            message += "\nGender: " + radioButtonString;
                            message += "\nAge: " + cbAge.getSelectedItem();
                            message += "\nDOB: " + result[2] + "-" + result[1] + "-" + result[5];
                            message += "\n\nIs the information correct?";
                        }

                        int returnVal = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (returnVal == JOptionPane.YES_OPTION) {

                            staffcontrol.updateRecord(staff);

                            JOptionPane.showMessageDialog(null, "Successfully update the record!", "Update record", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.setValueAt(tfName.getText(), table.getSelectedRow(), NAME_COLUMN);
                            tableModel.setValueAt(tfPassword.getText().replaceAll("[a-zA-Z0-9~!@#$%^&*()_+-=]", "*"), table.getSelectedRow(), PASSWORD_COLUMN);
                            tableModel.setValueAt(tfPhone.getText(), table.getSelectedRow(), PHNO_COLUMN);
                            tableModel.setValueAt(cbAge.getSelectedItem(), table.getSelectedRow(), AGE_COLUMN);
                            tableModel.setValueAt(radioButtonString, table.getSelectedRow(), GENDER_COLUMN);
                            tableModel.setValueAt(result[2] + "-" + result[1] + "-" + result[5], table.getSelectedRow(), DOB_COLUMN);
                            tfName.setText("");
                            tfPassword.setText("");
                            tfPhone.setText("");
                            tfID.setText("");
                            cbAge.setSelectedIndex(0);
                            dob.setCalendar(null);
                            buttonGroup.clearSelection();
                            message = null;
                            EDITED = false;
                        }
                    }
                } catch (Exception exx) {
                    JOptionPane.showMessageDialog(null, exx.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        jbDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = staffcontrol.getRecord();
                int row = 0;
                int count = 0;
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
                            staffcontrol.deleteRecord(tfID.getText());
                            JOptionPane.showMessageDialog(null, "Successfully delete the record!", "Removed", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.removeRow(count);
                            jbClear.doClick();
                        }
                        EXIST = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "No such record existed!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        jbClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                tfName.setText("");
                tfID.setText("");
                tfPassword.setText("");
                tfPhone.setText("");
                cbAge.setSelectedIndex(0);
                dob.setCalendar(null);
                buttonGroup.clearSelection();
            }
        });

        male.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonString = e.getActionCommand();

            }
        });

        female.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonString = e.getActionCommand();

            }
        });

        setTitle("Maintain Staff Information");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(890, 440);
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
//        new MaintainStaffFrame();
//    }

}
