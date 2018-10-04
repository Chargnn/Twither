package coulombe.twither.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import coulombe.twither.Global.TwitConstants;
import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Profile.ProfileActivity;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.TwitMessage.IMockServiceMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMessage extends AppCompatActivity {

    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_message);
        message = findViewById(R.id.editText8);

        getSupportActionBar().setTitle(R.string.WriteMessage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        Button send = findViewById(R.id.button4);
        final IMockServiceMessage service = HttpService.getMockMessage();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.send(new TwitMessage("", "")).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body()) {
                            Toast.makeText(UpdateMessage.this, "Message modifié!", Toast.LENGTH_SHORT).show();
                            openHomeActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });

        service.get().enqueue(new Callback<List<TwitMessage>>() {
            @Override
            public void onResponse(Call<List<TwitMessage>> call, Response<List<TwitMessage>> response) {
                message.setText(response.body().get(0).message);
            }

            @Override
            public void onFailure(Call<List<TwitMessage>> call, Throwable t) {

            }
        });
    }

    private void openHomeActivity(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
