package model.items;

import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units nut cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   *
   * @param healer The healing unit
   * @param receiver The receiven unit (the one who gets healed)
   * @return heal amount
   */
  public int getHeal(IUnit healer, IUnit receiver){
    int receiverhp = receiver.getCurrentHitPoints();
    receiverhp = receiverhp + 10;

    if(receiverhp > receiver.getMaxHitPoints()){
      receiver.setHitPoints(receiver.getMaxHitPoints());
    }
    return 10;
  }
}
