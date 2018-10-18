package cn.example.i18ndemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.applanga.android.Applanga;

public class MainActivity extends AppCompatActivity {

    TextView tv_msg;
    TextView tv_html;
    TextView tv_string_from_string;
    TextView tv_string_from_string_another_file;
    TextView tv_empty_str;
    TextView tv_empty_str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_msg = findViewById(R.id.tv_msg);
        tv_html = findViewById(R.id.tv_html);
        tv_string_from_string = findViewById(R.id.tv_string_from_string);
        tv_string_from_string_another_file = findViewById(R.id.tv_string_from_string_another_file);
        tv_empty_str = findViewById(R.id.tv_empty_str);
        tv_empty_str2 = findViewById(R.id.tv_empty_str2);

        String tip = getString(R.string.hello_tip);
        tv_msg.setText(tip);


        String html = getString(R.string.html);
        tv_html.setText(Html.fromHtml(html));

        String tipString = getString(R.string.string_from_string);
        tv_string_from_string.setText(tipString);

        String tiptv_string_from_string_another_file = getString(R.string.tv_string_from_string_another_file);
        tv_string_from_string_another_file.setText(tiptv_string_from_string_another_file);

        String tipEmpty = getString(R.string.str_empty);
        tv_empty_str.setText(tipEmpty);

        String tipEmpty2 = getString(R.string.empty_2_str);
        tv_empty_str2.setText(tipEmpty2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        Applanga.localizeContentView(this);
    }


    public void onBtnChange(View view) {
    }

}
