package com.retropoktan.rptrello.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by RetroPoktan on 2/13/16.
 */
public class SimpleDialogFragment extends AppCompatDialogFragment {

    private static final String ARG_MESSAGE = "ARG_MESSAGE";
    private static final String ARG_BUTTON_TEXT = "ARG_BUTTON_TEXT";
    private static final String ARG_TITLE = "ARG_TITLE";

    private ISimpleDialogFragmentListener mListener;

    private DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (mListener != null) {
                mListener.onDialogButtonClick();
            }
        }
    };

    public static SimpleDialogFragment newInstance(CharSequence title, CharSequence message, CharSequence button) {
        Bundle args = new Bundle();
        args.putCharSequence(ARG_TITLE, title);
        args.putCharSequence(ARG_MESSAGE, message);
        args.putCharSequence(ARG_BUTTON_TEXT, button);
        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SimpleDialogFragment newInstance(CharSequence title, CharSequence message) {
        return newInstance(title, message, null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISimpleDialogFragmentListener) {
            mListener = (ISimpleDialogFragmentListener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Dialog dialog;
        if (bundle != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(bundle.getCharSequence(ARG_TITLE));
            builder.setMessage(bundle.getCharSequence(ARG_MESSAGE));
            builder.setPositiveButton(bundle.getCharSequence(ARG_BUTTON_TEXT, "OK"), mOnClickListener);
            builder.setNegativeButton("CANCEL", null);
            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        } else {
            dialog = super.onCreateDialog(savedInstanceState);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mListener != null) {
            mListener.onDialogCancel();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setMessage(CharSequence message) {
        ((AlertDialog) getDialog()).setMessage(message);
    }

    public interface ISimpleDialogFragmentListener {
        void onDialogCancel();

        void onDialogButtonClick();
    }

}
