package kmitl.lab05.chayanon58070021.simplemydot.model;

import java.io.Serializable;

/**
 * Created by nutchy on 9/8/2017 AD.
 */

public class DotSerializable implements Serializable {
    private int centerX;
    private int centerY;
    private int radius;

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
