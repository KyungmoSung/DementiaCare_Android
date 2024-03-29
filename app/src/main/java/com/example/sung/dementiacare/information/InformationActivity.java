package com.example.sung.dementiacare.information;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.information.Environment.EnvironmentActivity;
import com.example.sung.dementiacare.information.Video.VideoActivity;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sung.dementiacare.MainActivity.getArrayFromResource;
import static com.example.sung.dementiacare.MainActivity.getResId;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class InformationActivity extends AppCompatActivity {

    final int INFO_RESOURCE_ID = R.array.list_info_menu;
    String[][] menuList;
    Intent intent;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.iv_menu_1)
    ImageView iv_menu_1;
    @BindView(R.id.iv_menu_2)
    ImageView iv_menu_2;
    @BindView(R.id.iv_menu_3)
    ImageView iv_menu_3;
    @BindView(R.id.iv_menu_4)
    ImageView iv_menu_4;

    @BindView(R.id.tv_menu_1)
    TextView tv_menu_1;
    @BindView(R.id.tv_menu_2)
    TextView tv_menu_2;
    @BindView(R.id.tv_menu_3)
    TextView tv_menu_3;
    @BindView(R.id.tv_menu_4)
    TextView tv_menu_4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorInformation));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorInformation));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("정보");

        menuList = getArrayFromResource(this, INFO_RESOURCE_ID);

        ImageView[] imageViews = {iv_menu_1, iv_menu_2, iv_menu_3, iv_menu_4};
        TextView[] textViews = {tv_menu_1, tv_menu_2, tv_menu_3, tv_menu_4};

        for (int i = 0; i < menuList.length; i++) {
            textViews[i].setText(menuList[i][0]);
            Glide.with(this).load(getResId(menuList[i][1], R.drawable.class)).into(imageViews[i]);
        }

    }

    @OnClick(R.id.card_menu_1)
    public void showDementia() {
        intent = new Intent(this, InformationDementiaActivity.class);
        intent.putExtra("title", menuList[0][0]);
        startActivity(intent);
    }

    @OnClick(R.id.card_menu_2)
    public void showCare() {
        intent = new Intent(this, InformationCareActivity.class);
        intent.putExtra("title", menuList[1][0]);
        startActivity(intent);
    }

    @OnClick(R.id.card_menu_3)
    public void showEnv() {
        intent = new Intent(this, EnvironmentActivity.class);
        intent.putExtra("title", menuList[2][0]);
        startActivity(intent);
    }

    @OnClick(R.id.card_menu_4)
    public void showVideo() {
        intent = new Intent(this, VideoActivity.class);
        intent.putExtra("title", menuList[3][0]);
        startActivity(intent);
    }
}
