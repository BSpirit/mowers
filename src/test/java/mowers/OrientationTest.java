package mowers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Orientation enumeration
 * @author Tony Clonier
 */
class OrientationTest {

    /**
     * Checks if fromValue method returns right Orientation instance.
     */
    @Test
    void fromValue() {
        assertEquals(Orientation.NORTH, Orientation.fromValue("N"));
        assertEquals(Orientation.EAST, Orientation.fromValue("E"));
        assertEquals(Orientation.SOUTH, Orientation.fromValue("S"));
        assertEquals(Orientation.WEST, Orientation.fromValue("W"));
    }

    /**
     * Checks if fromValue method throws illegalArgumentException if
     * input value is not valid.
     */
    @Test
    void fromValueThrowsException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Orientation.fromValue("1"));
        assertEquals("Unexpected value: 1", illegalArgumentException.getMessage());
    }

    /**
     * Checks left90Rotation returns the right Orientation instance.
     */
    @Test
    void left90Rotation() {
        assertEquals(Orientation.NORTH.left90Rotation(), Orientation.WEST);
        assertEquals(Orientation.WEST.left90Rotation(), Orientation.SOUTH);
        assertEquals(Orientation.SOUTH.left90Rotation(), Orientation.EAST);
        assertEquals(Orientation.EAST.left90Rotation(), Orientation.NORTH);
    }

    /**
     * Checks right90Rotation returns the right Orientation instance.
     */
    @Test
    void right90Rotation() {
        assertEquals(Orientation.NORTH.right90Rotation(), Orientation.EAST);
        assertEquals(Orientation.EAST.right90Rotation(), Orientation.SOUTH);
        assertEquals(Orientation.SOUTH.right90Rotation(), Orientation.WEST);
        assertEquals(Orientation.WEST.right90Rotation(), Orientation.NORTH);
    }
}