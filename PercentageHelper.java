package in.rm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageHelper extends JFrame {
    private JTextField inputValue, inputPercentage;
    private JComboBox<String> operationSelector;
    private JTextArea displayOutput;
    private JButton clearButton;

    public PercentageHelper() {
        setTitle("Percentage Calculator Tool");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.GRAY);

        inputValue = new JTextField(10);
        inputPercentage = new JTextField(10);
        operationSelector = new JComboBox<>(new String[]{
                "Find Percentage", "Increase by %", "Decrease by %", "Find Base Value"});
        JButton calculateButton = new JButton("Compute");
        clearButton = new JButton("Reset");
        displayOutput = new JTextArea(4, 30);
        displayOutput.setEditable(false);
        displayOutput.setBackground(Color.WHITE);

        calculateButton.setBackground(new Color(60, 120, 170));
        calculateButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(190, 40, 40));
        clearButton.setForeground(Color.WHITE);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCalculation();
            }
        });

        clearButton.addActionListener(e -> {
            inputValue.setText("");
            inputPercentage.setText("");
            displayOutput.setText("");
        });

        add(new JLabel("Input Value:"));
        add(inputValue);
        add(new JLabel("Input Percentage:"));
        add(inputPercentage);
        add(operationSelector);
        add(calculateButton);
        add(clearButton);
        add(new JScrollPane(displayOutput));

        setVisible(true);
    }

    private void executeCalculation() {
        try {
            double value = Double.parseDouble(inputValue.getText());
            double percentage = Double.parseDouble(inputPercentage.getText());
            String selectedOperation = (String) operationSelector.getSelectedItem();
            double computedResult;

            if (selectedOperation.equals("Find Percentage")) {
                computedResult = (value / percentage) * 100;
                displayOutput.setText("Result: " + String.format("%.2f", computedResult) + "%");
            } else if (selectedOperation.equals("Increase by %")) {
                computedResult = value + (value * (percentage / 100.0));
                displayOutput.setText("Increased Value: " + String.format("%.2f", computedResult));
            } else if (selectedOperation.equals("Decrease by %")) {
                computedResult = value - (value * (percentage / 100.0));
                displayOutput.setText("Decreased Value: " + String.format("%.2f", computedResult));
            } else if (selectedOperation.equals("Find Base Value")) {
                computedResult = (value * 100) / percentage;
                displayOutput.setText("Base Value: " + String.format("%.2f", computedResult));
            } else {
                displayOutput.setText("Please choose a valid operation.");
            }
        } catch (NumberFormatException ex) {
            displayOutput.setText("Error: Enter valid numeric inputs.");
        } catch (ArithmeticException ex) {
            displayOutput.setText("Error: Division by zero is not allowed.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PercentageHelper());
    }
}
