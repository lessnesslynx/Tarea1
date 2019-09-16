package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Archer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

/**
 * Test set for bows
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class BowTest extends AbstractTestItem {

  private Bow bow;
  private Bow wrongBow;
  private Archer archer;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common bow";
    expectedPower = 8;
    expectedMinRange = 2;
    expectedMaxRange = 4;
    bow = new Bow(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges set.
   */
  @Override
  public void setWrongRangeItem() {
    wrongBow = new Bow("Wrong bow", 10, 1, 1);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(10, 10, 5, new Location(0, 0));
  }

  /**
   * Checks that the minimum range for a bow is greater than 1
   */
  @Override
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() > 1);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongBow;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return bow;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if Bow gets regular damage from axes
   */
  @Override
  @Test
  public void getAxeDamageTest(){
    archer.setHitPoints(50);
    archer.equipAxe(bow);
    bow.getSwordDamage(archer,10);
    assertEquals(archer.getCurrentHitPoints(),40);
  }

  /**
   * Checks if Bow gets regular damage from swords
   */
  @Override
  @Test
  public void getSwordDamageTest(){
    archer.setHitPoints(50);
    archer.equipAxe(bow);
    bow.getSwordDamage(archer,10);
    assertEquals(archer.getCurrentHitPoints(),40);
  }

  /**
   * Checks if Bow gets regular damage from spears
   */
  @Override
  @Test
  public void getSpearDamageTest(){
    archer.setHitPoints(50);
    archer.equipAxe(bow);
    bow.getSwordDamage(archer,10);
    assertEquals(archer.getCurrentHitPoints(),40);
  }

  /**
   * Checks if Bow gets effective damage from dark
   */
  @Override
  @Test
  public void getDarkDamageTest(){
    archer.setHitPoints(50);
    archer.equipAxe(bow);
    bow.getDarkDamage(archer,10);
    assertEquals(archer.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Bow gets effective damage from light
   */
  @Override
  @Test
  public void getLightDamageTest(){
    archer.setHitPoints(50);
    archer.equipAxe(bow);
    bow.getLightDamage(archer,10);
    assertEquals(archer.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Bow gets effective damage from Soul
   */
  @Override
  @Test
  public void getSoulDamageTest(){
    archer.setHitPoints(50);
    archer.equipAxe(bow);
    bow.getSoulDamage(archer,10);
    assertEquals(archer.getCurrentHitPoints(),35);
  }

  /**
   * Checks combat
   */
  @Override
  @Test
  void doCombatTest(){
    testSorcerer.equipLight(light);
    archer.equipSpear(bow);
    testSorcerer.setHitPoints(5);
    archer.setHitPoints(5);
    bow.doCombat(testSorcerer);
    assertEquals(0,testSorcerer.getCurrentHitPoints());
  }
}

