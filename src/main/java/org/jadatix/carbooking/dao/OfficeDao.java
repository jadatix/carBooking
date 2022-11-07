package org.jadatix.carbooking.dao;

import org.hibernate.query.Query;
import org.jadatix.carbooking.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class OfficeDao {
    private EntityManager entityManager;
    private final String tableName = new String("Office");

    @Autowired
    public OfficeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Office> get(Long id) {
        Office res = entityManager.find(Office.class, id);
        return Optional.ofNullable(res);
    }

    public List<Office> getAll() {
        String query = new StringBuilder().append("from ").append(tableName).toString();
        Query theQuery = (Query) entityManager.createQuery(query, Office.class);
        List<Office> offices = theQuery.getResultList();
        return offices;
    }

    @Transactional
    public void save(Office office) {
        entityManager.persist(office);
    }

    @Transactional
    public void update(Office office) {
        entityManager.merge(office);
    }

    @Transactional
    public void delete(Long id) {
        Office deletingOffice = get(id).get();
        entityManager.remove(deletingOffice);
    }

}
