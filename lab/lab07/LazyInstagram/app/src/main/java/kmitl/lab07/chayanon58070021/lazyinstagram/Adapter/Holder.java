package kmitl.lab07.chayanon58070021.lazyinstagram.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import kmitl.lab07.chayanon58070021.lazyinstagram.R;

/**
 * Created by nutchy on 10/10/2017 AD.
 */

public class Holder extends RecyclerView.ViewHolder{
    public ImageView imageView;

    public Holder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}
