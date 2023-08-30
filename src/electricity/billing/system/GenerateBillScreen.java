package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GenerateBillScreen extends JFrame implements ActionListener {
    String meterNum;
    Choice searchMonthField;
    JTextArea areaField;

    JButton generateBillButton;
    GenerateBillScreen(String meterNum) {
        this.meterNum = meterNum;

        setSize(500, 700);
        setLocation(500, 30);
        setLayout(new BorderLayout());

        //create new panel
        JPanel generateBillPanel = new JPanel();

        //create generate bill heading
        JLabel generateBillHeading = new JLabel("Generate Bill");

        //add meter number field
        JLabel meterNumField = new JLabel(meterNum);

        //add search month field
        String[] allMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        searchMonthField = UIElementsCreateHelper.createChoicePanel(allMonths, 520, 20, 100, 20);

        //add area field
        String areaText = "\n \n \t ------------------- Click on the ---------------\n \t ------------------- Generate Bill";
        areaField = UIElementsCreateHelper.createNewTextArea(50, 15, areaText);
        JScrollPane areaTextPane = new JScrollPane(areaField);

        //add generate bill button
        generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(this);

        //add all the widgets
        add(areaTextPane);
        generateBillPanel.add(generateBillHeading);
        generateBillPanel.add(meterNumField);
        generateBillPanel.add(searchMonthField);
        add(generateBillPanel, "North");
        add(generateBillButton, "South");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            String selectedMonth = searchMonthField.getSelectedItem();
            areaField.setText("\n Power Limited \n Electricity Bill For Month of "+ selectedMonth + ",2023\n\n\n");

            //generate customer info
            generateCustomerInfo();

            //generate customer info
            generateMeterInfo();

            //generate tax info
            generateTaxInfo();

            //generate bill info
            generateBillInfo(selectedMonth);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void generateCustomerInfo()
    {
        HashMap<String, String> customerInfo = DataSource.getCustomerByMeterNum(meterNum);
        if(!customerInfo.isEmpty())
        {
            String customerName = customerInfo.get("name");
            String meterNum = customerInfo.get("meterNum");
            String address = customerInfo.get("address");
            String city = customerInfo.get("city");
            String state = customerInfo.get("state");
            String email = customerInfo.get("email");
            String phone = customerInfo.get("phone");

            areaField.append("\n    Customer Name:           " + customerName);
            areaField.append("\n    Customer Meter Number:   " + meterNum);
            areaField.append("\n    Customer Address:        " + address);
            areaField.append("\n    Customer City:           " + city);
            areaField.append("\n    Customer State:          " + state);
            areaField.append("\n    Customer Email:          " + email);
            areaField.append("\n    Customer Phone Number:   " + phone);
        }
    }

    private void generateMeterInfo()
    {
        HashMap<String, String> meterInfo = DataSource.getMeterInfoByMeterNum(meterNum);
        if(!meterInfo.isEmpty())
        {
            String meterLocation = meterInfo.get("meterLocation");
            String meterType = meterInfo.get("meterType");
            String phaseCode = meterInfo.get("phaseCode");
            String billType = meterInfo.get("billType");
            String billDays = meterInfo.get("billDays");

            areaField.append("\n    Customer Meter Location: " + meterLocation);
            areaField.append("\n    Customer Meter Type:     " + meterType);
            areaField.append("\n    Customer Phase Code:     " + phaseCode);
            areaField.append("\n    Customer Bill Type:      " + billType);
            areaField.append("\n    Customer Bill Days:      " + billDays);
        }
    }

    private void generateTaxInfo()
    {
        HashMap<String, Integer> costDetails = DataSource.getCostDetails();
        if(!costDetails.isEmpty())
        {
            String costPerUnit = costDetails.get("cost_per_unit").toString();
            String meterRent = costDetails.get("meter_rent").toString();
            String serviceCharge = costDetails.get("service_charge").toString();
            String serviceTax = costDetails.get("service_tax").toString();
            String governmentTax = costDetails.get("government_tax").toString();
            String fixedTax = costDetails.get("fixed_tax").toString();

            areaField.append("\n    Cost Per Unit:           " + costPerUnit);
            areaField.append("\n    Meter Rent:              " + meterRent);
            areaField.append("\n    Service Charge:          " + serviceCharge);
            areaField.append("\n    Service Tax:             " + serviceTax);
            areaField.append("\n    Government Tax:          " + governmentTax);
            areaField.append("\n    Fixed Tax:               " + fixedTax);
        }
    }

    private void generateBillInfo(String selectedMonth)
    {
        ArrayList<Object[]> depositInfo = DataSource.getDepositInfoByMonthAndMeterNum(selectedMonth, meterNum);
        if(!depositInfo.isEmpty())
        {
            String curMonth = "";
            String unitConsumed = "";
            String totalBill = "";
            for(Object[] info : depositInfo)
            {
                curMonth = info[1].toString();
                unitConsumed = info[2].toString();
                totalBill = info[3].toString();
            }

            areaField.append("\n    Current Month:           " + curMonth);
            areaField.append("\n    Units Consumed:          " + unitConsumed);
            areaField.append("\n    Total Charges:           " + totalBill);
            areaField.append("\n    Total Payable:           " + totalBill);

        }
    }
    public static void main(String[] args) {
        new GenerateBillScreen("");
    }
}
