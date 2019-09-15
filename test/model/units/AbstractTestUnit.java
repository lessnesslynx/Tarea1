package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  private Alpaca targetAlpaca;
  Bow bow;
  Field field;
  Axe axe;
  Sword sword;
  Staff staff;
  Spear spear;
  Dark dark;
  Light light;
  Soul soul;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedSword(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipSword(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped, with an axe
   *
   * @param item
   *     to be equipped
   */
  public void checkEquippedAxe(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipAxe(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped, with a spear
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedSpear(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipSpear(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped, with a staff
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedStaff(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipStaff(item);
    assertNull(getTestUnit().getEquippedItem());
  }


  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped, with a bow
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedBow(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipBow(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedSword(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedSpear(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedStaff(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedBow(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  /**
   * Tests if the method gets the corresponding amount for max Hit Points
   */
    @Test
    void getMaxHitPoints() {
    assertEquals(targetAlpaca.getMaxHitPoints(),50);
    }

  /**
   * Tests if the method gets the corresponding amount of hit points
   */
  @Test
    void getCurrentHitPoints() {
    assertEquals(targetAlpaca.getMaxHitPoints(),50);
    axe.dealAxeDamage(axe,targetAlpaca);
    assertEquals(targetAlpaca.getCurrentHitPoints(),40);
    }

  /**
   * Tests if the method sets the corresponding amount of hit points
   */
  @Test
    void setHitPoints() {
    targetAlpaca.setHitPoints(45);
    assertEquals(targetAlpaca.getCurrentHitPoints(),45);
    }

  /**
   * Tests if the method gets a list of the current items of the unit
   */
  @Test
    void getItems() {
    ArrayList a = new ArrayList<>();
    assertEquals(targetAlpaca.getItems(),a);
    }

  /**
   * Tests if the method gets the corresponding object
   */
  @Test
    void getEquippedItem() {
    assertNull(targetAlpaca.getEquippedItem());
    targetAlpaca.setEquippedItem(axe);
    assertEquals(targetAlpaca.getEquippedItem(),axe);
    }

  /**
   * Tests if the method sets the corresponding object
   */
  @Test
    void setEquippedItem() {
      targetAlpaca.setEquippedItem(sword);
      assertEquals(targetAlpaca.getEquippedItem(),sword);
    }

  /**
   * Tests if the method gets the corresponding value
   */
  @Test
    void getLocation() {
      assertEquals(targetAlpaca.getLocation(),field.getCell(1, 0));
    }

  /**
   * Tests if the method sets the corresponding value
   */
  @Test
    void setLocation() {
      targetAlpaca.setLocation(field.getCell(1,1));
      assertEquals(targetAlpaca.getLocation(),field.getCell(1, 1));
    }

  /**
   * Tests if the method gets the corresponding value
   */
  @Test
    void getMovement() {
    assertEquals(targetAlpaca.getMovement(),2);
    }

  /**
   * Tests movement
   */
  @Test
    void moveTo() {
      targetAlpaca.moveTo(field.getCell(0,0));
      assertEquals(targetAlpaca.getLocation(),field.getCell(0, 0));
      targetAlpaca.moveTo(field.getCell(2,2));
      assertEquals(targetAlpaca.getLocation(),field.getCell(0, 0));
    }

  /**
   * Tests cases of normal damage
   */
  @Test
    void getNormalDamage() {
      targetAlpaca.setHitPoints(50);
      targetAlpaca.getNormalDamage(25);
      assertEquals(targetAlpaca.getCurrentHitPoints(),25);
      targetAlpaca.getNormalDamage(35);
      assertEquals(targetAlpaca.getCurrentHitPoints(),0);
    }

  /**
   *  Tests cases of resistant damage
   */
  @Test
    void getResistantDamage() {
      targetAlpaca.setHitPoints(50);
      targetAlpaca.getResistantDamage(25);
      assertEquals(targetAlpaca.getCurrentHitPoints(),45);
      targetAlpaca.getResistantDamage(15);
      assertEquals(targetAlpaca.getCurrentHitPoints(),45);
    }

  /**
   * Tests cases of Effective damage
   */
  @Test
    void getEffectiveDamage() {
      targetAlpaca.setHitPoints(50);
      targetAlpaca.getEffectiveDamage(20);
      assertEquals(targetAlpaca.getCurrentHitPoints(),20);
      targetAlpaca.getEffectiveDamage(20);
      assertEquals(targetAlpaca.getCurrentHitPoints(),0);
    }

  /**
   * Tests a simple heal on a unit
   */
  @Test
    void getHeal() {
      targetAlpaca.setHitPoints(20);
      targetAlpaca.getHeal(10);
      targetAlpaca.setHitPoints(30);
    }

  /**
   * Checks counter attack, sets a new weapon and a different unit to test counterattack
   */
  @Test
    void counterattack() {
      targetAlpaca.setHitPoints(50);
      targetAlpaca.setLocation(field.getCell(1,1));
      Fighter testEnemy = new Fighter(100,100,2,field.getCell(1, 0),axe);
      testEnemy.equipAxe(axe);
      targetAlpaca.setEquippedItem(axe);
      testEnemy.counterattack(testEnemy,axe,targetAlpaca);
      assertEquals(targetAlpaca.getCurrentHitPoints(),40);
    }

  @Test
  void equipStaff() {
    targetAlpaca.equipStaff(staff);
    assertNull(targetAlpaca.getEquippedItem());

  }

  @Test
  void equipBow() {
    targetAlpaca.equipBow(bow);
    assertNull(targetAlpaca.getEquippedItem());

  }
}
