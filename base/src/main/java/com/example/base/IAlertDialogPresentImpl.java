package com.example.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;


/**
 * Created by Skygge on 2018/10/04.
 */

public class IAlertDialogPresentImpl implements IAlertDialogPresent {

    public IAlertDialogView iAlertDialogView;

    public IAlertDialogPresentImpl() {
    }

    public IAlertDialogPresentImpl(IAlertDialogView iAlertDialogView) {
        this.iAlertDialogView = iAlertDialogView;
    }


    @Override
    public void showAlertDialog(Context context, final int titleId, String msg, int positiveId, int positiveColor) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        if (!TextUtils.isEmpty(msg)) {
            builder.setMessage(msg);
        }
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iAlertDialogView != null)
                    iAlertDialogView.onPositiveButtonClicked(titleId);
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).
                setTextColor(positiveColor);

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);

    }

    @Override
    public void showAlertDialog(Context context, final int titleId, int positiveId) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iAlertDialogView != null)
                    iAlertDialogView.onPositiveButtonClicked(titleId);
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public void showAlertDialog(Context context, String title, int positiveId) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iAlertDialogView != null)
                    iAlertDialogView.onPositiveButtonClicked(-1);
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public void showAlertDialog(Context context, String titleId, int positiveId, final DialogInterface.OnClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId, final DialogInterface.OnClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msgId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(dialog, which);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }


    @Override
    public void showAlertDialog(Context context, final int titleId, String msg, int positiveId, int negativeId, int negativeColor, int positiveColor) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iAlertDialogView != null)
                    iAlertDialogView.onPositiveButtonClicked(titleId);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iAlertDialogView != null)
                    iAlertDialogView.onNegativeButtonClicked(titleId);
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).
                setTextColor(negativeColor);

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).
                setTextColor(positiveColor);

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public AlertDialog showAlertDialog(Context context, int titleId, String msg, int positiveId, int negativeId, int negativeColor, int positiveColor, final DialogInterface.OnClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return null;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(dialog, which);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).
                setTextColor(negativeColor);

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).
                setTextColor(positiveColor);

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
        return alertDialog;
    }


    @Override
    public void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId) {
        showAlertDialog(context, titleId, msgId, positiveId, negativeId, 0xFFFFFF, 0xFFFFFF);


    }

    @Override
    public void showAlertDialog(Context context, int titleId, int msgId, int positiveId) {
        showAlertDialog(context, titleId, msgId, positiveId, -1);
    }

    @Override
    public void showAlertDialog(Context context, int titleId, int msgId, int positiveId, final DialogInterface.OnClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msgId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }


    @Override
    public void showAlertDialog(Context context, final int titleId, int msgId, int positiveId, int negativeId, int positiveColor, int negativeColor) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msgId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iAlertDialogView.onPositiveButtonClicked(titleId);
                dialog.dismiss();
            }
        });

        if (negativeId != -1) {
            builder.setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    iAlertDialogView.onNegativeButtonClicked(titleId);
                    dialog.dismiss();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        if (negativeId != -1) {
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).
                    setTextColor(ContextCompat.getColor(context, negativeColor));
        }

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).
                setTextColor(ContextCompat.getColor(context, positiveColor));

        if (negativeId != -1) {
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);
        }

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);


    }

    @Override
    public void showAlertDialog(Context context, int titleId, int msgId, int positiveId, int negativeId, final OnDialogClickedListener onDialogClickedListener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msgId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onDialogClickedListener.onPositiveClicked();
                dialog.dismiss();
            }
        });

        if (negativeId != -1) {
            builder.setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onDialogClickedListener.onNegativeClicked();
                    dialog.dismiss();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        if (negativeId != -1) {
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);
        }

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public void showAlertDialog(Context context, int titleId, int positiveId, final DialogInterface.OnClickListener listener) {

        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
    }

    @Override
    public AlertDialog showAlertDialogNegative(Context context, int titleId, int msgId, int positiveId, int negativeId, int negativeColor, int positiveColor, final DialogInterface.OnClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return null;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(msgId);
        builder.setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(dialog, which);
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).
                setTextColor(negativeColor);

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).
                setTextColor(positiveColor);

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
        return alertDialog;
    }

}
