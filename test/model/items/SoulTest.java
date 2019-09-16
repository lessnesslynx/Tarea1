package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SoulTest extends AbstractTestItem {

    private Soul soul;
    private Soul wrongSoul;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common soul item";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        soul = new Soul(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongSoul = new Soul("Wrong dark item", 0, -1, -2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10,10,5,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongSoul;
    }

    @Override
    public IEquipableItem getTestItem() {
        return soul;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Check if dealLightDamage deals damage (and the appropriate amount of damage)
     */
    @Test
    void dealSoulDamageTest(){

        sorcerer.setHitPoints(50);

        soul.dealSoulDamage(dark,sorcerer);
        assertEquals(sorcerer.getCurrentHitPoints(),50);
        soul.dealSoulDamage(light,sorcerer);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Soul gets effective damage from Sword
     */
    @Override
    @Test
    public void getSwordDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipSoul(soul);
        soul.getSwordDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Soul gets effective damage from Spear
     */
    @Override
    @Test
    public void getSpearDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipSoul(soul);
        soul.getSpearDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Soul gets effective damage from Axe
     */
    @Override
    @Test
    public void getAxeDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipSoul(soul);
        soul.getAxeDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks if Soul gets effective damage from Bows
     */
    @Override
    @Test
    public void getBowDamageTest(){
        sorcerer.setHitPoints(50);
        sorcerer.equipSoul(soul);
        soul.getBowDamage(sorcerer,10);
        assertEquals(sorcerer.getCurrentHitPoints(),35);
    }

    /**
     * Checks combat
     */
    @Override
    @Test
    void doCombatTest(){
        testSorcerer.equipLight(soul);
        testFighter.equipSpear(axe);
        testSorcerer.setHitPoints(5);
        testFighter.setHitPoints(5);
        soul.doCombat(testSorcerer);
        assertEquals(0,testSorcerer.getCurrentHitPoints());
    }
}


