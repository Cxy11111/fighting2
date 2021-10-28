package cn.edu.swufe.happ;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class YNcrud {

        private DBHelper dbHelper;

        public YNcrud(Context context){
            dbHelper=new DBHelper(context);
        }

        public int insert(DECoption decoption){
            //打开连接，写入数据
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();

            values.put(DECoption.KEY_name,decoption.name);
            //
            long decoption_Id=db.insert(DECoption.TABLE,null,values);
            db.close();
            return (int)decoption_Id;
        }

        public void delete(int decoption_Id){
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            db.delete(DECoption.TABLE,DECoption.KEY_ID+"=?", new String[]{String.valueOf(decoption_Id)});
            db.close();
        }
        public void update(DECoption decoption){
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();


            values.put(DECoption.KEY_name,decoption.name);

            db.update(DECoption.TABLE,values,DECoption.KEY_ID+"=?",new String[] { String.valueOf(decoption.DECoption_ID) });
            db.close();
        }

        public ArrayList<HashMap<String, String>> getDECoptionList(){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            String selectQuery="SELECT "+
                    DECoption.KEY_ID+","+
                    DECoption.KEY_name+","
                    +DECoption.TABLE;
            ArrayList<HashMap<String,String>> studentList=new ArrayList<HashMap<String, String>>();
            Cursor cursor=db.rawQuery(selectQuery,null);

            if(cursor.moveToFirst()){
                do{
                    HashMap<String,String> student=new HashMap<String,String>();
                    student.put("id",cursor.getString(cursor.getColumnIndex(DECoption.KEY_ID)));
                    student.put("name",cursor.getString(cursor.getColumnIndex(DECoption.KEY_name)));
                    studentList.add(student);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return studentList;
        }

        public DECoption getDECoptionById(int Id){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            String selectQuery="SELECT "+
                    DECoption.KEY_ID + "," +
                    DECoption.KEY_name +
                    " FROM " + DECoption.TABLE
                    + " WHERE " +
                    DECoption.KEY_ID + "=?";
            int iCount=0;
            DECoption student=new DECoption();
            Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
            if(cursor.moveToFirst()){
                do{
                    student.DECoption_ID =cursor.getInt(cursor.getColumnIndex(DECoption.KEY_ID));
                    student.name =cursor.getString(cursor.getColumnIndex(DECoption.KEY_name));

                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return student;
        }



    }
