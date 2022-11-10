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

    public List<Office> getAll() {
        return officeDao.getAll();
    }

    public Office save(Office office) {
        return officeDao.save(office);
    }

    public Optional<Office> get(Long id) {
        return officeDao.get(id);
    }

    public Office update(Long id, Office office) {
        //validation?
        office.setId(id);
        return officeDao.update(office);
    }

    public void delete(Long id) {
        officeDao.delete(id);
    }
}
