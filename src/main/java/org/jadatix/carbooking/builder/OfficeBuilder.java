package org.jadatix.carbooking.builder;

import java.security.SecureRandom;

import org.jadatix.carbooking.model.Office;

public class OfficeBuilder {
    Office office;

    public OfficeBuilder() {
        init();
    }

    private void init() {
        Integer value = new SecureRandom().nextInt(1, 99999);
        office = new Office();
        office.setCity("City_" + value);
        office.setStreet("Street_" + value);
    }

    public static OfficeBuilder builder() {
        return new OfficeBuilder();       
    }

    public OfficeBuilder setId(Long id) {
        office.setId(id);
        return this;
    }

    public OfficeBuilder setCity(String city) {
        office.setCity(city);
        return this;
    }

    public OfficeBuilder setStreet(String street) {
        office.setStreet(street);
        return this;
    }

    public Office build() {
        Office oldOffice = this.office;
        init();
        return oldOffice;
    }
}
