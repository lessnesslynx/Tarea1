package model.factory;

import model.map.Location;
import model.units.Cleric;

public class ClericFactory {

    public static Cleric makeCleric(int hitPoints, final int maxHitPoints, final int movement, final Location position){
        return new Cleric(hitPoints,maxHitPoints,movement,position);
    }

}
