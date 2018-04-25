import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.Locale;

public class SpringApplet extends JApplet {
    private SimTask simTask;
    private SimEngine simEngine;
    private Timer timer;

    // Metoda zmieniająca format liczby zmiennoprzecinkowej
    public static String format(double number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#.##");
        return decimalFormat.format(number);
    }

    // Metoda rysująca linię reprezentującą sprężynę
    public void createSpring(Graphics2D graphics2D, Vector2D suspensionPoint, Vector2D position) {
        double x1 = suspensionPoint.x;
        double y1 = suspensionPoint.y;
        double x2 = position.x;
        double y2 = position.y;

        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        graphics2D.draw(line);
    }

    // Metoda inicjalizująca symulację
    @Override
    public void init() {
        double mass = 20.0;
        double springConstant = 3.0;
        double dampingRatio = 0.3;
        double springLength = 200.0;
        Vector2D position = new Vector2D(600.0, 300.0);
        Vector2D velocity = new Vector2D(0.0, 0.0);
        Vector2D suspensionPoint = new Vector2D(400.0, 50.0);
        double gravitationalAcceleration = 9.81;

        this.simEngine = new SimEngine(mass, springConstant, dampingRatio, springLength, position, velocity,
            suspensionPoint, gravitationalAcceleration);

        this.simTask = new SimTask(this, simEngine, 0.1);
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(simTask, 1000, 10);
    }

    // Metoda przedstawiająca obraz symulacji
    @Override
    public void paint(Graphics graphics) {
        this.setSize(800, 600);
        this.setBackground(Color.white);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphics2D.setPaint(Color.black);
        Vector2D position = simEngine.getPosition();
        Vector2D suspensionPoint = simEngine.getSuspensionPoint();

        this.createSpring(graphics2D, suspensionPoint, position);

        graphics2D.fillOval((int) position.x - 10, (int) position.y - 10, 20, 20);
        graphics2D.setColor(Color.green);
    }
}
