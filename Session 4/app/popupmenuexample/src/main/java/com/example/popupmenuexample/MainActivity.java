package com.example.popupmenuexample;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button popupMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        popupMenuBtn = findViewById(R.id.popup_menu_btn);
        popupMenuBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.popup_menu_btn) {
            PopupMenu popup = new PopupMenu(MainActivity.this, popupMenuBtn);
            popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.item1:
                            textView.setText(getString(R.string.item_1));
                            return true;
                        case R.id.item2:
                            textView.setText(getString(R.string.item_2));
                            return true;
                    }
                    return false;
                }
            });
            popup.show();
        }
    }
}
