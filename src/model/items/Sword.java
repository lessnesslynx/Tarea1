package model.items;

import model.units.IUnit;

/**
 * This class represents a Sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

    /** Makes this weapon deal Sword damage to another unit
     *
     * @param receiverItem The equipped item of the unit who will receive damage
     * @param receiver A unit who will receive damage
     */
  private void dealSwordDamage(IEquipableItem receiverItem, IUnit receiver){
      int baseDamage = this.getPower();
      receiverItem.getSwordDamage(receiver,baseDamage);
  }



  /** Makes the unit receive Axe damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getAxeDamage(IUnit receiver, int baseDamage){
      receiver.getResistantDamage(baseDamage);
  }

  /** Makes the unit receive Spear damage depending on their item, overrides parent's method to make damage effective
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  @Override
  public void getSpearDamage(IUnit receiver, int baseDamage){
      receiver.getEffectiveDamage(baseDamage);
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



  /** Makes the unit receive Dark damage depending on their item, overrides parent's method to make damage resistant
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

  public void doCombat(IUnit receiver){
      dealSwordDamage(receiver.getEquippedItem(),receiver);
      if(receiver.getCurrentHitPoints() > 0){
          //do counterattack
      }
  }

}
