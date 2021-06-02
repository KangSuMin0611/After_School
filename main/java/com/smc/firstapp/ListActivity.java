package com.smc.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smc.firstapp.model.ListItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Context context = this;
    ArrayList<ListItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //데이터 준비
        data.add(new ListItem("호폴", "호로로롤"));
        data.add(new ListItem("이상훈", "이상훈의 작은악마"));
        data.add(new ListItem("김은석", "밥은 먹고 나녀"));
        data.add(new ListItem("강수민", "수민수우웅민"));
        data.add(new ListItem("한준희", "한한준준희"));
        data.add(new ListItem("최인서", "나야 모르지"));
        data.add(new ListItem("정운", "정운아 미안.."));
        data.add(new ListItem("다혜", "많을 다 은혜"));
        data.add(new ListItem("지찬", "정지찬 맞음"));
        data.add(new ListItem("대현", "이게뭐임"));
        data.add(new ListItem("남군윤", "앙라라ㅏ"));
        data.add(new ListItem("탁진호", "으르르"));
        data.add(new ListItem("강순호", "으오오오"));
        data.add(new ListItem("강진희", "ㅁㄴㅇㄻ"));
        data.add(new ListItem("공유", "아이디"));

        // 뷰 준비
        RecyclerView rv = findViewById(R.id.rv);

        // 어댑터를 View에 바인딩
        rv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @NonNull
        @NotNull
        @Override
        // 뷰를  N개 있어서 만들어줌
        public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        //data를 뷰에다가 몇개인지 보내는것.
        public void onBindViewHolder(@NonNull @NotNull ListActivity.MyAdapter.ViewHolder viewHolder, int i) {
            ListItem item = data.get(i);
            viewHolder.tvTitle.setText(item.title);
            viewHolder.tvDesc.setText(item.desc);
        }

        @Override

        //data에 몇개 있냐 물어보고
        public int getItemCount() {
            return data.size(); //.length 같은 거?
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;
            TextView tvDesc;

            public ViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvDesc = itemView.findViewById(R.id.tvDesc);
            }

        }
    }
}