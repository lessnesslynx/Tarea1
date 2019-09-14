package model.items;

import model.units.IUnit;

/**
 * This class represents a Dark Magic item.
 * <p>
 * Dark magic items are strong against weapons and soul magic but weak against soul light. And strong against physical
 * weapons
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

    /** Makes the item deal Dark damage to a unit
     *
     * @param receiverItem The equipped item of the unit who will receive damage
     * @param receiver A unit who will receive damage
     */
    public void dealDarkDamage(IEquipableItem receiverItem, IUnit receiver){
        int baseDamage = this.getPower();
        receiverItem.getDarkDamage(receiverItem,receiver,baseDamage);
    }

    /** Makes the unit receive Light damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiverItem The equipped item of the user who will receive damage
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getLightDamage(IEquipableItem receiverItem, IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Makes the unit receive Soul damage depending on their item, overrides parent's method to make damage resistant
     *
     * @param receiverItem The equipped item of the user who will receive damage
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getSoulDamage(IEquipableItem receiverItem, IUnit receiver, int baseDamage){
        receiver.getResistantDamage(baseDamage);
    }

}