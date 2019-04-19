package com.example.asa;

import android.app.DownloadManager;
import android.content.Intent;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login<MyStringRequest> extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    private static final String URL_FOR_LOGIN = "http://www.novaproget.com/archistore/_/web_service/user.php";
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        TextView txtresult = (TextView) findViewById(R.id.textView2);

    }

    public void azzera(View v){
        TextView f= (TextView) findViewById(R.id.email);
        f.setText("");
    }

    public void sposta(View v){
        startActivity(new Intent(Login.this,MainActivity.class));
    }


    public void login_One(View v){
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        check(username,password);

        loginUser(username,password);


    }




private void check(String username,String password){
    if (TextUtils.isEmpty(username)) {
        editTextUsername.setError("Please enter your username");
        editTextUsername.requestFocus();
        return;
    }

    if (TextUtils.isEmpty(password)) {
        editTextPassword.setError("Please enter your password");
        editTextPassword.requestFocus();
        return;
    }
}

    private void loginUser( final String email, final String password) {
        final TextView txtresult = (TextView) findViewById(R.id.textView2);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL_FOR_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                txtresult.setText("Response is: "+ response.substring(0,500));
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", email); //Add the data you'd like to send to the server.
                MyData.put("password", password); //Add the data you'd like to send to the server.
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }



    }


