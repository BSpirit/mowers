package mowers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Instruction enumeration
 * @author Tony Clonier
 */
class InstructionTest {

    /**
     * Checks if fromValue method returns right Instruction instance.
     */
    @Test
    void fromValue() {
        assertEquals(Instruction.FORWARD, Instruction.fromValue("F"));
        assertEquals(Instruction.LEFT, Instruction.fromValue("L"));
        assertEquals(Instruction.RIGHT, Instruction.fromValue("R"));
    }

    /**
     * Checks if fromValue method throws illegalArgumentException if
     * input value is not valid.
     */
    @Test
    void fromValueThrowsException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Instruction.fromValue("1"));
        assertEquals("Unexpected value: 1", illegalArgumentException.getMessage());
    }
}