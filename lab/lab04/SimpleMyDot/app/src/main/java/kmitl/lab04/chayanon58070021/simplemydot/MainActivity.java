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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kmitl.lab04.chayanon58070021.simplemydot.model.Colors;
import kmitl.lab04.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab04.chayanon58070021.simplemydot.model.DotParcelable;
import kmitl.lab04.chayanon58070021.simplemydot.model.DotSerializable;
import kmitl.lab04.chayanon58070021.simplemydot.model.Dots;
import kmitl.lab04.chayanon58070021.simplemydot.model.Screenshot;
import kmitl.lab04.chayanon58070021.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangedListener,
        DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dots dots;
    private Colors colors;
    private Screenshot screenshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Default value
        colors = new Colors();
        dots = new Dots();
        dots.setListerner(this);
        dotView = (DotView) findViewById(R.id.dotview);
        dotView.setOnDotViewPressListener(this);
        screenshot = new Screenshot();
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

    private void shareImageIntent(File file) {
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

    public void onShareBtn(View view) {
        onShare();
    }

    public void onShare() {
        if (askPermission()) {
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            Bitmap bitmap = screenshot.getScreenShot(rootView);
            File imageFile = screenshot.saveBitmap(bitmap);
            shareImageIntent(imageFile);
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
        final int dotIndex = dots.findDot(x, y);
        if (dotIndex == -1) {
            Dot dot = new Dot(x, y, 70, colors.getColor());
            dots.addDot(dot);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final SeekBar seek = new SeekBar(this);
            seek.setMax(100);
//            builder.setTitle("Do you want to?");
            builder.setItems(new CharSequence[]{"Edit Color", "Edit Size", "Delete"},
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i){
                        case 0:
                            dots.editColor(dotIndex, colors.getColor());
                            break;
                        case 1:
                            dots.editSize(dotIndex);
                            break;
                        case 2:
                            dots.remove(dotIndex);
                            break;
                        default:
                            break;
                    }
                }
            });

//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(final DialogInterface dialog, int which) {
//                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
//                    builderInner.setView(seek);
//                    builderInner.setTitle("Change Radius");
//                    builderInner.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        @Override
//                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//                        }
//
//                        @Override
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//
//                        }
//
//                        @Override
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//
//                        }
//                    });
//                    builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//
//                        }
//                    });
//                    builderInner.show();
//                    Toast.makeText(getApplicationContext(),
//                            "OK", Toast.LENGTH_LONG).show();
//                }
//            });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
            builder.show();
        }
    }


    private boolean askPermission() {
        int hasWriteExtPermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //when deny/not allow >> return -1, allow return >> 0

        if (hasWriteExtPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 999);
            return false;
        }
        return true;
    }

    public void onUndo(View view) {
        this.dots.undo();
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
                    onShare();
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
