package coulombe.twither;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText password;
    private boolean[] passwordVerification = new boolean[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Inscription");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        progressBar = findViewById(R.id.progressBar);
        password = findViewById(R.id.editText5);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                progressBar.setProgress(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onPasswordTextChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onPasswordTextChanged(){
        progressBar.setProgress(0);
        for(int i = 0; i < 4; i++)
            passwordVerification[i] = false;

        //Doit avoir Majuscule, chiffre, entre 8 et 20 caractère
        if(password.getText().toString().matches(".*[A-Z].*") && !passwordVerification[0]){ // capital
            progressBar.setProgress(progressBar.getProgress() + 25);
            passwordVerification[0] = true;
        }

        if(password.getText().toString().matches(".*\\d+.*") && !passwordVerification[1]){ // number
            progressBar.setProgress(progressBar.getProgress() + 25);
            passwordVerification[1] = true;
        }

        if(password.getText().toString().length() >= 8 && !passwordVerification[2]){ // min length
            progressBar.setProgress(progressBar.getProgress() + 25);
            passwordVerification[2] = true;
        }

        if(password.getText().toString().length() == 20 && !passwordVerification[3]){ // max length
            progressBar.setProgress(progressBar.getProgress() + 25);
            passwordVerification[3] = true;
        }

        Drawable progressDrawable = progressBar.getProgressDrawable().mutate();
        switch(progressBar.getProgress()){
            case 25:
            {
                progressDrawable.setColorFilter(Color.rgb(255, 84, 84), android.graphics.PorterDuff.Mode.SRC_IN);
                break;
            }
            case 50:
            {
                progressDrawable.setColorFilter(Color.rgb(255, 166, 84), android.graphics.PorterDuff.Mode.SRC_IN);
                break;
            }
            case 75:
            {
                progressDrawable.setColorFilter(Color.rgb(223, 255, 84), android.graphics.PorterDuff.Mode.SRC_IN);
                break;
            }
            case 100:
            {
                progressDrawable.setColorFilter(Color.rgb(123, 255, 84), android.graphics.PorterDuff.Mode.SRC_IN);
                break;
            }
        }
        progressBar.setProgressDrawable(progressDrawable);
    }
}
