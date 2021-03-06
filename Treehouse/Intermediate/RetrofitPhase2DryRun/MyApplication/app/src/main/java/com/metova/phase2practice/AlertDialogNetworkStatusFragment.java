package com.metova.phase2practice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * Created by jodi on 7/28/17.
 */

public class AlertDialogNetworkStatusFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Context context = getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.error_message)
                    .setPositiveButton(R.string.error_ok_button_text, null);   // set message and onclick listener - in this case
            // its null so it will just close the dialog (no
            // further action.
            AlertDialog dialog = builder.create();
            return dialog;
        }
}
