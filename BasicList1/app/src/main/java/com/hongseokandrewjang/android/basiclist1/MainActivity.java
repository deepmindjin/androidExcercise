package com.hongseokandrewjang.android.basiclist1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button listView1btn;
    Button listView2btn;
    Button listCustombtn;
    Button gridViewbtn;
    Button gridCustombtn;
    Button expandableListbtn;
    Button recyclerViewbtn;
    Button recyclerAnimaitonViewbtn;
    Button recyclerCardViewBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1btn = (Button)findViewById(R.id.listView1btn);
        listView2btn = (Button)findViewById(R.id.listView2btn);
        listCustombtn = (Button)findViewById(R.id.listCustombtn);
        gridViewbtn = (Button)findViewById(R.id.gridViewbtn);
        gridCustombtn = (Button)findViewById(R.id.gridCustombtn);
        expandableListbtn = (Button)findViewById(R.id.expandableListbtn);
        recyclerViewbtn = (Button)findViewById(R.id.recyclerViewbtn);
        recyclerAnimaitonViewbtn = (Button)findViewById(R.id.recyclerAnimationViewBtn);
        recyclerCardViewBtn = (Button)findViewById(R.id.recyclerCardViewBtn);

        listView1btn.setOnClickListener(this);
        listView2btn.setOnClickListener(this);
        listCustombtn.setOnClickListener(this);
        gridViewbtn.setOnClickListener(this);
        gridCustombtn.setOnClickListener(this);
        expandableListbtn.setOnClickListener(this);
        recyclerViewbtn.setOnClickListener(this);
        recyclerAnimaitonViewbtn.setOnClickListener(this);
        recyclerCardViewBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.listView1btn:
                intent = new Intent(this, BasicList1Activity.class);
                break;
            case R.id.listView2btn:
                intent = new Intent(this, BasicList2Activity.class);
                break;
            case R.id.listCustombtn:
                intent = new Intent(this, CustomListActivity.class);
                break;
            case R.id.gridViewbtn:
                intent = new Intent(this, BasicGridActivity.class);
                break;
            case R.id.gridCustombtn:
                intent = new Intent(this, CustomGridActivity.class);
                break;
            case R.id.expandableListbtn:
                intent = new Intent(this, ExpandableActivity.class);
                break;
            case R.id.recyclerViewbtn:
                intent = new Intent(this, RecyclerActivity.class);
                break;
            case R.id.recyclerAnimationViewBtn:
                intent = new Intent(this, RecyclerAnimationActivity.class);
                break;
            case R.id.recyclerCardViewBtn:
                intent = new Intent(this, RecyclerCardActivity.class);
                break;
        }
        startActivity(intent);
    }
}
