package cn.edu.swufe.happ;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public NoteManager(Context context){
        dbHelper = new DBHelper(context);
       TBNAME = DBHelper.TB_NAME;
    }

    public void add(NoteItem item){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put("curnote",item.curNote);
        db.insert(TBNAME,null,values);


        //long id=db.insert(TBNAME,null,values);
        db.close();
       // return (int)id;
    }

    public void addAll(List<NoteItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (NoteItem item : list) {
            ContentValues values = new ContentValues();
            values.put("curnote", item.curNote);

            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public int newaddAll(NoteItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("curnote", item.curNote);
        long id=db.insert(TBNAME, null, values);

        db.close();
        return (int)id;
    }


    public List<NoteItem> listAll(){
        List<NoteItem>noteList =null;//定义返回数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();//获得数据库的访问
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            noteList = new ArrayList<NoteItem>();
            while (cursor.moveToNext()){
                NoteItem item =new NoteItem();
                item.id=cursor.getInt(cursor.getColumnIndex("ID"));
                //item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
               // item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                item.curNote=cursor.getString(cursor.getColumnIndex("CURNOTE"));
                noteList.add(item);
            }
            cursor.close();
        }
        db.close();
        return noteList;
    }


    public ArrayList<HashMap<String, String>> getList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                NoteItem.ID+","+
                NoteItem.CURNOTE+" FROM "+TBNAME;
        ArrayList<HashMap<String,String>> list2=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> item=new HashMap<String,String>();
                item.put("id",cursor.getString(cursor.getColumnIndex(NoteItem.ID)));
                item.put("name",cursor.getString(cursor.getColumnIndex(NoteItem.CURNOTE)));
                list2.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list2;
    }



  public void delete(int id){

      SQLiteDatabase db = dbHelper.getReadableDatabase();//获得数据库的访问
      db.delete(TBNAME,NoteItem.ID+"=?", new String[]{String.valueOf(id)});
      db.close();

   }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }


public void update(NoteItem item){
    SQLiteDatabase db=dbHelper.getWritableDatabase();
    ContentValues values=new ContentValues();

    values.put("curnote",item.curNote);


    db.update(TBNAME,values,NoteItem.ID+"=?",new String[] { String.valueOf(item.id)});
    db.close();
}

    public NoteItem getNoteById(int Id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                NoteItem.ID+","+
                NoteItem.CURNOTE+" FROM "+TBNAME
                + " WHERE " +
                NoteItem.ID+ "=?";
        int iCount=0;
        NoteItem noteItem=new NoteItem();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                noteItem.id =cursor.getInt(cursor.getColumnIndex(NoteItem.ID));
                noteItem.curNote =cursor.getString(cursor.getColumnIndex(NoteItem.CURNOTE));

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteItem;
    }




}
