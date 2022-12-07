package org.jadatix.carbooking.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.response.OfficeResponse;
import org.jadatix.carbooking.builder.OfficeBuilder;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.AbstractService;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
public class OfficeControllerTest extends AbstractControllerTest<Office, OfficeRequest, OfficeResponse>{

    @MockBean
    private OfficeService service;

    @Override
    protected AbstractService getService() {
        return this.service;
    }

    @Override
    protected String getMapping() {
        return "/api/v1/offices";
    }

    @Override
    protected OfficeResponse toResponse(Office office) {
        OfficeResponse or= new OfficeResponse(office.getId());
        or.setCity(office.getCity());
        or.setStreet(office.getStreet());
        return  or;
    }

    @Override
    protected Office getRandomEntity() {
        return OfficeBuilder.builder().build();
    }
}
