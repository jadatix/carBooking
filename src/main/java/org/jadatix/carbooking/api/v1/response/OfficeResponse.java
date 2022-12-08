package org.jadatix.carbooking.api.v1.response;


public class OfficeResponse extends AbstractResponse{

    private String city;
    private String street;

    public OfficeResponse(Long id) {
        super(id);
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
