package mowers;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Mower.
 * A Mower needs knowledge of the Lawn it is located in.
 * @author Tony Clonier
 */
public class Mower implements Runnable {

    private Position position;
    private Orientation orientation;
    private Lawn lawn;
    private List<Instruction> instructions = new LinkedList<>();

    /**
     * Creates a Mower with specified position and orientation
     * @param position current position of the Mower
     * @param orientation current orientation of the Mower
     */
    public Mower(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    /**
     * Returns a Mower from its string reprentation.
     * The representation is as followed: "x y ORIENTATION"
     * with x and y being integers and ORIENTATION being either N, E, W or S.
     * @param stringRepresentation String representation of a Mower
     * @return Mower instance related to string representation
     * @throws IllegalArgumentException if string representation is invalid
     */
    public static Mower fromString(String stringRepresentation) throws IllegalArgumentException {
        try {
            String[] fields = stringRepresentation.split(" ");
            Position position = new Position(
                    Integer.parseInt(fields[0]),
                    Integer.parseInt(fields[1])
            );
            Orientation orientation = Orientation.fromValue(fields[2]);
            return new Mower(position, orientation);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Could not create Mower with '" +
                    stringRepresentation +"' representation");
        }
    }

    /**
     * Reads and executes the list of instructions given to the Mower.
     * A Mower can either rotate (left/right) or move forward.
     * Any movement that would cause the mower to leave the lawn or
     * run into another mower is silently discarded.
     */
    @Override
    public void run() {
        for (Instruction instruction: this.instructions) {
            switch (instruction) {
                case LEFT:
                    this.orientation = this.orientation.left90Rotation();
                    break;
                case RIGHT:
                    this.orientation = this.orientation.right90Rotation();
                    break;
                case FORWARD:
                    this.moveForward();
                    break;
            }
        }
    }

    /**
     * Add instruction for Mower.
     * @param instruction Instruction to be added
     */
    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    /**
     * Moves the Mower one position forward.
     * Any movement that would cause the mower to leave the lawn or
     * run into another mower is silently discarded.
     * Method is thread safe.
     */
    public void moveForward() {
        Position forwardPosition = this.getForwardPosition();
        this.lawn.lock();
            if (this.lawn.inLawn(forwardPosition) && !this.lawn.isOccupied(forwardPosition)) {
                this.lawn.removeOccupiedPosition(this.position);
                this.lawn.addOccupiedPosition(forwardPosition);
                this.position = forwardPosition;
            }
        this.lawn.unlock();
    }

    /**
     * Returns the forward position of the Mower.
     * @return Forward position
     */
    public Position getForwardPosition() {
        int x = this.position.getX();
        int y = this.position.getY();
        switch (this.orientation) {
            case NORTH:
                return new Position(x, y + 1);
            case WEST:
                return new Position(x - 1, y);
            case SOUTH:
                return new Position(x, y - 1);
            case EAST:
                return new Position(x + 1, y);
            default:
                return null;
        }
    }

    /**
     * Returns a string represantation of a mower.
     * @return string representation of a mower
     */
    @Override
    public String toString() {
        return this.position + " " + this.orientation;
    }

    /**
     * Returns position of the mower.
     * @return position of the mower
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns orientation of the mower.
     * @return orientation of the mower
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns the instructions list of the mower.
     * @return instructions list of the mower
     */
    public List<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * Returns lawn in which the mower is located.
     * @return lawn in which the mower is located
     */
    public Lawn getLawn() {
        return lawn;
    }

    /**
     * Sets the lawn in which the mower is located
     * @param lawn new lawn in which the mower is located
     */
    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }
}
