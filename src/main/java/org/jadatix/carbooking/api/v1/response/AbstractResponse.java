package org.jadatix.carbooking.api.v1.response;


import org.jadatix.carbooking.model.IdentifierEntity;

public abstract class AbstractResponse{

    //do we need hold id in response?
    private Long id;

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
