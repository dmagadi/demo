package com.sngastro.sngcontacts.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sngastro.sngcontacts.R;
import com.sngastro.sngcontacts.contact.Email;
import com.sngastro.sngcontacts.contact.PhoneNumber;

import java.util.ArrayList;

/**
 * Created by Aamir on 9/7/2015.
 */
public class EmailAdapter extends ArrayAdapter<Email> {

    public EmailAdapter(Context context, ArrayList<Email> resource) {
        super(context, R.layout.email_list_item, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.email_list_item, parent, false);
        final Email email = getItem(position);
        TextView typeView = (TextView) view.findViewById(R.id.type);
        typeView.setText(email.getType());
        TextView emailView = (TextView) view.findViewById(R.id.email);
        emailView.setText(email.getEmail());
        Button copyBtn = (Button) view.findViewById(R.id.emailButton);
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ClipboardManager clipboard = (ClipboardManager)   getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied", email.getEmail());
                clipboard.setPrimaryClip(clip);*/
            }
        });
        return view;

    }
}