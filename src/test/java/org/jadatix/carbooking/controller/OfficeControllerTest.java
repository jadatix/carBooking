package org.jadatix.carbooking.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.response.OfficeResponse;
import org.jadatix.carbooking.builder.OfficeBuilder;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
public class OfficeControllerTest extends AbstractControllerTest<Office, OfficeRequest, OfficeResponse> {
    @MockBean
    OfficeService officeService;

    @Override
    protected String getControllerPath() {
        return "/api/v1/offices";
    }

    @Override
    protected Class<Office> getEntityClass() {
        return Office.class;
    }

    @Override
    protected OfficeService getService() {
        return officeService;
    }

    @Override
    protected Office getNewEntity() {
        return OfficeBuilder.builder().build();
    }
}
