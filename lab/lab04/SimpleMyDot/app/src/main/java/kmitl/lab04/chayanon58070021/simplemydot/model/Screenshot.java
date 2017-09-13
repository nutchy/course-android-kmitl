package kmitl.lab04.chayanon58070021.simplemydot.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by nutchy on 9/12/2017 AD.
 */

public class Screenshot {

    public Screenshot() {
    }


    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static File saveBitmap(Bitmap bm) {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File filedir = new File(dirPath);
        File imageFile = new File(filedir, "simplemydot.jpg");
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
}
