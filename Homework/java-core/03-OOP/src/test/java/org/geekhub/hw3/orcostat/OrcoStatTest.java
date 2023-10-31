package org.geekhub.hw3.orcostat;

import org.geekhub.hw3.orcostat.model.Orc;
import org.geekhub.hw3.orcostat.model.air.Aircraft;
import org.geekhub.hw3.orcostat.model.air.Pilot;
import org.geekhub.hw3.orcostat.model.ground.Driver;
import org.geekhub.hw3.orcostat.model.ground.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrcoStatTest {

    private OrcoStat orcoStat;

    @BeforeEach
    void setUp() {
        orcoStat = new OrcoStat();
    }

    @Test
    void track_negatively_alive_orcs() {
        orcoStat.smashOrc(new Orc());
        orcoStat.smashOrc(new Driver());
        orcoStat.smashOrc(new Pilot());

        int smashedOrcs = orcoStat.getNegativelyAliveOrcCount();

        assertEquals(3, smashedOrcs);
    }

    @Test
    void can_smash_empty_tank() {
        Tank tank = new Tank(3);
        orcoStat.smashTechnique(tank);

        assertEquals(0, orcoStat.getNegativelyAliveOrcCount());
    }

    @Test
    void can_smash_tank_with_equipage() {
        Tank tank = new Tank(3);
        tank.putOrc(new Driver());
        tank.putOrc(new Orc());

        orcoStat.smashTechnique(tank);

        assertEquals(2, orcoStat.getNegativelyAliveOrcCount());
    }

    @Test
    void combine_smashed_equipage_and_individual_orcs() {
        Tank tank = new Tank(3);
        tank.putOrc(new Driver());
        tank.putOrc(new Orc());
        tank.putOrc(new Orc());

        orcoStat.smashOrc(new Orc());
        orcoStat.smashTechnique(tank);

        int orcCount = orcoStat.getNegativelyAliveOrcCount();

        assertEquals(4, orcCount);
    }

    @Test
    void zero_damage_in_the_beginning() {
        int damage = orcoStat.getLosesInDollars();

        assertEquals(0, damage);
    }

    @Test
    void individual_orc_increase_damage_in_dollars() {
        orcoStat.smashOrc(new Orc());

        int damage = orcoStat.getLosesInDollars();

        assertEquals(10_000, damage);
    }

    @Test
    void tank_without_equipage_and_orc_damage_cost_in_dollars() {
        orcoStat.smashTechnique(new Tank(6));

        int damageInDollars = orcoStat.getLosesInDollars();

        assertEquals(3_000_000, damageInDollars);
    }

    @Test
    void tank_with_equipage_and_orc_damage_cost_in_dollars() {
        Tank tank = new Tank(6);
        tank.putOrc(new Orc());
        tank.putOrc(new Driver());

        orcoStat.smashTechnique(tank);

        int damageInDollars = orcoStat.getLosesInDollars();
        assertEquals(3_025_000, damageInDollars);
    }

    @Test
    void multiple_techniques_with_equipage_and_orc_damage_cost_in_dollars() {
        Tank tank = new Tank(6);
        tank.putOrc(new Driver());
        Aircraft aircraft = new Aircraft(new Pilot(100));

        orcoStat.smashTechnique(aircraft);
        orcoStat.smashTechnique(tank);
        orcoStat.smashOrc(new Orc());

        int damageInDollars = orcoStat.getLosesInDollars();

        assertEquals(13_045_000, damageInDollars);
    }
}
