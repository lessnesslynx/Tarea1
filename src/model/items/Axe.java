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

  /*HACER TEST Y DOCUMENTAR*/
  @Override
  public int getDamage(IUnit attacker, IUnit receiver){
    IEquipableItem receiver_item = receiver.getEquippedItem();
    int damage = this.getPower();
    if (receiver_item instanceof Spear || receiver_item instanceof Light || receiver_item instanceof Dark || receiver_item instanceof Soul){
      //efectivo
      damage = damage + (damage/2);
    }
    else if (receiver_item instanceof Sword){
      //debil
      damage = damage - 20;
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
