package com.hongseokandrewjang.android.firebase_database01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ChickenStore> sChickenStores= new ArrayList<>();

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference chickenRef;

    CustomAdapter<ChickenStore> adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        chickenRef = mFirebaseDatabase.getReference("CHICKENSTORE");

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_in_main);
        adapter = new CustomAdapter<>(sChickenStores,R.layout.detail_item_layout,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        chickenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChickenStore chickenStore = snapshot.getValue(ChickenStore.class);
                    sChickenStores.add(chickenStore);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
