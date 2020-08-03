package mowers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Class whose purpose is to simulate the movement of mowers located in a lawn.
 * A simulation object can be instantiated from a file using loadFromFile method.
 * Once a simulation is loaded, use run() method to run the simulation.
 * A FixedThreadPool is used to process the mowers.
 */
public class Simulation {

    private Lawn lawn;
    private final List<Mower> mowers = new ArrayList<>();
    private final int nWorkers = 4;
    private final ExecutorService service = Executors.newFixedThreadPool(this.nWorkers);

    /**
     * Runs the simulation.
     * @throws InterruptedException if interruption is received
     */
    public void run() throws InterruptedException {
        for (Mower m: this.mowers) {
            service.execute(m);
        }

        service.shutdown();
        // Wait for all tasks to be finished
        service.awaitTermination(3, TimeUnit.MINUTES);
    }

    /**
     * Creates a simulation from a file.
     * An example input file can be found in src/main/resources/
     * @param file path of the file to load
     * @throws FileNotFoundException if file doesn't exist
     */
    public void loadFromFile(String file) throws FileNotFoundException {
        // Close scanner if exception encountered
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
            this.parseLawn(sc);
            this.parseMowers(sc);
        }
    }

    /**
     * Private method used by loadFromFile to
     * parse a Lawn.
     * @param sc scanner used to read file
     */
    private void parseLawn(Scanner sc) throws IllegalArgumentException {
        try {
            String[] dimensions = sc.nextLine().split(" ");
            this.lawn = new Lawn(
                    Integer.parseInt(dimensions[0] + 1),
                    Integer.parseInt(dimensions[1] + 1)
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Could not parse Lawn");
        }
    }

    /**
     * Private method used by loadFromFile to
     * parse the mowers.
     * @param sc scanner used to read file
     */
    private void parseMowers(Scanner sc) throws IllegalArgumentException {
        while(sc.hasNextLine()) {
            Mower m = Mower.fromString(sc.nextLine());
            if (this.lawn.isOccupied(m.getPosition()))
                throw new IllegalArgumentException("Lawn cannot have mowers in the same positions");
            m.setLawn(this.lawn);
            this.lawn.addOccupiedPosition(m.getPosition());
            for (String instruction : sc.nextLine().split("")) {
                m.addInstruction(Instruction.fromValue(instruction));
            }

            this.mowers.add(m);
        }
    }

    /**
     * Returns the lawn used by the simulation.
     * @return lawn used by the simulation
     */
    public Lawn getLawn() {
        return lawn;
    }

    /**
     * Returns the mowers tested by the simulation.
     * @return mowers tested by the simulation.
     */
    public List<Mower> getMowers() {
        return mowers;
    }
}
