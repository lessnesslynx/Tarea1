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

  private void dealBowDamage(IEquipableItem receiverItem, IUnit receiver){
    int baseDamage = this.getPower();
    receiverItem.getAxeDamage(receiver,baseDamage);
  }

  /** Makes the unit receive Soul damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiver The unit who will receive damage
   */
  @Override
  public void getSoulDamage(IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Makes the unit receive Dark damage depending on their item, overrides parent's method to make damage effective
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getDarkDamage(IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Makes the unit receive Light damage depending on the item, overrides parent's method to make damage effective
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getLightDamage(IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Item does combat, inflicting Bow damage
   *
   * @param receiver the unit who will receive damage
   */
  public void doCombat(IUnit receiver){
    dealBowDamage(receiver.getEquippedItem(),receiver);
    if(receiver.getCurrentHitPoints() > 0){
      receiver.counterattack(receiver,receiver.getEquippedItem(),this.getOwner());
    }
  }

  /** Counter attack, it deals damage after an attack, by getting called after an attack
   *
   * @param target the target, who will receive damage on the counter attack
   */
  @Override
  public void doCounter(IUnit target){
    dealBowDamage(target.getEquippedItem(),target);
  }
}
