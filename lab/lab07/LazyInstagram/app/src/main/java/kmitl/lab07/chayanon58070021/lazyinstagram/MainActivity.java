package kmitl.lab07.chayanon58070021.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.chayanon58070021.lazyinstagram.Adapter.PostAdapter;
import kmitl.lab07.chayanon58070021.lazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.chayanon58070021.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("android");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(String userName){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Connect Api if success >> onResponse , else >> onFailure
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = lazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    // response 200
                    UserProfile userProfile = response.body();
                    display(userProfile);
                    displayImages(userProfile);

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
    private void display(UserProfile userProfile){
        TextView textUser = (TextView) findViewById(R.id.textView);
        textUser.setText("@"+userProfile.getUser());
        TextView textPost = (TextView)  findViewById(R.id.textView2);
        textPost.setText("Post\n"+userProfile.getPost());
        TextView textFollower =  (TextView) findViewById(R.id.textView3);
        textFollower.setText("Follower\n"+userProfile.getFollower());
        TextView textFollwing = (TextView)  findViewById(R.id.textView4);
        textFollwing.setText("Following\n"+userProfile.getFollowing());
        TextView textBio = (TextView)  findViewById(R.id.textView8);
        textBio.setText(userProfile.getBio());
    }


    private void displayImages(UserProfile userProfile){
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(imageView);
    }

}
