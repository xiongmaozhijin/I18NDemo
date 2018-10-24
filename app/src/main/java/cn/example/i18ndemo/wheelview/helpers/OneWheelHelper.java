package cn.example.i18ndemo.wheelview.helpers;

import android.content.Context;
import android.text.TextUtils;

import cn.example.i18ndemo.R;
import cn.example.i18ndemo.wheelview.WheelView;
import cn.example.i18ndemo.wheelview.adapters.ArrayWheelAdapter;

/**
 * 单滚轮帮助类
 * <p>
 * Author : ZhangYi on 2015/11/17 19:09
 * <br/>E-mail : zhangyi_0820@163.com
 */
public class OneWheelHelper {
    private Context context;
    private WheelView wheelView;
    private String[] contents;
    private int maxLength;
    private String label; // 标签(单位)

    public OneWheelHelper(Context context, WheelView wheelView) {
        this(context, wheelView, null, null);
    }

    public OneWheelHelper(Context context, WheelView wheelView, String[] contents) {
        this(context, wheelView, contents, null);
    }

    public OneWheelHelper(Context context, WheelView wheelView, String[] contents, String label) {
        this(context, wheelView, contents, -1, label);
    }

    public OneWheelHelper(Context context, WheelView wheelView, String[] contents, int maxLength, String label) {
        this.context = context;
        this.wheelView = wheelView;
        this.contents = contents;
        this.maxLength = maxLength;
        this.label = label;
        init();
    }

    private void init() {
//        wheelView.TEXT_SIZE = context.getResources().getDisplayMetrics().widthPixels / 16;
        wheelView.TEXT_SIZE = (int) context.getResources().getDimension(R.dimen.wheel_txv_size);

        if (contents != null && contents.length > 0) {
            int max_length = maxLength < 0 ? 4 : maxLength;
            wheelView.setAdapter(new ArrayWheelAdapter<>(contents, max_length));// 设置显示数据
        }
        if (!TextUtils.isEmpty(label)) {
            wheelView.setLabel(label);// 添加标签(单位)
        }
        wheelView.setCyclic(false);// 不循环滚动
        wheelView.setCurrentItem(0);// 初始化时显示的数据
    }

    /**
     * 设置显示数据
     */
    public void setContents(String[] contents) {
        this.contents = contents;
        init();
    }

    /**
     * @return 选中值
     */
    public String getContent() {
        return contents[wheelView.getCurrentItem()];
    }

    /**
     * 设置当前选中位置
     */
    public void setCurrentItem(int current) {
        wheelView.setCurrentItem(current);// 初始化时显示的数据
    }
}
