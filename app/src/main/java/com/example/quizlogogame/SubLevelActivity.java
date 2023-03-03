package com.example.quizlogogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class SubLevelActivity extends AppCompatActivity {

    RecyclerView logoRecycler;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);

        pos = getIntent().getIntExtra("pos", 0);

        logoRecycler = findViewById(R.id.logoRecycler);
        logoRecycler.setLayoutManager(new GridLayoutManager(SubLevelActivity.this, 3));

        findViewById(R.id.backBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}