package model.items;

import model.units.IUnit;

/**
 * This class represents a Light Magic item.
 * <p>
 * Light magic items are strong against weapons and dark magic but weak against soul magic.
 *
 * @author Jorge Valenzuela
 * @since 1.1
 */

public class Light extends AbstractItem {

    /**
     * Creates a new Light Magic item
     *
     * @param name     the name of the Light Magic item
     * @param power    the damage of the Light Magic item
     * @param minRange the minimum range of the Light Magic item
     * @param maxRange the maximum range of the Light Magic item
     */
    public Light(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /** Makes the item deal Light damage to a unit
     *
     * @param receiverItem The equipped item of the unit who will receive damage
     * @param receiver A unit who will receive damage
     */
    private void dealLightDamage(IEquipableItem receiverItem, IUnit receiver){
        int baseDamage = this.getPower();
        receiverItem.getLightDamage(receiver,baseDamage);
    }

    /** Makes the unit receive Dark damage depending on their item, overrides parent's method to make damage resistant
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getDarkDamage(IUnit receiver, int baseDamage){
        receiver.getResistantDamage(baseDamage);
    }

    /** Makes the unit receive Soul damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getSoulDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Item does combat, inflicting Light damage
     *
     * @param receiver the unit who will receive damage
     */
    public void doCombat(IUnit receiver){
        dealLightDamage(receiver.getEquippedItem(),receiver);
        if(receiver.getCurrentHitPoints() > 0){
            receiver.counterattack(receiver,receiver.getEquippedItem(),this.getOwner());
        }
    }

    /** Makes the unit receive Axe damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getAxeDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Makes the unit receive Sword damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getSwordDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Makes the unit receive Spear damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    public void getSpearDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }
    /** Makes the unit receive Bow damage depending on their item, overrides parent's method to make damage effective
     *
     * @param receiver The unit who will receive damage
     * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
     */
    void getBowDamage(IUnit receiver, int baseDamage){
        receiver.getEffectiveDamage(baseDamage);
    }

    /** Counter attack, it deals damage after an attack, by getting called after an attack
     *
     * @param target the target, who will receive damage on the counter attack
     */
    @Override
    public void doCounter(IUnit target){
        dealLightDamage(target.getEquippedItem(),target);
    }

}
