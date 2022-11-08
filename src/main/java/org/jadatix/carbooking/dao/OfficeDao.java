package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class OfficeDao {
    private OfficeRepository officeRepository;
    private final String tableName = new String("Office");

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

    public void save(Office office) {
        officeRepository.save(office);
    }

    public void update(Office office) {
        officeRepository.findById(office.getId())
                .map(o -> {
                    o.setCity(Objects.requireNonNull(office.getCity()));
                    o.setStreet(Objects.requireNonNull(office.getStreet()));
                    return officeRepository.save(o);
                })
                .orElseGet(() -> officeRepository.save(office));
    }

    public void delete(Long id) {
        Office deletingOffice = get(id).get();
        officeRepository.delete(deletingOffice);
    }

}
