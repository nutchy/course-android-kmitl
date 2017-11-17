package kmitl.lab07.chayanon58070021.lazyinstagram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import kmitl.lab07.chayanon58070021.lazyinstagram.Holder.PostItemHolder;
import kmitl.lab07.chayanon58070021.lazyinstagram.Holder.UserDetailHolder;
import kmitl.lab07.chayanon58070021.lazyinstagram.Layout;
import kmitl.lab07.chayanon58070021.lazyinstagram.Model.Post;
import kmitl.lab07.chayanon58070021.lazyinstagram.R;

/**
 * Created by nutchy on 10/13/2017 AD.
 */

public class LazyInstagramAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String[] data = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/froyo.png"
    };

    private Context context;
    private List<Layout> layouts;
    private List<Post> posts;

    public LazyInstagramAdapter(Context context) {
        this.context = context;
    }

    public LazyInstagramAdapter(Context context, List<Layout> layouts) {
        this.context = context;
        this.layouts = layouts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case Layout.TYPE_USER_DETAIL:
                View vUserDetail = inflater.inflate(R.layout.view_user_detail, parent, false);
                return new UserDetailHolder(vUserDetail);
            case Layout.TYPE_POST_ITEM:
                View vPost = inflater.inflate(R.layout.item, parent, false);
                return new PostItemHolder(vPost);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case Layout.TYPE_USER_DETAIL:
                UserDetailHolder userDetailHolder = (UserDetailHolder) holder;
                break;
            case Layout.TYPE_POST_ITEM:
                PostItemHolder postItemHolder = (PostItemHolder) holder;
                ImageView image = postItemHolder.imageView;
                Glide.with(context).load(posts.get(0).getUrl()).into(image);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return layouts.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return layouts.get(position).getType();
    }


}
