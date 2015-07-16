package com.sngastro.sngcontacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aamir on 7/10/2015.
 */
class ContactArrayAdapter2 extends ArrayAdapter<ContactInfo> {

    public ContactArrayAdapter2(Context context, ArrayList<ContactInfo> resource) {
        super(context, R.layout.contact_view, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.contact_view, parent, false);
        final Context context = getContext();
        final ContactInfo contactInfo = getItem(position);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView homeView = (TextView) view.findViewById(R.id.home);
        TextView cellView = (TextView) view.findViewById(R.id.cell);
        TextView emailView = (TextView) view.findViewById(R.id.email);
        TextView dobView = (TextView) view.findViewById(R.id.dob);
        Button cellCallButton = (Button) view.findViewById(R.id.cellCallButton);
       cellCallButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {

                Intent i = new Intent(context, MainActivity.class).setFlags(Integer.parseInt(Intent.ACTION_CALL));
                i.setData(Uri.parse("Tel:" + contactInfo.getCell()));
                context.startActivity(i);

            }

        });
        Button homeCallButton = (Button) view.findViewById(R.id.homeCallButton);
        homeCallButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {

                Intent i = new Intent(context, MainActivity.class).setFlags(Integer.parseInt(Intent.ACTION_CALL));
                i.setData(Uri.parse("Tel:" + contactInfo.getHome()));
                context.startActivity(i);

            }

        });
        nameView.setText(contactInfo.getName());
        homeView.setText(contactInfo.getHome());
        cellView.setText(contactInfo.getCell());
        emailView.setText(contactInfo.getEmail());
        dobView.setText(contactInfo.getDOB());
        return view;
    }
}
