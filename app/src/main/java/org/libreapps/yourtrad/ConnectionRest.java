package org.libreapps.yourtrad;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.libreapps.yourtrad.obj.Message;
import org.libreapps.yourtrad.obj.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ConnectionRest extends AsyncTask<String, Void, String> {

    //private final static String URL = "http://127.0.0.1:5000/";
    private final static String URL = "http://10.0.2.2:5000/";

    private JSONObject jsonObj = null;
    private String action = "";
    private String token = null;

    public ConnectionRest(){
    }

    public ConnectionRest(String action){
        this.action = action;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            return get(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String get(String methode) throws IOException, JSONException {
        String url = URL + action ;
        InputStream is = null;
        String parameters = "";
        if(!methode.equals("POST")&&(jsonObj!=null)&&!methode.equals("CREATE_USER")){
            url += jsonObj.getInt("id");
        }
        if(jsonObj != null){
            if(methode.equals("PUT")){
                jsonObj.remove("id");
            }
            parameters  = "data="+URLEncoder.encode(jsonObj.toString(), "utf-8");
            //Log.v("URL", url+" "+parameters);
        }

        try {
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod(methode);
            if (token != null) {
                conn.setRequestProperty("Authorization", "Bearer " + URLEncoder.encode(token, "utf-8"));
            }

            // Pour les methode POST et PUT on envoie les parametre avec l'OutputStreamWriter
            if(methode.equals("POST")||methode.equals("PUT")){
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(parameters);// here i sent the parameter
                out.close();
            }
            else{
                conn.setDoInput(true);
                conn.connect();
            }

            is = conn.getInputStream();
            // Lit le InputStream et l'enregistre dans une string
            return readIt(is);
        } finally
        {
            // Pour s'assurer de la bonne fermeture du flux
            if (is != null)
            {
                is.close();
            }
        }
    }

    private String readIt(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

    public ArrayList<User> parse(final String json) {
        try {
            final ArrayList<User> products = new ArrayList<>();
            final JSONArray jProductArray = new JSONArray(json);
            for (int i = 0; i < jProductArray.length(); i++) {
                products.add(new User(jProductArray.optJSONObject(i)));
            }
            return products;
        } catch (JSONException e) {
            Log.v("TAG","[JSONException] e : " + e.getMessage());
        }
        return null;
    }

    public void setObj(JSONObject jsonObj){
        this.jsonObj = jsonObj;
    }
    public void setAction(String monAction){ this.action = monAction;}
    public void setToken(String monToken) { this.token = monToken; }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
