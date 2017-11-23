package cz.fi.muni.legomanager.entity;

import java.util.Objects;

/**
 * Class for representing users.
 *
 * @author Lukáš Dvořák
 */
public class User {

    private String name;
    private String password;
    private boolean isAdmin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, isAdmin);
    }
}
