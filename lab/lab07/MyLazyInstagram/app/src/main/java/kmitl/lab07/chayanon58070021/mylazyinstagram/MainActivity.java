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
    public LazyInstagramApi lazyInstagramApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazyinstagram_main);
        getConnection();
        getContentByName("cartoon");



    }

    public void getConnection(){
        client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
    }

    private void getContentByName(String userName){

        // Connect Api if success >> onResponse , else >> onFailure
        Call<UserProfile> call = lazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    // response 200
                    UserProfile userProfile = response.body();

                    display(userProfile);

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
    private void display(UserProfile userProfile){


        List<Layout> layouts = new ArrayList<>();
        layouts.add(new Layout(Layout.TYPE_USER_DETAIL));
        layouts.add(new Layout(Layout.TYPE_POST_ITEM));

        PostAdapter postAdapter = new PostAdapter(this);
        LazyInstagramAdapter lazyInstagramAdapter = new LazyInstagramAdapter(this, layouts);
        lazyInstagramAdapter.setUserProfile(userProfile);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
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
