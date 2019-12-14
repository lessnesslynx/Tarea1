package model.factory;

import model.map.Location;
import model.units.Sorcerer;

public class SorcererFactory {

    public static Sorcerer makeSorcerer(int hitPoints, final int maxHitPoints, final int movement, final Location position){
        return new Sorcerer(hitPoints,maxHitPoints,movement,position);
    }

}
