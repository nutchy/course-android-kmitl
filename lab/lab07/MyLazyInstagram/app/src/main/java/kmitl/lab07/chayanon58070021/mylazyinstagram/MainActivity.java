package kmitl.lab07.chayanon58070021.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter.LazyInstagramAdapter;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Adapter.PostAdapter;
import kmitl.lab07.chayanon58070021.mylazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.chayanon58070021.mylazyinstagram.Model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public OkHttpClient client;
    public Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazyinstagram_main);
        getConnection("cartoon");
        getUserProfile("cartoon");


    }

    public void getConnection(String userName){
        client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getPostByName(String userName){


    }

    private void getUserProfile(String userName){


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
//                    displayImages(userProfile);

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
    private void display(UserProfile userProfile){
//        TextView textUser = (TextView) findViewById(R.id.textView);
//        textUser.setText("@"+userProfile.getUser());
//        TextView textPost = (TextView)  findViewById(R.id.textView2);
//        textPost.setText("Post\n"+userProfile.getPost());
//        TextView textFollower =  (TextView) findViewById(R.id.textView3);
//        textFollower.setText("Follower\n"+userProfile.getFollower());
//        TextView textFollwing = (TextView)  findViewById(R.id.textView4);
//        textFollwing.setText("Following\n"+userProfile.getFollowing());
//        TextView textBio = (TextView)  findViewById(R.id.textView8);
//        textBio.setText(userProfile.getBio());

        List<Layout> layouts = new ArrayList<>();
//        layouts.add(new Layout(Layout.TYPE_USER_DETAIL));
        layouts.add(new Layout(Layout.TYPE_POST_ITEM));

        PostAdapter postAdapter = new PostAdapter(this);
        LazyInstagramAdapter lazyInstagramAdapter = new LazyInstagramAdapter(this, layouts);
        lazyInstagramAdapter.setPosts(userProfile.getPosts());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lazyInstagramAdapter);

    }


    private void displayImages(UserProfile userProfile){
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(imageView);
    }

}
