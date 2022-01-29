package com.map.scannerhhhh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    static String Username;
    static int Id;
    static String ip = "blc-sal.herokuapp.com";
    static String userUrl = "http://" + ip + "/users/all";
    private ArrayList<com.map.scannerhhhh.beans.User> user;
    private ArrayList<String> userList;

    StringRequest request;
    Bundle bundle = new Bundle();

    private EditText email;
    private EditText password;
    private Button login;

    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        View overlay = findViewById(R.id.container);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        login.setOnClickListener(this);
    }


    public void onClick(View v) {

        request = new StringRequest(Request.Method.GET, userUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("hhhh","aze");
                Log.d("hhhh","aze");
                Log.d("hhhh","aze");
                try {
                    user= new ArrayList<>() ;
                    userList= new ArrayList<>() ;
                    Log.d("TAG", response);
                    Type type = new TypeToken<Collection<com.map.scannerhhhh.beans.User>>() {
                    }.getType();

                    Collection<com.map.scannerhhhh.beans.User> Users = new Gson().fromJson(response, type);
                    for(com.map.scannerhhhh.beans.User e : Users){

                        String test=e.getEmail();
                        String test1=e.getPassword();


                        if (email.getText().toString().equals(test) && password.getText().toString().equals(test1)) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Username=e.getUsername();
                            Id=e.getId();
                            bundle.putParcelableArrayList("MYLIST1",  user);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            num=1;
                            break;
                        }

                    }
                    if (num==0){
                    Toast.makeText(getApplicationContext(), "The email or the password is incorrect.", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception exception) {
                    exception.printStackTrace();
                }
            }},new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
    public static String getIp(){
        return ip;
    }

    public static String getUsername() {
        return Username;
    }

    public static int getId() {
        return Id;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static void setId(int id) {
        Id = id;
    }
}