package org.jadatix.carbooking.repository;

import org.jadatix.carbooking.model.IdentifierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SpecificationRepository<T extends IdentifierEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
