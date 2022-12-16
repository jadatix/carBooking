package org.jadatix.carbooking.api.v1.response;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.model.Office;

public class OfficeResponse extends AbstractResponse {
    private String city;
    private String street;

    public OfficeResponse(Office office) {
        super(office.getId());
        this.city = office.getCity();
        this.street = office.getStreet();
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

}
