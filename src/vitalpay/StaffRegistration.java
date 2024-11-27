package vitalpay;

import javax.swing.*;
import java.awt.*;

public class StaffRegistration extends JFrame {

    private JTextField firstname;
    private JTextField lastname;
    private JTextField address;
    private JComboBox<String> role;
    private JButton savedetails;

    public StaffRegistration() {
        setTitle("Vital Pay");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("VitalPay", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(0x1961dd));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);

        titlePanel.add(Box.createVerticalStrut(10));

        JLabel staffRegLabel = new JLabel("Staff Registration", SwingConstants.CENTER);
        staffRegLabel.setFont(new Font("Arial", Font.BOLD, 20));
        staffRegLabel.setForeground(new Color(0x1961dd));
        staffRegLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(staffRegLabel);

        add(titlePanel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // First Name
        inputPanel.add(new JLabel("First Name:", SwingConstants.LEFT));
        firstname = new JTextField();
        firstname.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(firstname);

        // Last Name
        inputPanel.add(new JLabel("Last Name:", SwingConstants.LEFT));
        lastname = new JTextField();
        lastname.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(lastname);

        // Address
        inputPanel.add(new JLabel("Street Address:", SwingConstants.LEFT));
        address = new JTextField();
        address.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(address);

        // Role
        inputPanel.add(new JLabel("Role:", SwingConstants.LEFT));
        String[] roles = {"Nigga", "Nurse", "Doctor"};
        role = new JComboBox<>(roles);
        role.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(role);

        add(inputPanel, BorderLayout.CENTER);

        // Save Detail Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        savedetails = new JButton("Save Details");
        savedetails.setPreferredSize(new Dimension(150, 40));
        savedetails.setAlignmentX(Component.CENTER_ALIGNMENT);
        savedetails.setFocusable(false);
        buttonPanel.add(savedetails);

        add(buttonPanel, BorderLayout.SOUTH);
        savedetails.addActionListener(e -> {
            String firstName = firstname.getText().trim();
            String lastName = lastname.getText().trim();
            String addressText = address.getText().trim();
            String selectedRole = (String) role.getSelectedItem();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !addressText.isEmpty()) {
                String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
                String password = "password123"; // Default password

                if (StaffData.staffExists(username)) {
                    JOptionPane.showMessageDialog(this,
                            "A staff member with this username already exists.\nPlease use a different name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Save staff details
                    Staff newStaff = new Staff(username, password, selectedRole);
                    StaffData.addStaff(username, password, selectedRole);

                    JOptionPane.showMessageDialog(this,
                            "Successfully Registered\nUsername: " + username + "\nPassword: " + password,
                            "Staff Registration",
                            JOptionPane.INFORMATION_MESSAGE);

                    this.setVisible(false);
                    VitalPayStaff staffFrame = new VitalPayStaff(newStaff);
                    staffFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please fill all the fields",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StaffRegistration().setVisible(true));
    }
}
