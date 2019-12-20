package projectStructure;

public class World {
    public static void main(String[] args) throws InterruptedException {
        Simulation sim = new Simulation(1000, 2000,  30);
        sim.startSimulation();
    }

}
