package model.items;

import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
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
    receiverItem.getAxeDamage(receiverItem,receiver);
  }

  /** Makes the unit receive Sword damage depending on their item, overrides parent's method to make damage effective
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   */
  @Override
  public void getSwordDamage(IEquipableItem receiverItem, IUnit receiver){
    int baseDamage = receiverItem.getPower();
    receiver.getEffectiveDamage(baseDamage);
  }

  /** Makes the unit receive Spear damage depending on their item, overrides parent's method to make damage resistant
   *
   * @param receiverItem The equipped item of the user who will receive damage
   * @param receiver The unit who will receive damage
   */
  @Override
  public void getSpearDamage(IEquipableItem receiverItem, IUnit receiver){
    int baseDamage = receiverItem.getPower();
    receiver.getResistantDamage(baseDamage);
  }


}
