package com.example.recyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<RecyclerViewItem> recyclerViewItems;

    public RecyclerViewAdapter(Context context, List<RecyclerViewItem> recyclerViewItems) {
        this.context = context;
        this.recyclerViewItems = recyclerViewItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        myViewHolder.item.setText(recyclerViewItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (getAdapterPosition() != -1) {
//                        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(getAdapterPosition());
//                        Toast.makeText(context, recyclerViewItem.getTitle(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }
}
