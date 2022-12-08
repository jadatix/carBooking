package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.response.OfficeResponse;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.AbstractService;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/offices")
public class OfficeController extends AbstractController<Office, OfficeRequest, OfficeResponse>{

    private OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Office> getService() {
        return service;
    }

    @Override
    protected OfficeResponse toResponse(Office office) {
        OfficeResponse or = new OfficeResponse(office.getId());
        or.setCity(office.getCity());
        or.setStreet(office.getStreet());
        return or;
    }
}
