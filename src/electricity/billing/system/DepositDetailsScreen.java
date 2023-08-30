package electricity.billing.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DepositDetailsScreen extends JFrame implements ActionListener {
    Choice meterNumberField;
    Choice monthField;
    JTable depositInfoTable;
    JButton searchButton;
    JButton printButton;
    JButton closeButton;
    String[] columnNames = {"Meter Number", "Month", "Unit", "Total Bill", "Status"};
    DepositDetailsScreen() {
        super("Deposit Details");
        getContentPane().setBackground(new Color(192, 186, 254));

        //add search by meter number field
        add(UIElementsCreateHelper.createNewInputLabel("Search By Meter Number", 20, 20, 150, 20));
        String[] meterNumbers = DataSource.getMeterNumFromBill();
        meterNumberField = UIElementsCreateHelper.createChoicePanel(meterNumbers, 180, 20, 100, 20);
        add(meterNumberField);

        //add search by month field
        add(UIElementsCreateHelper.createNewInputLabel("Search By Month", 400, 20, 100, 20));
        String[] allMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthField = UIElementsCreateHelper.createChoicePanel(allMonths, 520, 20, 100, 20);
        add(monthField);

        //create deposit details table
        depositInfoTable = new JTable();
        add(createDepositInfoTable(depositInfoTable, columnNames));


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
            ArrayList<Object[]> depositInfo = DataSource.getDepositInfoByMonthAndMeterNum(monthField.getSelectedItem(), meterNumberField.getSelectedItem());
            if(!depositInfo.isEmpty()){
                tableModel.addRow(depositInfo.get(0));
            }
            depositInfoTable.setModel(tableModel);
        }
        else if(e.getSource() == printButton)
        {
            try {
                depositInfoTable.print();
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

    private static JScrollPane createDepositInfoTable(JTable depositInfoTable, String[] columnNames)
    {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        ArrayList<Object[]> allBills = DataSource.getAllDepositInfo();
        for(Object[] bill : allBills)
        {
            tableModel.addRow(bill);
        }
        depositInfoTable.setModel(tableModel);

        JScrollPane depositInfoTablePane = new JScrollPane(depositInfoTable);
        depositInfoTablePane.setBounds(0, 100, 700, 600);
        return depositInfoTablePane;
    }

    public static void main(String[] args) {
        new DepositDetailsScreen();
    }
}
