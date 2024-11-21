package vitalpay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelofAccess extends JFrame implements ActionListener {

    private JButton admin;
    private JButton staff;

    public LevelofAccess() {
        setTitle("Vital Pay");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS)); 
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel title = new JLabel("VitalPay", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(0x1961dd));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);

        titlePanel.add(Box.createVerticalStrut(10));

        JLabel loa = new JLabel("Level of Access", SwingConstants.CENTER);
        loa.setFont(new Font("Arial", Font.BOLD, 30));
        loa.setForeground(new Color(0x1961dd));
        loa.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titlePanel.add(loa);

        add(titlePanel, BorderLayout.NORTH);

        JPanel LOA = new JPanel(new GridLayout(2, 1, 10, 10));
        LOA.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        admin = new JButton("Admin");
        admin.setPreferredSize(new Dimension(200, 150));
        admin.setMaximumSize(admin.getPreferredSize());
        admin.setAlignmentX(Component.CENTER_ALIGNMENT);
        admin.setFocusable(false);
        admin.addActionListener(this);

        staff = new JButton("Staff");
        staff.setPreferredSize(new Dimension(200, 150));
        staff.setMaximumSize(staff.getPreferredSize());
        staff.setAlignmentX(Component.CENTER_ALIGNMENT);
        staff.setFocusable(false);
        staff.addActionListener(this);

        LOA.add(admin);
        LOA.add(staff);

        add(LOA, BorderLayout.CENTER);
    }
    
    public JButton getAdminButton() {
        return admin;
    }

    public JButton getStaffButton() {
        return staff;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         
        if (e.getSource() == admin) {
            this.setVisible(false);
            VitalPayAdmin adm = new VitalPayAdmin();
            adm.setVisible(true);
            
        } else if (e.getSource() == staff) {
            this.setVisible(false);
            VitalPayStaff stf = new VitalPayStaff();
            stf.setVisible(true);
        }
    }
}
