package com.example.sung.dementiacare.photo.PhotoDiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sung.dementiacare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class PhotoDiaryActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    ArrayList<PhotoDiaryDo> diary;
    PhotoDiaryAdapter adapter;
    PhotoDiaryDao photoDiaryDao;

    @BindView(R.id.tool_bar_with_plus)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.photo_gridview)
    GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_diary);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPhoto));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("사진");

        photoDiaryDao = new PhotoDiaryDao(getApplicationContext(), null);
        diary = photoDiaryDao.getResults();

        adapter = new PhotoDiaryAdapter(getApplicationContext(), diary);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), PhotoDiaryDetailActivity.class);
                intent.putExtra("diary", diary.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        diary = photoDiaryDao.getResults();
        adapter.swapItems(diary);
    }

    @OnClick(R.id.add_btn)
    public void addPhoto() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                showPicker();
            } else {
                requestPermission(); // Code for permission
            }
        } else {
            showPicker();
        }
    }

    public void showPicker() {
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(this)
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(final Uri uri) {
                        Intent intent = new Intent(getApplicationContext(), PhotoDiaryEditActivity.class);
                        intent.putExtra("imageUri", uri.toString());
                        startActivity(intent);
                    }
                })
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(PhotoDiaryActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(PhotoDiaryActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(PhotoDiaryActivity.this, "외부 저장소 쓰기 권한을 허용해야 사진을 저장할 수 있습니다. 앱 설정에서이 권한을 허용하십시오.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(PhotoDiaryActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
}
