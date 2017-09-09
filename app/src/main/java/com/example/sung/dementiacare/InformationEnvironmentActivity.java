package com.example.sung.dementiacare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class InformationEnvironmentActivity extends AppCompatActivity {
    final int ARRAY_RESOURCE_ID = R.array.list_info_title_environment;

    static String[] menuList;

    @BindView(R.id.list_info_title)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_information);
        ButterKnife.bind(this);

        menuList = getResources().getStringArray(ARRAY_RESOURCE_ID);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listview_item_layout, menuList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), InformationEnvironmentWebViewActivity.class);
                intent.putExtra("sub_index", position);
                startActivity(intent);
            }
        });

    }

}
