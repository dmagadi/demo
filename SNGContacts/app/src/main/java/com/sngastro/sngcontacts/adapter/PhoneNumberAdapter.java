package com.sngastro.sngcontacts.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sngastro.sngcontacts.R;
import com.sngastro.sngcontacts.StartActivity;
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
        final View view = inflater.inflate(R.layout.phone_number_list_item, parent, false);
        final PhoneNumber phoneNumber = getItem(position);
        TextView typeView = (TextView) view.findViewById(R.id.type);
        typeView.setText(phoneNumber.getType());
        TextView numberView = (TextView) view.findViewById(R.id.number);
        numberView.setText(phoneNumber.getNumber());
        ImageButton callBtn = (ImageButton) view.findViewById(R.id.callButton);

        Context context = view.getContext();

        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage("Call " + phoneNumber.getNumber())
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Call", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        i.setData(Uri.parse("tel:" + "1" + phoneNumber.getNumber()));
                        view.getContext().startActivity(i);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        //executes after clicking twice

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "callBtnClick");
                builder.show();
            }
        });
        return view;

    }
}
