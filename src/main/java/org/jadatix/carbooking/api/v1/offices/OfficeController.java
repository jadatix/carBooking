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
        officeService.save(office);
        return new ResponseEntity<>("Office has been added successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Office>> getAllOffices() {
        List<Office> offices = officeService.getAll();
        HttpStatus status = offices != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(offices, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable Long id) {
        Office responseOffice = officeService.get(id).get();
        return new ResponseEntity<>(responseOffice, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> updateOffice(@PathVariable Long id, @RequestBody Office office) {
        Office updatedOffice = officeService.update(id, office);
        return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOfficeById(@PathVariable Long id) {
        officeService.delete(id);
    }

}
