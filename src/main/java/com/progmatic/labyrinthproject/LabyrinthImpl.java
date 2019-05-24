package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private final Coordinate[][] rudimentaryLabirynthCoordinate;
    private final HashMap< Coordinate, CellType> labyrinthCells;
    private int width = -1;
    private int height = -1;
    RandomLabyrinthPlayer randomPlayer;

    public LabyrinthImpl(Coordinate[][] rudimentaryLabirynthCoordinate) {
        this.labyrinthCells = new HashMap<>();
        this.randomPlayer = new RandomLabyrinthPlayer();
        this.rudimentaryLabirynthCoordinate = rudimentaryLabirynthCoordinate;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            this.width = Integer.parseInt(sc.nextLine());
            this.height = Integer.parseInt(sc.nextLine());

            for (int hh = 0; hh < this.height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < this.width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            this.rudimentaryLabirynthCoordinate[hh][ww] = new Coordinate(hh, ww);
                            this.labyrinthCells.put(rudimentaryLabirynthCoordinate[hh][ww], CellType.WALL);
                            break;
                        case 'E':
                            this.rudimentaryLabirynthCoordinate[hh][ww] = new Coordinate(hh, ww);
                            this.labyrinthCells.put(rudimentaryLabirynthCoordinate[hh][ww], CellType.END);
                            break;
                        case 'S':
                            this.rudimentaryLabirynthCoordinate[hh][ww] = new Coordinate(hh, ww);
                            this.labyrinthCells.put(rudimentaryLabirynthCoordinate[hh][ww], CellType.START);
                            break;
                        case ' ':
                            this.rudimentaryLabirynthCoordinate[hh][ww] = new Coordinate(hh, ww);
                            this.labyrinthCells.put(rudimentaryLabirynthCoordinate[hh][ww], CellType.EMPTY);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        for (Map.Entry<Coordinate, CellType> entry : labyrinthCells.entrySet()) {
            if (entry.getKey().equals(c)) {
                return entry.getValue();
            }
        }
        throw new CellException(c, "Nincs ilyen koordináta");
    }

    @Override
    public void setSize(int width, int height) {

    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        for (Map.Entry<Coordinate, CellType> entry : labyrinthCells.entrySet()) {
            if (entry.getKey().equals(c)) {
                entry.setValue(type);
                break;
            }
        }
        throw new CellException(c, "A megadott koordináta nem létezik");
    }

    @Override
    public Coordinate getPlayerPosition() {
        return randomPlayer.getMyCoordinate();
    }

    @Override
    public boolean hasPlayerFinished() {
        for (Map.Entry<Coordinate, CellType> entry : labyrinthCells.entrySet()) {
            if (entry.getKey().equals(randomPlayer.getMyCoordinate()) && entry.getValue().equals(CellType.END)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> possibleDirections = new ArrayList<>();
        possibleDirections.add(Direction.EAST);
        possibleDirections.add(Direction.NORTH);
        possibleDirections.add(Direction.SOUTH);
        possibleDirections.add(Direction.WEST);
        return possibleDirections;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        
    }

    public void putPlayerIn() {
        for (Map.Entry<Coordinate, CellType> entry : labyrinthCells.entrySet()) {
            if (entry.getValue().equals(CellType.START)) {
                randomPlayer.setMyCoordinate(entry.getKey());
            }
        }
    }

}
