import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipherGUI extends JFrame {
    private int shift = 3;
    private JPanel bottomPannel;
    private JRadioButton encryptButton;
    private JRadioButton decryptButton;
    private JButton actionButton;
    private JButton changeShiftButton;
    private JTextArea outputArea;
    private JTextArea inputArea;

    public CaesarCipherGUI() {
        setContentPane(bottomPannel);
        setTitle("Caesar Cipher GUI");
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        changeShiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValue = JOptionPane.showInputDialog(getContentPane(), "Enter the new shift value:", "Change Shift", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int newShift = Integer.parseInt(inputValue);
                    shift = newShift;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(getContentPane(), "Invalid shift value. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputArea.getText();
                StringBuilder outputText = new StringBuilder();

                if (encryptButton.isSelected()) {
                    for (char c : inputText.toCharArray()) {
                        if (Character.isLetter(c)) {
                            char base = Character.isLowerCase(c) ? 'a' : 'A';
                            outputText.append((char) (((c - base + shift) % 26) + base));
                        } else {
                            outputText.append(c);
                        }
                    }
                } else if (decryptButton.isSelected()) {
                    for (char c : inputText.toCharArray()) {
                        if (Character.isLetter(c)) {
                            char base = Character.isLowerCase(c) ? 'a' : 'A';
                            outputText.append((char) (((c - base - shift + 26) % 26) + base));
                        } else {
                            outputText.append(c);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "Please select Encrypt or Decrypt.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                outputArea.setText(outputText.toString());
            }
        });
    }

    public static void main(String[] args) {
        CaesarCipherGUI myGUI = new CaesarCipherGUI();
    }
}
