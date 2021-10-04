package by.epamtc.enrollmentsystem.model;


public class User {

    private int id;
    private String login;
    private byte[] password;
    private String email;
    private int roleId;

    public User() {
    }

    public User(int id,String login, byte[] password, String email, int roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
