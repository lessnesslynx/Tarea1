package model.items;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

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
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /*HACER TEST Y DOCUMENTAR*/
  @Override
  public int getDamage(IUnit attacker, IUnit receiver){
    IEquipableItem receiver_item = receiver.EquippedItem();
    int damage = this.power;
    if (receiver_item instanceof Sword){
      /*efectivo*/
      damage = damage + (damage/2);
    }
    else if (receiver_item instanceof Axe || receiver_item instanceof LightMagic){
      /*debil*/
      damage = damage - 20;
    }
    int attackerhp = attacker.getCurrentHitPoints();
    attackerhp = attackerhp - damage;
    if(attackerhp<0){
      attacker.currentHitPoints = 0
    }
    else{
      attacker.currentHitpoints = attackerhp
    }
  }

  return damage;
}
