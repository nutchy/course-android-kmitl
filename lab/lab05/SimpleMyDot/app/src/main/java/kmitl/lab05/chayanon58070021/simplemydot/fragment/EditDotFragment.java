package kmitl.lab05.chayanon58070021.simplemydot.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import kmitl.lab05.chayanon58070021.simplemydot.model.Dot;

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

    int seek_val_final;

    private void initView(View rootView){
        Button cancelBtn = rootView.findViewById(R.id.cancelBtn);
        Button confirmBtn = rootView.findViewById(R.id.confirmBtn);
        Button colorBtn = rootView.findViewById(R.id.colorBtn);
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        colorBtn.setOnClickListener(this);



        SeekBar seekBar = rootView.findViewById(R.id.seekBar);
        seekBar.setProgress(dot.getRadius());
        final TextView seekValue = rootView.findViewById(R.id.seekVal);
        seekValue.setText(seekBar.getProgress()+"");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress_val;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progess, boolean b) {
                progress_val = progess;
                seek_val_final = progress_val;
                seekValue.setText(seekBar.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekValue.setText(seekBar.getProgress()+"");
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
                dot.setRadius(seek_val_final);
                onDotUpdateListener.onDotUpdated(dot, dotIndex);
                break;
        }
    }

    private void updateDot(int color, int radius){
        this.dot.setColor(color);
        this.dot.setRadius(radius);
    }

    private void showPicker(View view){
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(dot.getColor())
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

                        updateDot(selectedColor, seek_val_final);
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
