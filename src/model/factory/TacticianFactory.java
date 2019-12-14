package model.factory;

import model.Tactician;

public class TacticianFactory {

    public static Tactician makeNewTactician(String name){
        return new Tactician(name);
    }

}
