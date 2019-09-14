package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

class LightTest extends AbstractTestItem {

    private Light light;
    private Light wrongLight;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common dark item";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        light = new Light(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongLight = new Light("Wrong dark item", 0, -1, -2);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10,10,5,new Location(0,0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongLight;
    }

    @Override
    public IEquipableItem getTestItem() {
        return light;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

}
