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

    private void pushOfficeToDb(Long id){
        loginAs(Role.MANAGER);
        Office office = generateEntity();
        office.setId(id);
        service.create(office);
        logoutUser();
    }

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
    void createAsManagerTest(){
        loginAs(Role.MANAGER);
        Office office = generateEntity();
        assertDoesNotThrow(()->service.create(office));
    }

    @Test
    void createAsUserTest(){
        loginAs(Role.USER);
        Office office = generateEntity();
        assertThrows(AccessDeniedException.class,()->service.create(office));
    }

    @Test
    void getAsManagerTest(){
        pushOfficeToDb(1L);
        loginAs(Role.MANAGER);
        assertDoesNotThrow(()->service.get(1L));
    }

    @Test
    void getAsUserTest(){
        pushOfficeToDb(1L);
        loginAs(Role.USER);
        assertDoesNotThrow(()->service.get(1L));
    }

    @Test
    void getAllAsManagerTest(){
        loginAs(Role.MANAGER);
        assertDoesNotThrow(()-> service.getAll());
    }

    @Test
    void getAllAsUserTest(){
        loginAs(Role.USER);
        assertDoesNotThrow(()-> service.getAll());
    }

    @Test
    void updateAsManagerTest(){
        loginAs(Role.MANAGER);
        pushOfficeToDb(1L);
        Office newOffice = generateEntity();
        newOffice.setId(1L);
        assertDoesNotThrow(()->service.update(newOffice));
    }

    @Test
    void updateAsUserTest(){
        loginAs(Role.USER);
        Office newOffice = generateEntity();
        newOffice.setId(1L);
        assertThrows(AccessDeniedException.class, ()->service.update(newOffice));
    }

    @Test
    void deleteAsManagerTest(){
        pushOfficeToDb(1L);
        loginAs(Role.MANAGER);
        assertDoesNotThrow(()->service.delete(1L));
    }

    @Test
    void deleteAsUserTest(){
        pushOfficeToDb(1L);
        loginAs(Role.USER);
        assertThrows(AccessDeniedException.class, ()->service.delete(1L));
    }
}
