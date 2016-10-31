package com.hongseokandrewjang.android.firebase_auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FIREBASE_AUTH";
    ArrayList<User> users = new ArrayList<>();

    Button btnSignUp;
    Button btnSignIn;
    Button btnWithFacebook;
    EditText etPassword;
    EditText etEmail;
    ListView mListView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase database;
    DatabaseReference userDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWithFacebook = (Button)findViewById(R.id.btnFacebook);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        mListView = (ListView)findViewById(R.id.listView);

        setData();

        // 1. 인증객체 가져오기
        mAuth = FirebaseAuth.getInstance();
        // 2. 리스너 설정
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        // 4. 신규계정 생성
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (!email.equals("")&&!password.equals("")){
                    addUser(email,password);
                }else{
                    Toast.makeText(MainActivity.this,"잘못된 입력입니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 5. 들어가기
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (!email.equals("")&&!password.equals("")){
                    signIn(email,password);
                }else{
                    Toast.makeText(MainActivity.this,"잘못된 입력입니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (!email.equals("")&&!password.equals("")){
                    signInWithFaceBook(email,password);
                }else{
                    Toast.makeText(MainActivity.this,"잘못된 입력입니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Datasbase 불러오기
        updateList();
    }

    public void updateList(){
        CustomAdapter adapter = new CustomAdapter(users,getLayoutInflater());
        mListView.setAdapter(adapter);
    }

    public void signInWithFaceBook(String email, String password){
//        mAuth.
    }

    public void addUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "등록이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "접속에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(MainActivity.this, "접속에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            User new_user = new User(email,password);
                            addNewUser(new_user);
                        }
                    }
                });
    }
    // 3. 리스너 해제 및 재등록 처리
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void setData(){

        database = FirebaseDatabase.getInstance();
        userDatas = database.getReference();

        userDatas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                for (DataSnapshot user_info : users.getChildren()){
                    String userId = user_info.getKey();
                    User user = users.getValue(User.class);
                    user.userId = userId;
                    MainActivity.this.users.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void addNewUser(User new_user) {
        User user = new User(new_user.userPassword, new_user.userEmail);
        Map<String, Object> userInfo = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Users/"+user.userId, userInfo);

        userDatas.updateChildren(childUpdates);
    }
}
