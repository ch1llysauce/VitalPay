package vitalpay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VitalPayAdmin extends JFrame implements ActionListener {

    // Declare instance variables for buttons
    private JButton searchBtn;
    private JButton registerBtn;
    private JButton reportBtn;
    private JButton addPatientBtn;
    private JButton logoutBtn;
    
    public VitalPayAdmin() {
        // Frame settings
        setTitle("VitalPay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize and add components
        add(createGradientLabel());
        add(createButtonPanel());
        add(createUserInfoPanel());

        setVisible(true);
    }

    
    private JLabel createGradientLabel() {
        JLabel gradientLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Set gradient
                GradientPaint gradient = new GradientPaint(0, 0, Color.RED, getWidth(), 0, Color.BLUE);
                g2d.setPaint(gradient);

                // Draw text
                g2d.setFont(new Font("Poppins", Font.BOLD, 30));
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.drawString("VitalPay", 0, 30);
            }
        };

        gradientLabel.setBounds(225, 20, 200, 50);
        return gradientLabel;
    }

    
    public JPanel createButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(5, 1, 10, 10));
        btnPanel.setBounds(50, 100, 150, 200);

        // Buttons
        searchBtn = createButton("Search");
        registerBtn = createButton("Register (Staff)");
        reportBtn = createButton("General Report");
        addPatientBtn = createButton("Add Patient");
        logoutBtn = createButton("Logout");

        // Action listeners
        registerBtn.addActionListener(this);
        addPatientBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        // Add buttons to panel
        btnPanel.add(searchBtn);
        btnPanel.add(registerBtn);
        btnPanel.add(reportBtn);
        btnPanel.add(addPatientBtn);
        btnPanel.add(logoutBtn);

        return btnPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
  
        if (e.getSource() == registerBtn) {
            StaffRegistration staffreg = new StaffRegistration();
            this.setVisible(false);
            staffreg.setVisible(true);
        }
        
        else if(e.getSource() == addPatientBtn){
            AddPatient addpatient = new AddPatient();
            this.setVisible(false);
            addpatient.setVisible(true);
        }
        
        else if(e.getSource() == logoutBtn){
            VitalPayLogin login = new VitalPayLogin();
            this.setVisible(false);
            login.setVisible(true);
        }
    }

    
    private JPanel createUserInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(115, 147, 179));
        infoPanel.setBounds(250, 100, 300, 135);
        infoPanel.setLayout(null);

        // Labels for user info
        JLabel unLabel = createLabel("Username", 20, 20);
        JLabel userTypeLabel = createLabel("UserType", 20, 70);
        JLabel userNameValue = createLabel("hotdog", 140, 20, Color.YELLOW);
        JLabel userTypeValue = createLabel("Admin", 140, 70, Color.YELLOW);

        // Add labels to panel
        infoPanel.add(unLabel);
        infoPanel.add(userTypeLabel);
        infoPanel.add(userNameValue);
        infoPanel.add(userTypeValue);

        return infoPanel;
    }

    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        return button;
    }

    
    private JLabel createLabel(String text, int x, int y) {
        return createLabel(text, x, y, Color.WHITE);
    }

    
    private JLabel createLabel(String text, int x, int y, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(x, y, 100, 30);
        return label;
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VitalPayAdmin::new);
    }
}
