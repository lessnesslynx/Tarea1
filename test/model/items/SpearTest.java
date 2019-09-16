package model.items;

import model.map.Location;
import model.units.Hero;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for spears
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpearTest extends AbstractTestItem {

  private Spear javelin;
  private Spear wrongSpear;
  private Hero hero;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpear = new Spear("Wrong spear", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(10, 10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpear;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return javelin;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  /**
   * Checks if Spear gets effective damage from Axe
   */
  @Override
  @Test
  public void getAxeDamageTest(){
    hero.setHitPoints(50);
    hero.equipSoul(spear);
    spear.getAxeDamage(hero,10);
    assertEquals(hero.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Spear gets resistant damage from Sword
   */
  @Override
  @Test
  public void getSwordDamageTest(){
    hero.setHitPoints(50);
    hero.equipSoul(spear);
    spear.getSwordDamage(hero,10);
    assertEquals(hero.getCurrentHitPoints(),50);
  }

  /**
   * Checks if Spear gets effective damage from Dark
   */
  @Override
  @Test
  public void getDarkDamageTest(){
    hero.setHitPoints(50);
    hero.equipSoul(spear);
    spear.getDarkDamage(hero,10);
    assertEquals(hero.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Spear gets effective damage from Light
   */
  @Override
  @Test
  public void getLightDamageTest(){
    hero.setHitPoints(50);
    hero.equipSoul(spear);
    spear.getLightDamage(hero,10);
    assertEquals(hero.getCurrentHitPoints(),35);
  }

  /**
   * Checks if Spear gets effective damage from Soul
   */
  @Override
  @Test
  public void getSoulDamageTest(){
    hero.setHitPoints(50);
    hero.equipSoul(spear);
    spear.getSoulDamage(hero,10);
    assertEquals(hero.getCurrentHitPoints(),35);
  }

  /**
   * Checks combat
   */
  @Override
  @Test
  void doCombatTest(){
    testSorcerer.equipLight(light);
    hero.equipSpear(spear);
    testSorcerer.setHitPoints(5);
    hero.setHitPoints(5);
    spear.doCombat(testSorcerer);
    assertEquals(0,testSorcerer.getCurrentHitPoints());
  }
}
