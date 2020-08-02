package mowers;

/**
 * Represents possible Mower Orientation.
 * @author Tony Clonier
 */
public enum Orientation {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private final String value;

    /**
     * Private constructor used to create Orientation instances.
     * @param value String representation of an Orientation
     */
    Orientation(String value) {
        this.value = value;
    }

    /**
     * Returns Orientation instance from String value
     * @param value String representation of Orientation. Must be "N", "E", "W or "F"
     * @return Orientation instance related to input value
     * @throws IllegalArgumentException if value is not "N", "E", "W or "F"
     */
    public static Orientation fromValue(String value) throws IllegalArgumentException {
        switch (value) {
            case "N":
                return NORTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            case "S":
                return SOUTH;
            default:
                throw new IllegalArgumentException("Unexpected value: " + value);
        }
    }

    /**
     * Returns 90° left rotation of the Orientation
     * @return Rotated Orientation
     * @throws IllegalStateException if instance is not NORTH, WEST, EAST or SOUTH
     */
    public Orientation left90Rotation() throws IllegalStateException {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                throw new IllegalStateException("Unrecognised case: "+ this);
        }
    }

    /**
     * Returns 90° right rotation of the Orientation
     * @return Rotated Orientation
     * @throws IllegalStateException if instance is not NORTH, WEST, EAST or SOUTH
     */
    public Orientation right90Rotation() throws IllegalStateException {
        switch (this) {
            case NORTH:
                return EAST;
            case WEST:
                return NORTH;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            default:
                throw new IllegalStateException("Unrecognised case: "+ this);
        }
    }

    /**
     * Returns a String representation of an Orientation.
     * @return String representation of an Orientation
     */
    @Override
    public String toString() {
        return this.value;
    }
}
