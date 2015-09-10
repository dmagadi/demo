package com.sngastro.sngcontacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sngastro.sngcontacts.R;
import com.sngastro.sngcontacts.contact.ContactInfo;

import java.util.ArrayList;

/**
 * Created by Aamir on 7/12/2015.
 */
public class ContactArrayAdapter extends ArrayAdapter<ContactInfo> {
    public ContactArrayAdapter(Context context, ArrayList<ContactInfo> resource) {
        super(context, R.layout.list_item, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ContactInfo contactInfo = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.itemTextView);
        textView.setText(contactInfo.getFirstName() + " " + contactInfo.getLastName());
        return view;

    }

}
