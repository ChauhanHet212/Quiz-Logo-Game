package com.example.quizlogogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quizlogogame.Adapters.LevelAdapter;
import com.example.quizlogogame.Adapters.SubLevelAdapter;

public class SubLevelActivity extends AppCompatActivity {

    RecyclerView logoRecycler;
    SubLevelAdapter subLevelAdapter;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);

        pos = getIntent().getIntExtra("pos", 0);
        ((TextView)findViewById(R.id.sub_level)).setText("Level " + (pos + 1));

        logoRecycler = findViewById(R.id.logoRecycler);
        logoRecycler.setLayoutManager(new GridLayoutManager(SubLevelActivity.this, 3));
        subLevelAdapter = new SubLevelAdapter(SubLevelActivity.this, AllQuiz.ALL_QUIZ.get(pos), pos);
        logoRecycler.setAdapter(subLevelAdapter);

        findViewById(R.id.backBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        subLevelAdapter.notifyDataSetChanged();
    }
}