package com.smc.firstapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smc.firstapp.api.Api;
import com.smc.firstapp.model.ListItem;
import com.smc.firstapp.model.SearchRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = findViewById(R.id.textview);
        EditText editText = findViewById(R.id.edittext);
        Button button = findViewById(R.id.button);
        Button btnList = findViewById(R.id.btnList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(
                                MainActivity.this,
                                ListActivity.class
                        )
                );
            }
        });
    }

    void callApi() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                Api.create().search("아이패드")
                        .enqueue(new Callback<SearchRes>() {
                            @Override
                            public void onResponse(Call<SearchRes> call, Response<SearchRes> response) {
                                SearchRes res = response.body();
                                for(ListItem item : res.items){
                                    Log.d("honghonghong", item.title);
                                }
                            }

                            @Override
                            public void onFailure(Call<SearchRes> call, Throwable t) {

                            }
                        });

            }
        }.start();
    }
}







