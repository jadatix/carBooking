package org.jadatix.carbooking.service;

import org.jadatix.carbooking.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        return Office.builder().build();
    }

    @Override
    protected OfficeService getService() {
        return service;
    }
}
