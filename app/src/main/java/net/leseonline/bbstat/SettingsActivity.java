package net.leseonline.bbstat;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import net.leseonline.bbstat.db.DatabaseHelper;

/**
 * Created by mlese on 5/13/2016.
 */
public class SettingsActivity extends AppCompatActivity {
    private DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_settings);
        final Spinner teams = (Spinner) this.findViewById(R.id.settingsSpinner);
        Cursor c = mDbHelper.getTeams();
        int n = c.getColumnCount();
        TeamCursorAdapter adapter = new TeamCursorAdapter(this, c);
        teams.setAdapter(adapter);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }

}
