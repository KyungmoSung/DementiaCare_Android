package com.example.sung.dementiacare.information.Environment;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class EnvironmentActivity extends AppCompatActivity {
    final int ENV_RESOURCE_ID = R.array.arrays_environment;
    final String SOURCE = "출처 : 힐링환경간호연구소";

    ArrayList<EnvironmentDo> env;
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

        env = getArrayFromResource(ENV_RESOURCE_ID);

        final EnvironmentAdapter adapter = new EnvironmentAdapter(this,env);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EnvironmentWebViewActivity.class);
                intent.putExtra("env", env.get(position));
                startActivity(intent);
            }
        });
    }

    public ArrayList<EnvironmentDo> getArrayFromResource(int resourceId) {
        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(resourceId);

        int n = ta.length();
        ArrayList<EnvironmentDo> env = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                env.add(i, new EnvironmentDo(res.getStringArray(id)[0],res.getStringArray(id)[1],res.getStringArray(id)[2]));
            } else {
                Log.e("getStringArray", "Not found");
            }
        }
        ta.recycle();
        return env;
    }
}
