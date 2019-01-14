package com.example.shivam.erpsystem.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.erpsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    EditText email, pass;
    Button login, registration;
    TextView newuser, forgetpass;
    CheckBox remeberme;
    String mail, password, user_email,user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        registration = (Button) findViewById(R.id.regi);
        newuser = (TextView) findViewById(R.id.newtouser);
        forgetpass = (TextView) findViewById(R.id.forgetpass);
        remeberme = (CheckBox) findViewById(R.id.remberme);

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ForGotPassword = new Intent(LoginActivity.this, ForGotPasswordActivity.class);
                startActivity(ForGotPassword);
            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(Register);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(LoginActivity.this,DashboardActivity.class);
                startActivity(Login);
               /* if (validate()) {
                    new SendPostRequest().execute();
                }
*/
            }
        });

    }

    public boolean validate() {

        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        boolean check = true;
        if (mail.isEmpty()) {
            email.setError("Field can't be empty");
            check = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please enter a valid email address");
            check = false;
        } /*else if (password.isEmpty()) {
            pass.setError("Field can't be empty");
            check = false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            pass.setError("at least 1 special character,at least 4 characters");
            check = false;
        }*/

        return check;
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {
                mail = email.getText().toString().trim();
                password = pass.getText().toString().trim();

                URL url = new URL("http://192.168.43.246/erpsystem/user_login.php"); // here is your URL path
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", mail);
                postDataParams.put("password", password);
                postDataParams.put("u_id", "!@#123#$$^^#");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            try {
                //converting response to json object
                JSONObject obj = new JSONObject(result);
                int count=0;
                while(count<obj.length())
                {
                    user_name=obj.getString("user_name");
                    user_email=obj.getString("email");
                    count++;
                }
                if (obj.get("success").equals(1)) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    Intent Login = new Intent(LoginActivity.this, DashboardActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("user_name",user_name);
                    bundle.putString("user_email",user_email);
                    Login.putExtras(bundle);
                    startActivity(Login);
                    finish();



                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),obj.getString("Invalid username or password"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

    }
}
