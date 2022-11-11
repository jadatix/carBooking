package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.springframework.stereotype.Component;

@Component
public class OfficeDao extends AbstractDao<Office> {
    private final OfficeRepository repository;

    public OfficeDao(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected OfficeRepository getRepository() {
        return repository;
    }
}
