package coulombe.twither.Profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.coulombe.Message;
import org.coulombe.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Global.ErrorParser;
import coulombe.twither.Home.HomeListViewAdapter;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.message.MessageService;
import coulombe.twither.Service.user.UserService;
import coulombe.twither.Singleton.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private List<Message> twitMessages = new ArrayList<>();
    ArrayAdapter<Message> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        int authorid;
        if(getIntent().getStringExtra("id") == null || getIntent().getStringExtra("id") == "")
            authorid = Session.getInstance().id;
        else
            authorid = Integer.parseInt(getIntent().getStringExtra("id"));

        UserService service = HttpService.getUser();
        service.get(authorid).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getSupportActionBar().setTitle(getString(R.string.ProfileOf) + " " + response.body().nickname);
                loadMessages(response.body().id);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                getSupportActionBar().setTitle("Erreur");
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        adapter = new HomeListViewAdapter(this);

        loadUserInfo();

        ListView twit_list_view = findViewById(R.id.list_view_profile);
        twit_list_view.setAdapter(adapter);
    }

    private void loadUserInfo(){
        TextView username = findViewById(R.id.textView9);
        TextView bio = findViewById(R.id.textView10);
        username.setText(Session.getInstance().nickname);
        bio.setText(Session.getInstance().bio);
    }

    private void loadMessages(int id){
        MessageService service = HttpService.getMessage();
        service.getAll(id).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.body() == null) {
                    try {
                        Toast.makeText(getApplicationContext(), ErrorParser.parse(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                for(Message m : response.body()) {
                    twitMessages.add(m);
                }
                adapter.clear();
                adapter.addAll(twitMessages);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Can't reach server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
