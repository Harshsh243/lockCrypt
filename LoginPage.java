import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class LoginPage {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        frame.setUndecorated(false); // Keep the title bar and borders
        frame.setLocationRelativeTo(null);
        
        // Create a panel with a gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Gradient background
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Title label
        JLabel titleLabel = new JLabel("Welcome Back!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        // Login ID
        JLabel loginIdLabel = new JLabel("Login ID:");
        JTextField loginIdField = new JTextField(20);
        styleTextField(loginIdField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        styleTextField(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        styleTextField(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        styleButton(loginButton);

        // Add action listener to the button
        loginButton.addActionListener((ActionEvent e) -> {
            String loginId = loginIdField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            // Simple validation
            if (!loginId.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                openWelcomePage();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; panel.add(titleLabel, gbc);
        gbc.gridwidth = 1; gbc.gridy = 1; panel.add(loginIdLabel, gbc);
        gbc.gridx = 1; panel.add(loginIdField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(emailLabel, gbc);
        gbc.gridx = 1; panel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(passwordLabel, gbc);
        gbc.gridx = 1; panel.add(passwordField, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(loginButton, gbc);

        // Add panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBackground(new Color(255, 255, 255, 200));
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        textField.setPreferredSize(new Dimension(300, 40));
        textField.setMargin(new Insets(5, 5, 5, 5));
    }

    private static void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(100, 40));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private static void openWelcomePage() {
    try {
        // Use a correctly escaped path
        File htmlFile = new File("C:\\Users\\SUYASH\\Desktop\\gg\\welcome.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    } catch (IOException e) {
        e.printStackTrace(); // Print stack trace for debugging
    }
}


}
