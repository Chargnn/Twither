package coulombe.twither.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.coulombe.Message;

import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.message.MessageService;
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

        Log.i("sa",getIntent().getStringExtra("id"));
        final int authorid = Integer.parseInt(getIntent().getStringExtra("id"));
        final int messageid = Integer.parseInt(getIntent().getStringExtra("messageid"));

        Button send = findViewById(R.id.button4);
        final MessageService service = HttpService.getMessage();
        service.get(authorid, messageid).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                message.setText(response.body().message);
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.update(authorid, messageid, new Message(messageid, authorid, message.getText().toString())).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(!response.body())
                            Toast.makeText(UpdateMessage.this, "Le message ne peut pas être modifier", Toast.LENGTH_SHORT).show();

                        Toast.makeText(UpdateMessage.this, "Le message à été modifier", Toast.LENGTH_SHORT).show();
                        openHomeActivity();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });
        /*final IMockServiceMessage service = HttpService.getMockMessage();
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
        });*/

        /*service.get().enqueue(new Callback<List<TwitMessage>>() {
            @Override
            public void onResponse(Call<List<TwitMessage>> call, Response<List<TwitMessage>> response) {
                message.setText(response.body().get(0).message);
            }

            @Override
            public void onFailure(Call<List<TwitMessage>> call, Throwable t) {

            }
        });*/
    }

    private void openHomeActivity(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
