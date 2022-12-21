package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.response.OfficeResponse;
import org.jadatix.carbooking.exception.FieldValidationException;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("api/v1/offices")
public class OfficeController extends AbstractController<Office, OfficeRequest, OfficeResponse>{

    private OfficeService service;
    private ConversionService converter;
    private Validator validator;

    @Autowired
    public OfficeController(OfficeService service, ConversionService conversion) {
        this.service = service;
        this.converter = conversion;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
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
        switch(requestBody.getKey()){
            case "city":
                String newCity = converter.convert(requestBody.getValue(),String.class);
                validateField("city",newCity);
                entity.setCity(newCity);
                break;
            case "street":
                String newStreet = converter.convert(requestBody.getValue(),String.class);
                validateField("city",newStreet);
                entity.setStreet(newStreet);
                break;
            default:
                break;
        }
    }

    @Override
    protected void validateField(String fieldName, Object fieldValue){
        Set<ConstraintViolation<OfficeRequest>> result = validator.validateValue(OfficeRequest.class,fieldName,fieldValue);
        if(!result.isEmpty()){
            throw new FieldValidationException(result.toString());
        }
    }
}
