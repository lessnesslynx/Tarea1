package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DarkTest extends AbstractTestItem {

    private Dark dark;
    private Dark wrongDark;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common dark magic";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        dark = new Dark(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongDark = new Dark("Wrong dark magic", 0, -1, -2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10,10,5,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongDark;
    }

    @Override
    public IEquipableItem getTestItem() {
        return dark;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Checks if Dark gets effective damage from axes
     */
    @Override
    @Test
    public void getAxeDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        dark.getAxeDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets effective damage from swords
     */
    @Override
    @Test
    public void getSwordDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        dark.getSwordDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets effective damage from spears
     */
    @Override
    @Test
    public void getSpearDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        dark.getSpearDamage(sorcerer,10);
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
        dark.getBowDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets effective damage from light
     */
    @Override
    @Test
    public void getLightDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        dark.getLightDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Dark gets regular damage from Dark
     */
    @Override
    @Test
    public void getDarkDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipAxe(dark);
        dark.getDarkDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),40);
    }

    /**
     * Check if dealDarkDamage deals damage (and the appropriate amount of damage)
     */
    @Test
    void dealDarkDamageTest(){

        sorcerer.setHitPoints(50);

        dark.dealDarkDamage(dark,sorcerer);
        assertEquals(sorcerer.getCurrentHitPoints(),40);
        dark.dealDarkDamage(light,sorcerer);
        assertEquals(sorcerer.getCurrentHitPoints(),40);
    }

}