package com.maning.mndialoglibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.maning.mndialoglibrary.listeners.OnDialogDismissListener;
import com.maning.mndialoglibrary.utils.MSizeUtils;
import com.maning.mndialoglibrary.utils.StatusBarUtils;

import java.lang.reflect.Field;

/**
 * @author : maning
 * @desc :
 */
public class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
        init();
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected BaseDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //设置整个Dialog的宽高
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = MSizeUtils.getScreenHeight(getContext());
        layoutParams.gravity = Gravity.CENTER;
        getWindow().setAttributes(layoutParams);
    }

    public void initStatusBar(boolean windowFullscreen, boolean isDarkFont) {
        //全屏模式
        if (windowFullscreen) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            } else {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        } else {
            StatusBarUtils.setStatusBarMode(getWindow(), isDarkFont);
        }
    }

    @Override
    public void show() {
        super.show();
    }

}
