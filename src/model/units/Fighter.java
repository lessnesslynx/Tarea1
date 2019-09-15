package model.units;

import model.items.Axe;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter( int hitPoints, final int maxHitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, maxHitPoints, movement, location, 3, items);
  }


  /**
   *  Sets the currently equipped item of this unit. Axe in the case of a Fighter
   * @param axe The axe to be equipped
   */
  @Override
  public void equipAxe(final IEquipableItem axe){
    equippedItem = axe;
  }
}
