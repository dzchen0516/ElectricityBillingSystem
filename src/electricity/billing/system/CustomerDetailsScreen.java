package electricity.billing.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerDetailsScreen extends JFrame implements ActionListener {
    Choice meterNumberField;
    Choice nameField;
    JTable customerInfoTable;
    JButton searchButton;
    JButton printButton;
    JButton closeButton;
    String[] columnNames = {"Name", "Meter Number", "Address", "City", "State", "Email", "Phone"};
    CustomerDetailsScreen() {
        super("Customer Details");
        getContentPane().setBackground(new Color(192, 186, 254));

        //add search by meter number field
        add(UIElementsCreateHelper.createNewInputLabel("Search By Meter Number", 20, 20, 150, 20));
        String[] meterNumbers = DataSource.getMeterNumFromCustomer();
        meterNumberField = UIElementsCreateHelper.createChoicePanel(meterNumbers, 180, 20, 100, 20);
        add(meterNumberField);

        //add search by name field
        add(UIElementsCreateHelper.createNewInputLabel("Search By Name", 400, 20, 100, 20));
        String[] customerNames = DataSource.getNameFromCustomer();
        nameField = UIElementsCreateHelper.createChoicePanel(customerNames, 520, 20, 100, 20);
        add(nameField);

        //create customer details table
        customerInfoTable = new JTable();
        add(createCustomerInfoTable(customerInfoTable, columnNames));

        //create search button
        searchButton = UIElementsCreateHelper.createNewButton("Search", 20, 70, 80, 20);
        searchButton.addActionListener(this);
        add(searchButton);

        //create print button
        printButton = UIElementsCreateHelper.createNewButton("Print", 120, 70, 80, 20);
        printButton.addActionListener(this);
        add(printButton);

        //create close button
        closeButton = UIElementsCreateHelper.createNewButton("Close", 600, 70, 80, 20);
        closeButton.addActionListener(this);
        add(closeButton);

        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchButton)
        {
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            ArrayList<Object[]> customerInfo = DataSource.getCustomerByNameAndMeterNum(nameField.getSelectedItem(), meterNumberField.getSelectedItem());
            if(!customerInfo.isEmpty()){
                tableModel.addRow(customerInfo.get(0));
            }
            customerInfoTable.setModel(tableModel);
        }
        else if(e.getSource() == printButton)
        {
            try {
                customerInfoTable.print();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        else
        {
            setVisible(false);
        }
    }

    private static JScrollPane createCustomerInfoTable(JTable customerInfoTable, String[] columnNames)
    {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        ArrayList<Object[]> allCustomer = DataSource.getAllCustomerInfo();
        for(Object[] customerInfo : allCustomer)
        {
            tableModel.addRow(customerInfo);
        }
        customerInfoTable.setModel(tableModel);

        JScrollPane customerInfoTablePane = new JScrollPane(customerInfoTable);
        customerInfoTablePane.setBounds(0, 100, 700, 600);
        return customerInfoTablePane;
    }

    public static void main(String[] args) {
        new CustomerDetailsScreen();
    }
}
