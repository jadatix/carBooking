package org.jadatix.carbooking.api.v1.offices;

import org.jadatix.carbooking.api.v1.ControllerEntity;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.OfficeService;
import org.jadatix.carbooking.service.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/offices")
public class OfficeController implements ControllerEntity<Office> {
    private OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }


    @Override
    public OfficeService getService() {
        return service;
    }
}
