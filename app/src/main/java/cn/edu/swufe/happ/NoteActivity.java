package cn.edu.swufe.happ;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;
import static cn.edu.swufe.happ.R.layout.list_item2;

public class NoteActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
     public final String TAG = "NoteActivity";
     TextView note1;
     //ListView note2;
     private String logData = "";
     //private final String DATE_SP_KEY = "lastRateDataStr";
     List<String>data= new ArrayList<String>();
     List<String> list1;
     ArrayList<HashMap<String, String>> list2;
      ListAdapter adapter;
     NoteManager manager;
      SimpleAdapter adapter2;
      private int _ID=0;
    private TextView note_ID;
    String add_note;
    String new_note;
    String add_id;
   int new_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);
        initListView();
        ListView listView =(ListView)findViewById(R.id.list_view);

         //list1= new ArrayList<String>();
         list2=new  ArrayList<HashMap<String, String>>();
         manager=new NoteManager(this);
//           for(NoteItem noteItem:manager.listAll()){
//               list1.add(noteItem.getCurNote()); }
          list2=manager.getList();

//        ListAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,list1);

///////
        adapter2 = new SimpleAdapter( NoteActivity.this,list2, list_item2, new String[] { "id","name"},
            new int[] {R.id.note_Id, R.id.note_text});

        listView.setAdapter(adapter2);
    }

    private void initListView(){
        ListView listView =(ListView)findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setEmptyView(findViewById(R.id.nodata));

    }



//应用菜单的方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addnotes,menu);
        return true;
    }
//菜单项事件处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_add){
            //打开列表窗口
            Intent add1=new Intent(this,AddActivity.class);
            startActivityForResult(add1,1);




        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==2){
          add_id =data.getStringExtra("noteid");
            add_note =data.getStringExtra("note");
          ListView listView =(ListView)findViewById(R.id.list_view);
           // manager=new NoteManager(this);
            //list1= new ArrayList<String>();
            list2= new  ArrayList<HashMap<String, String>>();
           // list1.add(add_note);
            HashMap<String,String>map = new HashMap<>();
            map.put("id",add_id);
            map.put("name",add_note);
            list2.add(map);


//            NoteItem item = new NoteItem();
//            item.curNote=add_note;
//            item.id=_ID;
//
//            _ID=manager.newaddAll(item);
//            //manager.add(item);

//            for(NoteItem noteItem:manager.listAll()){
//                list1.add(noteItem.getCurNote()); }
            list2=manager.getList();

            adapter2 = new SimpleAdapter( NoteActivity.this,list2, list_item2, new String[] { "id","name"},
                    new int[] {R.id.note_Id, R.id.note_text});
            listView.setAdapter(adapter2);
//            ListAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,list1);
//          listView.setAdapter(adapter);


        }else if(requestCode==3 && resultCode==4){
            new_note =data.getStringExtra("new_note");
             add_id= data.getStringExtra("noteid");

           ListView listView =(ListView)findViewById(R.id.list_view);
           //list1= new ArrayList<String>();
            list2= new  ArrayList<HashMap<String, String>>();
            HashMap<String,String>map = new HashMap<>();
            map.put("id",add_id);
            map.put("name",new_note);
            list2.add(map);

            list2=manager.getList();

            adapter2 = new SimpleAdapter( NoteActivity.this,list2, list_item2, new String[] { "id","name"},
                    new int[] {R.id.note_Id, R.id.note_text});
            listView.setAdapter(adapter2);

//

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        ListView listView =(ListView)findViewById(R.id.list_view);
        Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String, String> map = (HashMap<String, String>) itemAtPosition;
        String titleStr = map.get("id");
        String detailStr = map.get("name");
        TextView title = (TextView) view.findViewById(R.id.note_Id);
        TextView detail = (TextView) view.findViewById(R.id.note_text);
       // TextView note = (TextView) view.findViewById(android.R.id.text1);
        //String note2 = String.valueOf(note.getText());
       // note_ID = (TextView) view.findViewById(R.id.note_Id);
        //String  noteId = note_ID.getText().toString();
        //打开新的页面，传入参数

        Intent noteEdit =new Intent(this,EditActivity.class);
        noteEdit.putExtra("edit_note",detailStr);
        noteEdit.putExtra("note_ID",Integer.parseInt(titleStr));
        startActivityForResult(noteEdit,3) ;



    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
        ListView listView =(ListView)findViewById(R.id.list_view);
       Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String, String> map = (HashMap<String, String>) itemAtPosition;
        final String titleStr = map.get("id");
        final int shanchu = Integer.parseInt(titleStr);
        String detailStr = map.get("name");
        TextView title = (TextView) view.findViewById(R.id.note_Id);
        TextView detail = (TextView) view.findViewById(R.id.note_text);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("确认要删除该备忘录吗？").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {  //which表示激活事件的是哪一个
                //Log.i(TAG, "onClick: 对话框事件处理 ");
                list2.remove(position);//引入final
                adapter2.notifyDataSetChanged();
                manager.delete(shanchu);
                //list2=manager.getList();
            }
        })
                .setNegativeButton("否",null);
        builder.create().show();
       //Log.i(TAG, "onItemLongClick: size"+ listItems.size());
        return true;//true 短按事件不执行，f短按事件执行

    }




}
