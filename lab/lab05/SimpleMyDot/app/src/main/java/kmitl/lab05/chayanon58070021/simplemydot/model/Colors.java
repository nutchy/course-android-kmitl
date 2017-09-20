package kmitl.lab05.chayanon58070021.simplemydot.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nutchy on 9/11/2017 AD.
 */

public class Colors {

    private List<Integer> colors = new ArrayList<>();
    private int red, green, blue;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public Colors() {
        setRed(new Random().nextInt(255));
        setBlue(new Random().nextInt(255));
        setGreen(new Random().nextInt(255));
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.DKGRAY);
    }

    public int getColor(){
        return colors.get(new Random().nextInt(colors.size()));

    }
}
