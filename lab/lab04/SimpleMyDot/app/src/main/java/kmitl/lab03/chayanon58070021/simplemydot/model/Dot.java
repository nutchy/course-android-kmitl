package kmitl.lab03.chayanon58070021.simplemydot.model;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by nutchy on 8/25/2017 AD.
 */

public class Dot {
    private int colorR, colorG, colorB;
    private int centerX;
    private int centerY;
    private int radius;

    private OnDotChangedListerner listerner;

    public interface OnDotChangedListerner {
        void onDotChanged(Dot dot);
    }

    public void setListerner(OnDotChangedListerner listerner) {
        this.listerner = listerner;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listerner.onDotChanged(this); // call back
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCenterX() {

        return centerX;
    }

    public Dot(int centerX, int centerY, int radius, OnDotChangedListerner listerner) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listerner = listerner;
        this.listerner.onDotChanged(this);


    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listerner.onDotChanged(this); // call back

    }

    public void setOrigin(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.listerner.onDotChanged(this); // callback

    }

    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }
}
