package cn.edu.swufe.happ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YNDetail extends AppCompatActivity implements View.OnClickListener {

    Button btnSava, btnDelete;
    Button btnClose;
    EditText editTextName;
    private int _DECoption_ID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decision_edit);

        btnSava = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        btnSava.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _DECoption_ID = 0;
        Intent intent = new Intent();
        _DECoption_ID = intent.getIntExtra("decoption_Id",0);
        YNcrud repo = new YNcrud(this);
        DECoption decoption;
        decoption = repo.getDECoptionById( _DECoption_ID);

        editTextName.setText(decoption.name);

    }


    @Override
    public void onClick(View v) {
        YNcrud repo = new YNcrud(this);
        if (v == findViewById(R.id.btnSave)){
            DECoption decoption = new DECoption();

            decoption.name = editTextName.getText().toString();
            decoption.DECoption_ID =  _DECoption_ID;

            if( _DECoption_ID==0){
                _DECoption_ID = repo.insert(decoption);

                Toast.makeText(this,"New Student Insert",Toast.LENGTH_SHORT).show();
            }else{
                repo.update(decoption);
                Toast.makeText(this,"Student Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (v == findViewById(R.id.btnDelete)){
            repo.delete( _DECoption_ID);
            Toast.makeText(this,"Student Record deleted",Toast.LENGTH_SHORT).show();
            finish();
        }else if (v == findViewById(R.id.btnClose)){
            finish();
        }
    }
}

