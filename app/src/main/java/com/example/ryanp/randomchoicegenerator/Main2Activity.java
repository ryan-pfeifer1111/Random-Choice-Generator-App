package com.example.ryanp.randomchoicegenerator;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private FloatingActionButton eraseButton;
    private ArrayList<String> choices;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private Button submit;

    private void addChoice(String choice){
        choices.add(choice);
        adapter.notifyDataSetChanged();
    }

    private void removeChoice(int index){
        ArrayList<String> newlist = new ArrayList<String>();
        for(int i = 0; i < choices.size(); i++){
            if( i != index){
                newlist.add(choices.get(i));
            }
        }
        choices = newlist;
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        choices = new ArrayList<String>();
        choices.add("test");
        choices.add("help");
        choices.add("please");
        addButton = (FloatingActionButton) findViewById(R.id.addButton);
        eraseButton = (FloatingActionButton) findViewById(R.id.eraseButton);
        list = (ListView) findViewById(R.id.itemList);
        adapter = new ArrayAdapter<String>(Main2Activity.this, R.layout.my_listview_layout, R.id.listview_layout, choices);
        list.setAdapter(adapter);
        submit = (Button) findViewById(R.id.submitButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("Enter your choice");
                final EditText input = new EditText(Main2Activity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //handle the input text here
                        addChoice(input.getText().toString());
                        //submit.setText(input.getText().toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }

        });

        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < list.getCount();i++){
                    View elem = list.getChildAt(i);
                    CheckBox box = (CheckBox) findViewById(R.id.checkbox);
                    if(box.isChecked()){
                        choices.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
                //HANDLE THE BIG RED DELETE BUTTON HERE
                //ALSO FOR LONG PRESS, DELETE ALL ELEMENTS
                //DELETES SHOULD BRING UP A DIALOGUE BOX
            }
        });

    }
}
