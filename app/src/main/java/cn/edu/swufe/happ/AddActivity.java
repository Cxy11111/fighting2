package cn.edu.swufe.happ;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    EditText note;
    private int note_id=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add1);
        note = findViewById(R.id.add_putin);
        Button btn = findViewById(R.id.add_save);
        String str = note.getText().toString();
        btn.setOnClickListener(this);



    }



    public void openOne(View btn){
        Intent hello=new Intent(this,NoteActivity.class);
        startActivity(hello);
    }


    public void save(View btn){

    }


    @Override
    public void onClick(View v) {
        //Intent notes=new Intent(this,NoteActivity.class);
         //写新的，然后保存，传数据过去
        NoteManager manager = new NoteManager(this);
        note = findViewById(R.id.add_putin);
        String str = note.getText().toString();
        NoteItem noteItem = new NoteItem();
        noteItem.curNote = note.getText().toString();
        noteItem.id = note_id;
        note_id = manager.newaddAll(noteItem);
       String str1 = String.valueOf(note_id);


//        //获取当前系统时间
//        Date today = Calendar.getInstance().getTime();
//        //转换为字符串类型
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        final String todayStr=sdf.format(today);
        Intent intent = getIntent();
        intent.putExtra("noteid",str1);
        intent.putExtra("note",str);
//       Bundle bd1 = new Bundle();
//        bd1.putString("putin_note",str);
//       // bd1.putString("putin_time",todayStr);
//      //  intent.putExtras(bd1);
//        intent.putExtra("putin_note",str);
//        //intent.putExtra("putin_time",todayStr);
        setResult(2,intent);
        finish();

        //startActivity(notes);
    }



}



