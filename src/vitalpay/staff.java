import javax.swing.*;
import java.awt.*;

public class staff {
    public static void main(String[] args) {
        // Frame ng mismong system siyempre
        JFrame frame = new JFrame("VitalPay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);

        // title na label pa san ka pa
        JLabel titleLabel = new JLabel("VitalPay", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 10, 200, 30);
        frame.add(titleLabel);

        // pindutan
        JButton patientButton = new JButton("Add Patient");
        patientButton.setBounds(50, 80, 150, 30);
        frame.add(patientButton);

        JButton archiveButton = new JButton("Generate Bill");
        archiveButton.setBounds(50, 120, 150, 30);
        frame.add(archiveButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 160, 150, 30);
        frame.add(logoutButton);

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(112, 128, 144));
        infoPanel.setBounds(250, 80, 300, 150);
        infoPanel.setLayout(null);

        // Mismong Username bali pangalan ko na lang nilagay ko (weni)
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(20, 20, 80, 20);
        infoPanel.add(usernameLabel);

        JLabel usernameValue = new JLabel("Weni<3");
        usernameValue.setFont(new Font("Arial", Font.BOLD, 14));
        usernameValue.setForeground(Color.RED);
        usernameValue.setBounds(120, 20, 100, 20);
        infoPanel.add(usernameValue);

        // system ito para sa staff kaya staff lalagay natin
        JLabel userTypeLabel = new JLabel("UserType");
        userTypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userTypeLabel.setForeground(Color.WHITE);
        userTypeLabel.setBounds(20, 60, 80, 20);
        infoPanel.add(userTypeLabel);

        JLabel userTypeValue = new JLabel("Staff");
        userTypeValue.setFont(new Font("Arial", Font.BOLD, 14));
        userTypeValue.setForeground(Color.RED);
        userTypeValue.setBounds(120, 60, 100, 20);
        infoPanel.add(userTypeValue);

        frame.add(infoPanel);
        frame.setVisible(true);
        
        //Special thanks kay barredo sa pag help sakin)
    }
}