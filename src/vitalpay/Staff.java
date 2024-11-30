package vitalpay;

//Class file for a staff member with username, password, and role
//Also connected with StaffData.java para hindi na magerror
public class Staff {

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
