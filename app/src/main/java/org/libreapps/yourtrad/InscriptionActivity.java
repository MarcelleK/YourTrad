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

public class InscriptionActivity extends AppCompatActivity {

    private EditText firstname;
    private EditText lastname;
    private EditText username;
    private EditText email;
    private EditText password;

    private Button buttonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        firstname = (EditText) findViewById(R.id.edittext_firstname);
        lastname  = (EditText) findViewById(R.id.edittext_lastname);
        //username  = (EditText) findViewById(R.id.edittext_lastname);
        email     = (EditText) findViewById(R.id.edittext_email);
        password  = (EditText) findViewById(R.id.edittext_password);

        buttonValidate = (Button) findViewById(R.id.button_validate);

        buttonValidate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    ConnectionRest connectionRest = new ConnectionRest("user");
                    JSONObject user = new JSONObject();
                    user.put("prenom", firstname.getText().toString());
                    user.put("nom", lastname.getText().toString());
                    user.put("email", email.getText().toString());
                    user.put("password", password.getText().toString());
                    connectionRest.setJsonObj(user);
                    //connectionRest.execute("CREATE_USER");
                    String token = (String) connectionRest.get("CREATE_USER");

                    Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                    intent.putExtra("token", token);
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