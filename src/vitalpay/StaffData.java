package vitalpay;

import java.util.HashMap;
import javax.swing.JOptionPane;

public class StaffData {

    private static final HashMap<String, Staff> staffMap = new HashMap<>();

    static {
        staffMap.put("admin", new Staff("admin", "admin123", "Admin"));
    }

    public static void addStaff(String username, String password, String role) {
        if (username == null || password == null || role == null || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please fill all the fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        username = username.toLowerCase();
        if (staffMap.containsKey(username)) {
            JOptionPane.showMessageDialog(null,
                    "A staff member with this username already exists.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            staffMap.put(username, new Staff(username, password, role));
            JOptionPane.showMessageDialog(null,
                    "Staff added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static Staff getStaff(String username) {
        return staffMap.get(username);
    }

    public static boolean staffExists(String username) {
        return staffMap.containsKey(username);
    }

    public static Staff validateStaff(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please complete all the fields.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        username = username.toLowerCase();
        Staff staff = staffMap.get(username);

        if (staffMap.containsKey(username)) {
            if (staff.getPassword().equals(password)) {
                return staff;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect password.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Username not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}

class Staff {

    private String username;
    private String password;
    private String role;

    public Staff(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
