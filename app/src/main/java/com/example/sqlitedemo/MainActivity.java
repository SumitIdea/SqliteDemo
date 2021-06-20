package com.example.sqlitedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button save,read,update,delete;
    MyDatabse myDatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabse=new MyDatabse(this);

        e1=findViewById(R.id.et_firstnem);
        e2=findViewById(R.id.et_lastnem);
        e3=findViewById(R.id.et_roll);
        save=findViewById(R.id.btnsv);
        read=findViewById(R.id.btnrd);
        e4=findViewById(R.id.et_idforupdate);
        update=findViewById(R.id.btnupdate);
        delete=findViewById(R.id.btndelete);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleteData=myDatabse.isDeleteData(e4.getText().toString(),
                                                            e1.getText().toString(),
                                                            e2.getText().toString(),
                                                            e3.getText().toString());

                if(deleteData==true)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Problem in Data Deleted", Toast.LENGTH_SHORT).show();
                }
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean updateData=myDatabse.isUpDate(e4.getText().toString(),
                                                    e1.getText().toString(),
                                                    e2.getText().toString(),
                                                    e3.getText().toString());

                if(updateData==true)
                {
                    Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Problem in Data Update", Toast.LENGTH_SHORT).show();
                }
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isData=myDatabse.insertData(e1.getText().toString(),
                                                    e2.getText().toString(),
                                                    e3.getText().toString());

                if(isData==true)
                {
                    Toast.makeText(MainActivity.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Problem in Data inserting", Toast.LENGTH_SHORT).show();
                }

                e1.setText("");
                e2.setText("");
                e3.setText("");

            }
        });


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor data=myDatabse.getAllData();

                if(data.getCount()==0)
                {
                    showMessage("Error","No Data");
                }
                StringBuffer stringBuffer=new StringBuffer();
                while(data.moveToNext())
                {
                    stringBuffer.append("Id is "+data.getString(0)+"\n");
                    stringBuffer.append("First_Name is "+data.getString(1)+"\n");
                    stringBuffer.append("Last_Name is "+data.getString(2)+"\n");
                    stringBuffer.append("Roll_No is "+data.getString(3)+"\n\n");
                }
                showMessage("Data",stringBuffer.toString());
            }
        });


    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}