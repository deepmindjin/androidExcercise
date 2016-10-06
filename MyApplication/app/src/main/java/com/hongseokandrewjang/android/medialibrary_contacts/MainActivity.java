package com.hongseokandrewjang.android.medialibrary_contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactsData> datas = new ArrayList<>();
    private final static int REQUEST_CODE = 100;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            initData();
        else
            checkPermission();
    }


    @TargetApi(M)
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            String permissionArray[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionArray, REQUEST_CODE);
        } else {
            initData();
        }
    }

    public void initData() {
        datas = getPhoneNumbers();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ContactsAdapter adapter = new ContactsAdapter(datas, R.layout.cardviewlayout, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<ContactsData> getContacts() {
        String projections[] = {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID
        };

        // ---------------------------------------------
//        String selection = MediaStore.Audio.Media.TITLE + " != '야생화' ";
//        String selection = MediaStore.Audio.Media.TITLE + " != ? ";
        // ---------------------------------------------

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projections, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                ContactsData data = new ContactsData();

                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = cursor.getString(idIndex);

                Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        // 주소록과 전화번호의 data set이 분리되어 있다
                        // 그래서 주소록에서 그 사람의 id를 찾아서 전화번호의 여러 table 중에서 id가 같은 것만 골라서 가져온다
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, // " 폰의 아이디 = 주소록의 아이디 "
                        null,
                        null);
                if (phoneCursor.moveToFirst()) {
                    String tel_num = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    data.number = tel_num;
                    data.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                }
                datas.add(data);
                phoneCursor.close();
            }

        }
        cursor.close();     // 이거안하면 앱이 엄청느려짐
        return datas;
    }

    public ArrayList<ContactsData> getPhoneNumbers() {
        String repeatName = "";
        ArrayList<ContactsData> datas = new ArrayList<>();
        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc");
        if (c != null) {
            while (c.moveToNext()) {
                ContactsData data = new ContactsData();
                data.name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                if (!repeatName.equals(data.name)) {
                    repeatName = data.name;
                    data.number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    datas.add(data);
                }
            }
        }
        c.close();
        return datas;
    }
}
