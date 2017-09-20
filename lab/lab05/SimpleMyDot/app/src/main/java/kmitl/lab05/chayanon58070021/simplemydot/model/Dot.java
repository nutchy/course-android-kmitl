package kmitl.lab05.chayanon58070021.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nutchy on 8/25/2017 AD.
 */

public class Dot implements Parcelable {
    private int color;
    private int centerX;
    private int centerY;
    private int radius;
    private Colors colors;

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public Dot(int centerX, int centerY, int radius, Colors colors) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.colors = colors;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.color);
        dest.writeInt(this.centerX);
        dest.writeInt(this.centerY);
        dest.writeInt(this.radius);
    }

    protected Dot(Parcel in) {
        this.color = in.readInt();
        this.centerX = in.readInt();
        this.centerY = in.readInt();
        this.radius = in.readInt();
    }

    public static final Parcelable.Creator<Dot> CREATOR = new Parcelable.Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel source) {
            return new Dot(source);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };
}
