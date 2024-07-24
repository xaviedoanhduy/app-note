package edu.huflit.bt_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class  ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactVH> {

    ArrayList<Contact> contacts;
    Context context;
    OnClick listener;



    public ContactAdapter( Context context, ArrayList<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    public ContactAdapter(ArrayList<Contact> contacts, Context context, OnClick listener) {
        this.contacts = contacts;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_contact, parent, false);
        ContactVH contactVH = new ContactVH(view);
        return contactVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactVH holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvPhone.setText(contact.getPhone());
        holder.tvName.setText(contact.getName());
// Cach 1
//        holder.ivPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, contact.getPhone(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, contact.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.setOnClick(contact);
//            }
//        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.setOnClick(contact);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactVH extends RecyclerView.ViewHolder{

        TextView tvPhone, tvName;
        ImageView ivPhone, ivMessage;

        public ContactVH(@NonNull View itemView) {
            super(itemView);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvName = itemView.findViewById(R.id.tvName);

        }
    }

    public interface OnClick{
        void setOnClick(Contact contact);
    }
}
