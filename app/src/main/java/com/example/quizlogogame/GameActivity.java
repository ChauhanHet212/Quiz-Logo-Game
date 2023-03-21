package com.example.quizlogogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizlogogame.Adapters.ViewPagerAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ViewPager2 viewPager;

    int pos1, pos2;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viewPager = findViewById(R.id.viewPager);

        pos1 = getIntent().getIntExtra("pos1", 0);
        pos2 = getIntent().getIntExtra("pos2", 0);

        findViewById(R.id.backBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new ViewPagerAdapter(GameActivity.this, pos1, pos2, viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pos2);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                ((TextView) findViewById(R.id.logo_count)).setText("logo " + (position + 1) + "/" + AllQuiz.ALL_QUIZ.get(pos1).size());
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}
