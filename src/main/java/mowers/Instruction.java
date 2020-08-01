package mowers;

public enum Instruction {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    private String value;

    Instruction(String value) {
        this.value = value;
    }

    public static Instruction fromValue(String value) {
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
}
