package model.factory;

import model.map.Location;
import model.units.SwordMaster;

public class SwordMasterFactory {

    public static SwordMaster makeSwordMaster(int hitPoints, final int maxHitPoints, final int movement, final Location position){
        return new SwordMaster(hitPoints,maxHitPoints,movement,position);
    }

}
