package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImagingOpException;

public class UIElementsCreateHelper {
    public static JLabel createNewInputLabel(String labelName, int x, int y, int width, int height)
    {
        JLabel newLabel = new JLabel(labelName);
        newLabel.setBounds(x,y,width,height);
        return newLabel;
    }

    public static JTextField createNewInputTextField(int x, int y, int width, int height)
    {
        JTextField newField = new JTextField();
        newField.setBounds(x,y,width,height);
        return newField;
    }

    public static JButton createNewButton(String buttonName, int x, int y, int width, int height)
    {
        JButton newButton = new JButton(buttonName);
        newButton.setBounds(x,y, width,height);
        return newButton;
    }

    public static Choice createChoicePanel(String[] choiceList, int x, int y, int width, int height)
    {
        Choice choicePanel = new Choice();
        for(String choice : choiceList)
        {
            choicePanel.add(choice);
        }
        choicePanel.setBounds(x,y, width,height);
        return choicePanel;
    }

    public static JMenu createNewMenu(String title)
    {
        JMenu newMenu = new JMenu(title);
        newMenu.setFont(new Font("serif", Font.PLAIN, 15));
        return newMenu;
    }

    public static JMenuItem createNewMenuItem(String title, String iconPath)
    {
        JMenuItem newItem = new JMenuItem(title);
        newItem.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon menuIConRaw = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image menuIconImage = menuIConRaw.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        newItem.setIcon(new ImageIcon(menuIconImage));
        return newItem;
    }

    public static JPanel createNewPanel(Color backgroundColor)
    {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setBackground(backgroundColor);
        return newPanel;
    }

    public static JLabel createNewHeadingLabel(String heading, int x, int y, int width, int height, int fontSize)
    {
        JLabel newHeadingLabel = new JLabel(heading);
        newHeadingLabel.setBounds(x,y,width,height);
        newHeadingLabel.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        return newHeadingLabel;
    }

    public static JTextArea createNewTextArea(int rows, int cols, String text)
    {
        JTextArea newTextArea = new JTextArea(rows, cols);
        newTextArea.setText(text);
        newTextArea.setFont(new Font("Sanserif", Font.ITALIC,15));

        return newTextArea;
    }

}
