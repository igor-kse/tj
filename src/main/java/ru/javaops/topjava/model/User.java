package ru.javaops.topjava.model;

import org.springframework.util.CollectionUtils;
import ru.javaops.topjava.util.MealsUtil;

import java.util.*;

public class User extends BaseNamedEntity {

    private String email;

    private String password;

    private boolean enabled;

    private Date registrationDate = new Date();

    private Set<Role> roles;

    private int caloriesPerDay;

    public User(String name, String email, String password, boolean enabled, Role... roles) {
        super(null, name);
        this.caloriesPerDay = MealsUtil.DEFAULT_CALORIES_PER_DAY;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        setRoles(Arrays.asList(roles));
    }

    public User(String name,int caloriesPerDay, String email, String password, boolean enabled, Role... roles) {
        super(null, name);
        this.caloriesPerDay = caloriesPerDay;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        setRoles(Arrays.asList(roles));
    }

    public User(Long id, String name, int caloriesPerDay, String email, String password, boolean enabled, Date registrationDate, Role... roles) {
        super(id, name);
        this.email = email;
        this.caloriesPerDay = caloriesPerDay;
        this.password = password;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
        setRoles(Arrays.asList(roles));
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

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
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
