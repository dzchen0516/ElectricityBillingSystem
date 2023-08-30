package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateBillScreen extends JFrame implements ActionListener {
    Choice meterNumberField;
    Choice monthField;
    JLabel nameField;
    JLabel addressField;
    JTextField unitConsumedField;
    JButton submitButton;
    JButton cancelButton;
    CalculateBillScreen() {
        super("Calculate Electricity Bill");

        //create new panel
        JPanel newCalculateBillPanel = UIElementsCreateHelper.createNewPanel(new Color(214, 195,247));

        //create heading
        newCalculateBillPanel.add(UIElementsCreateHelper.createNewHeadingLabel("Calculate Electricity Bill", 70, 10,300,20, 15));

        //add meter number input field
        newCalculateBillPanel.add(UIElementsCreateHelper.createNewInputLabel("Meter Number", 50, 80, 100, 20));
        String[] allMeterNumber = DataSource.getMeterNumFromCustomer();
        meterNumberField = UIElementsCreateHelper.createChoicePanel(allMeterNumber, 180, 80, 110, 20);
        newCalculateBillPanel.add(meterNumberField);

        //add name field
        newCalculateBillPanel.add(UIElementsCreateHelper.createNewInputLabel("Name", 50, 120, 100, 20));
        nameField = UIElementsCreateHelper.createNewInputLabel("", 180, 120, 150, 20);
        newCalculateBillPanel.add(nameField);

        //add address field
        newCalculateBillPanel.add(UIElementsCreateHelper.createNewInputLabel("Address", 50, 160, 100, 20));
        addressField = UIElementsCreateHelper.createNewInputLabel("", 180, 160, 150, 20);
        newCalculateBillPanel.add(addressField);

        //update name and address for selected meter number
        updateNameAddressField(meterNumberField.getSelectedItem(), nameField, addressField);

        //add listener to the meter number field so that it can update the name and address field
        //for different meter number
        meterNumberField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateNameAddressField(meterNumberField.getSelectedItem(), nameField, addressField);
            }
        });

        //add unit consumed field
        newCalculateBillPanel.add(UIElementsCreateHelper.createNewInputLabel("Unit Consumed", 50, 200, 100, 20));
        unitConsumedField = UIElementsCreateHelper.createNewInputTextField(180, 200, 150, 20);
        newCalculateBillPanel.add(unitConsumedField);

        //add month field
        newCalculateBillPanel.add(UIElementsCreateHelper.createNewInputLabel("Month", 50, 240, 100, 20));
        String[] allMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthField = UIElementsCreateHelper.createChoicePanel(allMonths, 180, 240, 150, 20);
        newCalculateBillPanel.add(monthField);

        //submit button
        submitButton = UIElementsCreateHelper.createNewButton("Submit", 80, 300, 100, 25);
        submitButton.addActionListener(this);
        newCalculateBillPanel.add(submitButton);

        //cancel button
        cancelButton = UIElementsCreateHelper.createNewButton("Cancel", 220, 300, 100, 25);
        cancelButton.addActionListener(this);
        newCalculateBillPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(newCalculateBillPanel, "Center");

        //add calculate bill screen image
        RawIconConverter calculateScreenIcon = new RawIconConverter(0, 0, 250, 200, "icon/budget.png");
        add(calculateScreenIcon.convertIcons(), "East");

        setSize(650, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    private static void updateNameAddressField(String meterNum, JLabel nameField, JLabel addressField)
    {
        HashMap<String, String> customerInfo = DataSource.getCustomerByMeterNum(meterNum);
        nameField.setText(customerInfo.get("name"));
        addressField.setText(customerInfo.get("address"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton)
        {
            String meterNum = meterNumberField.getSelectedItem();
            Integer unitConsumed = Integer.parseInt(unitConsumedField.getText());
            String month = monthField.getSelectedItem();

            //calculate total bill
            Integer totalBill = 0;
            HashMap<String, Integer> costDetails = DataSource.getCostDetails();
            totalBill += unitConsumed * costDetails.get("cost_per_unit");
            totalBill += costDetails.get("meter_rent") + costDetails.get("service_charge") +
                        costDetails.get("government_tax") + costDetails.get("fixed_tax");

            DataSource.addBillForMeterNum(meterNum, month, unitConsumed.toString(), totalBill.toString());

            JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
            setVisible(false);
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBillScreen();
    }
}
