package model.factory;

import model.map.Location;
import model.units.Hero;

public class HeroFactory {

    public static Hero makeHero(int hitPoints,final int maxHitPoints, final int movement, final Location position){
        return new Hero(hitPoints,maxHitPoints,movement,position);
    }

}
