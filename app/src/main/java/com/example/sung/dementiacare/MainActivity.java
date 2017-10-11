package com.example.sung.dementiacare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sung.dementiacare.information.InformationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.layout_information)
    void openInformationActivity() {
        startActivity(new Intent(this, InformationActivity.class));
    }

    @OnClick(R.id.layout_support)
    void openSupportActivity() {

        startActivity(new Intent(this, SubMenuSupportActivity.class));
    }

    @OnClick(R.id.layout_notification)
    void openNotificationActivity() {
        startActivity(new Intent(this, SubMenuNotificationActivity.class));
    }

    @OnClick(R.id.layout_picture)
    void openPictureActivity() {
        startActivity(new Intent(this, SubMenuPictureActivity.class));
    }
}
