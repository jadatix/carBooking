package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.repository.OfficeRepository;
import org.jadatix.carbooking.utility.SpecificationFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OfficeDao implements DaoEntity<Office> {
    private OfficeRepository repository;

    public OfficeDao(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    public OfficeRepository getRepository() {
        return repository;
    }

    @Override
    public Optional<Office> getImmutable(Long id) {
        return getRepository().findById(id);
    }
}
