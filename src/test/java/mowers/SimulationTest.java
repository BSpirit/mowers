package mowers;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    /**
     * Checks a nominal case (No collision + in boundaries)
     */
    @Test
    void runNominalCase() throws FileNotFoundException, InterruptedException {
        // Given
        Simulation simulation = new Simulation();
        simulation.loadFromFile("src/test/resources/no_collision.txt");

        // When
        simulation.run();

        // Then
        Mower m0 = simulation.getMowers().get(0);
        Mower m1 = simulation.getMowers().get(1);
        assertEquals(new Position(1, 3), m0.getPosition());
        assertEquals(Orientation.NORTH, m0.getOrientation());
        assertEquals(new Position(5, 1), m1.getPosition());
        assertEquals(Orientation.EAST, m1.getOrientation());
    }
}