package cn.example.i18ndemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applanga.android.Applanga;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomTitleView extends FrameLayout {

    @BindView(R.id.ibtn_left)
    ImageButton _ibtn_left;
    @BindView(R.id.btn_left)
    Button _btn_left;
    @BindView(R.id.tv_title)
    TextView _tv_title;
    @BindView(R.id.btn_right)
    Button _btn_right;
    @BindView(R.id.ibtn_right)
    ImageButton _ibtn_right;
    @BindView(R.id.rl_parent)
    RelativeLayout _rl_parent;

    public CustomTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_title, this);
        ButterKnife.bind(view);
        intView(context, attrs);

        Applanga.localizeContentView(this);
    }

    private void intView(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        int color_res_id_parent = ta.getColor(R.styleable.CustomTitleView_color_res_id_parent, -1);
        int img_res_id_ibtn_left = ta.getResourceId(R.styleable.CustomTitleView_img_res_id_ibtn_left, -1);
        String txt_btn_left = ta.getString(R.styleable.CustomTitleView_txt_btn_left);
        String txt_tv_title = ta.getString(R.styleable.CustomTitleView_txt_tv_title);
        String txt_btn_right = ta.getString(R.styleable.CustomTitleView_txt_btn_right);
        int img_res_id_ibtn_right = ta.getResourceId(R.styleable.CustomTitleView_img_res_id_ibtn_right, -1);
        ta.recycle();

        setBackground(color_res_id_parent);
        setTitle(txt_tv_title);
        setLeftImgBtnRes(img_res_id_ibtn_left);
        setRightImgBtnRes(img_res_id_ibtn_right);
        setLeftBtnTXT(txt_btn_left);
        setRightBtnTXT(txt_btn_right);
    }

    public CustomTitleView setBackground(@ColorInt int color) {
        if (color != -1) {
            _rl_parent.setBackgroundColor(color);
        }
        return this;
    }

    public CustomTitleView setTitle(@StringRes int res_id) {
        return setTitle(getContext().getString(res_id));
    }

    public CustomTitleView setTitle(String txt) {
        if (TextUtils.isEmpty(txt)) {
            _tv_title.setVisibility(GONE);
        } else {
            _tv_title.setVisibility(VISIBLE);
            _tv_title.setText(txt);
        }
        return this;
    }

    public CustomTitleView setLeftImgBtnRes(int res_id) {
        if (res_id == -1) {
            _ibtn_left.setVisibility(GONE);
        } else {
            _ibtn_left.setVisibility(VISIBLE);
            _ibtn_left.setImageResource(res_id);
        }


        return this;
    }

    public CustomTitleView setRightImgBtnRes(int res_id) {
        if (res_id == -1) {
            _ibtn_right.setVisibility(GONE);
        } else {
            _ibtn_right.setVisibility(VISIBLE);
            _ibtn_right.setImageResource(res_id);
        }


        return this;
    }

    public CustomTitleView setLeftBtnTXT(@StringRes int res_id) {
        return setLeftBtnTXT(getContext().getString(res_id));
    }

    public CustomTitleView setLeftBtnTXT(String txt) {
        if (TextUtils.isEmpty(txt)) {
            _btn_left.setVisibility(GONE);
        } else {
            _btn_left.setVisibility(VISIBLE);
            _btn_left.setText(txt);
        }
        return this;
    }


    public CustomTitleView setRightBtnTXT(@StringRes int res_id) {
        return setRightBtnTXT(getContext().getString(res_id));
    }

    public CustomTitleView setRightBtnTXT(String txt) {
        if (TextUtils.isEmpty(txt)) {
            _btn_right.setVisibility(GONE);
        } else {
            _btn_right.setVisibility(VISIBLE);
            _btn_right.setActivated(true);
            _btn_right.setText(txt);
        }
        return this;
    }

    private void doEffect(ImageView imgView, @IdRes int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        if (bitmap == null || (imgView == null)) {
            return;
        }

        Canvas canvas;
        Paint paint;

        Bitmap pressedBitmap = bitmap.copy(bitmap.getConfig(), true);
        canvas = new Canvas(pressedBitmap);
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(90);
        canvas.drawRect(0, 0, pressedBitmap.getWidth(), pressedBitmap.getHeight(), paint);

        imgView.setImageDrawable(createStateListDrawable(
                new BitmapDrawable(getResources(), pressedBitmap),
                new BitmapDrawable(getResources(), bitmap)
                )
        );
    }

    private static StateListDrawable createStateListDrawable(Drawable pressDrawable, Drawable normalDrawable) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
        sld.addState(new int[]{}, normalDrawable);

        return sld;
    }


}
