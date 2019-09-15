package model.units;

import java.util.List;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {



  /** Gets the max hit points of the unit
   *
  *  @return max hit points of the unit
  */
  int getMaxHitPoints();

  /** Gets the current hit points of the unit
   *
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /** Sets the current hit points of the unit
   *
   * @param hp
   *    hit points to be set
   */
  void setHitPoints(int hp);

  /** Gets the list of items carried items by the unit
   *
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /** Gets the currently equipped Item
   *
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /** Makes the unit get Normal damage
   *
   * @param baseDamage Base damage
   */
  void getNormalDamage(int baseDamage);

  /** Makes the unit get Resistant (-20) damage
   *
   * @param baseDamage Base damage
   */
  void getResistantDamage(int baseDamage);

  /** Makes the unit get Effective (x1.5) damage
   *
   * @param baseDamage  Base damage
   */
  void getEffectiveDamage(int baseDamage);

  /** Makes the unit get healed
   *
   * @param power Power of the staff
   */
  void getHeal(int power);

  /** Issues counterattack
   *
   * @param receiver The unit who receives the damage of the attack, who (may) counterattacks
   * @param item The item to counterattack with
   * @param attacker The attacker to counterattack
   */
  void counterattack(IUnit receiver, IEquipableItem item, IUnit attacker);

  /** Equips a staff to a Sorcerer, nothing if the unit is not a Sorcerer
   *
   * @param staff The staff to be equipped
   */
  void equipStaff(IEquipableItem staff);

  /** Equips a bow to an Archer, nothing if the unit is not a Archer
   *
   * @param bow The bow to be equipped
   */
  void equipBow(IEquipableItem bow);

  void equipAxe(IEquipableItem axe);

  void equipSpear(IEquipableItem spear);

  void equipDark(IEquipableItem dark);

  void equipLight(IEquipableItem light);

  void equipSoul(IEquipableItem soul);

  void equipSword(IEquipableItem sword);
}
