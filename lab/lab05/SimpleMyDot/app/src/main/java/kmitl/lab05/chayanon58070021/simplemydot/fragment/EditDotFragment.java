package kmitl.lab05.chayanon58070021.simplemydot.fragment;


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

import kmitl.lab05.chayanon58070021.simplemydot.R;
import kmitl.lab05.chayanon58070021.simplemydot.model.Dot;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener{

    private Dot dot;

    public interface OnDotUpdateListener{
        void onDotUpdated(Dot dot);
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
    }

    int seek_val_final;

    private void initView(View rootView){
        Button cancelBtn = rootView.findViewById(R.id.cancelBtn);
        Button confirmBtn = rootView.findViewById(R.id.confirmBtn);
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);



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
                break;
            case R.id.confirmBtn:
                dot.setRadius(seek_val_final);
                onDotUpdateListener.onDotUpdated(dot);
                break;
        }
    }

    private int getRadius(String s){
        return Integer.parseInt(s);
    }
}
