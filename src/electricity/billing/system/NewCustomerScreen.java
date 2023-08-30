package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomerScreen extends JFrame implements ActionListener {
    JTextField customerNameField;
    JTextField meterNumberField;
    JTextField addressField;
    JTextField cityField;
    JTextField stateField;
    JTextField emailField;
    JTextField phoneField;
    JButton nextButton;
    JButton cancelButton;
    NewCustomerScreen() {
        super("New Customer");

        //create new panel
        JPanel newCustomerPanel = UIElementsCreateHelper.createNewPanel(new Color(252, 186,3));

        //add heading
        newCustomerPanel.add(UIElementsCreateHelper.createNewHeadingLabel("New Customer", 180, 10,200,20, 15));

        //add new customer input field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("Customer Name", 50, 80, 110, 20));
        customerNameField = UIElementsCreateHelper.createNewInputTextField(180, 80, 150, 20);
        newCustomerPanel.add(customerNameField);

        //add meter number input field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("Meter Number", 50, 120, 100, 20));
        meterNumberField = UIElementsCreateHelper.createNewInputTextField(180, 120, 150, 20);
        newCustomerPanel.add(meterNumberField);

        //add random number to meter field
        Random random = new Random();
        long number = random.nextLong() % 10000000;
        meterNumberField.setText("" + Math.abs(number));

        //add address field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("Address", 50, 160, 100, 20));
        addressField = UIElementsCreateHelper.createNewInputTextField(180, 160, 150, 20);
        newCustomerPanel.add(addressField);

        //add city field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("City", 50, 200, 100, 20));
        cityField = UIElementsCreateHelper.createNewInputTextField(180, 200, 150, 20);
        newCustomerPanel.add(cityField);

        //add state field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("State", 50, 240, 100, 20));
        stateField = UIElementsCreateHelper.createNewInputTextField(180, 240, 150, 20);
        newCustomerPanel.add(stateField);

        //add email field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("Email", 50, 280, 100, 20));
        emailField = UIElementsCreateHelper.createNewInputTextField(180, 280, 150, 20);
        newCustomerPanel.add(emailField);

        //add phone field
        newCustomerPanel.add(UIElementsCreateHelper.createNewInputLabel("Phone", 50, 320, 100, 20));
        phoneField = UIElementsCreateHelper.createNewInputTextField(180, 320, 150, 20);
        newCustomerPanel.add(phoneField);

        //add next button
        nextButton = UIElementsCreateHelper.createNewButton("Next", 120, 390, 100, 25);
        nextButton.addActionListener(this);
        newCustomerPanel.add(nextButton);

        //add cancel button
        cancelButton = UIElementsCreateHelper.createNewButton("Cancel", 230, 390, 100, 25);
        cancelButton.addActionListener(this);
        newCustomerPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(newCustomerPanel, "Center");

        //add new customer screen image
        RawIconConverter newCustomerScreenIcon = new RawIconConverter(0, 0, 230, 200, "icon/boy.png");
        add(newCustomerScreenIcon.convertIcons(), "West");

        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton)
        {
            String customerName = customerNameField.getText();
            String meterNumber = meterNumberField.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            DataSource.addCustomer(customerName, meterNumber, address, city, state, email, phone);
            DataSource.addUser(meterNumber, "", customerName, "", "Customer");

            JOptionPane.showMessageDialog(null,"Customer added successfully");
            setVisible(false);
            new MeterInfoScreen(meterNumber);
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomerScreen();
    }
}
