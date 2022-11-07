package org.jadatix.carbooking.api.v1.offices;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/offices")
public class OfficeController {
    public final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity<Office> addOffice(@RequestBody Office office) {
        officeService.saveOffice(office);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Office>> getAllOffices() {
        List<Office> offices = officeService.getAllOffices();
        HttpStatus status = offices != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(offices, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable Long id) {
        Office responseOffice = officeService.getOfficeById(id).get();
        HttpStatus status = responseOffice != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseOffice, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> updateOffice(@PathVariable Long id, @RequestBody Office office) {
        office.setId(id);
        officeService.updateOffice(office);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOfficeById(@PathVariable Long id) {
        try {
            officeService.deleteOfficeById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
