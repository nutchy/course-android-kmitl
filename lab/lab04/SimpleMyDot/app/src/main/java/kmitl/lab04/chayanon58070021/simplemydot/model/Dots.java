package kmitl.lab04.chayanon58070021.simplemydot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutchy on 9/11/2017 AD.
 */

public class Dots {
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
}
