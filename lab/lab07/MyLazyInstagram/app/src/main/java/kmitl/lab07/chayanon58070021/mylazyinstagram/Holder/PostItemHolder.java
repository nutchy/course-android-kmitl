package kmitl.lab07.chayanon58070021.mylazyinstagram.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kmitl.lab07.chayanon58070021.mylazyinstagram.R;

public class PostItemHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView like;
    public TextView comment;

    public PostItemHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }
}
