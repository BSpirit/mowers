package mowers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private Lawn lawn;
    private List<Mower> mowers;

    public void loadFromFile(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));
        try {
            if (sc.hasNextLine()) {
                String[] dimensions = sc.nextLine().split(" ");
                this.lawn = new Lawn(
                        Integer.parseInt(dimensions[0]),
                        Integer.parseInt(dimensions[1])
                );
            }

            this.mowers = new ArrayList<Mower>();
            while(sc.hasNextLine()) {
                String mower_line = sc.nextLine();
                Mower m = Mower.fromString(mower_line);
                m.setLawn(this.lawn);
                String instructions = sc.nextLine();
                for (String instruction: instructions.split("")) {
                    m.addInstruction(Instruction.fromValue(instruction));
                }

                this.mowers.add(m);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            sc.close();
        }
    }

    public void run() throws InterruptedException {
        for (Mower m: this.mowers) {
            this.getLawn().addOccupiedCoordinate(m.getCoordinate());
        }

        for (Mower m: this.mowers) {
            m.run();
        }

        for (Mower m: this.mowers) {
            m.join();
        }

        for (Mower m: this.mowers) {
            System.out.println(m);
        }
    }

    public Lawn getLawn() {
        return lawn;
    }

    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    public void setMowers(List<Mower> mowers) {
        this.mowers = mowers;
    }
}
