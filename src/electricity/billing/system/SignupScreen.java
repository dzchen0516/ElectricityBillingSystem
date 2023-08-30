package electricity.billing.system;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;

public class SignupScreen extends JFrame implements ActionListener {
    Choice roleChoiceField;
    JTextField meterField;
    JTextField employerID;
    JTextField usernameField;
    JTextField nameField;
    JTextField passwordField;
    JLabel meterNumberLabel;
    JLabel employerIDLabel;
    JButton createButton;
    JButton backButton;
    SignupScreen() {
        super("Sign Up");
        getContentPane().setBackground(new Color(168, 203, 255));

        //role choice
        add(UIElementsCreateHelper.createNewInputLabel("Create Account As", 30, 50, 125,20));
        String[] roleList = {"Admin", "Customer"};
        roleChoiceField = UIElementsCreateHelper.createChoicePanel(roleList, 170, 50, 125, 20);
        add(roleChoiceField);

        //meter field, invisible if the role is admin
        meterNumberLabel = UIElementsCreateHelper.createNewInputLabel("Meter Number", 30, 100, 125, 20);
        meterField = UIElementsCreateHelper.createNewInputTextField(170, 100, 125,20);
        meterNumberLabel.setVisible(false);
        meterField.setVisible(false);
        add(meterNumberLabel);
        add(meterField);

        //employer ID
        employerIDLabel = UIElementsCreateHelper.createNewInputLabel("Employer ID", 30, 100, 125, 20);
        add(employerIDLabel);
        employerID = UIElementsCreateHelper.createNewInputTextField(170, 100, 125,20);
        add(employerID);

        //user name
        add(UIElementsCreateHelper.createNewInputLabel("Username", 30, 140, 125, 20));
        usernameField = UIElementsCreateHelper.createNewInputTextField(170, 140, 125,20);
        add(usernameField);

        //name
        add(UIElementsCreateHelper.createNewInputLabel("Name", 30, 180, 125, 20));
        nameField = UIElementsCreateHelper.createNewInputTextField(170, 180, 125,20);
        add(nameField);

        //pop up username for meter num that's already sign up in the system
        //so that we can disable user to dit the user name again
        addMeterNumFieldListener(meterField, nameField);

        //password
        add(UIElementsCreateHelper.createNewInputLabel("Password", 30, 220, 125, 20));
        passwordField = UIElementsCreateHelper.createNewInputTextField(170, 220, 125,20);
        add(passwordField);

        //add listener to the choice panel
        addChoicePanelListener(roleChoiceField, meterNumberLabel, meterField, employerIDLabel, employerID, nameField);

        //create button
        createButton = UIElementsCreateHelper.createNewButton("Create", 50, 285, 100, 25);
        createButton.addActionListener(this);
        add(createButton);

        //back button
        backButton = UIElementsCreateHelper.createNewButton("Back", 180, 285, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        //sign up screen img
        RawIconConverter signupScreenImg = new RawIconConverter(350,50,220, 220, "icon/sign-up.png");
        add(signupScreenImg.convertIcons());

        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton)
        {
            String meter = meterField.getText();
            String username = usernameField.getText();
            String name = nameField.getText();
            String password = passwordField.getText();
            String role = roleChoiceField.getSelectedItem();

            try
            {
                //create new account if it's admin, update old account if it's customer
                if(roleChoiceField.getSelectedItem().equals("Admin"))
                {
                    DataSource.addUser(meter, username, name, password, role);
                }
                else
                {
                    DataSource.updateUser(meter, username, password, role);
                }

                JOptionPane.showMessageDialog(null, "Account Created");
                setVisible(false);
                new LoginScreen();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        else if(e.getSource() == backButton)
        {
            setVisible(false);
            new LoginScreen();
        }
    }

    private static void addMeterNumFieldListener(JTextField meterField, JTextField nameField)
    {
        meterField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String customerName = DataSource.getUserByMeterNum(meterField.getText());
                if(!customerName.isEmpty())
                {
                    nameField.setText(customerName);
                }
            }
        });
    }

    private static void addChoicePanelListener(Choice roleChoice, JLabel meterNumberLabel, JTextField meter,
                                               JLabel employeeIDLabel, JTextField employerID, JTextField nameField)
    {
        roleChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String role = roleChoice.getSelectedItem();
                if(role.equals("Customer"))
                {
                    employeeIDLabel.setVisible(false);
                    employerID.setVisible(false);
                    meterNumberLabel.setVisible(true);
                    meter.setVisible(true);
                    nameField.setEditable(false);
                }
                else
                {
                    employeeIDLabel.setVisible(true);
                    employerID.setVisible(true);
                    meterNumberLabel.setVisible(false);
                    meter.setVisible(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        new SignupScreen();
    }
}
