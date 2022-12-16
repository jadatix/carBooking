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

    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity<String> addOffice(@RequestBody Office office) {
        officeService.create(office);
        return new ResponseEntity<>("Office has been added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Office>> getAllOffices() {
        List<Office> offices = officeService.getAll();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable Long id) {
        Office responseOffice = officeService.get(id);
        return new ResponseEntity<>(responseOffice, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> updateOffice(@PathVariable Long id, @RequestBody Office office) {
        Office updatedOffice = officeService.update(office);
        return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOfficeById(@PathVariable Long id) {
        officeService.delete(id);
        return new ResponseEntity<>("Office has been deleted successfully",HttpStatus.NO_CONTENT);
    }

}
