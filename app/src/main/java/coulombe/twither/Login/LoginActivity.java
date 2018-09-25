package coulombe.twither.Login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import coulombe.twither.Global.TwitUser;
import coulombe.twither.Home.HomeActivity;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.TwitUser.IMockServiceUser;
import coulombe.twither.Signup.SignupActivity;
import coulombe.twither.Singleton.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(getString(R.string.Login) + " " + getString(R.string.app_name));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        TextView signup = findViewById(R.id.textView2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

        final Button signin = findViewById(R.id.button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    private void openSignupActivity(){
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    private void signin(){
        openHomeActivity();
    }

    private void openHomeActivity(){
        final Intent i = new Intent(this, HomeActivity.class);
        IMockServiceUser service = HttpService.getMockUser();
        service.get().enqueue(new Callback<TwitUser>() {
            @Override
            public void onResponse(Call<TwitUser> call, Response<TwitUser> response) {
                EditText username = findViewById(R.id.editText);
                EditText password = findViewById(R.id.editText2);

                if(username == null || username.getText().equals("")) {
                    loginFailure();
                    return;
                }

                if(password == null || password.getText().equals("")) {
                    loginFailure();
                    return;
                }

                if(response.body().nickname.toString().equals(username.getText().toString()) || response.body().email.toString().equals(username.getText().toString()) && response.body().password.toString().equals(password.getText().toString())) {
                    Session.setInstance(response.body());
                    startActivity(i);
                } else {
                    loginFailure();
                }
            }

            @Override
            public void onFailure(Call<TwitUser> call, Throwable t) {
                loginFailure();
            }
        });
    }

    private void loginFailure(){
        Toast.makeText(LoginActivity.this, "Mauvais compte (temporaire)", Toast.LENGTH_SHORT).show();
    }
}
