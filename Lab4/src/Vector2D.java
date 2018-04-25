public class Vector2D {
    public double x;
    public double y;

    // Konstruktor domyślny
    public Vector2D() {
        this.x = 0.0;
        this.y = 0.0;
    }

    // Konstruktor z parametrami
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Metoda zwracająca sumę wektorów this i that
    public Vector2D add(Vector2D that) {
        return new Vector2D(this.x + that.x, this.y + that.y);
    }

    // Metoda zwracająca różnicę wektorów this i that
    public Vector2D subtract(Vector2D that) {
        return new Vector2D(this.x - that.x, this.y - that.y);
    }

    // Metoda skalująca wektor this przez stałą scale
    public Vector2D scale(double scale) {
        return new Vector2D(scale * this.x, scale * this.y);
    }

    // Metoda zwracająca długość wektora this
    public double getMagnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    // Metoda dzieląca wektor this przez jego długość
    public Vector2D normalize() {
        return new Vector2D(this.x / this.getMagnitude(), this.y / this.getMagnitude());
    }

    // Metoda przedstawiająca postać wektora
    public void present(String name) {
        String x = SpringApplet.format(this.x);
        String y = SpringApplet.format(this.y);
        System.out.println("  " + name + ": " + x + " " + y);
    }
}
