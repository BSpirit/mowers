package mowers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Lawn class.
 * @author Tony Clonier
 */
class LawnTest {

    /**
     * Checks Lawn construction throws IllegalArgumentException
     * when input parameters are not valid.
     */
    @Test
    void lawnConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Lawn(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Lawn(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Lawn(1, -1));
    }

    /**
     * Checks inLawn method returns true if input position is in the Lawn
     * boundaries.
     */
    @Test
    void inLawn() {
        // Given
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(1, 1);

        // Then
        assertTrue(lawn.inLawn(position));
    }

    /**
     * Checks inLawn method returns false if input position is not in the Lawn
     * boundaries.
     */
    @Test
    void notInLawn() {
        // Given
        Lawn lawn = new Lawn(5, 5);
        Position position1 = new Position(6, 1);
        Position position2 = new Position(1, 6);
        Position position3 = new Position(-1, 1);
        Position position4 = new Position(1, -1);

        // Then
        assertFalse(lawn.inLawn(position1));
        assertFalse(lawn.inLawn(position2));
        assertFalse(lawn.inLawn(position3));
        assertFalse(lawn.inLawn(position4));
    }

    /**
     * Checks an occupied position can be specified in the Lawn.
     */
    @Test
    void checkAddOccupiedPositionAndIsOccupied() {
        // Given
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(1, 1);

        // When
        lawn.addOccupiedPosition(position);

        // Then
        assertTrue(lawn.isOccupied(position));
        assertFalse(lawn.isOccupied(new Position(0, 0)));
    }

    /**
     * Checks an occupied position can be unset in the Lawn.
     */
    @Test
    void removeOccupiedPosition() {
        // Given
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(1, 1);
        lawn.addOccupiedPosition(position);

        // When
        lawn.removeOccupiedPosition(position);


        // Then
        assertFalse(lawn.isOccupied(position));
    }
}