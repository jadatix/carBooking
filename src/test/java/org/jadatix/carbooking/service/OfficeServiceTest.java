package org.jadatix.carbooking.service;

import org.jadatix.carbooking.builder.OfficeBuilder;
import org.jadatix.carbooking.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OfficeServiceTest implements ServiceTest<Office> {

    @Autowired
    private OfficeService service;

    @Override
    public void assertEntity(Office actual, Office expected) {
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getStreet(), actual.getStreet());
    }

    @Override
    public Office generateEntity() {
        return OfficeBuilder.builder().build();
    }

    @Override
    public OfficeService getService() {
        return service;
    }

}
