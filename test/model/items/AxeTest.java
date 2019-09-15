package model.items;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class AxeTest extends AbstractTestItem {

  private Axe axe;
  private Axe wrongAxe;
  private Fighter fighter;

  @Override
  public void setTestItem() {
    expectedName = "Common axe";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges set.
   */
  @Override
  public void setWrongRangeItem() {
    wrongAxe = new Axe("Wrong axe", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(10, 10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongAxe;
  }

  @Override
  public IEquipableItem getTestItem() {
    return axe;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if Axe gets regular damage from axes
   */
  @Override
  @Test
  public void getAxeDamageTest(){
    fighter.equipAxe(axe);
    axe.getAxeDamage(testFighter,10);
    assertEquals(testFighter.getCurrentHitPoints(),40);
  }

  /**
   * Checks if Axe gets effective damage from swords
   */
  @Override
  @Test
  public void getSwordDamageTest(){
    fighter.equipAxe(axe);
    axe.getSwordDamage(testFighter,10);
    assertEquals(35, testFighter.getCurrentHitPoints());
  }

  /**
   * Checks if Axe gets resistant damage from spears
   */
  @Override
  @Test
  public void getSpearDamageTest(){
    fighter.equipAxe(axe);
    axe.getSpearDamage(testFighter,10);
    assertEquals(testFighter.getCurrentHitPoints(),50);
  }

  /**
   * Checks if Axe gets effective damage from dark magic
   */
  @Override
  @Test
  public void getDarkDamageTest(){
    fighter.equipAxe(axe);
    axe.getDarkDamage(testFighter,10);
    assertEquals(testFighter.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Axe gets effective damage from light magic
   */
  @Override
  @Test
  public void getLightDamageTest(){
    fighter.equipAxe(axe);
    axe.getLightDamage(testFighter,10);
    assertEquals(testFighter.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Axe gets effective damage from soul magic
   */
  @Override
  @Test
  public void getSoulDamageTest(){
    fighter.equipAxe(axe);
    axe.getSoulDamage(testFighter,10);
    assertEquals(testFighter.getCurrentHitPoints(),35);
  }
}