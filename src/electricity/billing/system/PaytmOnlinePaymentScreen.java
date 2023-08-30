package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaytmOnlinePaymentScreen extends JFrame implements ActionListener {
    String meterNum;
    JButton backButton;
    PaytmOnlinePaymentScreen(String meterNum) {
        this.meterNum = meterNum;
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try
        {
            editorPane.setPage("https://paytm.com/online-payments");
            editorPane.setBounds(400,150,800,600);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            editorPane.setContentType("text/html");
            editorPane.setContentType("<html>Error!</html>");
        }

        JScrollPane newPane = new JScrollPane(editorPane);
        add(newPane);

        backButton = UIElementsCreateHelper.createNewButton("Back", 640,20,80,30);
        backButton.addActionListener(this);
        editorPane.add(backButton);

        setSize(800,600);
        setLocation(400,150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PayBillScreen(meterNum);
    }

    public static void main(String[] args) {
        new PaytmOnlinePaymentScreen("");
    }
}
