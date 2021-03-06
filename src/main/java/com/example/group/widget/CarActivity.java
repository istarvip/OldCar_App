package com.example.group.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.adapter.MyPagerAdapter;
import com.example.group.util.DisplayUtil;

import java.util.ArrayList;

public class CarActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener,BannerPager.BannerClickListener {

    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private Button button1;
    private Button button2;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离
    private  int three=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button1.setOnClickListener(new MyOnClickListener());
        button2.setOnClickListener(new MyOnClickListener());
        initViews();
        initBanner();
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.button1) {
                Intent intent = new Intent(CarActivity.this, ReleaseActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(CarActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        }
    }

    private void initBanner() {
        BannerPager banner = (BannerPager) findViewById(R.id.banner_pager);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) banner.getLayoutParams();
        params.height = (int) (DisplayUtil.getSreenWidth(this) * 250f/ 640f);
        banner.setLayoutParams(params);

        ArrayList<Integer> bannerArray = new ArrayList<Integer>();
        bannerArray.add(Integer.valueOf(R.drawable.banner_1));
        bannerArray.add(Integer.valueOf(R.drawable.banner_2));
        bannerArray.add(Integer.valueOf(R.drawable.banner_3));
        bannerArray.add(Integer.valueOf(R.drawable.banner_4));
        bannerArray.add(Integer.valueOf(R.drawable.banner_5));
        banner.setImage(bannerArray);
        banner.setOnBannerListener(this);
        banner.start();
    }

    @Override
    public void onBannerClick(int position) {
        Intent intent=new Intent(this,AdvActivity.class);
        startActivity(intent);
    }


    private void initViews() {
        vpager_four = (ViewPager) findViewById(R.id.vpager_four);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);



        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.introduce, null, false));
        listViews.add(mInflater.inflate(R.layout.appearance, null, false));
        listViews.add(mInflater.inflate(R.layout.decoration, null, false));
        vpager_four.setAdapter(new MyPagerAdapter(listViews));
        vpager_four.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);

        vpager_four.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                break;
            case R.id.tv_three:
                vpager_four.setCurrentItem(2);
                break;

        }
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }


}