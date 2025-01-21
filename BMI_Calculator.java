import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMI_Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JPanel inputPan = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel heightL = new JLabel("               Height (cm):");
        JTextField heightField = new JTextField();
        JLabel weightL = new JLabel("               Weight (kg):");
        JTextField weightField = new JTextField();
        JLabel resultL = new JLabel("               BMI:");
        JLabel resultValue = new JLabel("-");

        inputPan.add(heightL);
        inputPan.add(heightField);
        inputPan.add(weightL);
        inputPan.add(weightField);
        inputPan.add(resultL);
        inputPan.add(resultValue);


        JPanel buttonPan = new JPanel(new FlowLayout());

        JButton calculate = new JButton("Calculate");
        JButton clear = new JButton("Clear");
        JButton exit = new JButton("Exit");

        buttonPan.add(calculate);
        buttonPan.add(clear);
        buttonPan.add(exit);
        frame.add(inputPan, BorderLayout.CENTER);
        frame.add(buttonPan, BorderLayout.SOUTH);

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightField.getText()) / 100; // Convert cm to meters
                    double weight = Double.parseDouble(weightField.getText());
                    double bmi = weight / (height * height);
                    String classification;
                    if (bmi < 18.5) {
                        classification = "Underweight";
                    } else if (bmi < 24.9) {
                        classification = "Normal weight";
                    } else if (bmi < 29.9) {
                        classification = "Overweight";
                    } else {
                        classification = "Obese";
                    }
                    resultValue.setText(String.format("%.2f (%s)", bmi, classification));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for height and weight.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heightField.setText("");
                weightField.setText("");
                resultValue.setText("-");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
