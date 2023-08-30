package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PayBillScreen extends JFrame implements ActionListener {
    String meterNum;
    JLabel meterNumberField;
    JLabel nameField;
    JLabel unitField;
    JLabel totalBillField;
    JLabel statusField;
    Choice monthField;
    JButton payButton;
    JButton backButton;
    PayBillScreen(String meterNum) {
        super("Pay Bill");
        this.meterNum = meterNum;

        //create heading
        add(UIElementsCreateHelper.createNewHeadingLabel("Pay Bill", 120, 5,400,30,24));

        //add meter number input field
        add(UIElementsCreateHelper.createNewInputLabel("Meter Number", 35, 80, 200, 20));
        meterNumberField = UIElementsCreateHelper.createNewInputLabel("", 300, 80, 200, 20);
        add(meterNumberField);

        //add name field
        add(UIElementsCreateHelper.createNewInputLabel("Name", 35, 140, 200, 20));
        nameField = UIElementsCreateHelper.createNewInputLabel("", 300, 140, 200, 20);
        add(nameField);

        //add month field
        add(UIElementsCreateHelper.createNewInputLabel("Month", 35, 200, 200, 20));
        String[] allMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthField = UIElementsCreateHelper.createChoicePanel(allMonths, 300, 200, 150, 20);
        add(monthField);

        //add unit field
        add(UIElementsCreateHelper.createNewInputLabel("Unit", 35, 260, 200, 20));
        unitField = UIElementsCreateHelper.createNewInputLabel("", 300, 260, 200, 20);
        add(unitField);

        //add total bill field
        add(UIElementsCreateHelper.createNewInputLabel("Total Bill", 35, 320, 200, 20));
        totalBillField = UIElementsCreateHelper.createNewInputLabel("", 300, 320, 200, 20);
        add(totalBillField);

        //add status field
        add(UIElementsCreateHelper.createNewInputLabel("Status", 35, 380, 200, 20));
        statusField = UIElementsCreateHelper.createNewInputLabel("", 300, 380, 200, 20);
        statusField.setForeground(Color.RED);
        add(statusField);

        //populate meter number and name fields
        populateMeterNumAndName();

        //populate unit, total bill, status fields
        populateUnitAndBillAndStatus();

        //add listener to month field
        addListenerToMonthField();

        //add pay button
        payButton = UIElementsCreateHelper.createNewButton("Pay", 100,460,100,25);
        payButton.addActionListener(this);
        add(payButton);

        //add back button
        backButton = UIElementsCreateHelper.createNewButton("Back", 230,460,100,25);
        backButton.addActionListener(this);
        add(backButton);

        setSize(900, 600);
        setLocation(350, 200);
        setLayout(null);
        setVisible(true);
    }

    private void populateMeterNumAndName()
    {
        HashMap<String, String> customerInfo = DataSource.getCustomerByMeterNum(meterNum);
        meterNumberField.setText(customerInfo.get("meterNum"));
        nameField.setText(customerInfo.get("name"));
    }

    private void populateUnitAndBillAndStatus()
    {
        ArrayList<Object[]> depositInfo = DataSource.getDepositInfoByMonthAndMeterNum(monthField.getSelectedItem(), meterNum);
        for(Object[] info : depositInfo)
        {
            unitField.setText(info[2].toString());
            totalBillField.setText(info[3].toString());
            statusField.setText(info[4].toString());
        }
    }

    private void addListenerToMonthField()
    {
        monthField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try
                {
                    ArrayList<Object[]> depositInfo = DataSource.getDepositInfoByMonthAndMeterNum(monthField.getSelectedItem(), meterNum);
                    for(Object[] info : depositInfo)
                    {
                        unitField.setText(info[2].toString());
                        totalBillField.setText(info[3].toString());
                        statusField.setText(info[4].toString());
                    }
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == payButton)
        {
            try
            {
                DataSource.updateBillForMeterNumAndMonth(meterNum, monthField.getSelectedItem());
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            setVisible(false);
            new PaytmOnlinePaymentScreen(meterNum);
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBillScreen("");
    }
}
