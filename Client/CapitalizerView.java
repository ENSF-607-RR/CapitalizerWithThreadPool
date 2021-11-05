package Client;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CapitalizerView extends JFrame {

    private JTextField inputField = new JTextField(20);
    private JTextField outputField = new JTextField(20);
    private JButton capitalizeButton = new JButton("Capitalize");

    public CapitalizerView(){
        JPanel capitalizerPanel = new JPanel();

        capitalizerPanel.setSize(300, 300);
        capitalizerPanel.add(inputField);
        capitalizerPanel.add(capitalizeButton);
        capitalizerPanel.add(outputField);

        this.add(capitalizerPanel);

    }

    public String getInputField() {
        return inputField.getText();
    }

    public void setOutputField(String text) {
        outputField.setText(text);
    }

    public void addCapitalizeButton(ActionListener listenForCapitalize){
        capitalizeButton.addActionListener(listenForCapitalize);
    }

}
