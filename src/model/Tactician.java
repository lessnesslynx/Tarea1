package model;

import model.units.Archer;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/** This class represents a player
 *
 * @author Jorge Valenzuela
 */
public class Tactician {

    public List<IUnit> units = new ArrayList<>();

    private String name;

    //Tactician requiere Observer Pattern para observar cambios en el juego

    IUnit SelectedUnit;

    public Tactician(String name){
        this.name = name;
    }

    /**
     *
     * @param Unit Unit to be selected
     */
    void SelectUnit(IUnit Unit){
        this.SelectedUnit = Unit;
    }

    public String getName(){
        return name;
    }



}
