package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.OfficeDao;
import org.jadatix.carbooking.model.Office;
import org.springframework.stereotype.Service;

@Service
public class OfficeService implements ServiceEntity<Office> {
    private OfficeDao dao;

    public OfficeService(OfficeDao dao) {
        this.dao = dao;
    }

    @Override
    public OfficeDao getDao() {
        return dao;
    }
}
