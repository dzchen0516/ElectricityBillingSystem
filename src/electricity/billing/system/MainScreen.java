package electricity.billing.system;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainScreen extends JFrame implements ActionListener {
    String userType;
    String meterNum;
    JMenuItem newCustomer;
    JMenuItem customerDetails;
    JMenuItem depositDetails;
    JMenuItem calculateBills;
    JMenuItem updateInformation;
    JMenuItem viewInformation;
    JMenuItem payBill;
    JMenuItem billDetails;
    JMenuItem generateBill;
    JMenuItem notepad;
    JMenuItem calculator;
    JMenuItem exit;
    MainScreen(String userType, String meterNum) {
        this.userType = userType;
        this.meterNum = meterNum;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //main screen background
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();
        RawIconConverter mainScreenBackground = new RawIconConverter(0, 0, (int)bounds.getWidth(), (int)bounds.getHeight(), "icon/main-screen-background.png");
        add(mainScreenBackground.convertIcons());

        //create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //create all the menus
        if(userType.equals("Admin"))
        {
            createOperationMenu(menuBar);
        }
        else
        {
            createBillMenu(menuBar);

            createUserMenu(menuBar);

            createInfoMenu(menuBar);
        }

        createUtilMenu(menuBar);
        createExitMenu(menuBar);


        setLayout(new FlowLayout());
        setVisible(true);
    }

    private void createOperationMenu(JMenuBar menuBar)
    {
        JMenu operationMenu = UIElementsCreateHelper.createNewMenu("Menu");
        menuBar.add(operationMenu);

        newCustomer = UIElementsCreateHelper.createNewMenuItem("New Customer", "icon/newcustomer.png");
        customerDetails = UIElementsCreateHelper.createNewMenuItem("Customer Details", "icon/customerDetails.png");
        depositDetails = UIElementsCreateHelper.createNewMenuItem("Deposit Details", "icon/depositdetails.png");
        calculateBills = UIElementsCreateHelper.createNewMenuItem("Calculate Bills", "icon/calculatebills.png");

        newCustomer.addActionListener(this);
        customerDetails.addActionListener(this);
        depositDetails.addActionListener(this);
        calculateBills.addActionListener(this);

        operationMenu.add(newCustomer);
        operationMenu.add(customerDetails);
        operationMenu.add(depositDetails);
        operationMenu.add(calculateBills);
    }

    private void createInfoMenu(JMenuBar menuBar)
    {
        JMenu infoMenu = UIElementsCreateHelper.createNewMenu("Information");
        menuBar.add(infoMenu);

        updateInformation = UIElementsCreateHelper.createNewMenuItem("Update Information", "icon/refresh.png");
        viewInformation = UIElementsCreateHelper.createNewMenuItem("View Information", "icon/information.png");

        updateInformation.addActionListener(this);
        viewInformation.addActionListener(this);

        infoMenu.add(updateInformation);
        infoMenu.add(viewInformation);
    }

    private void createUserMenu(JMenuBar menuBar)
    {
        JMenu userMenu = UIElementsCreateHelper.createNewMenu("User");
        menuBar.add(userMenu);

        payBill = UIElementsCreateHelper.createNewMenuItem("Pay Bill", "icon/pay.png");
        billDetails = UIElementsCreateHelper.createNewMenuItem("Bill Details", "icon/detail.png");

        payBill.addActionListener(this);
        billDetails.addActionListener(this);

        userMenu.add(payBill);
        userMenu.add(billDetails);
    }
    private void createBillMenu(JMenuBar menuBar)
    {
        JMenu billMenu = UIElementsCreateHelper.createNewMenu("Bill");
        menuBar.add(billMenu);

        generateBill = UIElementsCreateHelper.createNewMenuItem("Generate Bill", "icon/bill.png");
        generateBill.addActionListener(this);

        billMenu.add(generateBill);
    }

    private void createUtilMenu(JMenuBar menuBar)
    {
        JMenu utilMenu = UIElementsCreateHelper.createNewMenu("Utility");
        menuBar.add(utilMenu);

        notepad = UIElementsCreateHelper.createNewMenuItem("Notepad", "icon/notepad.png");
        calculator = UIElementsCreateHelper.createNewMenuItem("Calculator", "icon/calculator.png");

        notepad.addActionListener(this);
        calculator.addActionListener(this);

        utilMenu.add(notepad);
        utilMenu.add(calculator);
    }

    private void createExitMenu(JMenuBar menuBar)
    {
        JMenu exitMenu = UIElementsCreateHelper.createNewMenu("Exit");
        menuBar.add(exitMenu);

        exit = UIElementsCreateHelper.createNewMenuItem("Exit", "icon/exit.png");
        exit.addActionListener(this);

        exitMenu.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("New Customer"))
        {
            new NewCustomerScreen();
        }
        else if (action.equals("Customer Details"))
        {
            new CustomerDetailsScreen();
        }
        else if (action.equals("Deposit Details"))
        {
            new DepositDetailsScreen();
        }
        else if (action.equals("Calculate Bills"))
        {
            new CalculateBillScreen();
        }
        else if (action.equals("View Information"))
        {
            new ViewInformationScreen(meterNum);
        }
        else if (action.equals("Update Information"))
        {
            new UpdateInformationScreen(meterNum);
        }
        else if (action.equals("Bill Details"))
        {
            new BillDetailsScreen(meterNum);
        }
        else if (action.equals("Calculator"))
        {
            try {
                Process p = new ProcessBuilder("open", "/System/Applications/Calculator.app").start();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        else if (action.equals("Notepad"))
        {
            try {
                Process p = new ProcessBuilder("open", "/System/Applications/TextEdit.app").start();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        else if (action.equals("Exit"))
        {
            setVisible(false);
            new LoginScreen();
        }
        else if (action.equals("Pay Bill"))
        {
            new PayBillScreen(meterNum);
        }
        else if (action.equals("Generate Bill"))
        {
            new GenerateBillScreen(meterNum);
        }
    }

    public static void main(String[] args) {
        new MainScreen("", "");
    }
}
