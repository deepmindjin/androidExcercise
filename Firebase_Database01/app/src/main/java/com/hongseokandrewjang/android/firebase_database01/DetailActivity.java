package com.hongseokandrewjang.android.firebase_database01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static ArrayList<MENU> menus = new ArrayList<>();

    TextView tvStoreName;
    TextView tvBranchName;
    TextView tvDeliveryFee;
    ImageView imgChicken;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvStoreName = (TextView)findViewById(R.id.tvStoreName);
        tvBranchName = (TextView)findViewById(R.id.tvBranchName);
        tvDeliveryFee = (TextView)findViewById(R.id.tvDeliveryFee);
        imgChicken = (ImageView)findViewById(R.id.imgChicken);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_in_detail);


        int position = getIntent().getIntExtra("position",0);
        ChickenStore store = MainActivity.sChickenStores.get(position);
        String store_name = store.getNAME();
        String branch_name = store.getBRANCH();
        Long delivery_fee = store.getDELIVERY_FEE();
        String logo_url = store.getLOGO();
        menus = (ArrayList<MENU>)store.getMENU();

        tvStoreName.setText(store_name);
        tvBranchName.setText(branch_name);
        tvDeliveryFee.setText(delivery_fee+"원");

        CustomAdapter<MENU> adapter = new CustomAdapter<>(menus,R.layout.detail_item_layout,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
