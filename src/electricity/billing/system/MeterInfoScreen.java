package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfoScreen extends JFrame implements ActionListener {
    JTextField meterNumberField;
    Choice meterLocations;
    Choice meterTypes;
    Choice phaseCodes;
    Choice billTypes;
    JButton submitButton;
    String meterNumber;
    MeterInfoScreen(String meterNumber) {
        super("Meter Info");

        this.meterNumber = meterNumber;
        //create new panel
        JPanel newMeterInfoPanel = UIElementsCreateHelper.createNewPanel(new Color(252, 186,3));

        //add heading
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewHeadingLabel("Meter Information", 180, 10,200,20,15));

        //add meter number input field
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("Meter Number", 50, 80, 100, 20));
        meterNumberField = UIElementsCreateHelper.createNewInputTextField(180, 80, 150, 20);
        meterNumberField.setText(meterNumber);
        newMeterInfoPanel.add(meterNumberField);

        //add meter location input field
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("Meter Location", 50, 120, 100, 20));
        String[] meterLocationChoices = {"Outside", "Inside"};
        meterLocations = UIElementsCreateHelper.createChoicePanel(meterLocationChoices, 180, 120, 150, 20);
        newMeterInfoPanel.add(meterLocations);

        //add meter type field
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("Meter Type", 50, 160, 100, 20));
        String[] meterTypeChoices = {"Electric Meter", "Solar Meter", "Smart Meter"};
        meterTypes = UIElementsCreateHelper.createChoicePanel(meterTypeChoices, 180, 160, 150, 20);
        newMeterInfoPanel.add(meterTypes);

        //add phase code field
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("Phase", 50, 200, 100, 20));
        String[] phaseCodeChoices = {"011", "022", "033", "044", "055", "066", "077", "088", "099"};
        phaseCodes = UIElementsCreateHelper.createChoicePanel(phaseCodeChoices, 180, 200, 150, 20);
        newMeterInfoPanel.add(phaseCodes);

        //add bill type field
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("Bill Type", 50, 240, 100, 20));
        String[] billTypeChoices = {"Normal", "Industrial"};
        billTypes = UIElementsCreateHelper.createChoicePanel(billTypeChoices, 180, 240, 150, 20);
        newMeterInfoPanel.add(billTypes);

        //add billing time info
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("30 Days Billing Time...", 50, 280, 150, 20));
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("Note:", 50, 320, 100, 20));
        newMeterInfoPanel.add(UIElementsCreateHelper.createNewInputLabel("By default bill is calculated for 30 days only", 50, 340, 300, 20));

        //add submit button
        submitButton = UIElementsCreateHelper.createNewButton("Submit", 220, 390, 100, 25);
        submitButton.addActionListener(this);
        newMeterInfoPanel.add(submitButton);

        setLayout(new BorderLayout());
        add(newMeterInfoPanel, "Center");

        //add new customer screen image
        RawIconConverter newMeterInfoScreenIcon = new RawIconConverter(0, 0, 230, 200, "icon/details.png");
        add(newMeterInfoScreenIcon.convertIcons(), "East");

        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton)
        {
            String meterNumber = meterNumberField.getText();
            String meterLocation = meterLocations.getSelectedItem();
            String meterType = meterTypes.getSelectedItem();
            String phaseCode = phaseCodes.getSelectedItem();
            String billType = billTypes.getSelectedItem();
            String billDays = "30";

            DataSource.addMeter(meterNumber, meterLocation, meterType, phaseCode, billType, billDays);

            JOptionPane.showMessageDialog(null,"Meter Information Submited Successfully");
            setVisible(false);
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfoScreen("");
    }
}
