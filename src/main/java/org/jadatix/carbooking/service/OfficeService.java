package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.Dao;
import org.jadatix.carbooking.dao.OfficeDao;
import org.jadatix.carbooking.model.Office;
import org.springframework.stereotype.Service;

@Service
public class OfficeService extends AbstractService<Office> {

    private OfficeDao dao;

    public OfficeService(OfficeDao dao) {
        this.dao = dao;
    }

    @Override
    protected Dao<Office> getDao() {
        return dao;
    }

}
