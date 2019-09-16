package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for swords
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordTest extends AbstractTestItem {

  private Sword sword;
  private Sword wrongSword;
  private SwordMaster swordMaster;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common sword";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    sword = new Sword(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSword = new Sword("Wrong sword", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(10, 10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSword;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return sword;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  /**
   * Checks if Sword gets effective damage from Dark
   */
  @Override
  @Test
  public void getDarkDamageTest(){
    swordMaster.setHitPoints(50);
    swordMaster.equipSoul(sword);
    sword.getDarkDamage(swordMaster,10);
    assertEquals(swordMaster.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Sword gets effective damage from Light
   */
  @Override
  @Test
  public void getLightDamageTest(){
    swordMaster.setHitPoints(50);
    swordMaster.equipSoul(sword);
    sword.getLightDamage(swordMaster,10);
    assertEquals(swordMaster.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Sword gets effective damage from Soul
   */
  @Override
  @Test
  public void getSoulDamageTest(){
    swordMaster.setHitPoints(50);
    swordMaster.equipSoul(sword);
    sword.getSoulDamage(swordMaster,10);
    assertEquals(swordMaster.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Sword gets effective damage from Spear
   */
  @Override
  @Test
  public void getSpearDamageTest(){
    swordMaster.setHitPoints(50);
    swordMaster.equipSoul(sword);
    sword.getSpearDamage(swordMaster,10);
    assertEquals(swordMaster.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Sword gets resistant damage from Axe
   */
  @Override
  @Test
  public void getAxeDamageTest(){
    swordMaster.setHitPoints(50);
    swordMaster.equipSoul(sword);
    sword.getAxeDamage(swordMaster,10);
    assertEquals(swordMaster.getCurrentHitPoints(),50);
  }

  /**
   * Checks combat
   */
  @Override
  @Test
  void doCombatTest(){
    testSorcerer.equipLight(light);
    swordMaster.equipSpear(sword);
    testSorcerer.setHitPoints(5);
    swordMaster.setHitPoints(5);
    sword.doCombat(testSorcerer);
    assertEquals(0,testSorcerer.getCurrentHitPoints());
  }
}
