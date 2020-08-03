package mowers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Mower class
 * @author Tony Clonier
 */
class MowerTest {

    /**
     * Checks fromString method returns right Mower instance.
     */
    @Test
    void fromString() {
        // Given
        String representation = "1 1 N";

        // When
        Mower m = Mower.fromString(representation);

        // Then
        assertEquals(m.getOrientation(), Orientation.NORTH);
        assertEquals(m.getPosition(), new Position(1, 1));
    }

    /**
     * Checks fromString method throws illegalArgumentException if
     * input string representation is not valid.
     */
    @Test
    void fromStringThrowsException() {
        // Given
        String representation = "1 1";

        // Then
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Mower.fromString(representation));
        assertEquals("Could not create Mower with '1 1' representation", illegalArgumentException.getMessage());
    }

    /**
     * Checks getForwardPosition returns a valid forward position.
     */
    @Test
    void getForwardPosition() {
        // Given
        Mower[] mowers = {
                new Mower(new Position(1, 2), Orientation.NORTH),
                new Mower(new Position(1, 2), Orientation.EAST),
                new Mower(new Position(1, 2), Orientation.WEST),
                new Mower(new Position(1, 2), Orientation.SOUTH),
        };

        // When
        Position[] forwardPositions = new Position[mowers.length];
        for (int i = 0; i < forwardPositions.length; i++)
            forwardPositions[i] = mowers[i].getForwardPosition();

        // Then
        assertEquals(new Position(1, 3), forwardPositions[0]);
        assertEquals(new Position(2, 2), forwardPositions[1]);
        assertEquals(new Position(0, 2), forwardPositions[2]);
        assertEquals(new Position(1, 1), forwardPositions[3]);

    }

    /**
     * Checks mower will move forward when staying in the Lawn boundaries.
     */
    @Test
    void checksMoveForwardWhenInLawn() {
        // Given
        Mower m = new Mower(new Position(0, 0), Orientation.NORTH);
        m.setLawn(new Lawn(2, 2));

        // When
        m.moveForward();

        // Then
        assertEquals(new Position(0, 1), m.getPosition());
    }

    /**
     * Checks mower will discard moving instruction if exiting the Lawn boundaries.
     */
    @Test
    void checksMoveForwardWhenOutOfBoundaries() {
        // Given
        Mower[] mowers = {
                new Mower(new Position(0, 1), Orientation.NORTH),
                new Mower(new Position(1, 1), Orientation.EAST),
                new Mower(new Position(0, 0), Orientation.WEST),
                new Mower(new Position(1, 0), Orientation.SOUTH),
        };

        Lawn lawn = new Lawn(2, 2);
        for (Mower m: mowers) {
            m.setLawn(lawn);
        }

        // When
        for (Mower m: mowers) {
            m.moveForward();
        }

        // Then
        assertEquals(new Position(0, 1), mowers[0].getPosition());
        assertEquals(new Position(1, 1), mowers[1].getPosition());
        assertEquals(new Position(0, 0), mowers[2].getPosition());
        assertEquals(new Position(1, 0), mowers[3].getPosition());
    }


    /**
     * Checks Mower can follow a list of instructions.
     */
    @Test
    void run() throws InterruptedException {
        // Given
        Mower m = new Mower(new Position(0,0), Orientation.EAST);
        m.setLawn(new Lawn(2, 3)); // Upper right corner is (1, 2)
        m.addInstruction(Instruction.FORWARD);
        m.addInstruction(Instruction.LEFT);
        m.addInstruction(Instruction.FORWARD);
        m.addInstruction(Instruction.LEFT);
        m.addInstruction(Instruction.FORWARD);
        m.addInstruction(Instruction.RIGHT);
        m.addInstruction(Instruction.FORWARD);

        // when
        Thread t = new Thread(m);
        t.start();
        t.join();

        // Then
        assertEquals(new Position(0, 2), m.getPosition());
        assertEquals(Orientation.NORTH, m.getOrientation());
    }
}