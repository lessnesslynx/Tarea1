package model.items;

import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /** Makes the item deal Axe damage to a unit
   *
   * @param receiverItem The equipped item of the unit who will receive damage
   * @param receiver A unit who will receive damage
   */
  public void dealAxeDamage(IEquipableItem receiverItem, IUnit receiver){
    int baseDamage = this.getPower();
    receiverItem.getAxeDamage(receiver,baseDamage);
  }

  /** Makes the unit receive Sword damage depending on their item, overrides parent's method to make damage effective
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getSwordDamage(IUnit receiver, int baseDamage){
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Makes the unit receive Spear damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getSpearDamage(IUnit receiver, int baseDamage){
    receiver.getResistantDamage(baseDamage);
  }

  /** Makes the unit receive Soul damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
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

  /** Item does combat, inflicting Axe damage
   *
   * @param receiver the unit who will receive damage
   */
  @Override
  public void doCombat(IUnit receiver){
    dealAxeDamage(receiver.getEquippedItem(),receiver);
    if(receiver.getCurrentHitPoints() > 0){
      receiver.counterattack(receiver,this,this.getOwner());
    }

  }


  /** Counter attack, it deals damage after an attack, by getting called after an attack
   *
   * @param target the target, who will receive damage on the counter attack
   */
  @Override
  public void doCounter(IUnit target){
    dealAxeDamage(target.getEquippedItem(),target);
  }


}
