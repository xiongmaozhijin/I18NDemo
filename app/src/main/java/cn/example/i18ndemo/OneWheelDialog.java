package cn.example.i18ndemo;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.example.i18ndemo.wheelview.WheelView;
import cn.example.i18ndemo.wheelview.helpers.OneWheelHelper;

public class OneWheelDialog extends Dialog {

    public interface OnConfirmDataListener {
        void onConfirm(String data);
    }

    @BindView(R.id.wv)
    WheelView _wv;
    @BindView(R.id.btn_close)
    Button _btn_close;
    @BindView(R.id.tv_title)
    TextView _tv_title;

    private OneWheelHelper _helper;
    private OnConfirmDataListener _listener;
    private String[] _contents;
    private int _max_length;
    private String _label;

    public OneWheelDialog(Context context, String[] contents, String label, OnConfirmDataListener listener) {
        this(context, contents, -1, label, listener);
    }

    public OneWheelDialog(Context context, String[] contents, int max_length, String label, OnConfirmDataListener listener) {
        super(context, R.style.DefaultDialog_BottomInBottomOut);
        setContentView(R.layout.dialog_one_wheel);
        ButterKnife.bind(this);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        this._contents = contents;
        this._max_length = max_length;
        this._label = label;
        this._listener = listener;
        _helper = new OneWheelHelper(getContext(), _wv, _contents, _max_length, _label);
    }

    public void setLeftButtonText(int resId) {
        _btn_close.setText(resId);
    }

    public void setTitleText(int resId) {
        _tv_title.setText(resId);
        _tv_title.setVisibility(View.VISIBLE);
    }

    public void setCurrentItem(int pos) {
        _helper.setCurrentItem(pos);
    }

    @OnClick({R.id.btn_close, R.id.btn_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.btn_complete:
                dismiss();
                if (_listener != null) {
                    _listener.onConfirm(_helper.getContent());
                }
                break;
        }
    }
}
