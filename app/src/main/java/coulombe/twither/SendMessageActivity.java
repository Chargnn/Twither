package coulombe.twither;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class SendMessageActivity extends AppCompatActivity {

    EditText message;
    TextView remainingCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        getSupportActionBar().setTitle("Écrire un message");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        remainingCharacter = findViewById(R.id.textView);
        message = findViewById(R.id.editText7);
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                remainingCharacter.setText("Espaces restants: 140");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                remainingCharacter.setText("Espaces restants: " + (140 - s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



}
