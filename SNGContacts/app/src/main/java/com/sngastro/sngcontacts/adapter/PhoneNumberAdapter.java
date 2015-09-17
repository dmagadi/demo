package com.sngastro.sngcontacts.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sngastro.sngcontacts.R;
import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.contact.PhoneNumber;

import java.util.ArrayList;

/**
 * Created by Aamir on 9/7/2015.
 */
public class PhoneNumberAdapter extends ArrayAdapter<PhoneNumber> {

    public PhoneNumberAdapter(Context context, ArrayList<PhoneNumber> resource) {
        super(context, R.layout.phone_number_list_item, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.phone_number_list_item, parent, false);
        PhoneNumber phoneNumber = getItem(position);
        TextView numberView = (TextView) view.findViewById(R.id.number);
        numberView.setText(phoneNumber.getNumber() + " [" + phoneNumber.getType() + "]");
        return view;

    }
}
