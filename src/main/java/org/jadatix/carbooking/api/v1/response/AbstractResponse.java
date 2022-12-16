package org.jadatix.carbooking.api.v1.response;

public abstract class AbstractResponse {
    private Long id;

    public AbstractResponse() {
    }

    public AbstractResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
