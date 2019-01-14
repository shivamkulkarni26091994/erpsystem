package com.example.shivam.erpsystem.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.erpsystem.Activity.DashboardActivity;
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

public class RegisterActivity extends AppCompatActivity {
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
    private static final Pattern MOBILE_PATTERN =
            Pattern.compile("^([789]{1})([02346789]{1})([0-9]{8})$");
    private static final Pattern PANCRAD_PATTERN =
            Pattern.compile("^[A-Z]{3}[G|A|F|C|T|H|P]{1}[A-Z]{1}\\d{4}[A-Z]{1}$");
    EditText full_Name,email,mobile_number,address,gst_Number,pan_Number,enter_password;
    Button register;
    String name,mail,number,add,gst,pan,pass;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.loginpage);
        full_Name=findViewById(R.id.full_name);
        email=findViewById(R.id.email);
        mobile_number=findViewById(R.id.mobileno);
        address=findViewById(R.id.address);
        gst_Number=findViewById(R.id.gstno);
        pan_Number=findViewById(R.id.panno);
        enter_password=findViewById(R.id.password);
        register=findViewById(R.id.register);
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
});
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()) {
                    new Register().execute();
                }

            }
        });
    }

    public boolean validate() {
        name=full_Name.getText().toString().trim();
        mail=email.getText().toString().trim();
        number=mobile_number.getText().toString().trim();
        add=address.getText().toString().trim();
        gst=gst_Number.getText().toString().trim();
        pan=pan_Number.getText().toString().trim();
        pass=enter_password.getText().toString().trim();
        boolean check=true;
        if(name.isEmpty())
        {
            full_Name.setError("Field can't be empty");
            check=false;
        }else if(mail.isEmpty())
        {
            email.setError("Field can't be empty");
            check=false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            email.setError("Please enter a valid email address");
            check=false;
        }else if(number.isEmpty())
        {
            mobile_number.setError("Field can't be empty");
            check=false;
        }else if(!MOBILE_PATTERN.matcher(number).matches())
        {
            mobile_number.setError("Please enter a valid mobile number");
            check=false;
        }
        else if(add.isEmpty()) {
            address.setError("Field can't be empty");
            check=false;
        }else if(pan.isEmpty())
        {
            pan_Number.setError("Field can't be empty");
            check=false;
        }else if(pan.isEmpty())
        {
            pan_Number.setError("Field can't be empty");
            check=false;
        } else if(!PANCRAD_PATTERN.matcher(pan).matches())
        {
            enter_password.setError("Please enter a valid pan number");
            check=false;
        }else if(pass.isEmpty())
        {
            enter_password.setError("Field can't be empty");
            check=false;
        }
        else if(!PASSWORD_PATTERN.matcher(pass).matches())
        {
            enter_password.setError("at least 1 special character,at least 4 characters");
            check=false;
        }

        return check;
    }

    class Register extends AsyncTask<String,Integer,String>
    {
        protected void onPreExecute(){}
        @Override
        protected String doInBackground(String... strings) {

            try {
                name=full_Name.getText().toString();
                mail=email.getText().toString();
                number=mobile_number.getText().toString();
                add=address.getText().toString();
                gst=gst_Number.getText().toString();
                pan=pan_Number.getText().toString();
                pass=enter_password.getText().toString();
                URL url = new URL("http://192.168.43.247/erpsystem/user_signup.php"); // here is your URL path
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_name",name);
                postDataParams.put("email", mail);
                postDataParams.put("contact", number);
                postDataParams.put("password", pass);
                postDataParams.put("role_id", "2");
                postDataParams.put("org_id", "1");
                postDataParams.put("address", add);
                postDataParams.put("gst_no", gst);
                postDataParams.put("pan_no", pan);
                postDataParams.put("u_id", "!@#123#$$^^#");
                Log.e("params",postDataParams.toString());

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

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                    }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                //converting response to json object
                JSONObject obj = new JSONObject(result);
                if (obj.get("success").equals(1)) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
    public String getPostDataString(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){
            String key= itr.next();
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
