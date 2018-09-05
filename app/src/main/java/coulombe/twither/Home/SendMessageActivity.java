package coulombe.twither.Home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import coulombe.twither.Global.TwitConstants;
import coulombe.twither.R;

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
