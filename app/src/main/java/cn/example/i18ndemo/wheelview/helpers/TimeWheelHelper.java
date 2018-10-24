package cn.example.i18ndemo.wheelview.helpers;

import android.content.Context;

import java.util.Calendar;
import java.util.Locale;

import cn.example.i18ndemo.R;
import cn.example.i18ndemo.wheelview.WheelView;
import cn.example.i18ndemo.wheelview.adapters.NumberWheelAdapter;

public class TimeWheelHelper {

    private static final String TIME_FORMAT = "%1$02d:%2$02d";

    private Context context;
    private WheelView wv_hour;
    private WheelView wv_min;

    public TimeWheelHelper(Context context, WheelView wv_hour, WheelView wv_min) {
        this.context = context;
        this.wv_hour = wv_hour;
        this.wv_min = wv_min;
        init();
    }

    private void init() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int textSize = context.getResources().getDisplayMetrics().widthPixels / 16;

        wv_hour.TEXT_SIZE = textSize;
        wv_hour.setAdapter(new NumberWheelAdapter(0, 23));
        wv_hour.setCyclic(false);
        wv_hour.setCurrentItem(hour);
        wv_hour.setLabel(context.getString(R.string.hour_label));

        wv_min.TEXT_SIZE = textSize;
        wv_min.setAdapter(new NumberWheelAdapter(0, 59));
        wv_min.setCyclic(false);
        wv_min.setCurrentItem(min);
        wv_min.setLabel(context.getString(R.string.min_label));
    }

    public String getDateString() {
        return String.format(Locale.getDefault(), TIME_FORMAT,
                wv_hour.getCurrentItem(), wv_min.getCurrentItem());
    }

    public int[] getDateArray() {
        return new int[]{wv_hour.getCurrentItem(), wv_min.getCurrentItem(), 0};
    }
}