package by.epamtc.enrollmentsystem.model;

import java.util.Arrays;
import java.util.Objects;

public class UserInfo {

    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private String photoPath;
    private String address;
    private String passport;

    public UserInfo() {
    }

    public UserInfo(int id,String name, String surname, String patronymic, String photoPath, String address, String passport) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.photoPath = photoPath;
        this.address = address;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id && Objects.equals(name, userInfo.name) &&
                Objects.equals(surname, userInfo.surname) && Objects.equals(patronymic, userInfo.patronymic) &&
                Objects.equals(photoPath, userInfo.photoPath) && Objects.equals(address, userInfo.address) &&
                Objects.equals(passport, userInfo.passport);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (name == null?0:name.hashCode());
        result = prime * result + (surname == null?0:surname.hashCode());
        result = prime * result + (patronymic == null?0:patronymic.hashCode());
        result = prime * result + (photoPath == null?0:photoPath.hashCode());
        result = prime * result + (address == null?0:address.hashCode());
        result = prime * result + (passport == null?0:passport.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append(" Fields:\n");
        result.append("ID:").append(id).append("\n");
        result.append("name:").append(name).append("\n");
        result.append("surname:").append(surname).append("\n");
        result.append("patronymic").append(patronymic).append("\n");
        result.append("photopath").append(photoPath).append("\n");
        result.append("address").append(address).append("\n");
        result.append("passport").append(passport).append("\n");
        return result.toString();
    }
}
