package org.jadatix.carbooking.repository;

import org.jadatix.carbooking.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends SpecificationRepository<Office> {
}
