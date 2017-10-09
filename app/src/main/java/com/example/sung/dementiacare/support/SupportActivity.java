package com.example.sung.dementiacare.support;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class SupportActivity extends AppCompatActivity {

    final int SUPPORT_RESOURCE_ID = R.array.arrays_support;

    static String[][] menuList;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.list_support)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_support);
        ButterKnife.bind(this);

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorSupport));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("지원");

        menuList = getArrayFromResource(SUPPORT_RESOURCE_ID);

        SupportListAdapter adapter = new SupportListAdapter(menuList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(menuList[position][1]);
                intent.setData(uri);
                startActivity(intent);
            }
        });

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

