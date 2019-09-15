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
    private void dealDarkDamage(IEquipableItem receiverItem, IUnit receiver){
        int baseDamage = this.getPower();
        receiverItem.getDarkDamage(receiver,baseDamage);
    }

    /** Makes the unit receive Light damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getLightDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Makes the unit receive Soul damage depending on their item, overrides parent's method to make damage resistant
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getSoulDamage(IUnit receiver, int baseDamage){
        receiver.getResistantDamage(baseDamage);
    }

    /** Item does combat, inflicting Dark damage
     *
     * @param receiver the unit who will receive damage
     */
    public void doCombat(IUnit receiver){
        dealDarkDamage(receiver.getEquippedItem(),receiver);
        if(receiver.getCurrentHitPoints() > 0){
            receiver.counterattack(receiver,receiver.getEquippedItem(),this.getOwner());
        }
    }
    /** Counter attack, it deals damage after an attack, by getting called after an attack
     *
     * @param target the target, who will receive damage on the counter attack
     */
    @Override
    public void doCounter(IUnit target){
        dealDarkDamage(target.getEquippedItem(),target);
    }

}