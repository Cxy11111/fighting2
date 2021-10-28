package cn.edu.swufe.happ;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter1 extends ArrayAdapter {
    private static final String TAG ="MyAdapter1";
    public MyAdapter1(Context context, int resource, ArrayList<HashMap<String,String>> list){
        super(context,resource,list);
    }
    @NonNull
    //@Override
    public View getView(int position, View convertView, View parent){
        View itemView=convertView;
        if(itemView==null){
            itemView= (View) LayoutInflater.from(getContext()).inflate(R.layout.list_item ,
                    (ViewGroup) parent, false);
        }
        Map<String,String> map=(Map<String,String>)getItem(position);
        TextView title =(TextView)itemView.findViewById(R.id.itemTitle);
        TextView detail =(TextView)itemView.findViewById(R.id.itemDetail);

        title.setText(map.get("ItemTitle"));
        detail.setText(map.get("ItemDetail"));

        return itemView;
    }

}
