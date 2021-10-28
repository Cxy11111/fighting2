package cn.edu.swufe.happ;

import android.app.Activity;
import android.app.ListActivity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decmaker extends AppCompatActivity implements AdapterView.OnItemClickListener{
     private TextView text;

      List<String> decList;
      ListAdapter adapter;

      private EditText putin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decision_maker);

        ListView listView =(ListView)findViewById(R.id.dec_list);
        listView.setOnItemClickListener(this);





    }




    //获取输入的文本添加到listview中
    public void tianjia(View btn1){
        ListView listView = findViewById(R.id.dec_list);

        EditText opt1=findViewById(R.id.dec_putin);
        String str=opt1.getText().toString();




    }


    //开始抽取选项，跳转页面至结果
    public void kaishi(View btn2){

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
