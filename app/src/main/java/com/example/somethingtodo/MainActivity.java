package com.example.somethingtodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> ItemList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item=findViewById(R.id.editText);
        listView=findViewById(R.id.list);
        add=findViewById(R.id.button);


        ItemList = FileSave.readListData(this);
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,ItemList);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName =item.getText().toString();
                ItemList.add(itemName);
                item.setText("");
                FileSave.writeListItem(ItemList,getApplicationContext());
                arrayAdapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Do you really want to delete?");
                alert.setCancelable(false);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Selected plan is deleted.", Toast.LENGTH_LONG).show();

                        ItemList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        FileSave.writeListItem(ItemList,getApplicationContext());



                    }
                });

                AlertDialog alertDialog =alert.create();
                alertDialog.show();

            }
        });
    }
}