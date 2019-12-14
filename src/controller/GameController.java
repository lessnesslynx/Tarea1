package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.factory.*;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController implements ControllerSubscriber{


    Field field;
    List<Tactician> tacticians = new ArrayList<>();
    private int roundNumber;
    private int turnNumber;
    int[] turnShuffler;
    private int maxTurns;
    private int players;
    IUnit selectedUnit;
    IEquipableItem selectedItem;
    ArrayList<Observer> observers;

    /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
    GameController(int numberOfPlayers, int mapSize) {

    //crear Tacticians
    this.players=numberOfPlayers;
    int i;
    for(i=0;i<numberOfPlayers;i++){
      tacticians.add(TacticianFactory.makeNewTactician("Player "+i));
    }

    //crear mapa
    //Field field contiene el mapa

    Location[][] location;

    location = new Location[mapSize+1][mapSize+1];

    for(int j=0;j<mapSize+1;j++){
        for(int k=0;k<mapSize+1;k++){
            location[j][k] = new Location(j,k);
        }
    }

    this.field = new Field();

    for(int j=0;j<mapSize+1;j++){
        for(int k=0;k<mapSize+1;k++){
            this.field.addCell(location[j][k]);
        }
    }

    for(int j=0;j<mapSize;j++){
        for(int k=0;k<mapSize;k++){
            this.field.addConnection(location[j][k],location[j+1][k]);
            this.field.addConnection(location[j][k],location[j][k+1]);
        }
    }

    this.field.addConnection(location[mapSize][mapSize],location[mapSize-1][mapSize]);

    //Mapa no está aleatorizado, se puede jugar con las conexiones para generar un mapa aleatorizado





  }

  /** Gets the list of all the tacticians participating in the game
   *
   * @return the list of all the tacticians participating in the game.
   */
  List<Tactician> getTacticians() {
    return tacticians;
  }

  /** Gets the map of the current game
   *
   * @return the map of the current game
   */
  Field getGameMap() {
    return field;
  }

  /** Gets the tactician that's currently playing
   *
   * @return the tactician that's currently playing
   */
  Tactician getTurnOwner() {

    //turnNumber representa el número actual del turno
    //turnShuffler posee la información del orden del turno actual

    //Se obtiene el número del jugador actual con

    int playernumber = this.turnShuffler[this.turnNumber];

    return this.tacticians.get(playernumber);
  }

  /** Gets the number of rounds since the start of the game
   *
   * @return the number of rounds since the start of the game.
   */
  int getRoundNumber() {
    return this.roundNumber;
  }

  /** Gets the maximum nomber of rounds a match can last
   *
   * @return the maximum number of rounds a match can last
   */
  int getMaxRounds() {
    return maxTurns/players;
  }

  /**
   * Finishes the current player's turn.
   */
  void endTurn() {

    this.turnNumber++;

    if (this.turnNumber>3){
      this.turnNumber=0;
      this.roundNumber++;
    }

  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  void removeTactician(String tactician) {

    //Eliminar a las unidades

    //Eliminar al Tactician

    int tacticianindex = -1;

    for(int i = 0;i<tacticians.size();i++){
      if (tacticians.get(i).getName().equals(tactician)){
        tacticianindex=i;
      }
    }

    if(tacticianindex>=0 && tacticianindex<tacticians.size()){
      this.tacticians.remove(tacticianindex);
    }

  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  void initGame(final int maxTurns) {
    this.maxTurns=maxTurns;
    this.roundNumber = 1;

    this.turnNumber = 0;
    this.turnShuffler = new int[] {0, 1, 2, 3};

    //Iniciar juego

      //¿Crear 4 tacticians?

    for(int i=0;i<4;i++){
        this.tacticians.add(TacticianFactory.makeNewTactician("Player "+i));
    }
  }

  /**
   * Starts a game without a limit of turns.
   */
  void initEndlessGame() {

    //Iniciar juego

  }

  /** Gets the winner of winners of the game
   *
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  List<String> getWinners() {

    List<String> winners = new ArrayList<>();

    for(int i = 0;i<this.players;i++){
    winners.add(tacticians.get(i).getName());
    }
    return null;
  }

  /** Gets the selected unit
   *
   * @return the current player's selected unit
   */
  IUnit getSelectedUnit() {
    return this.selectedUnit;
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    //Selecciona unidad en x y

    //Field contiene la información
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return this.selectedUnit.getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  void equipItem(int index) {
    this.selectedUnit.getItems().get(index).equipTo(this.selectedUnit);
  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  void useItemOn(int x, int y) {

    //selectedUnit usa objeto en objetivo en x y

    //obtener objetivo en x y en infomación de field

      this.selectedUnit.getEquippedItem().doCombat(this.field.getMap().get("("+x+","+y+")").getUnit());

  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  void selectItem(int index) {

    this.selectedItem = this.selectedUnit.getItems().get(index);

  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {

      this.field.getCell(x,y).getUnit().getItems().add(this.selectedItem);

  }

  /**
   * Generates a string with a randomized turn order for a round given a "lastTurn" to avoid making the first turn of the round
   *
   * @param lastTurnNumber the number of the player that was the last turn in the previous round
   * @return a randomized turn order, which doesn't contain the last turn player from the previous round as the first turn of this round
   */
  int[] generateConditionedRandom(int lastTurnNumber){

    //Arreglo con arreglos donde 0 nuna es el primero

    int [][] shuffle = {{1,0,2,3},{1,0,3,2},{2,0,1,3},{2,0,3,1},{3,0,1,2},{3,0,2,1},{1,2,0,3},{1,3,0,2},{2,1,0,3},{2,3,0,1},{3,1,0,2},{3,2,0,1},{1,2,3,0},{1,3,2,0},{2,1,3,0},{2,3,1,0},{3,1,2,0},{3,2,1,0}};

    //Intercambiar lastTurnNumber con 0 para tener un resultado donde el último turno, correspondiente a lastTurnNumber no es el primero

    int temp;
    int tempindex=999;
    int zeroindex=999;

    int random = (int) (Math.random() * 18);

    int [] shuffleresult = shuffle[random];

    for(int i=0;i<4;i++){
      if(shuffleresult[i]==lastTurnNumber){
        tempindex=i;
      }
      if(shuffleresult[i]==0){
        zeroindex=i;
      }
    }

    temp = shuffleresult[tempindex];
    shuffleresult[tempindex]=0;
    shuffleresult[zeroindex]=temp;

    return shuffleresult;

    /*
    int [][] shuffle3_1 = {{0,2,3},{0,3,2},{2,0,3},{2,3,0},{3,0,2},{3,2,0}};
    int [][] shuffle3_2 = {{0,1,3},{0,3,1},{1,0,3},{1,3,0},{3,0,1},{3,1,0}};
    int [][] shuffle3_3 = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};

    */

    /*

    int[] randomIntegers;

    randomIntegers = new int[]{0,0,0,0};

    for(int i=0;i<4;i++){
      randomIntegers[i]= (int) Math.random()*99;
    }

    //lastTurnNumber corresponde al jugador al que le tocó el último turno

    //Aquí se decide al primer jugador

    int min=999;
    int secondmin=999;

    for(int i=0;i<4;i++){
      if(randomIntegers[i]<min){
        min = i;
        if(i!=0){
          secondmin=min;
        }
      }
    }

    if(min==lastTurnNumber){
      turnShuffler[0]=secondmin;
      turnShuffler[1]=min;
    }
    else{
      turnShuffler[0]=min;
      turnShuffler[1]=secondmin;
    }

    //Primer jugador decidido, decidir al resto de los jugadores
    //turnShuffler[0] es el primer jugador

    int third=999;
    int fourth=999;

    for(int i=0;i<4;i++){
      if(i!=min&&i!=secondmin){
        third = i;
      }
      if(i!=min&&i!=secondmin&&i!=third){
        fourth = i;
      }
    }

    return 0;

    */


  }

    /** Gives archer to tactician at location
     *
     * @param tactician Tactician who will receive the archer
     * @param location location at which receive the archer
     */
    void giveArcher(Tactician tactician, Location location){

      Archer archer = ArcherFactory.makeArcher(50,50,4,location);
      tactician.units.add(archer);

  }

    /** Gives alpaca to tactician at location
     *
     * @param tactician Tactician who will receive the alpaca
     * @param location Location at which receive the alpaca
     */
    void giveAlpaca(Tactician tactician, Location location){

      Alpaca alpaca = AlpacaFactory.makeAlpaca(50,50,4,location);
      tactician.units.add(alpaca);
  }

    /** Gives cleric to tactician at location
     *
     * @param tactician Tactician who will receive the cleric
     * @param location Location at which receive the cleric
     */
    void giveCleric(Tactician tactician, Location location){

      Cleric cleric = ClericFactory.makeCleric(50,50,4,location);
      tactician.units.add(cleric);
    }

    /** Gives fighter to tactician at location
     *
     * @param tactician Tactician who will receive the fighter
     * @param location Location at which receive the fighter
     */
    void giveFighter(Tactician tactician, Location location){

      Fighter fighter = FighterFactory.makeFighter(50,50,4,location);
      tactician.units.add(fighter);

    }

    /** Gives hero to tactician at location
     *
     * @param tactician Tactician who will receive the hero
     * @param location Location at which receive the hero
     */
    void giveHero(Tactician tactician, Location location){

        Hero hero = HeroFactory.makeHero(50,50,4,location);
        tactician.units.add(hero);

    }

    /** Gives sorcerer to tactician at location
     *
     * @param tactician Tactician who will receive the sorcerer
     * @param location Location at which to receive the sorcerer
     */
    void giveSorcerer(Tactician tactician, Location location){

        Sorcerer sorcerer = SorcererFactory.makeSorcerer(50,50,4,location);
        tactician.units.add(sorcerer);

    }

    /** Gives a sword master to a tactician at location
     *
     * @param tactician Tactician who will receive the sword master
     * @param location Location at which to receive the sword master
     */
    void giveSwordMaster(Tactician tactician, Location location){

        SwordMaster sm = SwordMasterFactory.makeSwordMaster(50,50,4,location);
        tactician.units.add(sm);

    }

    /** Registers a new observer to the controller
     *
     * @param newObserver The new observer to be registered
     */
    @Override
    public void register(Observer newObserver) {
        this.observers.add(newObserver);
    }

    /**
     * Unregisters a observer from the controller
     *
     * @param observer The observer to be removed
     */
    @Override
    public void unregister(Observer observer) {

        observers.remove(observer);

    }
}
