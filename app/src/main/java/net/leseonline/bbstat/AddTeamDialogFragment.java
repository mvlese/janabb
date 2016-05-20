package net.leseonline.bbstat;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.leseonline.bbstat.db.DatabaseHelper;

/**
 * Created by mlese on 5/6/2016.
 */
public class AddTeamDialogFragment extends DialogFragment {
    private View mRootView;
    private IAddTeamDialogListener mListener = null;

    /**
     * This interface provides feedback to the caller.
     */
    public interface IAddTeamDialogListener {
        public void onAddTeamDialogPositiveAction(AddTeamDialogFragment dialog);

        public void onAddTeamDialogNegativeAction(AddTeamDialogFragment dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            if (activity instanceof IAddTeamDialogListener) {
                mListener = (IAddTeamDialogListener) activity;
            }
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement IAddTeamDialogListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.add_team, container, false);
        getDialog().setTitle("Simple Dialog");

        EditText et = (EditText) mRootView.findViewById(R.id.editTeamName);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                         @Override
                                         public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                             if (actionId == EditorInfo.IME_NULL
                                                     && event.getAction() == KeyEvent.ACTION_DOWN) {
                                                 notifyListener(true);
                                             }
                                             return true;
                                         }
                                     }
        );
        et.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {
              }

              @Override
              public void afterTextChanged(Editable s) {
                  String name = s.toString().trim().toUpperCase();
                  Button button = (Button) mRootView.findViewById(R.id.button_ok);
                  TextView messageView = (TextView) mRootView.findViewById(R.id.addTeamMessageView);
                  messageView.setText("");
                  if (s.length() > 0) {
                      DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                      boolean exists = dbHelper.teamExists(name);
                      if (exists) {
                          button.setEnabled(false);
                          messageView.setText(name + " already exists.");
                      } else {
                          button.setEnabled(true);
                      }
                  } else {
                      button.setEnabled(false);
                  }
              }
          }

        );

        Button button = (Button) mRootView.findViewById(R.id.button_cancel);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notifyListener(false);
            }

        });

        button = (Button) mRootView.findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notifyListener(true);
            }

        });

        return mRootView;
    }

    public String getName() {
        EditText et = (EditText) mRootView.findViewById(R.id.editTeamName);
        String name = et.getText().toString().trim().toUpperCase();
        return name;
    }

    private void notifyListener(boolean isOk) {
        if (mListener != null) {
            if (isOk) {
                mListener.onAddTeamDialogPositiveAction(this);
            } else {
                mListener.onAddTeamDialogNegativeAction(this);
            }
        }
        dismiss();
    }

}
