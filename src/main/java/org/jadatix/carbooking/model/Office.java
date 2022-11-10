package org.jadatix.carbooking.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp = "[A-ZА-ЯЮЄЇЙ][a-zа-яюєїй]+",message = "The first letter should be capital")
    @Column(nullable = false)
    private String city;

    @Size(min = 1)
    @Column(nullable = false)
    @Pattern(regexp = "[([A-ZА-ЯЮЄЇЙ][a-zа-яюєїй ]+)+[0-9]+[a-z]{0,2}]")
    private String street;

    public Office() {
    }

    public Office(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

}
