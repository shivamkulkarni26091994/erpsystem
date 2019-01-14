package com.example.shivam.erpsystem.Fragment;


import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shivam.erpsystem.Activity.DashboardActivity;
import com.example.shivam.erpsystem.Activity.LoginActivity;
import com.example.shivam.erpsystem.Adapter.CategoryAdapter;
import com.example.shivam.erpsystem.Adapter.PieChartAdapter;
import com.example.shivam.erpsystem.Model.CategoryModel;
import com.example.shivam.erpsystem.R;

import org.json.JSONArray;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<CategoryModel> categoryModelList;
    EditText categoryName;
    private String cat_id, cat_name;
    ImageButton addNewCategory, cancelCategory;
    String cateName;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View catefragment = inflater.inflate(R.layout.fragment_category, container, false);
        FloatingActionButton fab = (FloatingActionButton) catefragment.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog addcategory = new Dialog(getActivity());
                addcategory.setContentView(R.layout.categoryaddlayout);
                categoryName = addcategory.findViewById(R.id.addcategotyName);
                addNewCategory = addcategory.findViewById(R.id.addNewCategory);
                cancelCategory = addcategory.findViewById(R.id.cancelCategory);
                addNewCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AddNewCategory().execute();

                    }
                });

               addcategory.show();

            }
        });

        recyclerView = catefragment.findViewById(R.id.categoryRecycler);
        categoryModelList = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        new GetGategory().execute();


        return catefragment;
    }


    public class GetGategory extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {
                URL url = new URL("http://192.168.43.246/erpsystem/get_all_category.php"); // here is your URL path
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("u_id", "!@#123#$$^^#");
                postDataParams.put("org_id", 5);

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
                if (result != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(result);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("categoryArray");

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            categoryModelList.add(new CategoryModel(c.getString("cat_id"), c.getString("cat_name")));
                        }
                        adapter = new CategoryAdapter(getActivity(), categoryModelList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
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

    public class AddNewCategory extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {
                URL url = new URL("http://192.168.43.246/erpsystem/save_update_category.php"); // here is your URL path
                JSONObject postDataParams = new JSONObject();
                cat_name=categoryName.getText().toString().trim();
                postDataParams.put("u_id", "!@#123#$$^^#");
                postDataParams.put("cat_id","");
                postDataParams.put("cat_name", cat_name);
                postDataParams.put("status", 1);
                postDataParams.put("org_id", "5");


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
                if (result != null) {
                    try {
                        JSONObject obj = new JSONObject(result);
                        if (obj.get("success").equals(1)) {
                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                            Toast.makeText(getActivity(),obj.getString("Invalid username or password"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
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
