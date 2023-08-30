package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ViewInformationScreen extends JFrame implements ActionListener {
    JLabel nameField;
    JLabel meterNumField;
    JLabel addressField;
    JLabel cityField;
    JLabel stateField;
    JLabel emailField;
    JLabel phoneField;
    String meterNum;
    JButton cancelButton;
    ViewInformationScreen(String meterNum) {
        super("View Information");
        this.meterNum = meterNum;
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //create heading
        add(UIElementsCreateHelper.createNewHeadingLabel("View Customer Information", 250, 0, 500, 40,15));

        //create name field
        add(UIElementsCreateHelper.createNewInputLabel("Name", 70, 80, 100, 20));
        nameField = UIElementsCreateHelper.createNewInputLabel("", 200, 80, 150, 20);
        add(nameField);

        //add meter field
        add(UIElementsCreateHelper.createNewInputLabel("Meter Number", 70, 140, 100, 20));
        meterNumField = UIElementsCreateHelper.createNewInputLabel("", 200, 140, 150, 20);
        add(meterNumField);

        //address field
        add(UIElementsCreateHelper.createNewInputLabel("Address", 70, 200, 100, 20));
        addressField = UIElementsCreateHelper.createNewInputLabel("", 200, 200, 150, 20);
        add(addressField);

        //city field
        add(UIElementsCreateHelper.createNewInputLabel("City", 70, 260, 100, 20));
        cityField = UIElementsCreateHelper.createNewInputLabel("", 200, 260, 150, 20);
        add(cityField);

        //state field
        add(UIElementsCreateHelper.createNewInputLabel("State", 500, 80, 100, 20));
        stateField = UIElementsCreateHelper.createNewInputLabel("", 600, 80, 150, 20);
        add(stateField);

        //email field
        add(UIElementsCreateHelper.createNewInputLabel("Email", 500, 140, 100, 20));
        emailField = UIElementsCreateHelper.createNewInputLabel("", 600, 140, 150, 20);
        add(emailField);

        //phone field
        add(UIElementsCreateHelper.createNewInputLabel("Phone", 500, 200, 100, 20));
        phoneField = UIElementsCreateHelper.createNewInputLabel("", 600, 200, 150, 20);
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

        //create cancel button
        cancelButton = UIElementsCreateHelper.createNewButton("Cancel", 220, 350, 120, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        //add icon to screen
        RawIconConverter viewInformationImgIcon = new RawIconConverter(100, 320, 600, 300, "icon/viewInfo.png");
        add(viewInformationImgIcon.convertIcons());

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton)
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewInformationScreen("");
    }
}
