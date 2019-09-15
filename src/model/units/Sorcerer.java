package model.units;


import model.items.Dark;
import model.items.IEquipableItem;
import model.items.Light;
import model.items.Soul;
import model.map.Location;


public class Sorcerer extends AbstractUnit{

    public Sorcerer(int hitPoints, final int maxHitPoints, final int movement, final Location location,
                    IEquipableItem... items) {
        super(hitPoints, maxHitPoints, movement, location, 3, items);
    }


    @Override
    public void equipDark(IEquipableItem dark){
        equippedItem = dark;
    }

    @Override
    public void equipLight(IEquipableItem light){
        equippedItem = light;
    }

    @Override
    public void equipSoul(IEquipableItem soul){
        equippedItem = soul;
    }


}
