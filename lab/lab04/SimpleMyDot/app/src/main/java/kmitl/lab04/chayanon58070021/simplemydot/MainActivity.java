package kmitl.lab04.chayanon58070021.simplemydot;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
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


    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void shareImage(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Dots Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    private File saveBitmap(Bitmap bm) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ now + ".jpg";
        File imageFile = new File(dirPath);
        TextView dir = (TextView) findViewById(R.id.status);
        dir.setText(dirPath);
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(imageFile);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }


    public void onShare(View view) {
        if(askPermission()) {
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            Bitmap bitmap = getScreenShot(rootView);
            File imageFile = saveBitmap(bitmap);
            shareImage(imageFile);
        }
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
            dots.addDot(dot);
        } else {
            dots.remove(dotIndex);
        }
    }

    private boolean askPermission() {
        int hasWriteExtPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //when deny/not allow >> return -1, allow return >> 0

        if (hasWriteExtPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 999);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // callback by requestPermissions()
        switch (requestCode) {
            case 999:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT)
                            .show();
                }

                return;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }


    }
}
