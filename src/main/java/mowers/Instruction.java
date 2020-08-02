package mowers;

/**
 * Represents possible Mower instructions.
 * @author Tony Clonier
 */
public enum Instruction {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    private final String value;

    /**
     * Private constructor used to create Instruction instances.
     * @param value String representation of Instruction
     */
    Instruction(String value) {
        this.value = value;
    }

    /**
     * Returns Instruction instance from String value
     * @param value String representation of Instruction. Must be "L", "R" or "F"
     * @return Instruction instance related to input value
     * @throws IllegalArgumentException if value is not "L", "R" or "F"
     */
    public static Instruction fromValue(String value) throws IllegalArgumentException {
        switch (value) {
            case "L":
                return LEFT;
            case "R":
                return RIGHT;
            case "F":
                return FORWARD;
            default:
                throw new IllegalArgumentException("Unexpected value: " + value);
        }
    }

    /**
     * Returns a String representation of an Instruction.
     * @return String representation of an Instruction
     */
    @Override
    public String toString() {
        return this.value;
    }
}
