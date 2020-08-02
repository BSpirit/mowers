package mowers;

import java.util.Objects;

/**
 * Represents cartesian coordinates.
 * @author Tony Clonier
 */
public class Position {

    private int x;
    private int y;

    /**
     * Creates a position with specified coordinates.
     * @param x Abscissa
     * @param y Ordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a String representation of a Position.
     * @return String representation of a Position
     */
    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

    /**
     * Returns true is the two Positions instances are equal.
     * Two Positions are considered equal if both their x and y attributes are equal.
     * @param o Position to be tested for equality
     * @return true if the two Positions are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return this.x == that.x && this.y == that.y;
    }

    /**
     * Returns the hash code value for this Position.
     * @return the integer hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns x attribute of Position.
     * @return x attribute of Position
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x attribute
     * @param x new value for y attribute
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns y attribute of Position.
     * @return y attribute of Position
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y attribute
     * @param y new value for y attribute
     */
    public void setY(int y) {
        this.y = y;
    }
}
