package cn.edu.swufe.happ;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity{
    String data[] = {"1","2","3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        ListView listView =(ListView)findViewById(R.id.list_test);
        String data[] = {"1","2","3"};

        List<String>list1= new ArrayList<String>();
        list1.add("1");
//
        ListAdapter adapter = new ArrayAdapter<String>(this,
              android.R.layout.simple_list_item_1,list1);
         listView.setAdapter(adapter);


    }
}
