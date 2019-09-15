package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Jorge Valenzuela Navarrete
 */
public class SorcererTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    @Override
    public void checkEquippedItem(IEquipableItem item) {

    }


    @Test
    void equipDarkTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipDark(dark);
        assertEquals(dark, sorcerer.getEquippedItem());
    }


    @Test
    void equipLightTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipLight(light);
        assertEquals(light, sorcerer.getEquippedItem());
    }

    @Test
    void equipSoulTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipSoul(light);
        assertEquals(soul, sorcerer.getEquippedItem());
    }


}
