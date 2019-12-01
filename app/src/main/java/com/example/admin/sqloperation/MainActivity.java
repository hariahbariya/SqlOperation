package com.example.admin.sqloperation;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText editTextname,editTextemail,editText_id;
    RadioGroup radioGroup;
    AlertDialog.Builder builder;
    Button button;
    String g;

    MyHelperClass  myHelperClass=new MyHelperClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.save_btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        radioGroup=(RadioGroup)findViewById(R.id.baap_radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.rmale:
                        g="male";
                        break;
                    case R.id.rfemale:
                        g="female";
                        break;
                }
            }
        });


        editTextname=(EditText)findViewById(R.id.one1);
        editTextemail=(EditText)findViewById(R.id.two1);

        boolean insert_check=myHelperClass.insert_data(editTextname.getText().toString(),editTextemail.getText().toString(),g);
        if(insert_check)
        {
            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Not", Toast.LENGTH_SHORT).show();
        }
    }


    public void show_data(View view)
    {
        Cursor cursor=myHelperClass.getAll();
        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            stringBuffer.append("Id: "+cursor.getString(0)+"\n");
            stringBuffer.append("Name: "+cursor.getString(1)+"\n");
            stringBuffer.append("Email: "+cursor.getString(2)+"\n");
            stringBuffer.append("Gender: "+cursor.getString(3)+"\n\n");
        }

        show_msg("My Data",stringBuffer.toString());
    }


    public void show_msg(String title,String msg)
    {
        builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.setMessage(msg);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public  void update_d(View view)
    {

        editTextname=(EditText)findViewById(R.id.one1);
        editTextemail=(EditText)findViewById(R.id.two1);
        editText_id=(EditText)findViewById(R.id.edit_id);
        
        boolean a=myHelperClass.update_data(editText_id.getText().toString(),editTextname.getText().toString(),editTextemail.getText().toString());
        if(a)
        {
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Not Update", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void Del(View view)
    {
        EditText editText=(EditText)findViewById(R.id.edit_id);
        Integer a=myHelperClass.delete_data(editText.getText().toString());
        
        if(a>0)
        {
            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Already deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
