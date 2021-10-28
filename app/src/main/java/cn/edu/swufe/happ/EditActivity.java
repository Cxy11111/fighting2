package cn.edu.swufe.happ;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//点击列表行进入页面
public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    EditText note;
    EditText note1;
    EditText noteid;
    private int note_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        note = findViewById(R.id.edit_putin);

        Button btn = findViewById(R.id.edit_save);
        String str = note.getText().toString();
        btn.setOnClickListener(this);

        Intent intent = getIntent();
        String edit_note = intent.getStringExtra("edit_note");
        note_id= intent.getIntExtra("note_ID",0);
        note.setText(edit_note);
       //oteid.setText(note_id);


    }


    @Override
    public void onClick(View v) {
        NoteManager manager = new NoteManager(this);
        note1 = findViewById(R.id.edit_putin);
        String str = note1.getText().toString();
        NoteItem noteItem = new NoteItem();

        //获取当前系统时间
        Date today = Calendar.getInstance().getTime();
        //转换为字符串类型
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final String todayStr=sdf.format(today);
        String datenote="上一次更新："+todayStr+"：    "+str;

        noteItem.curNote = str;
        noteItem.id = note_id;
        String str1= String.valueOf(note_id);
        //noteItem = manager.getNoteById(note_id);
        manager.update(noteItem);





        Intent intent = getIntent();
//        Bundle bd1 = new Bundle();
//        bd1.putString("new_note",str);
//        // bd1.putString("putin_time",todayStr);
//        //  intent.putExtras(bd1);
        intent.putExtra("new_note",str);
        intent.putExtra("noteid",str1);
//        //intent.putExtra("putin_time",todayStr);
        setResult(4,intent);
        finish();
    }
}
