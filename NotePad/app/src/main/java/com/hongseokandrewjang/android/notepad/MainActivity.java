package com.hongseokandrewjang.android.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<BbsData> BbsDataBase = new ArrayList<>();
    View activityMainView;
    RecyclerView recyclerView;
    RelativeLayout content_main;
    RelativeLayout content_write;
    RelativeLayout inputLayout;
    RelativeLayout detailLayout;
    EditText titleInput;
    EditText contextInput;
    TextView detailTitle;
    TextView detailContents;
    FloatingActionButton writeBtn;
    FloatingActionButton cancelBtn;
    FloatingActionButton flyingMenu;
    FloatingActionButton fromDetailToMain;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainView = findViewById(R.id.activityMainView);
        content_main = (RelativeLayout)findViewById(R.id.contentMainRelativeLayout);
        content_write = (RelativeLayout)findViewById(R.id.contentWriteRelativeLayout);
        inputLayout = (RelativeLayout)findViewById(R.id.inputLayout);
        writeBtn = (FloatingActionButton)findViewById(R.id.writeBtn);
        cancelBtn = (FloatingActionButton)findViewById(R.id.cancelBtn);
        flyingMenu = (FloatingActionButton)findViewById(R.id.flyingMenu);
        fromDetailToMain = (FloatingActionButton)findViewById(R.id.fromDetailToMainFloatingBtn);
        titleInput = (EditText)findViewById(R.id.titleInput);
        contextInput = (EditText)findViewById(R.id.contextInput);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        detailLayout = (RelativeLayout)findViewById(R.id.detailRelativeLayout);
        detailTitle = (TextView)findViewById(R.id.detailTitle);
        detailContents = (TextView)findViewById(R.id.detailContext);

        content_main.setVisibility(View.VISIBLE);
        content_write.setVisibility(View.GONE);
        detailLayout.setVisibility(View.GONE);

        // Floating Action Button을 누르면 content_write가 뜬다
        flyingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content_main.setVisibility(View.GONE);
                flyingMenu.setVisibility(View.GONE);
                content_write.setVisibility(View.VISIBLE);
                keyBoardON();
            }
        });

        // write 버튼을 누르면 글의 가장 위는 제목이 되고 그 다음부터는 contents가 된다
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    count++;
                    String title = titleInput.getText().toString();
                    String context = contextInput.getText().toString();
                    BbsData data = new BbsData(count + ". "+title, context,count);
                    BbsDataBase.add(data);
                    goMain();
                setNewAdapter();
            }
        });

        // cancel 버튼을 누르면 그냥 메인으로 간다
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });

        // Detail 에서 Main으로 다시
        fromDetailToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });

        // adapter 붙여주기
        setNewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        goMain();
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left);
        animation.setDuration(3000);
    }

    public void goMain(){
        flyingMenu.setVisibility(View.VISIBLE);
        content_main.setVisibility(View.VISIBLE);
        content_write.setVisibility(View.GONE);
        detailLayout.setVisibility(View.GONE);
        titleInput.setText("");
        contextInput.setText("");
    }
    public void keyBoardON(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
    }


    public void setNewAdapter() {
        recyclerAdapter recycleradapter = new recyclerAdapter(BbsDataBase, R.layout.content_item,this);
        recyclerView.setAdapter(recycleradapter);
    }

    public void goToDetail(BbsData data){
        flyingMenu.setVisibility(View.GONE);
        content_main.setVisibility(View.GONE);
        content_write.setVisibility(View.GONE);
        fromDetailToMain.setVisibility(View.VISIBLE);
        detailTitle.setText(data.title);
        detailContents.setText(data.contents);
        detailLayout.setVisibility(View.VISIBLE);
    }
}
