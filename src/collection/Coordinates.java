package collection;

import java.io.Serializable;

/**
 * Класс координат.
 */
public class Coordinates implements Serializable {
    private double x;
    private double y;

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y;
    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(){

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
