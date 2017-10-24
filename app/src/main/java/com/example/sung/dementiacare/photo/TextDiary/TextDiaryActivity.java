package com.example.sung.dementiacare.photo.TextDiary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class TextDiaryActivity extends AppCompatActivity {

    ArrayList<TextDiaryDo> diary;
    TextDiaryAdapter adapter;
    TextDiaryDao textDiaryDao;
    public TourGuide mTutorialHandler;

    @BindView(R.id.tool_bar_with_plus)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.add_btn)
    ImageButton add_btn;

    @BindView(R.id.lv_text_diary)
    ListView listView;
    @BindView(R.id.layout_empty)
    LinearLayout layout_empty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_diary);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPhoto));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("일기");

        textDiaryDao = new TextDiaryDao(getApplicationContext(), null);
        diary = textDiaryDao.getResults();

        adapter = new TextDiaryAdapter(getApplicationContext(), diary);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), TextDiaryDetailActivity.class);
                intent.putExtra("diary", diary.get(i));
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.add_btn)
    public void add() {
        Intent intent = new Intent(getApplicationContext(), TextDiaryEditActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        diary = textDiaryDao.getResults();
        adapter.swapItems(diary);
        Log.e("============", diary.size() + "");

        if (diary.size() > 0) {
            layout_empty.setVisibility(View.GONE);
            if(mTutorialHandler != null){
                mTutorialHandler.cleanUp();
            }
        } else {
            layout_empty.setVisibility(View.VISIBLE);

            ToolTip toolTip = new ToolTip()
                    .setTitle("일기 추가")
                    .setDescription("버튼을 눌러 새로운 일기를 추가해보세요!")
                    .setGravity(Gravity.LEFT | Gravity.BOTTOM);

            mTutorialHandler = TourGuide.init(this).with(TourGuide.Technique.Click)
                    .motionType(TourGuide.MotionType.ClickOnly)
                    .setPointer(new Pointer())
                    .setToolTip(toolTip)
                    .setOverlay(new Overlay())
                    .playOn(add_btn);
        }
    }

}
