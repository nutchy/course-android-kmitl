package kmitl.lab04.chayanon58070021.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nutchy on 9/8/2017 AD.
 */

public class DotParcelable implements Parcelable {

    private int CenterX;
    private int CenterY;
    private int Radius;

    public DotParcelable(int centerX, int centerY, int radius) {
        CenterX = centerX;
        CenterY = centerY;
        Radius = radius;
    }

    @Override
    public String toString() {
        return "DotParcelable{" +
                "CenterX=" + CenterX +
                ", CenterY=" + CenterY +
                ", Radius=" + Radius +
                '}';
    }

    public int getCenterX() {
        return CenterX;
    }

    public void setCenterX(int centerX) {
        CenterX = centerX;
    }

    public int getCenterY() {
        return CenterY;
    }

    public void setCenterY(int centerY) {
        CenterY = centerY;
    }

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    protected DotParcelable(Parcel in) {
        CenterX = in.readInt();
        CenterY = in.readInt();
        Radius = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(CenterX);
        dest.writeInt(CenterY);
        dest.writeInt(Radius);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParcelable> CREATOR = new Creator<DotParcelable>() {
        @Override
        public DotParcelable createFromParcel(Parcel in) {
            return new DotParcelable(in);
        }

        @Override
        public DotParcelable[] newArray(int size) {
            return new DotParcelable[size];
        }
    };
}
