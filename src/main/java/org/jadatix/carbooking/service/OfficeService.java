package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.OfficeDao;
import org.jadatix.carbooking.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

    private OfficeDao officeDao;

    @Autowired
    public OfficeService(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    public List<Office> getAllOffices() {
        return officeDao.getAll();
    }

    public void saveOffice(Office office) {
        officeDao.save(office);
    }

    public Optional<Office> getOfficeById(Long id) {
        return officeDao.get(id);
    }

    public void updateOffice(Office office) {
        //validation?
        officeDao.update(office);
    }

    public void deleteOfficeById(Long id) {
        officeDao.delete(id);
    }
}
