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
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private FloatingActionButton eraseButton;
    private ArrayList<String> choices;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private Button submit;
    private TextView output;

    private void addChoice(String choice){
        choices.add(choice);
        adapter.notifyDataSetChanged();
    }

    private void clearCheckBoxes(){
        View elem;
        CheckBox cb;
        for(int i = 0; i < list.getCount();i++){
            //elem = list.getAdapter().getView(i, null, null);
            //cb = (CheckBox) elem.findViewById(i);
            cb = (CheckBox) list.getChildAt(i).findViewById(R.id.checkbox);
            cb.setChecked(false);
        }
        //adapter.notifyDataSetChanged();
    }

   /* private void removeChoice(int index){ //NEVER USED, SAFE TO DELETE
        ArrayList<String> newlist = new ArrayList<String>();
        for(int i = 0; i < choices.size(); i++){
            if( i != index){
                newlist.add(choices.get(i));
            }
        }
        choices = newlist;
        adapter.notifyDataSetChanged();
    }*/


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
        output = (TextView) findViewById(R.id.outputChoice);

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
                ArrayList<Integer> indicesToRemove = new ArrayList<Integer>();
                View elem;
                CheckBox cb;
                for(int i = 0; i < list.getCount();i++){
                    //elem = list.getAdapter().getView(i, null, null);
                    //cb = (CheckBox) elem.findViewById(i);
                    cb = (CheckBox) list.getChildAt(i).findViewById(R.id.checkbox);
                    if(cb.isChecked()){
                        indicesToRemove.add(i);
                    }
                }
                for(int j = 0; j < indicesToRemove.size(); j++){
                    choices.remove(j);
                }
                clearCheckBoxes();
                adapter.notifyDataSetChanged();
            }
        });

        eraseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("Clear all choices?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choices.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = (int)(Math.random() * choices.size());
                String choice = choices.get(rand);
                output.setText(choice);
            }
        });
    }
}
