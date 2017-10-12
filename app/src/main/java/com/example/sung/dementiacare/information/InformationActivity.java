package com.example.sung.dementiacare.information;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class InformationActivity extends AppCompatActivity {
    final int arrayResourceId = R.array.list_info_title;
    String[] LIST_MENU;

    @BindView(R.id.list_info_title)
    ListView listView;

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

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorInformation));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("정보");


        LIST_MENU = res.getStringArray(arrayResourceId);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_info, LIST_MENU);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), InformationDementiaActivity.class);
                        intent.putExtra("title", LIST_MENU[position]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), InformationCareActivity.class);
                        intent.putExtra("title", LIST_MENU[position]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), InformationEnvironmentActivity.class);
                        intent.putExtra("title", LIST_MENU[position]);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), InformationVideoActivity.class);
                        intent.putExtra("title", LIST_MENU[position]);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
