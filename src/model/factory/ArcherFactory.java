package model.factory;

import model.map.Location;
import model.units.Archer;

public class ArcherFactory {

    public static Archer makeArcher(int hitPoints, final int maxHitPoints, final int movement, final Location position){
        return new Archer(hitPoints,maxHitPoints,movement,position);
    }

}
