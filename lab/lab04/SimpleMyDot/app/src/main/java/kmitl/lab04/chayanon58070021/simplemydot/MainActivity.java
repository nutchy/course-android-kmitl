package kmitl.lab04.chayanon58070021.simplemydot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab04.chayanon58070021.simplemydot.model.Colors;
import kmitl.lab04.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab04.chayanon58070021.simplemydot.model.DotParcelable;
import kmitl.lab04.chayanon58070021.simplemydot.model.DotSerializable;
import kmitl.lab04.chayanon58070021.simplemydot.model.Dots;
import kmitl.lab04.chayanon58070021.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangedListener, DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dots dots;
    private Colors colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final DotSerializable dotSerializable = new DotSerializable();
        dotSerializable.setCenterX(10);
        dotSerializable.setCenterY(20);
        dotSerializable.setRadius(30);

        final DotParcelable dotParcelable = new DotParcelable(111, 222, 333);

        Button btnOpenActivity = (Button) findViewById(R.id.open_activity);
        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.putExtra("xValue", 30);
                intent.putExtra("dotSerializable", dotSerializable);
                intent.putExtra("dotParcelable", dotParcelable);
                startActivity(intent);
            }
        });

        //Set Default value
        colors = new Colors();
        dots = new Dots();
        dots.setListerner(this);
        dotView = (DotView) findViewById(R.id.dotview);
        dotView.setOnDotViewPressListener(this);
    }

    public void onRandomDot(View view) {
        Random rand = new Random();
        int centerX = rand.nextInt(this.dotView.getWidth());
        int centerY = rand.nextInt(this.dotView.getHeight());

        Dot dot = new Dot(centerX, centerY, 90, colors.getColor());
        dots.addDot(dot);
    }

    public void onClearDots(View view) {
        dots.clear();
        dotView.invalidate();
    }

    public void onShare(View view){
        askPermission();
    }

    @Override
    public void onDotsChanged(Dots dots) {
        //Draw dot model to view
        dotView.setDot(dots);
        dotView.invalidate(); // will call onCreate() Again
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotIndex = dots.findDot(x, y);
        if (dotIndex == -1) {
        Dot dot = new Dot(x, y, 70, colors.getColor());
        dots.addDot(dot);}
        else {
            dots.remove(dotIndex);
        }
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 999;
    private void askPermission(){
        int hasWriteExtPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteExtPermission != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
            TextView tvStatus = (TextView) findViewById(R.id.status);
            tvStatus.setText(String.valueOf(hasWriteExtPermission));
            return;
        }
    }
}
