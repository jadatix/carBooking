package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OfficeDao {
    private OfficeRepository officeRepository;

    @Autowired
    public OfficeDao(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Optional<Office> get(Long id) {
        return officeRepository.findById(id);
    }

    public List<Office> getAll() {
        return officeRepository.findAll();
    }

    public Office save(Office office) {
        return officeRepository.save(office);
    }

    public Office update(Office office) {
        return officeRepository.save(office);
    }

    public void delete(Long id) {
        Optional<Office> deletingOffice = get(id);
        deletingOffice.ifPresent(officeRepository::delete);
    }

}
