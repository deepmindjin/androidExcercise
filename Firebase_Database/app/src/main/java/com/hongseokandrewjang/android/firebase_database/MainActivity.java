package com.hongseokandrewjang.android.firebase_database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference chickenstoreData;

    ListView listView;
    ArrayList<Map<String,ChickenStore>> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        chickenstoreData = database.getReference("CHICKENSTORE");

        chickenstoreData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot stores) {
                for (DataSnapshot storeData : stores.getChildren()){
                    String storeName = storeData.getKey();
                    ChickenStore store = stores.getValue(ChickenStore.class);
                    store.name = storeName;
                    for (DataSnapshot menus : storeData.getChildren()){
                        HashMap<String,ChickenStore.Menu> menu_item = new HashMap<>();
                        String menu_name = menus.getKey();
                        ChickenStore.Menu item = menus.getValue(ChickenStore.Menu.class);
                        item.menu_name = menu_name;
                        menu_item.put(menu_name,item);
                        Log.e("MENU ITEM","메뉴의 이름은 : "+menu_name+"메뉴는 : "+item.toString());
                        store.menus.add(item);
                    }
                    Log.e("STORE","가게는 : "+store.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    class ListAdapter extends BaseAdapter{

        LayoutInflater inflater;

        public ListAdapter(){
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = inflater.inflate(R.layout.list_item,null);

            TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
            TextView tvUid = (TextView)convertView.findViewById(R.id.tvUid);
            TextView tvEmail = (TextView)convertView.findViewById(R.id.tvEmail);

            Map<String,ChickenStore> data = datas.get(position);
            String uid = data.keySet().iterator().next();

            tvUid.setText("하이여");

            return convertView;
        }
    }
}