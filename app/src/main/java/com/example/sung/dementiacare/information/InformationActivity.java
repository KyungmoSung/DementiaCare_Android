package com.example.sung.dementiacare.information;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.information.Environment.EnvironmentActivity;
import com.example.sung.dementiacare.information.Video.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class InformationActivity extends AppCompatActivity {
    final int arrayResourceId = R.array.list_info_title;
    String[] LIST_MENU;
    Intent intent;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_information);
        Resources res = getResources();
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

        LIST_MENU = res.getStringArray(arrayResourceId);

    }

    @OnClick(R.id.card_dementia)
    public void showDementia() {
        intent = new Intent(this, InformationDementiaActivity.class);
        intent.putExtra("title", LIST_MENU[0]);
        startActivity(intent);
    }

    @OnClick(R.id.card_care)
    public void showCare() {
        intent = new Intent(this, InformationCareActivity.class);
        intent.putExtra("title", LIST_MENU[1]);
        startActivity(intent);
    }

    @OnClick(R.id.card_env)
    public void showEnv() {
        intent = new Intent(this, EnvironmentActivity.class);
        intent.putExtra("title", LIST_MENU[2]);
        startActivity(intent);
    }

    @OnClick(R.id.card_video)
    public void showVideo() {
        intent = new Intent(this, VideoActivity.class);
        intent.putExtra("title", LIST_MENU[3]);
        startActivity(intent);
    }
}
