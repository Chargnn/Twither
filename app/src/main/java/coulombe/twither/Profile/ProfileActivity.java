package coulombe.twither.Profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Global.TwitUser;
import coulombe.twither.Home.HomeListViewAdapter;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.TwitMessage.IMockServiceMessage;
import coulombe.twither.Service.TwitUser.IMockServiceUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private List<TwitMessage> twitMessages = new ArrayList<>();
    ArrayAdapter<TwitMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        adapter = new HomeListViewAdapter(this);
        IMockServiceUser service = HttpService.getMockUser();
        IMockServiceMessage serviceMessage = HttpService.getMockMessage();

        loadUserInfo(service);
        loadMessages(serviceMessage);

        ListView twit_list_view = findViewById(R.id.list_view_profile);
        twit_list_view.setAdapter(adapter);
    }

    private void loadUserInfo(IMockServiceUser service){
        service.get().enqueue(new Callback<TwitUser>() {
            @Override
            public void onResponse(Call<TwitUser> call, Response<TwitUser> response) {
                TextView username = findViewById(R.id.textView9);
                TextView bio = findViewById(R.id.textView10);

                username.setText(response.body().nickname);
                bio.setText(response.body().bio);

                getSupportActionBar().setTitle(getString(R.string.ProfileOf) + " " + response.body().nickname);
            }

            @Override
            public void onFailure(Call<TwitUser> call, Throwable t) {
            }
        });
    }

    private void loadMessages(IMockServiceMessage serviceMessage){
        serviceMessage.get().enqueue(new Callback<List<TwitMessage>>() {
            @Override
            public void onResponse(Call<List<TwitMessage>> call, Response<List<TwitMessage>> response) {
                for(TwitMessage m : response.body()) {
                    twitMessages.add(m);
                }
                adapter.clear();
                adapter.addAll(twitMessages);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TwitMessage>> call, Throwable t) {

            }
        });
    }
}
