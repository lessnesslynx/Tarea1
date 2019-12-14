package model.factory;

import model.map.Location;
import model.units.Alpaca;

public class AlpacaFactory {

    public static Alpaca makeAlpaca(int hitPoints, final int maxHitPoints, final int movement, final Location position){
        return new Alpaca(hitPoints,maxHitPoints,movement,position);
    }

}
