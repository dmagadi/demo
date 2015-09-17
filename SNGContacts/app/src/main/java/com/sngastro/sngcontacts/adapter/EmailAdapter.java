package com.sngastro.sngcontacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        Email email = getItem(position);
        TextView emailView = (TextView) view.findViewById(R.id.email);
        emailView.setText(email.getEmail() + " [" + email.getType() + "]");
        return view;

    }
}
