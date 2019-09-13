package model.items;

import model.units.IUnit;

/**
 * This class represents a Dark Magic item.
 * <p>
 * Dark magic items are strong against weapons and soul magic but weak against soul light.
 *
 * @author Jorge Valenzuela
 * @since 1.1
 */

public class Dark extends AbstractItem {

    /**
     * Creates a new Dark Magic item
     *
     * @param name     the name of the Dark Magic item
     * @param power    the damage of the Dark Magic item
     * @param minRange the minimum range of the Dark Magic item
     * @param maxRange the maximum range of the Dark Magic item
     */
    public Dark(final String name, final int power, final int minRange, final int maxRange) {
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
        if (receiver_item instanceof Dark || receiver_item instanceof Axe || receiver_item instanceof Sword || receiver_item instanceof Spear || receiver_item instanceof Bow){
            /*efectivo*/
            damage = damage + (damage/2);
        }
        else if(receiver_item instanceof Soul){
            /*debil*/
            damage = damage - 20;
        }

        int attackerhp = attacker.getCurrentHitPoints();
        attackerhp = attackerhp - damage;
        if(attackerhp<0){
            attacker.setHitPoints(0);
        }
        else{
            attacker.setHitPoints(attackerhp);
        }

        return damage;
    }
}