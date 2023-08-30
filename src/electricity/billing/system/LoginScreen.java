package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener {
    JTextField usernameField;
    JTextField passwordField;
    Choice loginRoleField;
    JButton loginButton;
    JButton cancelButton;
    JButton signupButton;
    LoginScreen() {
        super("Login");
        getContentPane().setBackground(Color.white);

        //user name field
        add(UIElementsCreateHelper.createNewInputLabel("UserName", 300, 60, 100, 20));
        usernameField = UIElementsCreateHelper.createNewInputTextField(400, 60, 150, 20);
        add(usernameField);

        //password field
        add(UIElementsCreateHelper.createNewInputLabel("Password", 300, 100, 100, 20));
        passwordField = UIElementsCreateHelper.createNewInputTextField(400, 100, 150, 20);
        add(passwordField);

        //login role field
        add(UIElementsCreateHelper.createNewInputLabel("Logging In As", 300, 140, 100, 20));
        String[] roleList = {"Admin", "Customer"};
        loginRoleField = UIElementsCreateHelper.createChoicePanel(roleList, 400, 140, 150,20);
        add(loginRoleField);

        //login button
        loginButton = UIElementsCreateHelper.createNewButton("Login", 330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        //cancel button
        cancelButton = UIElementsCreateHelper.createNewButton("Cancel", 460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        //signup button
        signupButton = UIElementsCreateHelper.createNewButton("Sign Up", 400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        //login screen img
        RawIconConverter loginScreenImg = new RawIconConverter(0,0,250, 250, "icon/profile.png");
        add(loginScreenImg.convertIcons());

        setSize(640, 300);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton)
        {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String userRole = loginRoleField.getSelectedItem();

            if(Authenticator.login(username, password, userRole))
            {
                String[] userInfo = DataSource.getUserByName(username);
                setVisible(false);
                new MainScreen(userRole, userInfo[2]);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Login");
            }

        }
        else if(e.getSource() == cancelButton)
        {
            setVisible(false);
        }
        else if(e.getSource() == signupButton)
        {
            setVisible(false);
            new SignupScreen();
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
