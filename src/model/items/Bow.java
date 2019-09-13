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

  /**
   *
   * @param attacker The attacker unit
   * @param receiver The receiver unit
   * @return damage dealt
   */
  @Override
  public int getDamage(IUnit attacker, IUnit receiver){
    IEquipableItem receiver_item = receiver.getEquippedItem();
    int damage = this.getPower();
    if (receiver_item instanceof Light || receiver_item instanceof Dark || receiver_item instanceof Soul){
      /*efectivo*/
      damage = damage + (damage/2);
    }

    int attackerhp = attacker.getCurrentHitPoints();
    attackerhp = attackerhp - damage;
    if(attackerhp<0){
      attacker.setHitPoints(0);
    }
    else{
      attacker.setHitPoints(attackerhp);
    }

    return damage;
  }


}
