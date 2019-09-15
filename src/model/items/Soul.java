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


    /** Makes the item deal Soul damage to a unit
     *
     * @param receiverItem The equipped item of the unit who will receive damage
     * @param receiver A unit who will receive damage
     */
    private void dealSoulDamage(IEquipableItem receiverItem, IUnit receiver){
        int baseDamage = this.getPower();
        receiverItem.getSoulDamage(receiver,baseDamage);
    }

    /** Makes the unit receive Dark damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getDarkDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Makes the unit receive Light damage depending on their item, overrides parent's method to make damage resistant
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getLightDamage(IUnit receiver, int baseDamage){
        receiver.getResistantDamage(baseDamage);
    }

    /** Item does combat, inflicting Soul damage
     *
     * @param receiver the unit who will receive damage
     */
    public void doCombat(IUnit receiver){
        dealSoulDamage(receiver.getEquippedItem(),receiver);
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
        dealSoulDamage(target.getEquippedItem(),target);
    }
}