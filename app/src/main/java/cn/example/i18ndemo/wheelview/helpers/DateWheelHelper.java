package cn.example.i18ndemo.wheelview.helpers;

import android.content.Context;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.example.i18ndemo.R;
import cn.example.i18ndemo.wheelview.OnWheelChangedListener;
import cn.example.i18ndemo.wheelview.WheelView;
import cn.example.i18ndemo.wheelview.adapters.NumberWheelAdapter;

/**
 * 日期滚轮帮助类
 * <p/>
 * Author : ZhangYi on 2015/10/29 18:07
 * <br/>E-mail : zhangyi_0820@163.com
 */
public class DateWheelHelper {

    private static final String DATE_FORMAT = "%1$04d-%2$02d-%3$02d";
    private static final String[] MONTHS_BIG = {"1", "3", "5", "7", "8", "10", "12"};
    private static final String[] MONTHS_LITTLE = {"4", "6", "9", "11"};

    private Context context;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private int startYear = Calendar.getInstance().get(Calendar.YEAR) - 100;
    private int endYear = Calendar.getInstance().get(Calendar.YEAR);

    private List<String> list_big;
    private List<String> list_little;
    // 添加"年"监听
    private OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            int selected_year = newValue + startYear;
            final int month = wv_month.getCurrentItem() + 1;
            if (month == 2) {
                if ((selected_year % 4 == 0 && selected_year % 100 != 0) || selected_year % 400 == 0) { //闰年
                    if (wv_day.getCurrentItem() > 28) {
                        wv_day.setCurrentItem(28, true);
                    }
                    wv_day.setAdapter(new NumberWheelAdapter(1, 29));
                } else {
                    if (wv_day.getCurrentItem() > 27) {
                        wv_day.setCurrentItem(27, true);
                    }
                    wv_day.setAdapter(new NumberWheelAdapter(1, 28));
                }
            }
        }
    };

    // 添加"月"监听
    private OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            int month_num = newValue + 1;
            if (list_big.contains(String.valueOf(month_num))) { // 31天的月
                wv_day.setAdapter(new NumberWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month_num))) { // 30天的月
                if (wv_day.getCurrentItem() == 30) {
                    wv_day.setCurrentItem(29, true);
                }
                wv_day.setAdapter(new NumberWheelAdapter(1, 30));
            } else { // 2月
                int selected_year = wv_year.getCurrentItem() + startYear;
                if ((selected_year % 4 == 0 && selected_year % 100 != 0) || selected_year % 400 == 0) { // 闰年
                    if (wv_day.getCurrentItem() > 28) {
                        wv_day.setCurrentItem(28, true);
                    }
                    wv_day.setAdapter(new NumberWheelAdapter(1, 29));
                } else {
                    if (wv_day.getCurrentItem() > 27) {
                        wv_day.setCurrentItem(27, true);
                    }
                    wv_day.setAdapter(new NumberWheelAdapter(1, 28));
                }
            }
        }
    };

    public DateWheelHelper(Context context, WheelView wv_year, WheelView wv_month, WheelView wv_day, Calendar calendar) {
        this.context = context;
        this.wv_year = wv_year;
        this.wv_month = wv_month;
        this.wv_day = wv_day;
        list_big = Arrays.asList(MONTHS_BIG);
        list_little = Arrays.asList(MONTHS_LITTLE);
        init(calendar);
    }

    private void init(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

//        int textSize = context.getResources().getDisplayMetrics().widthPixels / 16;
        int textSize = (int) context.getResources().getDimension(R.dimen.wheel_txv_size);

        // 年
        wv_year.TEXT_SIZE = textSize;
        wv_year.setAdapter(new NumberWheelAdapter(startYear, endYear));// 设置"年"的显示数据
        wv_year.addChangingListener(wheelListener_year);
        wv_year.setCyclic(false);// 不循环滚动
        wv_year.setCurrentItem((year - startYear) / 2);// 初始化时显示的数据
        wv_year.setLabel(context.getString(R.string.year_label));// 添加文字

        // 月
        wv_month.TEXT_SIZE = textSize;
        wv_month.setAdapter(new NumberWheelAdapter(1, 12));
        wv_month.addChangingListener(wheelListener_month);
        wv_month.setCyclic(false);
        wv_month.setCurrentItem(month);
        wv_month.setLabel(context.getString(R.string.month_label));

        // 日
        wv_day.TEXT_SIZE = textSize;
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumberWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumberWheelAdapter(1, 30));
        } else { // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                wv_day.setAdapter(new NumberWheelAdapter(1, 29));
            } else {
                wv_day.setAdapter(new NumberWheelAdapter(1, 28));
            }
        }
        wv_day.setCyclic(false);
        wv_day.setCurrentItem(day - 1);
        wv_day.setLabel(context.getString(R.string.day_label));
    }

    /**
     * @return 选中日期
     */
    public String getDate() {
        return String.format(Locale.getDefault(), DATE_FORMAT,
                wv_year.getCurrentItem() + startYear,
                wv_month.getCurrentItem() + 1,
                wv_day.getCurrentItem() + 1);
    }
}