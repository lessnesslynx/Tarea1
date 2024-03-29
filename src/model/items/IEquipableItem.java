package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();


  /** Makes a unit receive Sword damage
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  void getSwordDamage(IUnit receiver, int baseDamage);

  /** Makes a unit receive Spear damage
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  void getSpearDamage(IUnit receiver, int baseDamage);

  /** Makes a unit receive Axe damage
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  void getAxeDamage(IUnit receiver, int baseDamage);

  /** Makes a unit receive Soul damage
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  void getSoulDamage(IUnit receiver, int baseDamage);

  /** Makes a unit receive Dark damage
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  void getDarkDamage(IUnit receiver, int baseDamage);

  /** Makes a unit receive Light damage
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  void getLightDamage(IUnit receiver, int baseDamage);

  void doCounter(IUnit target);
}


