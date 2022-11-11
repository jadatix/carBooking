package org.jadatix.carbooking.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User extends AbstractEntity<User> {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Role role;
    @Column(unique = true, nullable = false)
    private String passport;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String secret;
    @Column(nullable = false)
    private LocalDate birthday;

    public User() {
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public String getPassport() {
        return passport;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getSecret() {
        return secret;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public class Builder extends AbstractBuilder<User> {
        public Builder setRole(Role role) {
            User.this.role = role;
            return this;
        }

        public Builder setPassport(String passport) {
            User.this.passport = passport;
            return this;
        }

        public Builder setFullName(String fullName) {
            User.this.fullName = fullName;
            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setSecret(String secret) {
            User.this.secret = secret;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            User.this.birthday = birthday;
            return this;
        }

        @Override
        public User build() {
            if (Objects.isNull(User.this.role)) {
                User.this.role = Role.USER;
            }

            if (Objects.isNull(User.this.passport)) {
                User.this.passport = getRandomString();
            }

            if (Objects.isNull(User.this.fullName)) {
                User.this.fullName = getRandomString();
            }

            if (Objects.isNull(User.this.email)) {
                User.this.email = getRandomString() + "@gmail.com";
            }


            if (Objects.isNull(User.this.secret)) {
                User.this.secret = getRandomString();
            }

            if (Objects.isNull(User.this.birthday)) {
                User.this.birthday = LocalDate.of(2002, Month.SEPTEMBER, 10);
            }

            return User.this;
        }
    }
}
