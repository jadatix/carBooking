package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.springframework.stereotype.Component;

@Component
public class OfficeDao implements Dao<Office> {

    private OfficeRepository repository;

    public OfficeDao(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    public OfficeRepository getRepository() {
        return repository;
    }

}
