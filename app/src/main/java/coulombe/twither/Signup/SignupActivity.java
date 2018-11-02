package coulombe.twither.Signup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.coulombe.User;

import java.io.IOException;

import coulombe.twither.Global.ErrorParser;
import coulombe.twither.Login.LoginActivity;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.session.SessionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText password;
    private boolean[] passwordVerification = new boolean[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle(R.string.Signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));



        final SessionService service = HttpService.getSession();
        progressBar = findViewById(R.id.progressBar);
        password = findViewById(R.id.editText5);
        Button signup = findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nickname = findViewById(R.id.editText3);
                final EditText email = findViewById(R.id.editText4);

                service.register(new User(0, nickname.getText().toString(), password.getText().toString(), email.getText().toString(), "")).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body() == null) {
                            try {
                                Toast.makeText(SignupActivity.this, ErrorParser.parse(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(response.body()) {
                            Toast.makeText(SignupActivity.this, "Votre compte à bien été créé!", Toast.LENGTH_SHORT).show();
                            openLoginActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Can't reach server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                progressBar.setProgress(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateProgressBar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateProgressBar(){
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
            default:{
                progressDrawable.setColorFilter(Color.LTGRAY, android.graphics.PorterDuff.Mode.SRC_IN);

                break;
            }
        }
        progressBar.setProgressDrawable(progressDrawable);
    }

    private void openLoginActivity(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
