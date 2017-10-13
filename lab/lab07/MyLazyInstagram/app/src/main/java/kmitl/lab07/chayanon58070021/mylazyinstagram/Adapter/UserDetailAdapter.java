package kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kmitl.lab07.chayanon58070021.mylazyinstagram.Holder.UserDetailHolder;
import kmitl.lab07.chayanon58070021.mylazyinstagram.MainActivity;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Model.UserProfile;
import kmitl.lab07.chayanon58070021.mylazyinstagram.R;

/**
 * Created by nutchy on 10/13/2017 AD.
 */

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailHolder> {

    private Context context;
    private UserProfile userProfile;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public UserDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.view_user_detail, parent, false);
        return new UserDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserDetailHolder holder, int position) {
        ImageView imageView = holder.avatar;
        Glide.with(context).load(userProfile.getUrlProfile()).into(imageView);

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
