package kmitl.lab05.chayanon58070021.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nutchy on 9/11/2017 AD.
 */

public class Dots implements Parcelable {
    private List<Dot> dots = new ArrayList<>();

    public List<Dot> getDots() {
        return dots;
    }


    private OnDotsChangedListener listener;

    public interface OnDotsChangedListener {
        void onDotsChanged(Dots dots);
    }

    public void setListerner(OnDotsChangedListener listener) {
        this.listener = listener;
    }

    public void addDot(Dot dot) {
        this.dots.add(dot);
        this.listener.onDotsChanged(this);

    }

    public void clear() {
        this.dots.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < dots.size(); i++) {
            double distance = Math.sqrt(Math.pow(dots.get(i).getCenterX() - x, 2))
                    + Math.sqrt(Math.pow(dots.get(i).getCenterY() - y, 2));
            if (distance <= dots.get(i).getRadius()) {
                return i;
            }
        }
        return -1;
    }

    public void remove(int index) {
        this.dots.remove(index);
        this.listener.onDotsChanged(this);
    }

    public void undo() {
        int index = dots.size() - 1;
        if (index >= 0) {
            this.dots.remove(index);
            this.listener.onDotsChanged(this);
        }
    }

    public void editColor(int index, int color) {
        this.dots.get(index).setColor(color);
        this.listener.onDotsChanged(this);
    }

    public void editSize(int index) {
        this.dots.get(index).setRadius(new Random().nextInt(110));
        this.listener.onDotsChanged(this);
    }

    public Dot getDotByIndex(int i) {
        return this.getDots().get(i);
    }

    public void setDots(Dot dot, int index) {
        this.dots.set(index, dot);
        this.listener.onDotsChanged(this);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.dots);
        dest.writeParcelable((Parcelable) this.listener, flags);
    }

    public Dots() {
    }

    protected Dots(Parcel in) {
        this.dots = in.createTypedArrayList(Dot.CREATOR);
        this.listener = in.readParcelable(OnDotsChangedListener.class.getClassLoader());
    }

    public static final Parcelable.Creator<Dots> CREATOR = new Parcelable.Creator<Dots>() {
        @Override
        public Dots createFromParcel(Parcel source) {
            return new Dots(source);
        }

        @Override
        public Dots[] newArray(int size) {
            return new Dots[size];
        }
    };
}
