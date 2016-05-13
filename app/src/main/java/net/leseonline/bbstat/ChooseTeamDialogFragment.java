package net.leseonline.bbstat;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import net.leseonline.bbstat.db.DatabaseHelper;
import net.leseonline.bbstat.stat.Team;

import org.w3c.dom.Text;

/**
 * Created by mlese on 5/12/2016.
 */
public class ChooseTeamDialogFragment extends DialogFragment {
    private View mRootView;

    private IChooseTeamDialogListener mListener = null;

    /**
     * This interface provides feedback to the caller.
     */
    public interface IChooseTeamDialogListener {
        public void onChooseTeamDialogPositiveAction(ChooseTeamDialogFragment dialog);
        public void onChooseTeamDialogNegativeAction(ChooseTeamDialogFragment dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            if (activity instanceof IChooseTeamDialogListener) {
                mListener = (IChooseTeamDialogListener) activity;
            }
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement IAddTeamDialogListener");
        }    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.choose_team, container, false);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        getDialog().setTitle("Simple Dialog");

        final Spinner teams = (Spinner) mRootView.findViewById(R.id.team_spinner);
        Cursor c = dbHelper.getTeams();
        int n = c.getColumnCount();
        TeamCursorAdapter adapter = new TeamCursorAdapter(getActivity(), c);
        teams.setAdapter(adapter);

//        teams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        Button button = (Button)mRootView.findViewById(R.id.button_cancel);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notifyListener(false);
            }

        });

        button = (Button)mRootView.findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notifyListener(true);
            }

        });

        return mRootView;
    }


    public Team getTeam() {
        // TODO mvl
        return null;
    }

    private void notifyListener(boolean isOk) {
        if (mListener != null) {
            if (isOk) {
                mListener.onChooseTeamDialogNegativeAction(this);
            } else {
                mListener.onChooseTeamDialogNegativeAction(this);
            }
        }
        dismiss();
    }

}
