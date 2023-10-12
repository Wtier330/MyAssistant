package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class ViewUtil {


    public static int dp2px(Context context, float dp)
    {
        return (int ) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE );
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics( outMetrics);
        return outMetrics .widthPixels ;
    }
    public static String getComplementaryColor(String hexColor) {
        // 解析输入的十六进制颜色值
        int inputColor = Color.parseColor(hexColor);

        // 计算互补色
        int red = 255 - Color.red(inputColor);
        int green = 255 - Color.green(inputColor);
        int blue = 255 - Color.blue(inputColor);

        // 将互补色转换为十六进制格式
        String complementaryHex = String.format("#%02X%02X%02X", red, green, blue);

        return complementaryHex;
    }
}