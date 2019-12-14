package model;

import model.factory.ArcherFactory;
import model.factory.TacticianFactory;
import model.items.*;
import model.map.Field;
import model.map.Location;
import model.units.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TacticianTest {

    @Test
    void SelectUnit(){

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");

        Location testlocation = new Location(0,0);
        Archer archer = ArcherFactory.makeArcher(50,50,2,testlocation);

        tactician.SelectUnit(archer);

        assertEquals(archer,tactician.SelectedUnit);
    }

}
