package kmitl.lab07.chayanon58070021.lazyinstagram.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kmitl.lab07.chayanon58070021.lazyinstagram.R;

/**
 * Created by nutchy on 10/11/2017 AD.
 */

public class UserDetailHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvPost;
    private TextView tvFollowing;
    private TextView tvFollower;
    private TextView tvBio;
    private ImageView avatar;

    public UserDetailHolder(View itemView) {
        super(itemView);
        tvId = (TextView) itemView.findViewById(R.id.user_id);
        tvPost = (TextView) itemView.findViewById(R.id.post);
        tvFollowing = (TextView) itemView.findViewById(R.id.following);
        tvFollower = (TextView) itemView.findViewById(R.id.follower);
        tvBio = (TextView) itemView.findViewById(R.id.bio);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
    }
}