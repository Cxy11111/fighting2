package cn.edu.swufe.happ;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DEC_maker2 extends ListActivity implements android.view.View.OnClickListener {

    private Button btnAdd,btnGetAll;
    private TextView decoption_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decision_maker);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        ListView listView =(ListView)findViewById(R.id.dec_list);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(DEC_maker2.this,YNDetail.class);
            intent.putExtra("decoption_Id",0);
            startActivity(intent);

        }else {

            YNcrud repo = new YNcrud(this);
            ArrayList<HashMap<String, String>> decoptionList =  repo.getDECoptionList();
            if(decoptionList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        decoption_Id = (TextView) view.findViewById(R.id.dec_putin);
                        String decoptionId = decoption_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),YNDetail.class);
                        objIndent.putExtra("decoption_Id", Integer.parseInt( decoptionId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( DEC_maker2.this,decoptionList, R.layout.list_item, new String[] { "id","name"}, new int[] {R.id.itemTitle, R.id.itemDetail});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this, "No student!", Toast.LENGTH_SHORT).show();
            }

        }
    }

}

