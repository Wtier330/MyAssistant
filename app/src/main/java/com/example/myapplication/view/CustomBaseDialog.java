package com.example.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public abstract class CustomBaseDialog extends Dialog {

    public CustomBaseDialog(@NonNull Context context) {
        super(context, R.style.Base_Theme_AppCompat_Dialog);
        setContentView(getLayoutId());
        Window mWindow = getWindow();
        if(mWindow!=null){
            WindowManager.LayoutParams mLayoutParams = mWindow.getAttributes();
            if (mLayoutParams!=null){
                mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mLayoutParams.gravity = getDialogLocation();
                mWindow.setAttributes(mLayoutParams);
            }
        }
        initData();
    }
    abstract int getDialogLocation();

    abstract int getLayoutId();
    abstract void initData();
}