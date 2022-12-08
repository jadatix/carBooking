package org.jadatix.carbooking.api.v1.response;

import org.jadatix.carbooking.model.Role;
import java.time.LocalDate;

public class UserResponse extends AbstractResponse{

    private Role role;
    private String fullName;
    private String email;
    private LocalDate birthday;

    public UserResponse(Long id) {
        super(id);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
