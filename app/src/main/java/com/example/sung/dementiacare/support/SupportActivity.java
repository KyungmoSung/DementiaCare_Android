package com.example.sung.dementiacare.support;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class SupportActivity extends AppCompatActivity {

    final int SUPPORT_RESOURCE_ID = R.array.arrays_support;
    Intent intent = new Intent(Intent.ACTION_VIEW);

    static String[][] menuList;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

//    @BindView(R.id.list_support)
//    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_support);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorSupport));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSupport));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("지원");

        menuList = getArrayFromResource(SUPPORT_RESOURCE_ID);

    }

    @OnClick(R.id.card_center)
    public void showCenter() {
        Uri uri = Uri.parse(menuList[0][1]);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.card_call)
    public void showCall() {
        Uri uri = Uri.parse(menuList[1][1]);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.card_family)
    public void showFamily() {
        Uri uri = Uri.parse(menuList[2][1]);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.card_partner)
    public void showPartner() {
        Uri uri = Uri.parse(menuList[3][1]);
        intent.setData(uri);
        startActivity(intent);
    }

    public String[][] getArrayFromResource(int resourceId) {
        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(resourceId);

        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                array[i] = res.getStringArray(id);
                Log.e("array", array[i][0] + ", " + array[i][1]);
            } else {
                Log.e("getStringArray", "Not found");
            }
        }
        ta.recycle();
        return array;
    }
}

