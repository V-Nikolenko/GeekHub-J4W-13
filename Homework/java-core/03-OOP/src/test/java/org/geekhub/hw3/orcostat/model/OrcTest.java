package org.geekhub.hw3.orcostat.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrcTest {
    @Test
    void default_orc_price_is_lada_vesta_sport() {
        final Orc orc = new Orc();

        assertEquals(10_000, orc.getPrice());
    }

    @Test
    void major_orc_price_can_differ_to_lada_vesta_sport() {
        final Orc orc = new Orc(30_000);

        assertEquals(30_000, orc.getPrice());
    }

    @Test
    void orc_price_can_be_increased_on_demand() {
        final Orc orc = new Orc();
        orc.setPrice(50_000);

        assertEquals(50_000, orc.getPrice());
    }
}
