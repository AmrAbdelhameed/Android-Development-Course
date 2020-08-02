package com.example.recyclerviewexample;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewEvent{
    EditText name;
    RecyclerView recyclerView;
    private List<RecyclerViewItem> recyclerViewItems;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerViewItems = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, recyclerViewItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recyclerViewAdapter);

//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
//                        Toast.makeText(MainActivity.this, recyclerViewItem.getTitle(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//
//                    }
//                })
//        );
    }

    public void add(View view) {
        String nameStr = name.getText().toString();
        if (!nameStr.isEmpty()) {
            recyclerViewItems.add(new RecyclerViewItem(nameStr));
            recyclerViewAdapter.notifyDataSetChanged();
            name.setText("");
        }
    }

    @Override
    public void onRecyclerViewClick(int pos) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(pos);
        Toast.makeText(MainActivity.this, recyclerViewItem.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void update(int pos) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(pos);
        Toast.makeText(MainActivity.this, "Updated: " + recyclerViewItem.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete(int pos) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(pos);
        Toast.makeText(MainActivity.this, "Deleted: " + recyclerViewItem.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
