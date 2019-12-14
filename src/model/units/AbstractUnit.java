package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  public final List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  IEquipableItem equippedItem;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  AbstractUnit(final int maxHitPoints, int hitPoints, final int movement,
               final Location location, final int maxItems, IEquipableItem... items) {
    this.maxHitPoints = hitPoints;
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public int getMaxHitPoints(){ return maxHitPoints; }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public void setHitPoints(int hp){
    currentHitPoints = hp;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }


  /** Moves unit to a destination
   *
   * @param targetLocation Target destination
   */
  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public void getNormalDamage(int baseDamage){

    this.currentHitPoints = this.currentHitPoints - baseDamage;
    if(this.currentHitPoints < 0) {
      this.currentHitPoints = 0;
    }
  }

  /** Makes the unit receive resistant damage (-20) based on the base damage
   *
   * @param baseDamage Base damage
   */
  @Override
  public void getResistantDamage(int baseDamage){
    baseDamage = baseDamage - 20;
    if(baseDamage<0){baseDamage = 0;}
    this.currentHitPoints = this.currentHitPoints - baseDamage;
    if(currentHitPoints<0){currentHitPoints = 0;}
  }

  /** Makes the unit receive effective (x1.5) damage based on the base damage
   *
   * @param baseDamage Base damage
   */
  @Override
  public void getEffectiveDamage(int baseDamage){
    baseDamage = baseDamage + (baseDamage/2);
    this.currentHitPoints = this.currentHitPoints - baseDamage;
    if(this.currentHitPoints < 0) {
      this.currentHitPoints = 0;
    }
  }

  /** Makes the unit get healed
   *
   * @param power Power of the staff
   */
  public void getHeal(int power){
    this.currentHitPoints = this.currentHitPoints + power;
    if(this.currentHitPoints > this.maxHitPoints){
      this.currentHitPoints = this.maxHitPoints;
    }
  }

  public void counterattack(IUnit receiver, IEquipableItem item, IUnit attacker){
    if(attacker.getLocation().distanceTo(receiver.getLocation())>=item.getMinRange()&&attacker.getLocation().distanceTo(receiver.getLocation())<=item.getMaxRange()) {
      item.doCounter(attacker);
    }
  }

  /** Equips staff, only if the unit is able to equip a staff
   *
   * @param staff The staff to be equipped
   */
  public void equipStaff(IEquipableItem staff){/*does nothing equips nothing*/}

  /** Equips bow, if the unit is able to equip a bow
   *
   * @param staff The bow to be equipped
   */
  public void equipBow(IEquipableItem staff){/*does nothing equips nothing*/}

  /** Equips axe, only if the unit is able to equip an axe
   *
   * @param axe Axe to be equipped
   */
  public void equipAxe(IEquipableItem axe){/*does nothing equips nothing*/}

  /** Equips spear, only if the unit is able to equip a spear
   *
   * @param axe Spear to be equipped
   */
  public void equipSpear(IEquipableItem axe){/*does nothing equips nothing*/}

  /** Equips dark magic, only if the unit is able to equip it
   *
   * @param dark Dark magic to be equipped
   */
  public void equipDark(IEquipableItem dark){/*does nothing equips nothing*/}

  /** Equips light magic, only if the unit is able to equip it
   *
   * @param light Light magic to be equipped
   */
  public void equipLight(IEquipableItem light){/*does nothing equips nothing*/}

  /** Equips soul magic, only if the unit is able to equip it.
   *
   * @param soul Soul magic to be equipped
   */
  public void equipSoul(IEquipableItem soul){/*does nothing equips nothing*/}

  /** Equips sword, only if the unit is able to equip a sword
   *
   * @param sword Sword to be equipped
   */
  public void equipSword(IEquipableItem sword){/*does nothing equips nothing*/}
}
