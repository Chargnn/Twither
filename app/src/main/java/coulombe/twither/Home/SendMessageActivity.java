package coulombe.twither.Home;

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

import coulombe.twither.Global.TwitConstants;
import coulombe.twither.Global.TwitMessage;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.TwitMessage.IMockServiceMessage;
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
        final IMockServiceMessage service = HttpService.getMockMessage();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.send(new TwitMessage("", "")).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body())
                            Toast.makeText(SendMessageActivity.this, "Message envoyé!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });

        remainingCharacter = findViewById(R.id.textView);
        message = findViewById(R.id.editText7);
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



}
