package com.example.sung.dementiacare.photo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class PhotoDiaryDetailActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.iv_image)
    ImageView iv_image;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_contents)
    TextView tv_contents;

    @BindView(R.id.tv_date)
    TextView tv_date;

    DiaryDo diaryDo;
    DiaryDao diaryDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_diary_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
//        Uri imageUri = Uri.parse(intent.getStringExtra("image"));
//        String title = intent.getStringExtra("title");
//        String contents = intent.getStringExtra("contents");
//        String date = intent.getStringExtra("date");
        diaryDo = intent.getParcelableExtra("diary");

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
        toolbar_title.setTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Glide.with(this)
                .load(diaryDo.getImageUri())
                .into(iv_image);
        if (diaryDo.getTitle().length() > 0) {
            tv_title.setText(diaryDo.getTitle());
            toolbar_title.setText(diaryDo.getTitle());
        } else {
            tv_title.setText("제목없음");
            toolbar_title.setText("사진");
        }
        if (diaryDo.getContents().length() > 0) {
            tv_contents.setText(diaryDo.getContents());
        } else {
            tv_contents.setText("내용없음");
        }
        tv_date.setText(diaryDo.getDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_modify:
                Toast.makeText(this, "action_modify",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("삭제");
                builder.setMessage("사진을 삭제 하시겠습니까?");
                builder.setPositiveButton("네",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();
                break;
        }
        return true;
    }
}
