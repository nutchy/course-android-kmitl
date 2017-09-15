package chayanon58070021.lab05.inclassroom.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chayanon58070021.lab05.inclassroom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String msg){
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        args.putString("message", msg);
        fragment.setArguments(args);
        return fragment;

    }

    String message = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_main, container, false); //
        TextView fragmentTextview = rootview.findViewById(R.id.fragmentTextview);
        fragmentTextview.setText(message);

        if (!message.isEmpty()) {
            fragmentTextview.setText(message);
        }
        return rootview;
    }

}
