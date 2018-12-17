package com.gunna.bigburger.androidapp.utils;

import android.content.Context;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gunna.bigburger.androidapp.R;

public class DialogUtils {


    public static MaterialDialog getInfoDialogDismiss(Context context, int title, int content) {
        return new MaterialDialog.Builder(context)
                .positiveText(R.string.ok)
                .title(title)
                .onPositive((dialog, which) -> dialog.dismiss())
                .content(content)
                .build();
    }


}
