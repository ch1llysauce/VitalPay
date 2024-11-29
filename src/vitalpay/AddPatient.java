package vitalpay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatient extends JFrame implements ActionListener {

    private JButton nextBtn, backBtn, searchBtn, saveBtn, closeBtn;
    private JLabel tLabel, idLabel, fnLabel, mnLabel, snLabel, numbLabel, dobLabel, addressLabel, bgLabel, genLabel, disLabel, symptomsLabel, diagnosisLabel, medicinesLabel, wardLabel, wardTypeLabel;
    private JTextField fnField, mnField, snField, numbField, dobField, addressField, idField, bgField, disField, symptomsField, diagnosisField, medicinesField;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JCheckBox wardCheckBox;
    private JComboBox<String> wardTypeComboBox;
    private JTable patientTable;
    private DefaultTableModel tableModel;

    public AddPatient() {
        // Create the main frame
        setTitle("New Patient Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize and add components
        initializeComponents();
        add(createButtonPanel());

        setVisible(true);
    }

    private void initializeComponents() {
        // Title label
        tLabel = new JLabel("NEW PATIENT FORM");
        tLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        tLabel.setBounds(290, 30, 300, 30);
        add(tLabel);


        // Table for patient details
        String[] columns = {"Patient ID", "First Name", "Middle Name", "Last Name",  "Contact Number", "Age", "Gender", "Blood Group", "Address", "Major Disease"};
        tableModel = new DefaultTableModel(columns, 0);
        patientTable = new JTable(tableModel);
        patientTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane(patientTable);
        tableScrollPane.setBounds(30, 100, 720, 150);
        add(tableScrollPane);

        // First name
        fnLabel = new JLabel("First Name:");
        fnLabel.setBounds(50, 270, 100, 20);
        add(fnLabel);

        fnField = new JTextField();
        fnField.setBounds(150, 270, 150, 25);
        add(fnField);

        // Middle name
        mnLabel = new JLabel("Middle Name:");
        mnLabel.setBounds(50, 310, 100, 20);
        add(mnLabel);

        mnField = new JTextField();
        mnField.setBounds(150, 310, 150, 25);
        add(mnField);

        // Surname
        snLabel = new JLabel("Last Name:");
        snLabel.setBounds(50, 350, 100, 20);
        add(snLabel);

        snField = new JTextField();
        snField.setBounds(150, 350, 150, 25);
        add(snField);

        // Phone number
        numbLabel = new JLabel("Phone Number:");
        numbLabel.setBounds(50, 390, 100, 20);
        add(numbLabel);

        numbField = new JTextField();
        numbField.setBounds(150, 390, 150, 25);
        add(numbField);

        // Date of Birth
        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 430, 100, 20);
        add(dobLabel);

        dobField = new JTextField("YYYY-MM-DD");
        dobField.setBounds(150, 430, 150, 25);
        add(dobField);

        // Address
        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 470, 100, 20);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 470, 150, 25);
        add(addressField);

        // Blood Group
        bgLabel = new JLabel("Blood Group:");
        bgLabel.setBounds(50, 510, 100, 20);
        add(bgLabel);

        bgField = new JTextField();
        bgField.setBounds(150, 510, 150, 25);
        add(bgField);

        // Disease
        disLabel = new JLabel("Major Diseases (if any):");
        disLabel.setBounds(50, 550, 200, 20);
        add(disLabel);

        disField = new JTextField();
        disField.setBounds(50, 580, 250, 50);
        add(disField);

        // Gender
        genLabel = new JLabel("Gender:");
        genLabel.setBounds(50, 640, 100, 20);
        add(genLabel);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(150, 640, 70, 20);
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(220, 640, 80, 20);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        add(maleRadio);
        add(femaleRadio);

        // Symptoms
        symptomsLabel = new JLabel("Symptoms:");
        symptomsLabel.setBounds(400, 270, 100, 20);
        add(symptomsLabel);

        symptomsField = new JTextField();
        symptomsField.setBounds(500, 270, 200, 25);
        add(symptomsField);

        // Diagnosis
        diagnosisLabel = new JLabel("Diagnosis:");
        diagnosisLabel.setBounds(400, 310, 100, 20);
        add(diagnosisLabel);

        diagnosisField = new JTextField();
        diagnosisField.setBounds(500, 310, 200, 25);
        add(diagnosisField);

        // Medicines
        medicinesLabel = new JLabel("Medicines:");
        medicinesLabel.setBounds(400, 350, 100, 20);
        add(medicinesLabel);

        medicinesField = new JTextField();
        medicinesField.setBounds(500, 350, 200, 25);
        add(medicinesField);

        // Ward required checkbox
        wardLabel = new JLabel("Ward Required?");
        wardLabel.setBounds(400, 390, 150, 25);
        add(wardLabel);

        wardCheckBox = new JCheckBox("YES");
        wardCheckBox.setBounds(550, 390, 50, 25);
        wardCheckBox.addActionListener(this);
        add(wardCheckBox);

        // Type of Ward (Initially hidden)
        wardTypeLabel = new JLabel("Type of Ward:");
        wardTypeLabel.setBounds(400, 430, 150, 25);
        wardTypeLabel.setVisible(false);
        add(wardTypeLabel);

        wardTypeComboBox = new JComboBox<>(new String[]{"General", "Single", "Duo"});
        wardTypeComboBox.setBounds(550, 430, 150, 25);
        wardTypeComboBox.setVisible(false);
        add(wardTypeComboBox);
    }
    // Button Panel (Next and Back)
    private JPanel createButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 2, 10, 10));
        btnPanel.setBounds(265, 700, 250, 40);

        // Buttons
        nextBtn = createButton("Next");
        backBtn = createButton("Back");

        // Action listeners
        backBtn.addActionListener(this);

        // Add buttons to the panel
        btnPanel.add(nextBtn);
        btnPanel.add(backBtn);

        return btnPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            this.setVisible(false);
            new VitalPayAdmin().setVisible(true);
        } else if (e.getSource() == wardCheckBox) {
            // Toggle visibility of ward type
            boolean isChecked = wardCheckBox.isSelected();
            wardTypeLabel.setVisible(isChecked);
            wardTypeComboBox.setVisible(isChecked);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddPatient::new);
    }
}
