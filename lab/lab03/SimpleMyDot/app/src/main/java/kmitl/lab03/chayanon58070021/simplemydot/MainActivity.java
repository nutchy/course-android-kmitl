package kmitl.lab03.chayanon58070021.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab03.chayanon58070021.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListerner {

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Default value
        dot = new Dot(0,0,50);
        dot.setListerner(this);

        dotView = (DotView) findViewById(R.id.dotview);

    }

    public void onRandomDot(View view) {
        Random rand = new Random();
        int centerX = rand.nextInt(this.dotView.getWidth());
        int centerY = rand.nextInt(this.dotView.getHeight());
        this.dot.setCenterY(centerY);
        this.dot.setCenterX(centerX);
//         new Dot(centerX, centerY,50,this); // when instructor complied will Call back to onDotChanged



    }

    @Override
    public void onDotChanged(Dot dot) {
        //Draw dot model to view
        dotView.setDot(dot);
        dotView.invalidate(); // will call onCreate() Again
    }
}
