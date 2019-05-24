/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.Random;

/**
 *
 * @author N007
 */
public class RandomLabyrinthPlayer implements Player {

    protected Coordinate myCoordinate = new Coordinate(0, 0);

    public RandomLabyrinthPlayer() {
    }

    

    @Override
    public Direction nextMove(Labyrinth l) {
        int pick = new Random().nextInt(Direction.values().length);
        return Direction.values()[pick];
    }

    public Coordinate getMyCoordinate() {
        return myCoordinate;
    }

    public void setMyCoordinate(Coordinate myCoordinate) {
        this.myCoordinate = myCoordinate;
    }
    
    

}
