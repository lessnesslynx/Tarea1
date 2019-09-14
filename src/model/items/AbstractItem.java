package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  int maxRange;
  int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name     the name of the item
   * @param power    the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange the minimum range of the item
   * @param maxRange the maximum range of the item
   */
  AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  /**
   * Equips item to unit
   *
   * @param unit The unit who will equip the item
   */
  @Override
  public void equipTo(final IUnit unit) {
    unit.setEquippedItem(this);
    owner = unit;
  }

  //Region properties
  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }
  //endregion

  /** Makes the unit receive Sword damage depending on their item
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  public void getSwordDamage(IUnit receiver, int baseDamage){
    receiver.getNormalDamage(baseDamage);
  }

  /** Makes the unit receive Axe damage depending on their item
   *
   * @param receiver The unit who will receive damage
   */
  public void getAxeDamage(IUnit receiver, int baseDamage){
    receiver.getNormalDamage(baseDamage);
  }

  /** Makes the unit receive Spear damage depending on their item
   *
   * @param receiver The unit who will receive damage
   */
  public void getSpearDamage(IUnit receiver,int baseDamage){
    receiver.getNormalDamage(baseDamage);
  }

  /** Makes the unit receive Soul damage depending on their item
   *
   * @param receiver The unit who will receive damage
   */
  public void getSoulDamage(IUnit receiver, int baseDamage){
    receiver.getNormalDamage(baseDamage);
  }

  /** Makes the unit receive Dark damage depending on their item
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  public void getDarkDamage(IUnit receiver, int baseDamage){
    receiver.getNormalDamage(baseDamage);
  }

  /** Makes the unit receive Light damage depending on their item
   *
   * @param receiver The unit who will receive damage
   * @param baseDamage Damage without taking resistance or effectiveness into consideration yet
   */
  public void getLightDamage(IUnit receiver, int baseDamage){
    receiver.getNormalDamage(baseDamage);
  }

}

