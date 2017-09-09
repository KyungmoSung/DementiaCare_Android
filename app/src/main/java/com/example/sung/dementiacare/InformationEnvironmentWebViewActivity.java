package com.example.sung.dementiacare;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 9..
 */

public class InformationEnvironmentWebViewActivity extends AppCompatActivity {
    final int INFORMATION_ENV_RESOURCE_ID = R.array.arrays_environment;

    public static String[][] envArray;

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int subIndex = intent.getIntExtra("sub_index", 0);
        envArray = getArrayFromResource(INFORMATION_ENV_RESOURCE_ID);

        String title = envArray[subIndex][0];
        String url = envArray[subIndex][1];

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
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
