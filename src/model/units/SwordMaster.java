package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(int hitPoints, final int maxHitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, maxHitPoints, movement, location, 3, items);
  }



  @Override
  public void equipSword(IEquipableItem sword){
    equippedItem = sword;
  }

}
