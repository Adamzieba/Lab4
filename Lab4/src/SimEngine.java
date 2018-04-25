public class SimEngine {
    private double mass;
    private double springConstant;
    private double dampingRatio;
    private double springLength;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D suspensionPoint;
    private double gravitationalAcceleration;

    // Konstruktor z parametrami
    public SimEngine(double mass, double springConstant, double dampingRatio, double springLength, Vector2D position,
                     Vector2D velocity, Vector2D suspensionPoint, double gravitationalAcceleration) {
        this.mass = mass;
        this.springConstant = springConstant;
        this.dampingRatio = dampingRatio;
        this.springLength = springLength;
        this.position = position;
        this.velocity = velocity;
        this.suspensionPoint = suspensionPoint;
        this.gravitationalAcceleration = gravitationalAcceleration;
    }

    // Metody związane z masą
    public double getMass() {
        return this.mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    // Metody związane ze współczynnikiem sprężystości 
    public double getSpringConstant() {
        return this.springConstant;
    }

    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }

    // Metody związane ze współczynnikiem tłumienia
    public double getDampingRatio() {
        return this.dampingRatio;
    }

    public void setDampingRatio(double dampingRatio) {
        this.dampingRatio = dampingRatio;
    }

    // Metody związane z długością swobodną sprężyny
    public double getSpringLength() {
        return this.springLength;
    }

    public void setSpringLength(double springLength) {
        this.springLength = springLength;
    }

    // Metody związane z położeniem masy
    public Vector2D getPosition() {
        return this.position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    // Metody związane z prędkością masy
    public Vector2D getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    // Metody związane z położeniem punktu zawieszenia
    public Vector2D getSuspensionPoint() {
        return this.suspensionPoint;
    }

    public void setSuspensionPoint(Vector2D suspensionPoint) {
        this.suspensionPoint = suspensionPoint;
    }

    // Metody związane z przyspieszeniem grawitacyjnym
    public double getGravitationalAcceleration() {
        return this.gravitationalAcceleration;
    }

    public void setGravitationalAcceleration(double gravitationalAcceleration) {
        this.gravitationalAcceleration = gravitationalAcceleration;
    }

    // Metoda obliczająca krok symulacji
    public void calculateSimulation(double timeStep) {
        Vector2D gravitationalForce = new Vector2D(0, this.getMass() * this.getGravitationalAcceleration());
        Vector2D dampingForce = this.getVelocity().scale(-this.getDampingRatio());

        Vector2D position = this.getPosition();
        Vector2D suspensionPoint = this.getSuspensionPoint();
        Vector2D spring = new Vector2D(position.x - suspensionPoint.x, position.y - suspensionPoint.y);

        double difference = this.getSpringLength() - spring.getMagnitude();
        Vector2D springForce = spring.normalize().scale(this.getSpringConstant() * difference);

        Vector2D netForce = springForce.add(gravitationalForce).add(dampingForce);
        Vector2D acceleration = netForce.scale(1 / this.getMass());
        
        Vector2D velocityDelta = acceleration.scale(timeStep);
        this.setVelocity(this.getVelocity().add(velocityDelta));

        Vector2D positionDelta = this.getVelocity().scale(timeStep);
        this.setPosition(this.getPosition().add(positionDelta));
    }

    // Metoda resetująca symulację
    public void resetSimulation() {
        this.velocity = new Vector2D(0.0, 0.0);
    }
}
