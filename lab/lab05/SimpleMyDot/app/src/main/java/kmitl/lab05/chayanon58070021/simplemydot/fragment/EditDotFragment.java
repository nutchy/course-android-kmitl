package kmitl.lab05.chayanon58070021.simplemydot.fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import kmitl.lab05.chayanon58070021.simplemydot.R;
import kmitl.lab05.chayanon58070021.simplemydot.model.Colors;
import kmitl.lab05.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab05.chayanon58070021.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener{

    private Dot dot;
    private int dotIndex;

    public interface OnDotUpdateListener{
        void onDotUpdated(Dot dot,int dotIndex);

    }

    private OnDotUpdateListener onDotUpdateListener;

    public void setOnDotUpdateListener(OnDotUpdateListener onDotUpdateListener) {
        this.onDotUpdateListener = onDotUpdateListener;
    }

    public EditDotFragment() {
        // Required empty public constructor
    }


    public EditDotFragment newInstance(Dot dot, int index, OnDotUpdateListener listener){
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable("dot", dot);
        args.putInt("dotIndex", index);
        fragment.setOnDotUpdateListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot = getArguments().getParcelable("dot");
        dotIndex = getArguments().getInt("dotIndex");
    }

    int seek_val_final, centerX, centerY;

    private void initView(View rootView){
        Button cancelBtn = rootView.findViewById(R.id.cancelBtn);
        Button confirmBtn = rootView.findViewById(R.id.confirmBtn);
        Button colorBtn = rootView.findViewById(R.id.colorBtn);
        final TextView xPos = rootView.findViewById(R.id.xPos);
        final TextView yPos = rootView.findViewById(R.id.yPos);
        final TextView radius = rootView.findViewById(R.id.seekVal);
        
        
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        colorBtn.setOnClickListener(this);


        SeekBar seekBarX = rootView.findViewById(R.id.seekBarX);
        SeekBar seekBarY = rootView.findViewById(R.id.seekBarY);
        SeekBar seekBar = rootView.findViewById(R.id.seekBar);

        System.out.println(dot.getCenterX()+" "+dot.getCenterY()+" "+dot.getRadius());
        seekBarX.setMax(1200);
        seekBarY.setMax(1500);
        System.out.println(rootView.getWidth());
        seekBar.setProgress(dot.getRadius());
        seekBarX.setProgress(dot.getCenterX());
        seekBarY.setProgress(dot.getCenterY());

        xPos.setText("X : "+seekBarX.getProgress()+"");
        yPos.setText("Y : "+seekBarY.getProgress()+"");
        radius.setText("Radius : "+seekBar.getProgress()+"");

        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateDot(i, dot.getCenterY(), dot.getRadius());
                xPos.setText("X : " + seekBar.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                xPos.setText("X : " + seekBar.getProgress());

            }
        });

        seekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateDot(dot.getCenterX(), i, dot.getRadius());
                yPos.setText("Y : " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                yPos.setText("Y : " + seekBar.getProgress());

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                updateDot(dot.getCenterX(), dot.getCenterY(),progress);
                radius.setText("Radius : "+seekBar.getProgress()+"");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                radius.setText("Radius : "+seekBar.getProgress()+"");

            }

        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelBtn:
                getActivity().onBackPressed();
                break;
            case R.id.colorBtn:
                showPicker(view);
                break;
            case R.id.confirmBtn:
                System.out.println(dot.getCenterX()+"-"+dot.getCenterY()+"-"+dot.getRadius()+"-"+dot.getColor());
                onDotUpdateListener.onDotUpdated(dot, dotIndex);

                break;
        }
    }

    private void updateDot(int x, int y, int radius){
        this.dot.setRadius(radius);
        this.dot.setCenterX(x);
        this.dot.setCenterY(y);
    }

    private void showPicker(View view){
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Choose color")
                .initialColor(Color.rgb(dot.getColors().getRed(), dot.getColors().getBlue(),
                dot.getColors().getGreen()))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {

                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        changeBackgroundColor(selectedColor);

//                        updateDot(selectedColor, seek_val_final);
                        Colors colors = new Colors();
                        colors.setRed(Color.red(selectedColor));
                        colors.setGreen(Color.green(selectedColor));
                        colors.setBlue(Color.blue(selectedColor));
                        dot.setColors(colors);
                        System.out.println(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }
}
