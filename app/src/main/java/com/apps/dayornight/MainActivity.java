package com.apps.dayornight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.apps.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kira on 2016/4/7.
 */
public class MainActivity extends BaseActivity {
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        list = new ArrayList<>();
        list.add("DeviceUtils");
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemAdapter(list));

        AppCompatCheckBox checkBox = (AppCompatCheckBox)findViewById(R.id.check);
        checkBox.setChecked(SharedPreferenceUtils.get(this));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferenceUtils.save(MainActivity.this,isChecked);
                recreateUI();
            }
        });


    }

    private void onItemClick(int position){
        startActivity(new Intent(this,DeviceActivity.class));
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{
        private List<String> list;

        public ItemAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return null == list ? 0 :list.size();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.recyclerview_item,parent,false));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.initItem(list.get(position),position);
        }
    }

    class ItemViewHolder extends  RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cardView =(CardView)itemView.findViewById(R.id.cardview);
            textView = (TextView)itemView.findViewById(R.id.textview);
        }

        public void initItem(String text, final int position){
            textView.setText(text);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(position);
                }
            });
        }

    }
}
