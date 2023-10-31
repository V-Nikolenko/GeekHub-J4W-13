package org.geekhub.hw3.orcostat.model.ground;

import org.geekhub.hw3.orcostat.model.Collection;
import org.geekhub.hw3.orcostat.model.Orc;
import org.geekhub.hw3.orcostat.model.SimpleCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TankTest {
    @Test
    void can_build_tank() {
        new Tank(3);
    }

    @Test
    void tank_price_is_3_000_000() {
        Tank tank = new Tank(3);

        assertEquals(3_000_000, tank.getPrice());
    }

    @Test
    void empty_tank_has_zero_equipage() {
        Tank tank = new Tank(3);

        int size = tank.getEquipage().size();

        assertEquals(0, size);
    }

    @Test
    void tank_can_have_some_equipage() {
        Tank tank = new Tank(3);
        tank.putOrc(new Orc());

        int size = tank.getEquipage().size();

        assertEquals(1, size);
    }

    @Test
    void should_not_put_orc_when_same_orc_put_again() {
        Tank tank = new Tank(3);
        Orc orc = new Orc();
        tank.putOrc(orc);
        tank.putOrc(orc);

        int size = tank.getEquipage().size();

        assertEquals(1, size);
    }

    @Test
    void tank_equipage_cannot_be_more_than_technical_max() {
        Tank tank = new Tank(3);
        tank.putOrc(new Orc());
        tank.putOrc(new Orc());
        tank.putOrc(new Orc());

        boolean sit = tank.putOrc(new Orc());

        assertFalse(sit);
        assertEquals(3, tank.getEquipage().size());
    }

    @Test
    void tank_driver_can_have_driver_license() {
        Tank tank = new Tank(3);
        Driver driver = new Driver(new SimpleCollection(DriverLicenseCategory.B, DriverLicenseCategory.BE));

        tank.putOrc(driver);

        assertEquals(1, tank.getEquipage().size());
        Driver actualDriver = (Driver) tank.getEquipage().getElements()[0];
        assertEquals(15_000, actualDriver.getPrice());

        Collection actualDriverLicenseCategories = actualDriver.getLicenseCategories();
        assertEquals(2, actualDriverLicenseCategories.size());
        assertEquals(DriverLicenseCategory.B, actualDriverLicenseCategories.getElements()[0]);
        assertEquals(DriverLicenseCategory.BE, actualDriverLicenseCategories.getElements()[1]);
    }

    @Test
    void shoot() {
        Tank tank = new Tank(3);
        tank.putOrc(new Driver());
        tank.putOrc(new Orc());

        String shoot = tank.shoot();

        assertEquals("Bam!", shoot);
    }

    @Test
    void destroy_empty() {
        Tank tank = new Tank(3);

        String result = tank.destroy();

        assertEquals("Destroyed!", result);
    }

    @Test
    void destroy_non_empty() {
        Tank tank = new Tank(3);
        tank.putOrc(new Driver());
        tank.putOrc(new Orc());
        tank.putOrc(new Orc());

        String result = tank.destroy();

        assertEquals("Destroyed!Crap!Aaaaaa!Aaaaaa!", result);
    }
}
