package kmitl.lab05.chayanon58070021.simplemydot;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import kmitl.lab05.chayanon58070021.simplemydot.fragment.DotFragment;
import kmitl.lab05.chayanon58070021.simplemydot.fragment.EditDotFragment;
import kmitl.lab05.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab05.chayanon58070021.simplemydot.model.Screenshot;

public class MainActivity extends AppCompatActivity implements
        DotFragment.OnDotSelectedListener, EditDotFragment.OnDotUpdateListener{

    private final String DOT_FRAGMENT = "dotfragment";
    private Screenshot screenshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenshot = new Screenshot();

        if(savedInstanceState == null) {
            initialDotViewFragment();
        }
    }


    private void initialDotViewFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, DotFragment.newInstance(this), DOT_FRAGMENT)
                .commit();
    }

    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDotUpdated(Dot dot, int dotIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        DotFragment fragment = (DotFragment) fragmentManager.findFragmentByTag(DOT_FRAGMENT);
        fragment.updateDotByIndex(dot, dotIndex);
    }

    @Override
    public void onDotSelected(Dot dot, int dotIndex) {
        showFragment(new EditDotFragment().newInstance(dot, dotIndex, this));
    }

    public void shareImage(File file) {
        Uri uri = FileProvider.getUriForFile(MainActivity.this,
                BuildConfig.APPLICATION_ID + ".provider", file);
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Dots Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    public void onShareBtn(View view) {
        this.onShare();
    }

    private void onShare() {
        if (askPermission()) {
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            File imgfile = screenshot.saveBitmap(screenshot.getScreenShot(rootView));
            shareImage(imgfile);
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
