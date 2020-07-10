package org.libreapps.yourtrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = (Button) findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                email     = ((EditText) findViewById(R.id.edittext_email)).getText().toString();
                password  = ((EditText) findViewById(R.id.edittext_password)).getText().toString();

                ConnectionRest connectionRest = new ConnectionRest("login");
                JSONObject user = new JSONObject();

                try {
                    user.put("email", email);
                    user.put("password", password);

                    connectionRest.setObj(user);
                    connectionRest.execute("POST");

                    String response = connectionRest.get();
                    JSONObject obj = new JSONObject(response);

                    Intent intent = new Intent(LoginActivity.this, Rest_Response.class);
                    intent.putExtra("token", obj.getString("token"));
                    intent.putExtra("response", obj.getString("response"));
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
