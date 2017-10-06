package com.example.sung.dementiacare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class SubMenuSupportActivity extends AppCompatActivity {


    Intent i ;
    Uri u;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_support);


        ButterKnife.bind(this);

        i = new Intent(Intent.ACTION_VIEW);
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        TextView tv3 = (TextView)findViewById(R.id.textView3);
        TextView tv4 = (TextView)findViewById(R.id.textView4);

        Log.e("park", "onCreate: ");

        tv1.setText("중앙치매센터");
        tv2.setText("치매지원기관 치매상담콜센터");
        tv3.setText("치매 파트너");
        tv4.setText("가족모임");


    }


    @OnClick(R.id.textView1)
    void onClickLayer1(){

        u = Uri.parse("https://www.nid.or.kr");
        i.setData(u);
        startActivity(i);
    }
    @OnClick(R.id.textView2)
    void onClickLayer2(){
        u = Uri.parse("https://www.nid.or.kr/support/callcenter.aspx");
        i.setData(u);
        startActivity(i);


    }
    @OnClick(R.id.textView3)
    void onClickLayer3(){
        u = Uri.parse("https://partner.nid.or.kr/main/main.aspx");
        i.setData(u);
        startActivity(i);

    }
    @OnClick(R.id.textView4)
    void onClickLayer4(){
        u = Uri.parse("https://www.nid.or.kr/support/jajo_group.aspx");
        i.setData(u);
        startActivity(i);


    }
}
