package coulombe.twither;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class SendMessageActivity extends AppCompatActivity {

    EditText message;
    TextView remainingCaracter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        getSupportActionBar().setTitle("Accueil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        remainingCaracter = findViewById(R.id.textView);
        message = findViewById(R.id.editText7);
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                remainingCaracter.setText("Espaces restants: 140");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int max = 140;
                int counta = 0;

                for(char c : s.toString().toCharArray()){
                    counta ++;
                    remainingCaracter.setText("Espaces restants: " + (max - counta));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



}
