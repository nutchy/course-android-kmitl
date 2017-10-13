package kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Holder.PostItemHolder;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Model.Post;
import kmitl.lab07.chayanon58070021.mylazyinstagram.R;


public class PostAdapter extends RecyclerView.Adapter<PostItemHolder> {

    private List<Post> posts;
    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public PostItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, parent, false);
        return new PostItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostItemHolder holder, int position) {
        ImageView image = holder.imageView;
        Glide.with(context).load(posts.get(position).getUrl()).into(image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
