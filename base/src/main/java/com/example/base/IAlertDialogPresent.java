package com.example.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Skygge on 2018/10/04
 */

public interface IAlertDialogPresent {

    void showAlertDialog(Context context, int titleId, String msg, int positiveId, int positiveColor);

    void showAlertDialog(Context context, int titleId, int positiveId);

    void showAlertDialog(Context context, int titleId, int positiveId, DialogInterface.OnClickListener listener);

    void showAlertDialog(Context context, String titleId, int positiveId);

    void showAlertDialog(Context context, String titleId, int positiveId, DialogInterface.OnClickListener listener);

    void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId, DialogInterface.OnClickListener listener);

    void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId);

    void showAlertDialog(Context context, int titleId, int msgId, int positiveId);

    void showAlertDialog(Context context, int titleId, int msgId, int positiveId, DialogInterface.OnClickListener listener);

    void showAlertDialog(Context context, int titleId, String msg, int positiveId, int negativeId, int negativeColor, int positiveColor);

    AlertDialog showAlertDialog(Context context, int titleId, String msg, int positiveId, int negativeId, int negativeColor, int positiveColor
            , DialogInterface.OnClickListener listener);

    void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId, int negativeColor
            , int positiveColor);

    void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId, OnDialogClickedListener onDialogClickedListener);

    AlertDialog showAlertDialogNegative(Context context, int titleId, int msgId, int positiveId, int negativeId, int negativeColor, int positiveColor, DialogInterface.OnClickListener listener);


    interface OnDialogClickedListener {

        void onPositiveClicked();

        void onNegativeClicked();
    }
}
