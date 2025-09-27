package ru.javaops.topjava.model;

import java.util.Date;
import java.util.Set;

public class User extends BaseNamedEntity {

    private String email;

    private String password;

    private boolean enabled;

    private Date registrationDate;

    private Set<Role> roles;

    private int caloriesPerDay;

    public User(Long id, String name, String email, String password, boolean enabled, Date registrationDate, Set<Role> roles, int caloriesPerDay) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
        this.roles = roles;
        this.caloriesPerDay = caloriesPerDay;
    }

    public User(String name, String email, String password, int caloriesPerDay, boolean enabled, Set<Role> roles, Date registrationDate) {
        super(null, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
        this.roles = roles;
        this.caloriesPerDay = caloriesPerDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                '}';
    }
}
