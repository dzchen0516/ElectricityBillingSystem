package electricity.billing.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BillDetailsScreen extends JFrame {
    String meterNum;
    JTable billDetailsTable;
    String[] columnNames = {"Meter Number", "Month", "Unit", "Total Bill", "State"};
    BillDetailsScreen(String meterNum) {
        this.meterNum = meterNum;

        billDetailsTable = new JTable();

        //create bill details table
        billDetailsTable = new JTable();
        add(createBillDetailsTable());

        setSize(700, 650);
        setLocation(400, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private JScrollPane createBillDetailsTable()
    {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        ArrayList<Object[]> allBillInfo = DataSource.getBillInfoByMeterNum(meterNum);
        for(Object[] bill : allBillInfo)
        {
            tableModel.addRow(bill);
        }
        billDetailsTable.setModel(tableModel);

        JScrollPane billInfoTablePane = new JScrollPane(billDetailsTable);
        billInfoTablePane.setBounds(0, 0, 700, 650);
        return billInfoTablePane;
    }

    public static void main(String[] args) {
        new BillDetailsScreen("");
    }
}
