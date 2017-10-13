package kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Layout;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Model.UserProfile;
import kmitl.lab07.chayanon58070021.mylazyinstagram.R;

public class LazyInstagramAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Layout> layouts;
    private UserProfile userProfile;

    public LazyInstagramAdapter(Context context, List<Layout> layouts) {
        this.context = context;
        this.layouts = layouts;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType){
            case Layout.TYPE_USER_DETAIL:
                View vUserDetail = inflater.inflate(R.layout.rc_user_detail, parent, false);
                return new UserDetailViewHolder(vUserDetail);
            case Layout.TYPE_POST_ITEM:
                View vPost = inflater.inflate(R.layout.rc_post_item, parent, false);
                return new PostsViewHolder(vPost);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == Layout.TYPE_USER_DETAIL){
            viewUserDetail ((UserDetailViewHolder) holder);
        } else if (holder.getItemViewType() == Layout.TYPE_POST_ITEM){
            viewPosts((PostsViewHolder) holder);
        }
    }

    private void viewUserDetail(UserDetailViewHolder holder){
        UserDetailAdapter userDetailAdapter = new UserDetailAdapter(context);
        userDetailAdapter.setUserProfile(userProfile);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(userDetailAdapter);

    }

    private void viewPosts(PostsViewHolder holder){
        PostAdapter postAdapter = new PostAdapter(context);
        postAdapter.setPosts(userProfile.getPosts());
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        holder.recyclerView.setAdapter(postAdapter);
    }

    @Override
    public int getItemCount() {
        return layouts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layouts.get(position).getType();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public PostsViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rc_post);
        }
    }

    public class UserDetailViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public UserDetailViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rc_user);
        }
    }


}
