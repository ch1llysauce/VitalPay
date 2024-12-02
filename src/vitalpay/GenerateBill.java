package vitalpay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateBill extends JFrame implements ActionListener {
    private JTextField patientIdField, payField, balanceField, daysField;
    private JLabel totalPrescriptionLabel, totalDiagnosticsLabel, totalRoomChargesLabel, vatLabel, grossTotalLabel, coverageLabel, payableLabel;
    private JTable prescriptionTable, diagnosticsTable;
    private DefaultTableModel prescriptionTableModel, diagnosticsTableModel;
    private JComboBox<String> planComboBox, roomTypeComboBox;
    private JButton addDrugBtn, removeDrugBtn, addTestBtn, removeTestBtn, generateBillBtn, backBtn;

    private static final double VAT_RATE = 0.12;
    private static final int SILVER_COVERAGE = 100000;
    private static final int GOLD_COVERAGE = 150000;
    private static final int PLATINUM_COVERAGE = 200000;
    private static final int PLATINUMPLUS_COVERAGE = 250000;

    public GenerateBill() {
        setTitle("Generate Bill");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        // Patient Information Section
        JLabel patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(30, 20, 80, 25);
        add(patientIdLabel);

        patientIdField = new JTextField();
        patientIdField.setBounds(110, 20, 150, 25);
        add(patientIdField);

        JLabel patientNameLabel = new JLabel("Patient Name: <output>");
        patientNameLabel.setBounds(300, 20, 500, 25);
        add(patientNameLabel);


        // Plan Selection
        JLabel planLabel = new JLabel("Maxicare Plan:");
        planLabel.setBounds(30, 60, 100, 25);
        add(planLabel);

        planComboBox = new JComboBox<>(new String[]{"Silver", "Gold", "Platinum", "Platinum Plus"});
        planComboBox.setBounds(130, 60, 150, 25);
        add(planComboBox);

        // Prescription Details
        JLabel prescriptionLabel = new JLabel("Prescription Details:");
        prescriptionLabel.setBounds(30, 100, 200, 25);
        add(prescriptionLabel);

        String[] prescriptionColumns = {"Drug Code", "Drug Name", "Quantity", "Unit Price", "Total Cost"};
        prescriptionTableModel = new DefaultTableModel(prescriptionColumns, 0);
        prescriptionTable = new JTable(prescriptionTableModel);
        prescriptionTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane prescriptionScrollPane = new JScrollPane(prescriptionTable);
        prescriptionScrollPane.setBounds(30, 130, 600, 150);
        add(prescriptionScrollPane);

        addDrugBtn = new JButton("Add Drug");
        addDrugBtn.setBounds(30, 290, 100, 25);
        addDrugBtn.addActionListener(this);
        add(addDrugBtn);

        removeDrugBtn = new JButton("Remove Drug");
        removeDrugBtn.setBounds(150, 290, 150, 25);
        removeDrugBtn.addActionListener(this);
        add(removeDrugBtn);

        // Diagnostics Details
        JLabel diagnosticsLabel = new JLabel("Diagnostics Details:");
        diagnosticsLabel.setBounds(30, 330, 200, 25);
        add(diagnosticsLabel);

        String[] diagnosticsColumns = {"Test Code", "Test Name", "Cost"};
        diagnosticsTableModel = new DefaultTableModel(diagnosticsColumns, 0);
        diagnosticsTable = new JTable(diagnosticsTableModel);
        diagnosticsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane diagnosticsScrollPane = new JScrollPane(diagnosticsTable);
        diagnosticsScrollPane.setBounds(30, 360, 600, 150);
        add(diagnosticsScrollPane);

        addTestBtn = new JButton("Add Test");
        addTestBtn.setBounds(30, 520, 100, 25);
        addTestBtn.addActionListener(this);
        add(addTestBtn);

        removeTestBtn = new JButton("Remove Test");
        removeTestBtn.setBounds(150, 520, 150, 25);
        removeTestBtn.addActionListener(this);
        add(removeTestBtn);

        // Room Details
        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(30, 560, 100, 25);
        add(roomTypeLabel);

        roomTypeComboBox = new JComboBox<>(new String[]{"Ward", "Semi-Private", "Private"});
        roomTypeComboBox.setBounds(130, 560, 150, 25);
        add(roomTypeComboBox);

        JLabel daysLabel = new JLabel("Days:");
        daysLabel.setBounds(300, 560, 50, 25);
        add(daysLabel);

        daysField = new JTextField();
        daysField.setBounds(350, 560, 50, 25);
        add(daysField);

        // Summary Section
        JLabel summaryLabel = new JLabel("Bill Breakdown:");
        summaryLabel.setBounds(700, 100, 200, 25);
        add(summaryLabel);

        totalPrescriptionLabel = new JLabel("Total Prescription Cost: 0");
        totalPrescriptionLabel.setBounds(700, 130, 300, 25);
        add(totalPrescriptionLabel);

        totalDiagnosticsLabel = new JLabel("Total Diagnostics Cost: 0");
        totalDiagnosticsLabel.setBounds(700, 160, 300, 25);
        add(totalDiagnosticsLabel);

        totalRoomChargesLabel = new JLabel("Total Room Charges: 0");
        totalRoomChargesLabel.setBounds(700, 190, 300, 25);
        add(totalRoomChargesLabel);

        vatLabel = new JLabel("VAT: 0");
        vatLabel.setBounds(700, 220, 300, 25);
        add(vatLabel);

        grossTotalLabel = new JLabel("Gross Total: 0");
        grossTotalLabel.setBounds(700, 250, 300, 25);
        add(grossTotalLabel);

        coverageLabel = new JLabel("Maxicare Coverage: 0");
        coverageLabel.setBounds(700, 280, 300, 25);
        add(coverageLabel);

        payableLabel = new JLabel("Payable Amount: 0");
        payableLabel.setBounds(700, 310, 300, 25);
        add(payableLabel);

        // Action Buttons
        generateBillBtn = new JButton("Generate Bill");
        generateBillBtn.setBounds(700, 360, 150, 25);
        generateBillBtn.addActionListener(this);
        add(generateBillBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(700, 400, 150, 25);
        backBtn.addActionListener(this);
        add(backBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDrugBtn) {
            // Add Drug Logic
            String drugCode = JOptionPane.showInputDialog(this, "Enter Drug Code:");
            String drugName = JOptionPane.showInputDialog(this, "Enter Drug Name:");
            String quantityStr = JOptionPane.showInputDialog(this, "Enter Quantity:");
            String unitPriceStr = JOptionPane.showInputDialog(this, "Enter Unit Price:");

            try {
                int quantity = Integer.parseInt(quantityStr);
                double unitPrice = Double.parseDouble(unitPriceStr);
                double totalCost = quantity * unitPrice;

                prescriptionTableModel.addRow(new Object[]{drugCode, drugName, quantity, unitPrice, totalCost});
                updatePrescriptionTotal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for quantity or unit price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == removeDrugBtn) {
            // Remove Drug Logic
            int selectedRow = prescriptionTable.getSelectedRow();
            if (selectedRow != -1) {
                prescriptionTableModel.removeRow(selectedRow);
                updatePrescriptionTotal();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a drug to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == addTestBtn) {
            // Add Test Logic
            String testCode = JOptionPane.showInputDialog(this, "Enter Test Code:");
            String testName = JOptionPane.showInputDialog(this, "Enter Test Name:");
            String costStr = JOptionPane.showInputDialog(this, "Enter Test Cost:");

            try {
                double cost = Double.parseDouble(costStr);

                diagnosticsTableModel.addRow(new Object[]{testCode, testName, cost});
                updateDiagnosticsTotal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for test cost.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == removeTestBtn) {
            // Remove Test Logic
            int selectedRow = diagnosticsTable.getSelectedRow();
            if (selectedRow != -1) {
                diagnosticsTableModel.removeRow(selectedRow);
                updateDiagnosticsTotal();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a test to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == generateBillBtn) {
            // Generate Bill Logic
            try {
                double totalPrescription = calculatePrescriptionTotal();
                double totalDiagnostics = calculateDiagnosticsTotal();
                double roomCharges = calculateRoomCharges();
                double vat = (totalPrescription + totalDiagnostics + roomCharges) * VAT_RATE;
                double grossTotal = totalPrescription + totalDiagnostics + roomCharges + vat;

                int coverage = getCoverageLimit();
                double payableAmount = Math.max(0, grossTotal - coverage);

                // Update labels
                totalPrescriptionLabel.setText("Total Prescription Cost: " + totalPrescription);
                totalDiagnosticsLabel.setText("Total Diagnostics Cost: " + totalDiagnostics);
                totalRoomChargesLabel.setText("Total Room Charges: " + roomCharges);
                vatLabel.setText("VAT: " + vat);
                grossTotalLabel.setText("Gross Total: " + grossTotal);
                coverageLabel.setText("Maxicare Coverage: " + grossTotal);
                payableLabel.setText("Payable Amount: " + payableAmount);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for room charges or days.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backBtn) {
            // Back Button Logic
            if(e.getSource()==backBtn){
                this.setVisible(false);
                new VitalPayAdmin().setVisible(true);
            this.dispose();
            }
        }
    }

    private void updatePrescriptionTotal() {
        totalPrescriptionLabel.setText("Total Prescription Cost: " + calculatePrescriptionTotal());
    }

    private void updateDiagnosticsTotal() {
        totalDiagnosticsLabel.setText("Total Diagnostics Cost: " + calculateDiagnosticsTotal());
    }

    private double calculatePrescriptionTotal() {
        double total = 0;
        for (int i = 0; i < prescriptionTableModel.getRowCount(); i++) {
            total += (double) prescriptionTableModel.getValueAt(i, 4);
        }
        return total;
    }

    private double calculateDiagnosticsTotal() {
        double total = 0;
        for (int i = 0; i < diagnosticsTableModel.getRowCount(); i++) {
            total += (double) diagnosticsTableModel.getValueAt(i, 2);
        }
        return total;
    }

    private double calculateRoomCharges() throws NumberFormatException {
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        int days = Integer.parseInt(daysField.getText());
        double rate = switch (roomType) {
            case "Private" ->
                5000;
            case "Semi-Private" ->
                3000;
            case "Ward" ->
                1500;
            default ->
                0;
        };
        return rate * days;
    }

    private int getCoverageLimit() {
        String plan = (String) planComboBox.getSelectedItem();
        return switch (plan) {
            case "Silver" ->
                SILVER_COVERAGE;
            case "Gold" ->
                GOLD_COVERAGE;
            case "Platinum" ->
                PLATINUM_COVERAGE;
            case "Platinum Plus" ->
                PLATINUMPLUS_COVERAGE;
            default ->
                0;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GenerateBill::new);
    }
}