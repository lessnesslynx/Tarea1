package model.items;

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

  /*HACER TEST Y DOCUMENTAR*/
  @Override
  public int getDamage(IUnit attacker, IUnit receiver) {
    IEquipableItem receiver_item = receiver.EquippedItem();
    int damage = this.power;
    if (receiver_item instanceof Axe) {
      /*efectivo*/
      damage = damage + (damage / 2);
    } else if (receiver_item instanceof Spear || receiver_item instanceof LightMagic) {
      /*debil*/
      damage = damage - 20;
    }
    int attackerhp = attacker.getCurrentHitPoints();
    attackerhp = attackerhp - damage;
    if (attackerhp < 0) {
      attacker.currentHitPoints = 0
    } else {
      attacker.currentHitpoints = attackerhp
    }
  }

  return damage;
}
