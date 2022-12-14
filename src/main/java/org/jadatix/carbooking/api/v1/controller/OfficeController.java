package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.response.OfficeResponse;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("api/v1/offices")
public class OfficeController extends AbstractController<Office, OfficeRequest, OfficeResponse>{

    private OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @Override
    protected OfficeService getService() {
        return service;
    }

    @Override
    protected OfficeResponse convertToResponse(Office office) {
        return new OfficeResponse(office);
    }

    @Override
    protected void convertPatchField(Office entity, Map.Entry<String, Object> requestBody) {
        String newValue = String.valueOf(requestBody.getValue());
        switch(requestBody.getKey()){
            case "city":
                entity.setCity(newValue);
                break;
            case "street":
                entity.setStreet(newValue);
                break;
            default:
                break;
        }
    }
}
