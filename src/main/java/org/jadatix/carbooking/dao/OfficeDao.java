package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.jadatix.carbooking.service.AuthenticateUserService.isManager;

@Component
public class OfficeDao extends AbstractDao<Office> {

    private OfficeRepository repository;

    public OfficeDao(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected OfficeRepository getRepository() {
        return repository;
    }

    @Override
    protected Specification<Office> getForReadOnly() {
        return null;
    }

    @Override
    protected Specification<Office> getForModify(Long id) {
        if (isManager()) {
            return getEqualSpecification("id", id);
        }
        return null;
    }

}
