package kmitl.lab03.chayanon58070021.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab03.chayanon58070021.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListerner {

    private Dot dot;
    private DotView dotView;

    private ArrayList<Dot> dot_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Default value

        dot_list = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotview);

    }

    public void onRandomDot(View view) {
        Random rand = new Random();
        int centerX = rand.nextInt(this.dotView.getWidth());
        int centerY = rand.nextInt(this.dotView.getHeight());
        Dot dot = new Dot(centerX, centerY, rand.nextInt(100), this);
        dot.setColorR(rand.nextInt(255));
        dot.setColorG(rand.nextInt(255));
        dot.setColorB(rand.nextInt(255));
//        this.dot.setOrigin(centerX, centerY);
        dot_list.add(dot);
//         new Dot(centerX, centerY,50,this); // when instructor complied will Call back to onDotChanged


    }

    public void onClearDots(View view){
        dot_list.clear();
        dotView.invalidate();
    }

    @Override
    public void onDotChanged(Dot dot) {
        //Draw dot model to view
        dotView.setDot(dot_list);

        dotView.invalidate(); // will call onCreate() Again
    }
}
