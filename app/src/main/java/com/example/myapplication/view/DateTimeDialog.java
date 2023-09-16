package com.example.myapplication.view;

import android.content.Context;
import android.icu.util.Calendar;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;

import java.text.DateFormat;
import java.util.Date;

public class DateTimeDialog extends CustomBaseDialog {
        public DateTimeDialog(Context context) {
            super(context);
        }

        @Override
        int getDialogLocation() {
            return Gravity.BOTTOM;
        }

        @Override
        int getLayoutId() {
            return R.layout.view_dialog_date;
        }


        private TextView cancel;
        private TextView done;
        private TextView text;
        private DatePicker picker;
        private TimePicker timePicker;
        private long currentTime;
        private Calendar calendar = Calendar.getInstance();

        void initData() {
            if(calendar==null){
                calendar = Calendar.getInstance();
            }
            currentTime = this.calendar.getTimeInMillis();
            cancel = findViewById(R.id.tv_data_dialog_cancel);
            done = findViewById(R.id.tv_data_dialog_done);
            picker = findViewById(R.id.data_picker);
            text = findViewById(R.id.date_text);
            timePicker = findViewById(R.id.time_picker);
            cancel.setOnClickListener(v -> {
                if (mCallback !=null)
                    mCallback.onDateChanged(currentTime);
                dismiss();
            });
            done.setOnClickListener(v -> {
                if (mCallback !=null)
                    mCallback.onDateChanged(currentTime);
                dismiss();
            });
//        picker ?.minDate = calMin.timeInMillis
//        picker ?.maxDate = calMax.timeInMillis

            picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    currentTime = calendar.getTimeInMillis();
                    if (mCallback!=null)
                        mCallback.onDateChanged(currentTime);
                    DateFormat medium = DateFormat.getDateTimeInstance(
                            DateFormat.MEDIUM,
                            DateFormat.SHORT
                    );
                    text.setText(medium.format(new Date(currentTime)));
                }
            });
            timePicker.setIs24HourView(true);
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
            timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                currentTime = calendar.getTimeInMillis();
                if (mCallback!=null)
                    mCallback.onDateChanged(currentTime);
                DateFormat medium = DateFormat.getDateTimeInstance(
                        DateFormat.MEDIUM,
                        DateFormat.SHORT
                );
                text.setText(medium.format(new Date(currentTime)));
            });
        }

        public void dismiss() {
            super.dismiss();
        }

        DialogCallback mCallback;

        void setCallback(DialogCallback callback) {
            mCallback = callback;
        }

        void setTime(Calendar calendar) {
            this.calendar = calendar;
            currentTime = this.calendar.getTimeInMillis();
        }

        abstract class DialogCallback {
            abstract void onDateChanged(long time);
        }
}
