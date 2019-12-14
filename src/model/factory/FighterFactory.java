package model.factory;

import model.map.Location;
import model.units.Fighter;

public class FighterFactory {

    public static Fighter makeFighter(int hitPoints, final int maxHitPoints, final int movement, final Location position){
        return new Fighter(hitPoints,maxHitPoints,movement,position);
    }

}
