package com.example.sung.dementiacare.information.Video;

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

public class VideoActivity extends AppCompatActivity {
    final int ARRAY_RESOURCE_ID = R.array.list_info_title_media;
    final String SOURCE = "출처 : 서울특별시광역치매센터, 중앙치매센터";

    String[] menuList;
    String title;

    @BindView(R.id.list_info_title)
    ListView listView;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.tv_source)
    TextView tv_source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
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
            tv_source.setVisibility(View.VISIBLE);
            tv_source.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorInformation));
            tv_source.setTextColor(Color.WHITE);
            tv_source.setText(SOURCE);
        }

        menuList = getResources().getStringArray(ARRAY_RESOURCE_ID);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_info, menuList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), VideoListActivity.class);
                intent.putExtra("sub_index", position);
                intent.putExtra("title", menuList[position]);
                startActivity(intent);
            }
        });
    }
}
