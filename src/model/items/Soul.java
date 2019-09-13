package model.items;

import model.units.IUnit;

/**
 * This class represents a Soul Magic item.
 * <p>
 * Soul magic items are strong against weapons and light magic but weak against dark magic.
 *
 * @author Jorge Valenzuela
 * @since 1.1
 */

public class Soul extends AbstractItem {

    /**
     * Creates a new Soul Magic item
     *
     * @param name     the name of the Soul Magic item
     * @param power    the damage of the Soul Magic item
     * @param minRange the minimum range of the Soul Magic item
     * @param maxRange the maximum range of the Soul Magic item
     */
    public Soul(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /**
     *
     * @param attacker The attacker unit
     * @param receiver The receiver unit
     * @return damage dealt
     */
    public int getDamage(IUnit attacker, IUnit receiver){
        IEquipableItem receiver_item = receiver.getEquippedItem();
        int damage = this.getPower();
        if (receiver_item instanceof Light || receiver_item instanceof Axe || receiver_item instanceof Sword || receiver_item instanceof Spear || receiver_item instanceof Bow){
            /*efectivo*/
            damage = damage + (damage/2);
        }
        else if(receiver_item instanceof Dark){
            /*debil*/
            damage = damage - 20;
        }

        int receiverhp = receiver.getCurrentHitPoints();
        receiverhp = receiverhp - damage;
        if(receiverhp<0){
            receiver.setHitPoints(0);
        }
        else{
            receiver.setHitPoints(receiverhp);
        }

        return damage;
    }
}