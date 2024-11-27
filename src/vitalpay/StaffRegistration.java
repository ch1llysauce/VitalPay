package vitalpay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StaffRegistration extends JFrame {

    private JTextField firstname;
    private JTextField lastname;
    private JTextField address;
    private JComboBox<String> role;
    private JButton savedetails;
    private JButton backBtn;
    private JTable staffTable;
    private DefaultTableModel tableModel;

    public StaffRegistration() {
        setTitle("Vital Pay - Staff Registration");
        setSize(800, 400);
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
        String[] roles = {"Staff", "Nurse", "Doctor"};
        role = new JComboBox<>(roles);
        role.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(role);

        add(inputPanel, BorderLayout.WEST);

        // Table Panel (Right Side)
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table Setup
        String[] columnNames = {"First Name", "Last Name", "Address", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        staffTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(staffTable);

        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

        // Save Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        savedetails = new JButton("Save Details");
        savedetails.setPreferredSize(new Dimension(150, 40));
        savedetails.setFocusable(false);
        
        backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setFocusable(false);
        
        buttonPanel.add(savedetails);
        buttonPanel.add(backBtn);
        
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listener for Save Button
        savedetails.addActionListener(e -> {
            String firstName = firstname.getText().trim();
            String lastName = lastname.getText().trim();
            String addressText = address.getText().trim();
            String selectedRole = (String) role.getSelectedItem();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !addressText.isEmpty()) {
                boolean isDuplicate = false;

                // Check for duplicate entry
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String existingFirstName = tableModel.getValueAt(i, 0).toString();
                    String existingLastName = tableModel.getValueAt(i, 1).toString();
                    String existingAddress = tableModel.getValueAt(i, 2).toString();
                    String existingRole = tableModel.getValueAt(i, 3).toString();

                    if (firstName.equalsIgnoreCase(existingFirstName)
                            && lastName.equalsIgnoreCase(existingLastName)
                            && addressText.equalsIgnoreCase(existingAddress)
                            && selectedRole.equalsIgnoreCase(existingRole)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate) {
                    JOptionPane.showMessageDialog(this,
                            "Staff member is already registered.",
                            "Duplicate Entry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Add data to the table
                    tableModel.addRow(new Object[]{firstName, lastName, addressText, selectedRole});

                    // Confirmation Dialog
                    int option = JOptionPane.showConfirmDialog(this,
                            "Successfully Registered!\nDo you want to register another staff?",
                            "Registration Successful",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);

                    if (option == JOptionPane.NO_OPTION) {
                        this.setVisible(false);
                        new VitalPayAdmin().setVisible(true);
                    } else {
                        // Clear fields for next registration
                        firstname.setText("");
                        lastname.setText("");
                        address.setText("");
                        role.setSelectedIndex(0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please fill all the fields",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Action Listener for Back Button
        backBtn.addActionListener(e -> {
            if(e.getSource()==backBtn){
                this.setVisible(false);
                new VitalPayAdmin().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StaffRegistration().setVisible(true));
    }
}


