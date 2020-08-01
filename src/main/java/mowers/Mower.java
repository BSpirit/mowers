package mowers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Mower extends Thread {

    private Coordinate coordinate;
    private Orientation orientation;
    private List<Instruction> instructions;
    private Lawn lawn;

    public Mower(Coordinate coordinate, Orientation orientation) {
        this.coordinate = coordinate;
        this.orientation = orientation;
        this.instructions = new LinkedList<>();
    }

    public static Mower fromString(String stringRepresentation) throws IllegalArgumentException {
        try {
            String[] fields = stringRepresentation.split(" ");
            Coordinate coordinate = new Coordinate(
                    Integer.parseInt(fields[0]),
                    Integer.parseInt(fields[1])
            );
            Orientation orientation = Orientation.fromValue(fields[2]);
            return new Mower(coordinate, orientation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not create Mower with '" +
                    stringRepresentation +"' representation");
        }
    }

    @Override
    public void run() {
        for (Instruction instruction: this.instructions) {
            switch (instruction) {
                case LEFT:
                    this.leftRotate();
                    break;
                case RIGHT:
                    this.rightRotate();
                    break;
                case FORWARD:
                    synchronized (this) {
                        Set<Coordinate> coordinates = this.getLawn().getOccupiedCoordinates();
                        System.out.println(coordinates);
                        System.out.println(this.getForwardCoordinate());
                        if (!coordinates.contains(this.getForwardCoordinate())) {
                            coordinates.remove(this.coordinate);
                            this.moveForward();
                            coordinates.add(this.coordinate);
                        }
                    }
                    break;
            }
        }
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public void leftRotate() {
        this.orientation = Orientation.leftRotate(this.orientation);
    }

    public void rightRotate() {
        this.orientation = Orientation.rightRotate(this.orientation);
    }

    public void moveForward() {
        int x = this.coordinate.getX();
        int y = this.coordinate.getY();
        switch (this.orientation) {
            case NORTH:
                this.coordinate.setY(y + 1);
                break;
            case WEST:
                this.coordinate.setX(x - 1);
                break;
            case SOUTH:
                this.coordinate.setY(y - 1);
                break;
            case EAST:
                this.coordinate.setX(x + 1);
                break;
        }
    }


    public Coordinate getForwardCoordinate() {
        int x = this.coordinate.getX();
        int y = this.coordinate.getY();
        switch (this.orientation) {
            case NORTH:
                return new Coordinate(x, y + 1);
            case WEST:
                return new Coordinate(x - 1, y);
            case SOUTH:
                return new Coordinate(x, y - 1);
            case EAST:
                return new Coordinate(x + 1, y);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.coordinate);
        sb.append(' ');
        sb.append(this.orientation);

        return sb.toString();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Lawn getLawn() {
        return lawn;
    }

    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }
}
