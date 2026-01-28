import java.awt.*;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;

/*
 * FRONTEND CLASS
 * Handles GUI and user interaction
 */
public class PasswordGeneratorUI extends JFrame {

    private final JCheckBox upperCaseBox, lowerCaseBox, numbersBox, symbolsBox;
    private JSlider lengthSlider;
    private final JTextField passwordField;
    private final JLabel strengthLabel, lengthLabel;

    public PasswordGeneratorUI() {
        setTitle("Password Generator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel(new GridLayout(11, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Password field
        panel.add(new JLabel("Generated Password:"));
        passwordField = new JTextField();
        passwordField.setEditable(false);
        panel.add(passwordField);

        // Length slider
        lengthLabel = new JLabel("Password Length: 12");
        lengthSlider = new JSlider(4, 32, 12);
        lengthSlider.setMajorTickSpacing(4);
        lengthSlider.setPaintTicks(true);
        lengthSlider.addChangeListener(e -> lengthLabel.setText("Password Length: " + lengthSlider.getValue()));

        panel.add(lengthLabel);
        panel.add(lengthSlider);

        // Checkboxes
        upperCaseBox = new JCheckBox("Uppercase (A-Z)");
        lowerCaseBox = new JCheckBox("Lowercase (a-z)", true);
        numbersBox = new JCheckBox("Numbers (0-9)");
        symbolsBox = new JCheckBox("Symbols (!@#$%)");

        panel.add(upperCaseBox);
        panel.add(lowerCaseBox);
        panel.add(numbersBox);
        panel.add(symbolsBox);

        // Buttons
        JButton generateBtn = new JButton("Generate Password");
        JButton copyBtn = new JButton("Copy to Clipboard");

        panel.add(generateBtn);
        panel.add(copyBtn);

        // Strength label
        strengthLabel = new JLabel("Strength: ");
        panel.add(strengthLabel);

        add(panel);

        // Generate button action
        generateBtn.addActionListener(e -> generatePassword());

        // Copy button action
        copyBtn.addActionListener(e -> copyPassword());

        setVisible(true);
    }

    private void generatePassword() {
        String password = PasswordService.generatePassword(
                lengthSlider.getValue(),
                upperCaseBox.isSelected(),
                lowerCaseBox.isSelected(),
                numbersBox.isSelected(),
                symbolsBox.isSelected()
        );

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Select at least one character option!");
            return;
        }

        passwordField.setText(password);
        strengthLabel.setText("Strength: " +
                PasswordService.checkStrength(password));
    }

    private void copyPassword() {
        String password = passwordField.getText();

        if (!password.isEmpty()) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(password), null);

            JOptionPane.showMessageDialog(this, "Password copied!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGeneratorUI());
    }
}
