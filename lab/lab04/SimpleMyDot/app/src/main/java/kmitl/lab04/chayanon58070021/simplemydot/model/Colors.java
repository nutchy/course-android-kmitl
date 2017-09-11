package kmitl.lab04.chayanon58070021.simplemydot.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nutchy on 9/11/2017 AD.
 */

public class Colors {

    private List<Integer> colors = new ArrayList<>();

    public Colors() {
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
