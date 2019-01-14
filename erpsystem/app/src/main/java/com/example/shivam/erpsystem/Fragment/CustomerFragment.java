package com.example.shivam.erpsystem.Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shivam.erpsystem.Activity.DashboardActivity;
import com.example.shivam.erpsystem.Activity.LoginActivity;
import com.example.shivam.erpsystem.Adapter.CategoryAdapter;
import com.example.shivam.erpsystem.Adapter.CustomerAdapter;
import com.example.shivam.erpsystem.Adapter.PieChartAdapter;
import com.example.shivam.erpsystem.Model.CategoryModel;
import com.example.shivam.erpsystem.Model.CustomerModel;
import com.example.shivam.erpsystem.Model.ProgressModel;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment {
    //private RecyclerView recyclerView;
    private PieChartAdapter pieChartAdapter;
    private List<ProgressModel> ProgressList;
    private RecyclerView recyclerView;
    private CustomerAdapter adapter;
    private List<CustomerModel> customerModelList;
    int total,paid;

    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_customer, container, false);


new SendPostRequest().execute();
        recyclerView = view.findViewById(R.id.customerRecycler);
        customerModelList = new ArrayList<>();
        adapter = new CustomerAdapter(getActivity(), customerModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        ProgressList = new ArrayList<>();
        pieChartAdapter= new PieChartAdapter(getActivity(), ProgressList);
        ProgressModel a = new ProgressModel(20,100,"20");
        ProgressList.add(a);
        a = new ProgressModel(paid,total,"40");
        ProgressList.add(a);

                ProgressList.add(a);
        CustomerModel customerModel=new CustomerModel("1","Shivam Kulkarni","shivamkulkarni26@gmail.com","9049431310","Ahemdnagr","123456789012345","Abcd123456");
        customerModelList.add(customerModel);
        customerModel=new CustomerModel("1","Shivam Kulkarni","shivamkulkarni26@gmail.com","9049431310","Ahemdnagr","123456789012345","Abcd123456");
        customerModelList.add(customerModel);
        customerModel=new CustomerModel("1","Shivam Kulkarni","shivamkulkarni26@gmail.com","9049431310","Ahemdnagr","123456789012345","Abcd123456");
        customerModelList.add(customerModel);
        customerModel=new CustomerModel("1","Shivam Kulkarni","shivamkulkarni26@gmail.com","9049431310","Ahemdnagr","123456789012345","Abcd123456");
        customerModelList.add(customerModel);
        customerModel=new CustomerModel("1","Shivam Kulkarni","shivamkulkarni26@gmail.com","9049431310","Ahemdnagr","123456789012345","Abcd123456");
        customerModelList.add(customerModel);
        return view;
    }
    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://192.168.43.246/erpsystem/get_customer_payment_status.php"); // here is your URL path
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("u_id", "!@#123#$$^^#");
                postDataParams.put("cust_id","1");
                postDataParams.put("org_id", "2");
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
                    total=obj.getInt("custTotalArray");
                    paid=obj.getInt("custPaidArray");
                    count++;
                }
                /*if (obj.get("success").equals(1)) {
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
                }*/
            } catch (JSONException e) {
                Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
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
