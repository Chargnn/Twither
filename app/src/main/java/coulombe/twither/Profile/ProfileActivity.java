package coulombe.twither.Profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.coulombe.Message;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Home.HomeListViewAdapter;
import coulombe.twither.R;
import coulombe.twither.Singleton.Session;

public class ProfileActivity extends AppCompatActivity {

    private List<Message> twitMessages = new ArrayList<>();
    ArrayAdapter<Message> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle(getString(R.string.ProfileOf) + " " + Session.getInstance().nickname);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        adapter = new HomeListViewAdapter(this);

        loadUserInfo();
        //loadMessages(serviceMessage);

        ListView twit_list_view = findViewById(R.id.list_view_profile);
        twit_list_view.setAdapter(adapter);
    }

    private void loadUserInfo(){
        TextView username = findViewById(R.id.textView9);
        TextView bio = findViewById(R.id.textView10);
        username.setText(Session.getInstance().nickname);
        bio.setText(Session.getInstance().bio);
    }

    /*private void loadMessages(IMockServiceMessage serviceMessage){
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
    }*/
}
