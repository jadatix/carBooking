package org.jadatix.carbooking.demodata;

import org.jadatix.carbooking.dao.OfficeDao;
import org.jadatix.carbooking.model.Office;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class OfficeDemoData {
    private static final Logger log = LoggerFactory.getLogger(OfficeDemoData.class);

    CommandLineRunner initDatabase(OfficeDao officeDao) {

        return args -> {

            List<Office> offices = Arrays.asList(new Office("Chernivtsi", "Holovna"),
                    new Office("Chernivtsi", "S. Bandery"));
            offices.forEach(office -> {
                log.info("Preloading {}", office);
                officeDao.create(office);
            });
        };
    }
}
