package com.example.quizlogogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.quizlogogame.Adapters.LevelAdapter;

public class LevelActivity extends AppCompatActivity {

    RecyclerView levelRecycler;
    LevelAdapter levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        levelRecycler = findViewById(R.id.levelRecycler);
        levelRecycler.setLayoutManager(new LinearLayoutManager(LevelActivity.this));
        levelAdapter = new LevelAdapter(LevelActivity.this, AllQuiz.ALL_QUIZ);
        levelRecycler.setAdapter(levelAdapter);

        findViewById(R.id.backBtn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        levelAdapter.notifyDataSetChanged();
    }
}