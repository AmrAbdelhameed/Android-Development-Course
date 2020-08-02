package com.example.sessionfive;

import android.view.View;

public interface ContactClickListener {
    void onClick(View view, Contact contact);

    void onMoreClick(View view, Contact contact);
}
