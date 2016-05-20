package net.leseonline.bbstat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.leseonline.bbstat.contact.Contact;
import net.leseonline.bbstat.db.DatabaseHelper;
import net.leseonline.bbstat.stat.Team;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AddTeamDialogFragment.IAddTeamDialogListener,
        ChooseTeamDialogFragment.IChooseTeamDialogListener {

    private final String TAG = "Main Activity";
    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS};
    private static final int REQUEST_CONTACTS = 1;
    private final int ADD_TEAM = 1;
    private final int CREATE_GAME = 2;
    private DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_add_team) {
            FragmentManager fm = getFragmentManager();
            AddTeamDialogFragment dialogFragment = new AddTeamDialogFragment();
            dialogFragment.show(fm, "Sample Fragment");
            return true;
        } else if (id == R.id.action_create_game) {
            FragmentManager fm = getFragmentManager();
            ChooseTeamDialogFragment dialogFragment = new ChooseTeamDialogFragment();
            dialogFragment.show(fm, "Sample Fragment");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void testGetContacts() {
        try {
            checkForContactsPermissions();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void checkForContactsPermissions() {
        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //given that Manifest.permission.READ_CONTACTS and Manifest.permission.WRITE_CONTACTS
            //are from the same permission group , on theory it is enough to check for only one of them
            //yet permission groups are subject to change therefore it is s good idea to check
            //for both permissions

            // Contacts permissions have not been granted.
            Log.i(TAG, "Contact permissions has NOT been granted. Requesting permissions.");
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        } else {
            // Contact permissions have been granted. Show the contacts fragment.
            Log.i(TAG,
                    "Contact permissions have already been granted. Displaying contact details.");
            ContactsReader reader = new ContactsReader(this);
            List<Contact> contacts = reader.readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Contact permissions approved.");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d(TAG, "Contact permissions denied.");
                }
                break;
            }
            default:
                break;
        }
    }

    public void onAddTeamDialogPositiveAction(AddTeamDialogFragment dialog) {
        String name = dialog.getName();
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
        long id = mDbHelper.addTeam(name);
        Team team = new Team(id, name);
        // Add team to db.
        // Add players to team.
    }

    public void onAddTeamDialogNegativeAction(AddTeamDialogFragment dialog) {
        // do nothing
    }

    public void onChooseTeamDialogPositiveAction(ChooseTeamDialogFragment dialog) {

    }

    public void onChooseTeamDialogNegativeAction(ChooseTeamDialogFragment dialog) {

    }

}

