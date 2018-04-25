import java.util.TimerTask;

public class SimTask extends TimerTask {
    private SpringApplet springApplet;
    private SimEngine simEngine;
    private double timeStep;

    // Konstruktor z parametrami
    public SimTask(SpringApplet springApplet, SimEngine simEngine, double timeStep) {
        this.springApplet = springApplet;
        this.simEngine = simEngine;
        this.timeStep = timeStep;
    }

    // Metody związane z polem springApplet
    public SpringApplet getSpringApplet() {
        return this.springApplet;
    }

    public void setSpringApplet(SpringApplet springApplet) {
        this.springApplet = springApplet;
    }

    // Metody związane z polem simEngine
    public SimEngine getSimEngine() {
        return this.simEngine;
    }

    public void setSimEngine(SimEngine simEngine) {
        this.simEngine = simEngine;
    }

    // Metody związane z polem timeStep
    public double getTimeStep() {
        return this.timeStep;
    }

    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }

    // Metoda przedstawiająca krok symulacji
    @Override
    public void run() {
        simEngine.calculateSimulation(this.timeStep);
        springApplet.repaint();
    }
}
