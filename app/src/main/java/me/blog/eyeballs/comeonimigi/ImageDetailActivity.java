package me.blog.eyeballs.comeonimigi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.blog.eyeballs.comeonimigi.image_detail_pager.ImageViewpageAdapter;

public class ImageDetailActivity extends AppCompatActivity {

    private ViewPager imageViewPager;
    private ImageViewpageAdapter imageViewpageAdapter;
    private ViewPager image_detail_viewpager;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        init();
    }

    private void init() {
        image_detail_viewpager = (ViewPager)findViewById(R.id.image_detail_viewpager);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

    }
}
