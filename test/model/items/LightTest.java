package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LightTest extends AbstractTestItem {

    private Light light;
    private Light wrongLight;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common dark item";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        light = new Light(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongLight = new Light("Wrong dark item", 0, -1, -2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10,10,5,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongLight;
    }

    @Override
    public IEquipableItem getTestItem() {
        return light;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Checks if Dark gets regular damage from axes
     */
    @Override
    @Test
    public void getAxeDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        light.getAxeDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets regular damage from swords
     */
    @Override
    @Test
    public void getSwordDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        light.getSwordDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets regular damage from spears
     */
    @Override
    @Test
    public void getSpearDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        light.getSpearDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets effective damage from bows
     */
    @Override
    @Test
    public void getBowDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        light.getBowDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

}
