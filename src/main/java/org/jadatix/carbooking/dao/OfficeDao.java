package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.jadatix.carbooking.service.AuthenticateUserService.isManager;

@Component
public class OfficeDao extends AbstractDao<Office> {

    private OfficeRepository repository;

    public OfficeDao(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Office update(Office t) {
        if(isManager()){
            return getRepository().save(t);
        } else {
            throw new AccessDeniedException();
        }
    }

    @Override
    public void delete(Long id) {
        if(isManager()){
            Optional<Office> found = get(id);
            found.ifPresent(getRepository()::delete);
        } else {
            throw new AccessDeniedException();
        }
    }


    @Override
    protected OfficeRepository getRepository() {
        return repository;
    }

}
