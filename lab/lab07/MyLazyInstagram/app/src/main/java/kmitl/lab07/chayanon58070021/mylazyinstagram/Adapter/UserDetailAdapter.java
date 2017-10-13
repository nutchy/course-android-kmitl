package kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.chayanon58070021.mylazyinstagram.Holder.UserDetailHolder;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Model.UserProfile;
import kmitl.lab07.chayanon58070021.mylazyinstagram.R;


public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailHolder> {

    private Context context;
    private UserProfile userProfile;

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public UserDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.user_detail, parent, false);
        return new UserDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserDetailHolder holder, int position) {
        ImageView imageView = holder.getAvatar();
        Glide.with(context).load(userProfile.getUrlProfile()).into(imageView);

        // Show ID,Post,Following,Follower,Bio
        TextView tvId = holder.getTvId();
        TextView tvPost = holder.getTvPost();
        TextView tvFollowing = holder.getTvFollowing();
        TextView tvFollower = holder.getTvFollower();
        TextView tvBio = holder.getTvBio();

        tvId.setText("@"+userProfile.getUser());
        tvPost.setText("Posts\n"+String.valueOf(userProfile.getPost()));
        tvFollowing.setText("Following\n"+String.valueOf(userProfile.getFollowing()));
        tvFollower.setText("Follower\n"+String.valueOf(userProfile.getFollower()));
        tvBio.setText(userProfile.getBio());
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
