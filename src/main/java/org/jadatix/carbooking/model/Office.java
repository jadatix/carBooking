package org.jadatix.carbooking.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "office")
public class Office extends AbstractEntity<Office> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String city;

    @Size(min = 1)
    @Column(nullable = false)
    private String street;

    public Office() {
    }

    public static Builder builder() {
        return new Office().new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return this.getId().equals(office.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public class Builder extends AbstractBuilder<Office> {
        public Builder setCity(String city) {
            Office.this.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            Office.this.street = street;
            return this;
        }

        @Override
        public Office build() {
            if (Objects.isNull(Office.this.city)) {
                Office.this.city = getRandomString();
            }

            if (Objects.isNull(Office.this.street)) {
                Office.this.street = getRandomString();
            }

            return Office.this;
        }
    }
}
