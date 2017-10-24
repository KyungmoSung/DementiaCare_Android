package com.example.sung.dementiacare.information.Environment;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 9..
 */

public class EnvironmentWebViewActivity extends AppCompatActivity {

    EnvironmentDo env;

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorInformation));
        }

        Intent intent = getIntent();
        env = intent.getParcelableExtra("env");

        String url = env.getWebUri();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }
}
