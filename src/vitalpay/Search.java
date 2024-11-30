package vitalpay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JFrame implements ActionListener {

    private JTable patientTable;
    private JPanel tablePanel;
    private DefaultTableModel tableModel;
    private JButton backBtn, search;
    private JTextField patientID;

    public Search() {
        // Create the main frame
        setTitle("Search Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize and add components
        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        
        JPanel ID = new JPanel();
        ID.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // 4 rows, 2 columns
        ID.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        ID.add(new JLabel("Patient ID:"));

        
        
        patientID = new JTextField();
        patientID.setPreferredSize(new Dimension(200, 30));
        ID.add(patientID);
        
        search = createButton("Search");
        search.setPreferredSize(new Dimension(100, 30));
        search.addActionListener(this);
        ID.add(search);

        add(ID, BorderLayout.NORTH);
        
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Table for patient details
        String[] columns = {
            "Patient ID", "First Name", "Middle Name", "Last Name",
            "Contact Number", "Age", "Gender", "Blood Group", "Address", "Major Disease"
        };
        tableModel = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        patientTable = new JTable(tableModel);
        patientTable.getTableHeader().setReorderingAllowed(false);

        // Add table inside a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(patientTable);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        //Add Table panel
        add(tablePanel, BorderLayout.CENTER);
        
        // Add button panel
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Buttons
        backBtn = createButton("Back");
        backBtn.setPreferredSize(new Dimension(100,30));
        backBtn.addActionListener(this);

        // Add buttons to the panel
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
            new VitalPayAdmin().setVisible(true); // Replace with the actual class for "Back" navigation
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Search::new);
    }
}
