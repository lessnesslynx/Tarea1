package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DarkTest extends AbstractTestItem {

    private Dark dark;
    private Dark wrongDark;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common dark magic";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        dark = new Dark(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongDark = new Dark("Wrong dark magic", 0, -1, -2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10,10,5,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongDark;
    }

    @Override
    public IEquipableItem getTestItem() {
        return dark;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

}