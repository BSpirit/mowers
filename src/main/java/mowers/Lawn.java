package mowers;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents the Lawn used for simulation.
 * @author Tony Clonier
 */
public class Lawn {

    private final int width;
    private final int height;
    private final Set<Position> occupiedPositions = new HashSet<>();
    private final Lock lock = new ReentrantLock();

    /**
     * Creates a Lawn with specified dimensions.
     * @param width Lawn width
     * @param height Lawn height
     * @throws IllegalArgumentException if either width or height is negative
     */
    public Lawn(int width, int height) throws IllegalArgumentException {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Lawn dimensions cannot be negative");
        this.width = width;
        this.height = height;
    }

    /**
     * Returns true if position is in Lawn.
     * @param position Position to be tested
     * @return true if Position is in Lawn
     */
    public boolean inLawn(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x >= 0 && x < this.width) && (y >=0 && y < this.height);
    }

    /**
     * Returns true if Position is occupied.
     * A Position can be occupied by a Mower.
     * @param position Position to be tested
     * @return true if Position is occupied
     */
    public boolean isOccupied(Position position) {
        return this.occupiedPositions.contains(position);
    }

    /**
     * Adds position to occupied positions.
     * @param position Position to be added
     */
    public void addOccupiedPosition(Position position) {
        if (this.inLawn(position))
            this.occupiedPositions.add(position);
    }

    /** Removes position from occupied positions.
     * @param position Position to be removed
     */
    public void removeOccupiedPosition(Position position) {
        if (this.inLawn(position))
            this.occupiedPositions.remove(position);
    }

    /**
     * Locks Lawn instance to safely perform concurrent operations.
     */
    public void lock() {
        this.lock.lock();
    }

    /**
     * Unlocks Lawn instance.
     */
    public void unlock() {
        this.lock.unlock();
    }

    /**
     * Returns width attribute of Lawn.
     * @return width attribute of Lawn
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns height attribute of Lawn.
     * @return height attribute of Lawn
     */
    public int getHeight() {
        return height;
    }
}
