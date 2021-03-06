package kmitl.lab05.chayanon58070021.simplemydot.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

import kmitl.lab05.chayanon58070021.simplemydot.BuildConfig;
import kmitl.lab05.chayanon58070021.simplemydot.MainActivity;
import kmitl.lab05.chayanon58070021.simplemydot.R;
import kmitl.lab05.chayanon58070021.simplemydot.model.Colors;
import kmitl.lab05.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab05.chayanon58070021.simplemydot.model.Dots;
import kmitl.lab05.chayanon58070021.simplemydot.model.Screenshot;
import kmitl.lab05.chayanon58070021.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotFragment extends Fragment implements Dots.OnDotsChangedListener,
        DotView.OnDotViewPressListener , View.OnClickListener{


    private static final String DOTS = "dots";
    public interface OnDotSelectedListener {
        void onDotSelected(Dot d, int dotIndex);
    }

    private OnDotSelectedListener onDotSelectedListener;

    private DotView dotView;
    private Dots dots;
    private Screenshot screenshot;
    public DotFragment() {
        // Required empty public constructor
    }

    public static DotFragment newInstance(OnDotSelectedListener listener){

        Bundle args = new Bundle();

        // Set put arguments
        DotFragment fragment = new DotFragment();
        fragment.setOnDotSelectedListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnDotSelectedListener(OnDotSelectedListener onDotSelectedListener) {
        this.onDotSelectedListener = onDotSelectedListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            dots = savedInstanceState.getParcelable(DOTS);

        } else {
            dots = new Dots();
        }
        dots.setListerner(this);
        onDotSelectedListener = (OnDotSelectedListener) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dot, container, false);
        init(rootView);
        //Set Default value
        screenshot = new Screenshot();

        // Inflate the layout for this fragment
        return rootView;
    }

    public void init(View rootView){
        Button randomBtn = rootView.findViewById(R.id.randomBtn);
        Button undoBtn = rootView.findViewById(R.id.undoBtn);
        Button clearBtn = rootView.findViewById(R.id.clearBtn);

        randomBtn.setOnClickListener(this);
        undoBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

        dotView = rootView.findViewById(R.id.dotview);
        dotView.setOnDotViewPressListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.randomBtn:
                onRandomDot();
                break;
            case R.id.undoBtn:
                onUndo();
                break;
            case R.id.clearBtn:
                onClearDots();
                break;
        }
    }

    public void onRandomDot() {
        Random rand = new Random();
        int centerX = rand.nextInt(this.dotView.getWidth());
        int centerY = rand.nextInt(this.dotView.getHeight());

        Dot dot = new Dot(centerX, centerY, 90, new Colors());
        dots.addDot(dot);
    }

    public void onClearDots() {
        dots.clear();
        dotView.invalidate();
    }

    public void onUndo() {
        this.dots.undo();
    }

    @Override
    public void onDotsChanged(Dots dots) {
        //Draw dot model to view
        dotView.setDots(dots);
        dotView.invalidate();
        System.out.println(dots.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Override
    public void onDotViewPressed(int x, int y) {
        final int dotIndex = dots.findDot(x, y);
        if (dotIndex == -1) {
            Dot dot = new Dot(x, y, 70, new Colors());
            dots.addDot(dot);
        } else {
            dots.remove(dotIndex);
        }

    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        final int dotIndex = dots.findDot(x, y);
        if (dotIndex == -1) {
            Dot dot = new Dot(x, y, 70, new Colors());
            dots.addDot(dot);
        } else {
            onDotSelectedListener.onDotSelected(dots.getDotByIndex(dotIndex), dotIndex);
        }
    }


    // Restore args when call this fragment again.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("dots", dots);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dotView.setDots(dots);
    }

    public void updateDotByIndex(Dot dot, int index){
        dots.setDots(dot,index);
    }

}
