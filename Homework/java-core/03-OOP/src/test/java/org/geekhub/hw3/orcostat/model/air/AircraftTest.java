package org.geekhub.hw3.orcostat.model.air;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AircraftTest {
    @Test
    void aircraft_price_is_10_million() {
        final Aircraft aircraft = new Aircraft(new Pilot());

        assertEquals(10_000_000, aircraft.getPrice());
    }

    @Test
    void aircraft_equipage_is_only_pilot() {
        Pilot pilot = new Pilot(500);
        final Aircraft aircraft = new Aircraft(pilot);

        assertEquals(1, aircraft.getEquipage().size());
        Pilot actualPilot = (Pilot) aircraft.getEquipage().getElements()[0];
        assertEquals(actualPilot.getPrice(), 20000);
        assertEquals(actualPilot.getFlewHours(), 500);
    }

    @Test
    void shoot() {
        Aircraft aircraft = new Aircraft(new Pilot());

        String shoot = aircraft.shoot();

        assertEquals("Pew-pew-pew!", shoot);
    }

    @Test
    void destroy() {
        Aircraft aircraft = new Aircraft(new Pilot());

        String result = aircraft.destroy();

        assertEquals("Destroyed!Hell!", result);
    }
}
