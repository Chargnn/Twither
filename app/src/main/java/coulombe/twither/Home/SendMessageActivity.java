package coulombe.twither.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.coulombe.Message;

import java.io.IOException;

import coulombe.twither.Global.ErrorParser;
import coulombe.twither.Global.TwitConstants;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.message.MessageService;
import coulombe.twither.Singleton.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageActivity extends AppCompatActivity {

    EditText message;
    TextView remainingCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        getSupportActionBar().setTitle(R.string.WriteMessage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        Button send = findViewById(R.id.button3);
        message = findViewById(R.id.editText7);
        final MessageService service = HttpService.getMessage();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.create(Session.getInstance().id, new Message(0, Session.getInstance().id, message.getText().toString())).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body() == null) {
                            try {
                                Toast.makeText(getApplicationContext(), ErrorParser.parse(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if(response.body()) {
                            Toast.makeText(SendMessageActivity.this, "Message envoyé!", Toast.LENGTH_SHORT).show();
                            openHomeActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                       Toast.makeText(getApplicationContext(), "Can't reach server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        remainingCharacter = findViewById(R.id.textView);
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                remainingCharacter.setText(getString(R.string.RemainingSpaces) + " " + TwitConstants.MAX_MESSAGE_LENGTH);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                remainingCharacter.setText(getString(R.string.RemainingSpaces) + " " + (TwitConstants.MAX_MESSAGE_LENGTH - s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void openHomeActivity(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
