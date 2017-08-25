package kmitl.lab03.chayanon58070021.simplemydot.model;

/**
 * Created by nutchy on 8/25/2017 AD.
 */

public class Dot {
    private int centerX;
    private int centerY;
    private int radius;

    public interface OnDotChangedListerner{
        void onDotChanged(Dot dot);
    }

    private OnDotChangedListerner listerner;
    public void setListerner(OnDotChangedListerner listerner){
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


}
