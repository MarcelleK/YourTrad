package org.libreapps.yourtrad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class InscriptionActivity extends AppCompatActivity
{

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

    private Button buttonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        buttonValidate = (Button) findViewById(R.id.button_validate);

        buttonValidate.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                firstname = ((EditText) findViewById(R.id.edittext_firstname)).getText().toString();
                lastname  = ((EditText) findViewById(R.id.edittext_lastname)).getText().toString();
                email     = ((EditText) findViewById(R.id.edittext_email)).getText().toString();
                password  = ((EditText) findViewById(R.id.edittext_password)).getText().toString();

                try
                {
                    ConnectionRest connectionRest = new ConnectionRest("user");
                    JSONObject user = new JSONObject();
                    user.put("firstname", firstname);
                    user.put("lastname", lastname);
                    user.put("email", email);
                    user.put("password", password);
                    connectionRest.setObj(user);
                    connectionRest.execute("POST");

                    String response = connectionRest.get();
                    JSONObject obj = new JSONObject(response);
                    String res = obj.getString("response");

                    Intent intent = new Intent(InscriptionActivity.this, Rest_Response.class);
                    intent.putExtra("response", res);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}