package mowers;


public class App {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Missing arg FILEPATH");
        } else {
            try {
                Simulation simulation = new Simulation();
                simulation.loadFromFile(args[0]);
                simulation.run();
                // Print the new positions of tested mowers
                for (Mower m: simulation.getMowers()) {
                    System.out.println(m);
                }
            } catch (Exception e) {
                System.out.println("Could not run simulation: " +
                        System.lineSeparator() +
                        e.getMessage());
            }
        }


    }
}
