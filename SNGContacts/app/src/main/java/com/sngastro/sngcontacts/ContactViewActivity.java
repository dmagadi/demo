package com.sngastro.sngcontacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ContactViewActivity extends ActionBarActivity {

    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);
        final ContactInfo contactInfo = (ContactInfo) getIntent().getSerializableExtra("ContactInfo");
        TextView nameView = (TextView) findViewById(R.id.name);
        TextView cellView = (TextView) findViewById(R.id.cell);
        TextView homeView = (TextView) findViewById(R.id.home);
        TextView emailView = (TextView) findViewById(R.id.email);
        nameView.setText(contactInfo.getName());
        cellView.setText(contactInfo.getCell());
        homeView.setText(contactInfo.getHome());
        emailView.setText(contactInfo.getEmail());
        Button cellCallButton = (Button) findViewById(R.id.cellCallButton);
        cellCallButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                new AlertDialog.Builder(ContactViewActivity.this).setMessage("Call Number").
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                            }
                        })
                        .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1" + contactInfo.getCell()));
                                startActivity(i);
                            }
                        }).show();
                Button homeCallButton = (Button) findViewById(R.id.homeCallButton);
                homeCallButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View v) {
                        new AlertDialog.Builder(ContactViewActivity.this).setMessage("Call Number").
                                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {

                                    }
                                })
                                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1" + contactInfo.getHome()));
                                        startActivity(i);
                                    }
                                }).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_home:
                finish();
                return true;
            default:
                return true;
        }
    }

}