package org.jadatix.carbooking.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.response.OfficeResponse;
import org.jadatix.carbooking.builder.OfficeBuilder;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@WebMvcTest
public class OfficeControllerTest extends AbstractControllerTest<Office, OfficeRequest, OfficeResponse> {
    @MockBean
    private OfficeService officeService;

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
    protected List<Function<Office, Object>> getValueToBeUpdated(OfficeRequest request) {
        return List.of(Office::getCity, Office::getStreet);
    }

    @Override
    protected Office getNewEntity() {
        return OfficeBuilder.builder().build();
    }

    @Override
    protected OfficeRequest getNewRequest() {
        return new OfficeRequest(getNewEntity());
    }

    @Override
    protected OfficeRequest convertToRequest(Office office) {
        return new OfficeRequest(office);
    }

    @Override
    protected OfficeResponse convertToResponse(Office office) {
        return new OfficeResponse(office);
    }
}
