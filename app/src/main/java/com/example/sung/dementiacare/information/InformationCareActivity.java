package com.example.sung.dementiacare.information;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

public class InformationCareActivity extends AppCompatActivity {
    final int MENU_INDEX = 1;
    final int[] ARRAY_RESOURCE_ID = {R.array.list_info_title_care, R.array.sub_list_info_title_care_3};

    int mainIndex;
    String[] menuList;
    String title;

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
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorInformation));
        }

        Intent intent = getIntent();

        if (intent.hasExtra("title")) {
            title = intent.getStringExtra("title");
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorInformation));
            toolbar_title.setTextColor(Color.WHITE);
            toolbar_title.setText(title);
        }

        if (intent.hasExtra("main_index")) {
            mainIndex = intent.getIntExtra("main_index", 0);
            menuList = getResources().getStringArray(ARRAY_RESOURCE_ID[1]);

            final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_info, menuList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), PdfViewerActivity.class);
                    intent.putExtra("menu_index", MENU_INDEX);
                    intent.putExtra("main_index", mainIndex);
                    intent.putExtra("sub_index", position);
                    intent.putExtra("title", menuList[position]);
                    startActivity(intent);
                }
            });
        } else {
            menuList = getResources().getStringArray(ARRAY_RESOURCE_ID[0]);

            final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_info, menuList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = getIntent();
                    if (position == 2 && !intent.hasExtra("main_index")) {
                        intent = new Intent(InformationCareActivity.this, InformationCareActivity.class);
                        intent.putExtra("main_index", position);
                        intent.putExtra("title", menuList[position]);
                        startActivity(intent);
                    } else {
                        intent = new Intent(InformationCareActivity.this, PdfViewerActivity.class);
                        intent.putExtra("menu_index", MENU_INDEX);
                        intent.putExtra("main_index", position);
                        intent.putExtra("sub_index", 0);
                        intent.putExtra("title", menuList[position]);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
