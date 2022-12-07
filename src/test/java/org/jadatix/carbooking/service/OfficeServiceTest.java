package org.jadatix.carbooking.service;

import org.jadatix.carbooking.builder.OfficeBuilder;
import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfficeServiceTest extends AbstractServiceTest<Office> {

    @Autowired
    private OfficeService service;


    @Override
    protected void assertEntity(Office actual, Office expected) {
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getStreet(), actual.getStreet());
    }

    @Override
    protected Office generateEntity() {
        return OfficeBuilder.builder().build();
    }

    @Override
    protected OfficeService getService() {
        return service;
    }

    @Test
    void testCreateOfficeAsUserRole(){
        loginAs(Role.USER);
        Office office = generateEntity();
        assertThrows(AccessDeniedException.class,()->service.create(office));
    }

    @Test
    void testGetOfficeAsUserRole(){
        Office office = pushEntityToDb();
        loginAs(Role.USER);
        assertEquals(office,service.get(office.getId()));
    }

    @Test
    void testUpdateOfficeAsUserRole(){
        Office newOffice = pushEntityToDb();
        loginAs(Role.USER);
        newOffice.setCity("Lviv");
        assertThrows(AccessDeniedException.class, ()->service.update(newOffice));
    }

    @Test
    void testDeleteOfficeAsUserRole(){
        Office office = pushEntityToDb();
        loginAs(Role.USER);
        assertThrows(AccessDeniedException.class, ()->service.delete(office.getId()));
    }
}