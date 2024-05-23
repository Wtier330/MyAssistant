package com.example.myapplication.ui;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.myapplication.R;

import java.util.function.Function;

import lombok.Setter;

public class MyEditText extends AppCompatEditText {
    @Setter
    private Function<String, Boolean> validFun;

    public MyEditText(@NonNull Context context) {
        super(context);
        init();

    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 给 EditText 设置点击即可全选
        setOnClickListener((l) -> selectAll());
        addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void InputIsEmpty() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.priority_high_24px),
                null,
                null,
                null);
        // 设置图标与文本之间的间距（单位：像素）
        int paddingPixels = getResources().getDimensionPixelSize(R.dimen.icon_text_padding);
        setCompoundDrawablePadding(paddingPixels);
        animTranslationX(1);
    }

    public void InputNotEmpty() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
        setCompoundDrawablePadding(0);
    }

    public boolean isNull() {
        return getTextToStr().isEmpty();
    }

    public String getTextToStr() {
        return getText().toString().trim();
    }

    public boolean validate() {
        if (validFun == null)
            return false;
        return this.validFun.apply(getTextToStr());
    }

    public void animTranslationX(int count) {
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "translationX", -30f, 30f, 0);
        animatorX.setDuration(100); // 设置动画持续时间为100毫秒
        animatorX.setInterpolator(new AccelerateDecelerateInterpolator()); // 设置插值器，使动画先加速后减速
        animatorX.setRepeatCount(count); // 设置重复次数为无限次
        animatorX.start(); // 启动动画
    }
}

