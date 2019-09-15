package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestItem {

  String expectedName;
  int expectedPower;
  short expectedMinRange;
  short expectedMaxRange;

  private Bow bow;
  private Axe axe;
  private Sword sword;
  private Staff staff;
  private Spear spear;
  Dark dark;
  private Light light;
  private Soul soul;

  private Field field = new Field();

  /**
   * Set up the game field
   */
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
            new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
            new Location(2, 1), new Location(2, 2));
  }

  Fighter testFighter = new Fighter(50, 50, 2, field.getCell(0, 1));
  private Sorcerer testSorcerer = new Sorcerer(50, 50, 2, field.getCell(0, 0));
  private SwordMaster swordMaster = new SwordMaster(50, 50, 2, field.getCell(0, 0));
  private Hero testHero = new Hero(50, 50, 2, field.getCell(0, 0));
  private Archer testArcher = new Archer(50,50,2,field.getCell(0, 0));

  /**
   * Creates a set of testing weapons
   */
  void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.dark = new Dark("Dark", 10,1,2);
    this.light = new Light("Light",10,1,2);
    this.soul = new Soul("Soul",10,1,2);
  }

  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  void setUp() {
    setTestItem();
    setWeapons();
    setWrongRangeItem();
    setTestUnit();
  }

  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItem();

  /**
   * Sets up an item with wrong ranges set.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnit();

  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
  }

  /**
   * @return the expected name of the item
   */
  String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  @Test
  void equippedToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    getTestItem().equipTo(unit);
    assertEquals(unit, getTestItem().getOwner());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();


  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective dark damage
   */
  @Test
  void getDarkDamageTest() {
    testFighter.equipAxe(axe);
    axe.dealAxeDamage(dark,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
  }

  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective light damage
   */
  @Test
  void getLightDamageTest() {
    testFighter.equipAxe(axe);
    axe.dealAxeDamage(light,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
  }

  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective and regular light damage
   */
  @Test
  void getSoulDamageTest() {
    testFighter.equipAxe(axe);
    axe.dealAxeDamage(soul,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
    axe.dealAxeDamage(axe,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),25);
  }

  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective and regular sword damage
   */
  @Test
  void getSwordDamageTest() {
    testFighter.equipAxe(axe);
    swordMaster.equipSword(sword);
    sword.dealSwordDamage(axe,testFighter);
    assertEquals(testFighter.getCurrentHitPoints(),35);
    sword.dealSwordDamage(sword,testFighter);
    assertEquals(testFighter.getCurrentHitPoints(),25);
  }

  /**
   * Checks that the a unit receives healing
   */
  @Test
  void getHealTest() {
    swordMaster.equipSword(sword);
    sword.dealSwordDamage(axe,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
    testSorcerer.getHeal(10);
    assertEquals(testSorcerer.getCurrentHitPoints(),45);
    testSorcerer.getHeal(10);
    assertEquals(testSorcerer.getCurrentHitPoints(),50);
  }

  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective, resistant and regular spear damage
   */
  @Test
  void getSpearDamageTest() {
    testFighter.equipAxe(axe);
    testHero.equipSpear(spear);
    spear.dealSpearDamage(soul,testFighter);
    assertEquals(testFighter.getCurrentHitPoints(),35);
    spear.dealSpearDamage(axe,testFighter);
    assertEquals(testFighter.getCurrentHitPoints(),35);
    spear.dealSpearDamage(spear,testFighter);
    assertEquals(testFighter.getCurrentHitPoints(),25);
  }

  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective and regular bow damage
   */
  @Test
  void getBowDamageTest() {
    testArcher.equipBow(bow);
    testHero.equipSpear(spear);
    testSorcerer.equipLight(light);
    bow.dealBowDamage(bow,testFighter);
    assertEquals(testFighter.getCurrentHitPoints(),40);
    bow.dealBowDamage(light,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
    bow.dealBowDamage(bow,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),25);
  }


  /**
   * Checks if counter attack does the corresponding damage
   */
  @Test
  void doCounterTest(){
    testSorcerer.equipLight(light);
    bow.doCounter(testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
  }

  /**
   * Checks that the a unit receives the corresponding damage
   * In this case, if an axe receives effective and regular axe damage
   */
  @Test
  void getAxeDamageTest(){
    testFighter.equipAxe(axe);
    testSorcerer.equipLight(light);
    axe.dealAxeDamage(light,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),35);
    axe.dealAxeDamage(axe,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),25);
    axe.dealAxeDamage(dark,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),10);
    axe.dealAxeDamage(soul,testSorcerer);
    assertEquals(testSorcerer.getCurrentHitPoints(),0);
  }

  /**
   * Checks combat
   */
  @Test
  void doCombatTest(){
    testFighter.equipAxe(axe);
    testSorcerer.equipLight(light);
    testSorcerer.setHitPoints(5);
    axe.doCombat(testSorcerer);
    assertEquals(0,testSorcerer.getCurrentHitPoints());
  }

  /**
   * Continuous attack sequence test to check if HPs keeps going down
   * Testing random effectiveness while doing it (specific weaknesses are tested in its subclasses)
   */
  @Test
  void DamageTest(){
    testFighter.equipAxe(axe);
    testSorcerer.equipLight(light);
    light.getSwordDamage(testFighter,10);
    assertEquals(50,testSorcerer.getCurrentHitPoints());
    assertEquals(35,testFighter.getCurrentHitPoints());
    bow.getSwordDamage(testFighter,10);
    assertEquals(25,testFighter.getCurrentHitPoints());
    bow.getLightDamage(testFighter,10);
    assertEquals(10,testFighter.getCurrentHitPoints());
    staff.getLightDamage(testSorcerer,10);
    assertEquals(40,testSorcerer.getCurrentHitPoints());
    dark.getSoulDamage(testSorcerer,10);
    assertEquals(40,testSorcerer.getCurrentHitPoints());
    soul.getLightDamage(testSorcerer,10);
    assertEquals(40,testSorcerer.getCurrentHitPoints());
    light.getDarkDamage(testSorcerer,10);
    assertEquals(40,testSorcerer.getCurrentHitPoints());
    soul.getDarkDamage(testSorcerer,10);
    assertEquals(25,testSorcerer.getCurrentHitPoints());
    light.getSoulDamage(testSorcerer,10);
    assertEquals(10,testSorcerer.getCurrentHitPoints());
    dark.getLightDamage(testSorcerer,10);
    assertEquals(0,testSorcerer.getCurrentHitPoints());



  }
}


