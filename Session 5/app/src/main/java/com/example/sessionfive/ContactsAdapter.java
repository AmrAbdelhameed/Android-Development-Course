package com.example.sessionfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
    private Context context;
    private List<Contact> contacts;
    private ContactClickListener contactClickListener;

    public ContactsAdapter(Context context, List<Contact> contacts, ContactClickListener contactClickListener) {
        this.context = context;
        this.contacts = contacts;
        this.contactClickListener = contactClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Contact contact = contacts.get(position);
        myViewHolder.name.setText(contact.getName());
        myViewHolder.phone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icMore;
        TextView name, phone;

        public MyViewHolder(View itemView) {
            super(itemView);
            icMore = itemView.findViewById(R.id.ic_more);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {
                        Contact contact = contacts.get(getAdapterPosition());
                        contactClickListener.onClick(view, contact);
                    }
                }
            });

            icMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {
                        Contact contact = contacts.get(getAdapterPosition());
                        contactClickListener.onMoreClick(view, contact);
                    }
                }
            });
        }
    }
}
