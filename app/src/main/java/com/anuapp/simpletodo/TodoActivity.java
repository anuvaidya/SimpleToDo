package com.anuapp.simpletodo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

// A simple to do app for testing purposes
// Adapter to create a view for the list
public class TodoActivity extends Activity {

	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	EditText etNewItem;
    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        setup();
        setupListener();
        lvItems.setAdapter(itemsAdapter);
    }

    private void setup()
    {
        lvItems = (ListView)findViewById(R.id.lvItems);
        etNewItem = (EditText)findViewById(R.id.etNewItem);
        btnAddItem = (Button)findViewById(R.id.btnAddItem);

        items = new ArrayList<>(10);
        itemsAdapter= new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,items);

        items.add("First Item");
        items.add("second Item");
        items.add("third item");

    }
    private void setupListener() {
        btnAddItem.setOnClickListener(btnAddListener);
        lvItems.setOnItemLongClickListener(lvItemsLongListener);
    }

    private View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            itemsAdapter.add(etNewItem.getText().toString());
            etNewItem.setText("");
        }
    };



    private AdapterView.OnItemLongClickListener lvItemsLongListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    items.remove(position);
                    itemsAdapter.notifyDataSetInvalidated();
                    return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }
    
}
