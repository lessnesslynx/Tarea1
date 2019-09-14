package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
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
  public void dealSwordDamage(IEquipableItem receiverItem, IUnit receiver){
      receiverItem.getSwordDamage(receiverItem,receiver);
  }



  /** Makes the unit receive Axe damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   */
  @Override
  public void getAxeDamage(IEquipableItem receiverItem, IUnit receiver){
      int baseDamage = receiverItem.getPower();
      receiver.getResistantDamage(baseDamage);
  }

  /** Makes the unit receive Spear damage depending on their item, overrides parent's method to make damage effective
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   */
  @Override
  public void getSpearDamage(IEquipableItem receiverItem, IUnit receiver){
      int baseDamage = receiverItem.getPower();
      receiver.getEffectiveDamage(baseDamage);
  }


}
