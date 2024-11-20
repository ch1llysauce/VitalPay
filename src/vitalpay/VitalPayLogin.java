package vitalpay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VitalPayLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    public VitalPayLogin(){
        //Frame Settings
        setTitle("Vital Pay");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        
        JLabel title = new JLabel("VitalPay Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(0x1961dd));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        title.setPreferredSize(new Dimension(400, 150)); // Adjust this based on your needs
        add(title, BorderLayout.NORTH);
        
        //Creating a Panel and its Settings
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Labels and Field: Username and Password
        inputPanel.add(new JLabel("Username", SwingConstants.LEFT));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Password", SwingConstants.LEFT));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        add(inputPanel, BorderLayout.CENTER);

        //Adding Login Button to Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 100));
        loginButton = new JButton("Login");
        loginButton.setFocusable(false);

        buttonPanel.add(loginButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    
    
    public static void main(String[] args){
        VitalPayLogin login = new VitalPayLogin();
        
        login.addLoginListener(e -> {
            String username = login.getUsername();
            String password = login.getPassword();
            
            if ("admin".equals(username) && "1234".equals(password)) {
                JOptionPane.showMessageDialog(login, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                login.setVisible(false);
                
                LevelofAccess loa = new LevelofAccess();
                loa.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(login, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        login.setVisible(true);
    }

}
