package kmitl.lab04.chayanon58070021.simplemydot.model;

/**
 * Created by nutchy on 8/25/2017 AD.
 */

public class Dot {
    private int color;
    private int centerX;
    private int centerY;
    private int radius;

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getCenterX() {

        return centerX;
    }

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setOrigin(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
