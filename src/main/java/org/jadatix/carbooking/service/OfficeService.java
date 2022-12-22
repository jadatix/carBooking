package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.OfficeDao;
import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.model.Office;
import org.springframework.stereotype.Service;

@Service
public class OfficeService extends AbstractService<Office> {

    private OfficeDao dao;

    public OfficeService(OfficeDao dao) {
        this.dao = dao;
    }

    @Override
    protected OfficeDao getDao() {
        return dao;
    }

    @Override
    public Office update(Office office){
        if( !AuthenticateUserService.isManager() ){
            throw new AccessDeniedException();
        }
        return getDao().update(office);
    }

    @Override
    public void delete(Long id) {
        if( !AuthenticateUserService.isManager() ){
            throw new AccessDeniedException();
        }
        getDao().delete(id);
    }

}
