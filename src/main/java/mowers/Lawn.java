package mowers;

import java.util.*;

public class Lawn {

    private int width;
    private int height;
    private Set<Coordinate> occupiedCoordinates;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
        this.occupiedCoordinates = new HashSet<>();
    }

    public void addOccupiedCoordinate(Coordinate coordinate) {
        this.occupiedCoordinates.add(coordinate);
    }

    public void removeOccupiedCoordinate(Coordinate coordinate) {
        this.occupiedCoordinates.remove(coordinate);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Coordinate> getOccupiedCoordinates() {
        return occupiedCoordinates;
    }

    public void setOccupiedCoordinates(Set<Coordinate> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }
}
