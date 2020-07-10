package org.libreapps.yourtrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

public class LanguageChoice extends AppCompatActivity {

    private String translate_input;
    private String language_input;
    private String language_output;
    private String chosen_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);

        final Spinner spinnerTranslateFrom = findViewById(R.id.spinner_translate_from);
        final Spinner spinnerTranslateTo = findViewById(R.id.spinner_translate_to);
        final Spinner spinnerTraductionLevel = findViewById(R.id.spinner_traduction_level);
        String[] items_languages = new String[]{"Français", "Anglais", "Espagnol", "Allemand"};
        String[] items_translate = new String[]{"Google Translate", "My Memory"};

        ArrayAdapter<String> adapter_language = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items_languages);
        ArrayAdapter<String> adapter_translate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items_translate);

        spinnerTranslateFrom.setAdapter(adapter_language);
        spinnerTranslateTo.setAdapter(adapter_language);
        spinnerTraductionLevel.setAdapter(adapter_translate);

        Button buttonValidate = (Button) findViewById(R.id.button_validate);
        buttonValidate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                translate_input = ((EditText) findViewById(R.id.edittext_translate_input)).getText().toString();
                language_input  = spinnerTranslateFrom.getSelectedItem().toString();
                language_output = spinnerTranslateTo.getSelectedItem().toString();
                chosen_model    = spinnerTraductionLevel.getSelectedItem().toString();

                try
                {
                    ConnectionRest connectionRest = new ConnectionRest("translate_sentence");
                    JSONObject to_send = new JSONObject();
                    to_send.put("translate_input", translate_input);
                    to_send.put("language_input", language_input);
                    to_send.put("language_output", language_output);
                    to_send.put("chosen_model", chosen_model);
                    connectionRest.setObj(to_send);
                    connectionRest.execute("POST");

                    String response = connectionRest.get();
                    JSONObject obj = new JSONObject(response);
                    String translation = obj.getString("translation");

                    Intent intent = new Intent(LanguageChoice.this, Rest_Response.class);
                    intent.putExtra("response", translation);
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