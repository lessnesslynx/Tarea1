package model.units;

import model.items.Bow;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an <i>Archer</i> type unit.
 * <p>
 * This kind of unit <b>can only use bows</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Archer extends AbstractUnit {

  /**
   * Creates a new archer
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param position
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(int hitPoints,final int maxHitPoints, final int movement, final Location position,
      final IEquipableItem... items) {
    super(hitPoints, maxHitPoints, movement, position, 3, items);
  }




  /**
   *  Sets the currently equipped item of this unit. Bow in the case of an archer
   * @param bow The bow to be equipped
   */
  @Override
  public void equipBow(final IEquipableItem bow) {

    equippedItem = bow;

  }
}
