package com.example.myapplication.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class MyEditText extends AppCompatEditText {
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
        addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 文本变化之前的操作
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 文本变化时的操作

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文本变化之后的操作
                String inputText = s.toString();
                if (inputText.isEmpty()) {
                    // 输入内容为空时的处理
                    setError("内容不能为空" );
                } else {
                    // 输入内容不为空时的处理
                    setError(null); // 清除错误提示
                }
            }
        });
    }
}
