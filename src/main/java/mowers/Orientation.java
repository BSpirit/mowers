package mowers;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private String value;

    Orientation(String value) {
        this.value = value;
    }

    public static Orientation fromValue(String value) {
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

    public static Orientation leftRotate(Orientation currentOrientation) throws IllegalStateException {
        switch (currentOrientation) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                throw new IllegalStateException("Unexpected value: " + currentOrientation);
        }
    }

    public static Orientation rightRotate(Orientation currentOrientation) throws IllegalStateException {
        switch (currentOrientation) {
            case NORTH:
                return EAST;
            case WEST:
                return NORTH;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            default:
                throw new IllegalStateException("Unexpected value: " + currentOrientation);
        }
    }

    @Override
    public String toString() {
        return this.value;
    }
}
