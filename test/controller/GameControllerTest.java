package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import model.Tactician;
import model.factory.*;
import model.items.*;
import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 128);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
  }

  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(128, gameMap.getSize()); // getSize deben definirlo
    assertTrue(controller.getGameMap().isConnected());
    Random testRandom = new Random(randomSeed);
    // Para testear funcionalidades que dependen de valores aleatorios se hacen 2 cosas:
    //  - Comprobar las invariantes de las estructuras que se crean (en este caso que el mapa tenga
    //    las dimensiones definidas y que sea conexo.
    //  - Setear una semilla para el generador de números aleatorios. Hacer esto hace que la
    //    secuencia de números generada sea siempre la misma, así pueden predecir los
    //    resultados que van a obtener.
    //    Hay 2 formas de hacer esto en Java, le pueden pasar el seed al constructor de Random, o
    //    usar el método setSeed de Random.
    //  ESTO ÚLTIMO NO ESTÁ IMPLEMENTADO EN EL MAPA, ASÍ QUE DEBEN AGREGARLO (!)
  }

  @Test
  void getTurnOwner() {
    //  En este caso deben hacer lo mismo que para el mapa
  }

  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).forEach(i -> {
      controller.initGame(randomTurnSequence.nextInt());
      assertEquals(randomTurnSequence.nextInt(), controller.getMaxRounds());
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
      controller.turnShuffler = new int[] {0,1,2,3};
    Tactician firstPlayer = controller.getTurnOwner();

    Tactician secondPlayer = new Tactician("Player 1");
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 1", tactician));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  void getWinners() {
      controller.tacticians = new ArrayList<>();
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 2").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  // Desde aquí en adelante, los tests deben definirlos completamente ustedes
  @Test
  void getSelectedUnit() {
      controller.initGame(2);

      Location testlocation = new Location(0,0);
      Location testlocation2 = new Location(0,1);
      Alpaca testunit = AlpacaFactory.makeAlpaca(50, 50,2,testlocation);
      Archer testunit2 = ArcherFactory.makeArcher(50,50,2,testlocation2);

      controller.selectedUnit = testunit;

      assertEquals(testunit,controller.getSelectedUnit());

      controller.selectedUnit = testunit2;

      assertEquals(testunit2,controller.getSelectedUnit());


  }

  @Test
  void selectUnitIn() {
  }

  @Test
  void getItems() {

      Location testlocation = new Location(0,0);

      Fighter fighter = FighterFactory.makeFighter(50,50,2,testlocation);

      Spear spear = new Spear("TestSpear",1,1,1);

      fighter.items.add(spear);

      List<IEquipableItem> eItems = new ArrayList<>();

      eItems.add(spear);

      controller.selectedUnit = fighter;

      assertEquals(eItems,controller.getItems());

  }

  @Test
  void equipItem() {

      Location testlocation = new Location(0,0);

      Cleric testunit = ClericFactory.makeCleric(50,50,2,testlocation);

      Staff staff = new Staff("TestStaff",1,1,1);

      controller.selectedItem = staff;

      controller.selectedUnit = testunit;

      Tactician tactician = TacticianFactory.makeNewTactician("Player 0");

      testunit.items.add(staff);

      controller.equipItem(0);

      assertEquals(controller.selectedUnit.getEquippedItem(),staff);

  }

  @Test
  void useItemOn() {

      Location testlocation = new Location(0,0);

      Location testlocation2 = new Location(0,1);

      Hero hero = HeroFactory.makeHero(50,50,2,testlocation);

      testlocation.setUnit(hero);

      Sorcerer sorcerer = SorcererFactory.makeSorcerer(50,50,2,testlocation2);

      Dark dark = new Dark("Dark",10,1,2);

      controller.field.addCell(testlocation);
      controller.field.addCell(testlocation2);
      controller.field.addConnection(testlocation,testlocation2);

      controller.selectedUnit=sorcerer;
      controller.selectedItem=dark;
      sorcerer.getItems().add(dark);
      controller.equipItem(0);

      controller.useItemOn(0,1);

      assertEquals(35,hero.getCurrentHitPoints());

  }

  @Test
  void selectItem() {

      Location testlocation = new Location(0,0);

      SwordMaster sm = SwordMasterFactory.makeSwordMaster(50,50,2,testlocation);
      Sword sword = new Sword("Sword",10,1,1);

      sm.items.add(sword);
      controller.selectedUnit=sm;
      controller.selectItem(0);

      assertEquals(sword,controller.selectedItem);
  }

  @Test
  void giveItemTo() {

  }

  @Test
    void generateConditionedRandom(){
      //Invariante a garantizar, el número que se le entrega NUNCA es el primero

      int [] semirandom = controller.generateConditionedRandom(0);
      assertNotEquals(0,semirandom[0]);

      semirandom = controller.generateConditionedRandom(1);
      assertNotEquals(1,semirandom[0]);

      semirandom = controller.generateConditionedRandom(2);
      assertNotEquals(2,semirandom[0]);

      semirandom = controller.generateConditionedRandom(3);
      assertNotEquals(3,semirandom[0]);
  }

    @Test
    void giveArcher() {

      Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
      Location testlocation = new Location(0,0);

      controller.giveArcher(tactician,testlocation);

      assertEquals(1,tactician.units.size());

    }

    @Test
    void giveAlpaca() {

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
        Location testlocation = new Location(0,0);

        controller.giveAlpaca(tactician,testlocation);

        assertEquals(1,tactician.units.size());
    }

    @Test
    void giveCleric() {

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
        Location testlocation = new Location(0,0);

        controller.giveCleric(tactician,testlocation);

        assertEquals(1,tactician.units.size());
    }

    @Test
    void giveFighter() {

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
        Location testlocation = new Location(0,0);

        controller.giveFighter(tactician,testlocation);

        assertEquals(1,tactician.units.size());
    }

    @Test
    void giveHero() {

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
        Location testlocation = new Location(0,0);

        controller.giveHero(tactician,testlocation);

        assertEquals(1,tactician.units.size());
    }

    @Test
    void giveSorcerer() {

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
        Location testlocation = new Location(0,0);

        controller.giveSorcerer(tactician,testlocation);

        assertEquals(1,tactician.units.size());
    }

    @Test
    void giveSwordMaster() {

        Tactician tactician = TacticianFactory.makeNewTactician("Player 0");
        Location testlocation = new Location(0,0);

        controller.giveSwordMaster(tactician,testlocation);

        assertEquals(1,tactician.units.size());
    }

    @Test
    void register(){

      Observer observer = new TacticianObserver();
      controller.observers = new ArrayList<>();
      controller.register(observer);

      assertEquals(1,controller.observers.size());

    }

    @Test
    void unregister(){
      Observer observer = new TacticianObserver();
      controller.observers = new ArrayList<>();
      controller.register(observer);
      controller.unregister(observer);

      assertEquals(0,controller.observers.size());
    }
}