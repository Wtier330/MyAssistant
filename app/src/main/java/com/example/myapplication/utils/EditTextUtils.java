package com.example.myapplication.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

public class EditTextUtils {

    public static void areEditTextsEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().isEmpty()) {

            }
        }
    }

    public static <T extends EditText> void InputIsEmpty(Context context, T editText) {
        animTranslationX(editText);
        // 设置靠左的图标（左、上、右、下）
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(context, R.drawable.priority_high_24px),
                null,
                null,
                null);
        // 设置图标与文本之间的间距（单位：像素）
        int paddingPixels = context.getResources().getDimensionPixelSize(R.dimen.icon_text_padding);
        editText.setCompoundDrawablePadding(paddingPixels);
    }

    public static <T extends EditText> void InputNotEmpty(Context context, T editText) {
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
        editText.setCompoundDrawablePadding(0);
    }

    private static <T extends View> void animTranslationX(T view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", -30f, 30f, 0);
        animatorX.setDuration(100); // 设置动画持续时间为100毫秒
        animatorX.setInterpolator(new AccelerateDecelerateInterpolator()); // 设置插值器，使动画先加速后减速
        animatorX.setRepeatCount(2); // 设置重复次数为无限次
        animatorX.start(); // 启动动画
    }
}
