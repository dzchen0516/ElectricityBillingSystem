package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UpdateInformationScreen extends JFrame implements ActionListener {
    JTextField nameField;
    JLabel meterNumField;
    JTextField addressField;
    JTextField cityField;
    JTextField stateField;
    JTextField emailField;
    JTextField phoneField;
    String meterNum;
    JButton updateButton;
    JButton cancelButton;
    UpdateInformationScreen(String meterNum)
    {
        super("Update Information");
        this.meterNum = meterNum;
        setBounds(400, 150, 777, 450);
        getContentPane().setBackground(new Color(229, 255, 227));
        setLayout(null);

        //create heading
        add(UIElementsCreateHelper.createNewHeadingLabel("Update Customer Information", 50, 10, 400, 40,15));

        //create name field
        add(UIElementsCreateHelper.createNewInputLabel("Name", 30, 70, 100, 20));
        nameField = UIElementsCreateHelper.createNewInputTextField(150, 70, 200, 20);
        add(nameField);

        //add meter field
        add(UIElementsCreateHelper.createNewInputLabel("Meter Number", 30, 110, 100, 20));
        meterNumField = UIElementsCreateHelper.createNewInputLabel("", 150, 110, 200, 20);
        add(meterNumField);

        //address field
        add(UIElementsCreateHelper.createNewInputLabel("Address", 30, 150, 100, 20));
        addressField = UIElementsCreateHelper.createNewInputTextField( 150, 150, 200, 20);
        add(addressField);

        //city field
        add(UIElementsCreateHelper.createNewInputLabel("City", 30, 190, 100, 20));
        cityField = UIElementsCreateHelper.createNewInputTextField(150, 190, 200, 20);
        add(cityField);

        //state field
        add(UIElementsCreateHelper.createNewInputLabel("State", 30, 230, 100, 20));
        stateField = UIElementsCreateHelper.createNewInputTextField(150, 230, 200, 20);
        add(stateField);

        //email field
        add(UIElementsCreateHelper.createNewInputLabel("Email", 30, 270, 100, 20));
        emailField = UIElementsCreateHelper.createNewInputTextField(150, 270, 200, 20);
        add(emailField);

        //phone field
        add(UIElementsCreateHelper.createNewInputLabel("Phone", 30, 310, 100, 20));
        phoneField = UIElementsCreateHelper.createNewInputTextField(150, 310, 200, 20);
        add(phoneField);

        //populate customer information
        HashMap<String, String> customerInfo = DataSource.getCustomerByMeterNum(meterNum);
        nameField.setText(customerInfo.get("name"));
        meterNumField.setText(customerInfo.get("meterNum"));
        addressField.setText(customerInfo.get("address"));
        cityField.setText(customerInfo.get("city"));
        stateField.setText(customerInfo.get("state"));
        emailField.setText(customerInfo.get("email"));
        phoneField.setText(customerInfo.get("phone"));

        //create update button
        updateButton = UIElementsCreateHelper.createNewButton("Update", 50, 360, 120, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        //create cancel button
        cancelButton = UIElementsCreateHelper.createNewButton("Cancel", 200, 360, 120, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        //add icon to screen
        RawIconConverter viewInformationImgIcon = new RawIconConverter(360, 0, 400, 410, "icon/update.png");
        add(viewInformationImgIcon.convertIcons());

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateButton)
        {
            String address = addressField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            DataSource.updateCustomer(address, city, state, email, phone, meterNum);

            JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
            setVisible(false);
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateInformationScreen("");
    }
}
