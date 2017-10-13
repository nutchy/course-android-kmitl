package kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kmitl.lab07.chayanon58070021.mylazyinstagram.Holder.PostItemHolder;
import kmitl.lab07.chayanon58070021.mylazyinstagram.R;

/**
 * Created by nutchy on 10/6/2017 AD.
 */

//class Holder extends RecyclerView.ViewHolder{
//    public ImageView image;
//
//    public Holder(View itemView) {
//        super(itemView);
//        image = (ImageView) itemView.findViewById(R.id.imageView);
//    }
//}


public class PostAdapter extends RecyclerView.Adapter<PostItemHolder> {
    String[] data = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/froyo.png"
    };

    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PostItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item, null, false);
        PostItemHolder holder = new PostItemHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(PostItemHolder holder, int position) {
        ImageView image = holder.imageView;
        Glide.with(context).load(data[position]).into(image);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
