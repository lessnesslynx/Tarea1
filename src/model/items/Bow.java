package model.items;

import model.units.IUnit;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Bow extends AbstractItem {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  /** Makes the unit receive Soul damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   */
  @Override
  public void getSoulDamage(IEquipableItem receiverItem, IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Makes the unit receive Dark damage depending on their item, overrides parent's method to make damage effective
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getDarkDamage(IEquipableItem receiverItem, IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Makes the unit receive Light damage depending on the item, overrides parent's method to make damage effective
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getLightDamage(IEquipableItem receiverItem, IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }
}
