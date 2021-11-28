package by.epamtc.enrollmentsystem.model;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {

    private long id;
    private String login;
    private byte[] password;
    private String email;
    private long roleId;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId &&
                Objects.equals(login, user.login) && Arrays.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (login == null?0:login.hashCode());
        result = prime * result + (Arrays.toString(password) == null?0:Arrays.hashCode(password));
        result = prime * result + (email == null?0:email.hashCode());
        result = prime * result + (int) roleId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("ID:").append(id).append("\n");
        result.append("login").append(login).append("\n");
        result.append("password:").append(Arrays.toString(password)).append("\n");
        result.append("email:").append(email).append("\n");
        result.append("role id:").append(roleId).append("\n");
        return result.toString();
    }
}
