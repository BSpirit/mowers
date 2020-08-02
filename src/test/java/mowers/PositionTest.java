package mowers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests Position class
 * @author Tony Clonier
 */
class PositionTest {

    /**
     * Tests equals method returns true in the right condition
     */
    @Test
    public void testEquals() {
        assertEquals(new Position(1, 2), new Position(1, 2));
    }

    /**
     * Tests equals method returns false in the right condition
     */
    @Test
    public void testNotEquals() {
        assertNotEquals(new Position(2, 1), new Position(1, 2));
    }
}