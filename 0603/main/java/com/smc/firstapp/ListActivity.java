package com.smc.firstapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smc.firstapp.api.Api;
import com.smc.firstapp.model.ListItem;
import com.smc.firstapp.model.SearchRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    Context context = this;
    ArrayList<ListItem> data = new ArrayList<>();
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // View 준비
        rv = findViewById(R.id.rv);
        Button btnSearch = findViewById(R.id.btnSearch);
        EditText etKeyword = findViewById(R.id.etKeyword);

        // 어댑터를 View에 바인딩
        rv.setAdapter(new MyAdapter());
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etKeyword.getText().toString();
                callApi(input);
            }
        });

        // 데이터 준비
//        callApi();

    }

    void callApi(String keyword) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                Api.create().search(keyword)
                        .enqueue(new Callback<SearchRes>() {
                            @Override
                            public void onResponse(Call<SearchRes> call, Response<SearchRes> response) {
                                SearchRes res = response.body();
                                data = res.items;
                                rv.getAdapter().notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<SearchRes> call, Throwable t) {

                            }
                        });

            }
        }.start();
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        // 뷰를 5개 만들어야겠네
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Log.d("hong", "onCreateViewHolder");
            View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListActivity.MyAdapter.ViewHolder viewHolder, int position) {
            ListItem item = data.get(position);
            viewHolder.tvTitle.setText(Html.fromHtml(item.title));
            viewHolder.tvMallName.setText(item.mallName);
            viewHolder.tvPrice.setText(String.format("%, d원", item.lprice));
            Glide.with(context)
                    .load(item.image)
                    .into(viewHolder.ivThumb);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.link)));
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size(); // 나 아이템 5개 있음
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;
            TextView tvMallName;
            TextView tvPrice;
            ImageView ivThumb;

            public ViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvMallName = itemView.findViewById(R.id.tvMallName);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                ivThumb = itemView.findViewById(R.id.ivThumb);
            }
        }
    }
}
